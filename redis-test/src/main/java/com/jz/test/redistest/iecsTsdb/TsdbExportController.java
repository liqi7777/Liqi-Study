package com.jz.test.redistest.iecsTsdb;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baidubce.services.tsdb.model.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.headingdata.trans.JavaTrans;
import com.headingdata.trans.pojos.LngLatPoint;
import com.jz.test.redistest.domain.*;
import com.jz.test.redistest.test.Test;
import com.jz.test.redistest.util.UrlStringUtils;
import com.jz.test.redistest.util.ZhtMapUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


/**
 * @author liqi
 * create  2021-10-12 16:06
 */

@Slf4j
@RestController
@RequestMapping("/tsdb")
public class TsdbExportController {
    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private CommonDownLoadUtil commonDownLoadUtil;

    @Autowired
    // @Qualifier("redisTemplate01")
    private RedisTemplate<String, Object> redisTemplate01;
//    @Autowired
//    @Qualifier("redisTemplate02")
//    private RedisTemplate<String, Object> redisTemplate02;

    @GetMapping("/exportVehiclePoints")
    @Operation(summary = "导出实时计算推出的车辆定位数据")
    public void exportVehiclePoints(String vehicleNo, String strStartTime, String strEndTime) throws Throwable {
        long startTime = DateUtil.parseDateTime(strStartTime).getTime();
        long endTime = DateUtil.parseDateTime(strEndTime).getTime();
        String fileName = getAivPositionFromProd(vehicleNo, startTime, endTime);
        commonDownLoadUtil.downLoad(httpServletResponse, fileName);
//        FileUtil.del(fileName);
    }

    @GetMapping("/exportOriginVehiclePoints")
    @Operation(summary ="导出原始车辆定位数据")
    public void exportOriginVehiclePoints(@RequestParam(required = false) String vehicleNo, String strStartTime, String strEndTime) throws Throwable {
        long startTime = DateUtil.parseDateTime(strStartTime).getTime();
        long endTime = DateUtil.parseDateTime(strEndTime).getTime();
        String fileName = getOrignAivPositionFromProd(vehicleNo, startTime, endTime);
        commonDownLoadUtil.downLoad(httpServletResponse, fileName);
//        FileUtil.del(fileName);
    }

    @GetMapping("/statisticsRedisVehicles")
    @Operation(summary ="获取缓存中存在的所有车辆数据")
    public String statisticsVehicles() throws Exception {
        Set<Object> keys = redisTemplate01.opsForHash().keys(VehicleConstant.VEHICLE_REAL_TIME_DATA);
        List<Object> objects = redisTemplate01.opsForHash().multiGet(VehicleConstant.VEHICLE_REAL_TIME_DATA, keys);
        for (Object object : objects) {
            IecsAivVehicInfoDTO object1 = (IecsAivVehicInfoDTO) object;
            if (Math.abs(System.currentTimeMillis() - object1.getCurTime()) > 60000) {
                keys.remove(object1.getVehicleNo());
            }
        }
        return keys.stream().map(o -> (String) o).collect(Collectors.joining(","));
    }

    @GetMapping("/statisticsOriginVehicles")
    @Operation(summary ="获取原始定位上报的所有车辆数据")
    public String statisticsOriginVehicles() throws Exception {
        String machNos = statisticsOriginVehiclesTsdb();
        return machNos;
    }

    @GetMapping("/compareOriginRedisVehicles")
    @Operation(summary ="比较原始定位和缓存定位之间的差异车辆")
    public String compareOriginRedisVehicles() throws Exception {
        String redisVehicles = statisticsVehicles();
        String originVehicles = statisticsOriginVehicles();
        Set<String> redisList = Arrays.stream(redisVehicles.split(",")).collect(Collectors.toSet());
        Set<String> orignList = Arrays.stream(originVehicles.split(",")).collect(Collectors.toSet());
        Sets.SetView<String> difference = Sets.difference(orignList, redisList);
        return difference.stream().collect(Collectors.joining(","));
    }


    @GetMapping("/statisticsTestJSOriginVehicles")
    @Operation(summary ="获取原始定位测试车辆(JS开头)上报的所有车辆数据")
    public List<String> statisticsTestJSOriginVehicles() throws Exception {
        String machNos = statisticsOriginVehiclesTsdb();
        List<String> js = Arrays.stream(machNos.split(",")).filter(s -> s.startsWith("JS")).collect(Collectors.toList());
        return js;
    }

    @Operation(summary = "测试某个区域、某个时间点的车辆数据的性能")
    @PostMapping("/tsdbAreaVehicle")
    public ApiData<TsdbTestAreaVehicleResDTO> tsdbTestAreaVehicle(@RequestBody @Valid TsdbTestAreaVehicleReqDTO tsdbTestAreaVehicleReqDTO) throws Exception {
        return new ApiData<>(queryPassAreaDataPoint(tsdbTestAreaVehicleReqDTO.getAreaNo(), tsdbTestAreaVehicleReqDTO.getStartTime(), tsdbTestAreaVehicleReqDTO.getEndTime(), "ITK"));
    }

    @Operation(summary = "统计RTG一段时间的运动轨迹")
    @PostMapping("/statisticsRTGTrack")
    public void statisticsRTGTrack(@RequestBody StatisticsRTGTrackDTO statisticsRTGTrackDTO) throws Throwable {
        long startTime = DateUtil.parseDateTime(statisticsRTGTrackDTO.getStartTime()).getTime();
        long endTime = DateUtil.parseDateTime(statisticsRTGTrackDTO.getEndTime()).getTime();
        List<String> lists = new ArrayList<>();
        List<Query> queryList = Arrays.asList(new Query()
                .withMetric(TsdbConstants.rTGMachType)
                .withField(TsdbConstants.ItkField)
                .withFilters(new Filters()
                        .withAbsoluteStart(startTime)
                        .withAbsoluteEnd(endTime)
                        .addTag("pid", "215")
                        .addTag("machNo", statisticsRTGTrackDTO.getMachNo())
                ));
        String fileTemplate = String.format("/Users/liqi/Desktop/twins模拟测试数据/%sPoints.txt", statisticsRTGTrackDTO.getMachNo());
        BufferedWriter bw = FileUtil.getWriter(fileTemplate, "utf-8", false);

        List<com.jz.test.redistest.iecsTsdb.IecsRtgStateFeedbackDTO> iecsRtgStateFeedbackDTOS = new ArrayList<>();
        QueryDatapointsResponse response = TsdbProdClientConfig.getPrdClient().queryDatapoints(queryList);
        for (Result result : response.getResults()) {
            for (Group group : result.getGroups()) {
                for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                    com.jz.test.redistest.iecsTsdb.IecsRtgStateFeedbackDTO iecsRtgStateFeedbackDTO = JSONObject.parseObject(timeAndValue.getStringValue(), com.jz.test.redistest.iecsTsdb.IecsRtgStateFeedbackDTO.class);
                    long time = timeAndValue.getTime();
                    LocalDateTime of = LocalDateTimeUtil.of(time);
                    iecsRtgStateFeedbackDTO.setSotUpdateTm(of);
                    iecsRtgStateFeedbackDTOS.add(iecsRtgStateFeedbackDTO);
                }
            }
        }
        iecsRtgStateFeedbackDTOS.sort(Comparator.comparing(com.jz.test.redistest.iecsTsdb.IecsRtgStateFeedbackDTO::getSotUpdateTm));
        for (com.jz.test.redistest.iecsTsdb.IecsRtgStateFeedbackDTO iecsRtgStateFeedbackDTO : iecsRtgStateFeedbackDTOS) {
            bw.append(iecsRtgStateFeedbackDTO.getSotUpdateTm().toString()).append("    ");
            bw.append(iecsRtgStateFeedbackDTO.getRtgCenterCoordinate()).append("    ");
           bw.append(iecsRtgStateFeedbackDTO.getMgRunning()).append("   ");
            // bw.append(iecsRtgStateFeedbackDTO.getSosWorkStatus()).append("   ");
            bw.append("\n");
            bw.flush();
        }
        commonDownLoadUtil.downLoad(httpServletResponse, fileTemplate);
    }


    @Operation(summary = "统计QC一段时间的运动轨迹")
    @PostMapping("/statisticsQCTrack")
    public void statisticsQCTrack(@RequestBody StatisticsRTGTrackDTO statisticsRTGTrackDTO) throws Throwable {
        long startTime = DateUtil.parseDateTime(statisticsRTGTrackDTO.getStartTime()).getTime();
        long endTime = DateUtil.parseDateTime(statisticsRTGTrackDTO.getEndTime()).getTime();
        List<String> lists = new ArrayList<>();
        List<Query> queryList = Arrays.asList(new Query()
                .withMetric("QC_REAL_TIME")
                .withField(TsdbConstants.ItkField)
                .withFilters(new Filters()
                        .withAbsoluteStart(startTime)
                        .withAbsoluteEnd(endTime)
                        .addTag("pid", "22")
                        .addTag("machNo", statisticsRTGTrackDTO.getMachNo())
                ));
        String fileTemplate = String.format("/Users/liqi/Desktop/twins模拟测试数据/%sPoints.txt", statisticsRTGTrackDTO.getMachNo());
        BufferedWriter bw = FileUtil.getWriter(fileTemplate, "utf-8", false);

        List<IecsQcDeviceInfoDTO> iecsQcDeviceInfoDTOS = new ArrayList<>();
        QueryDatapointsResponse response = TsdbProdClientConfig.getPrdClient().queryDatapoints(queryList);
        for (Result result : response.getResults()) {
            for (Group group : result.getGroups()) {
                for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                    IecsQcDeviceInfoDTO iecsQcDeviceInfoDTO = JSONObject.parseObject(timeAndValue.getStringValue(), IecsQcDeviceInfoDTO.class);
                    long time = timeAndValue.getTime();
                    iecsQcDeviceInfoDTO.setCurTime(time);
                    iecsQcDeviceInfoDTOS.add(iecsQcDeviceInfoDTO);
                }
            }
        }
        iecsQcDeviceInfoDTOS.sort(Comparator.comparing(IecsQcDeviceInfoDTO::getCurTime));
        for (IecsQcDeviceInfoDTO iecsQcDeviceInfoDTO : iecsQcDeviceInfoDTOS) {
            bw.append(LocalDateTimeUtil.of(iecsQcDeviceInfoDTO.getCurTime()).toString()).append("    ");
            bw.append(iecsQcDeviceInfoDTO.getQcCenterCoordinate()).append("    ");
            bw.append(iecsQcDeviceInfoDTO.getQcScopeCoordinate()).append("   ");
            bw.append("\n");
            bw.flush();
        }
        commonDownLoadUtil.downLoad(httpServletResponse, fileTemplate);
    }


    @Operation(summary ="统计RTG,QCPLC一段时间的数据")
    @PostMapping("/statisticsQCRTGPLC")
    public void statisticsQCRTGPLC(@RequestBody StatisticsRTGTrackDTO statisticsRTGTrackDTO) throws Throwable {
        long startTime = DateUtil.parseDateTime(statisticsRTGTrackDTO.getStartTime()).getTime();
        long endTime = DateUtil.parseDateTime(statisticsRTGTrackDTO.getEndTime()).getTime();
        String machNo = statisticsRTGTrackDTO.getMachNo();
        String machType = "";
        if (machNo.startsWith("H1")) {
            machType = "QC";
        } else {
            machType = "RTG";
        }
        List<String> lists = new ArrayList<>();
        List<Query> queryList = Arrays.asList(new Query()
                .withMetric("TRAJECTORY")
                .withField(TsdbConstants.ItkField)
                .withFilters(new Filters()
                        .withAbsoluteStart(startTime)
                        .withAbsoluteEnd(endTime)
                        .addTag("pid", "12")
                        .addTag("machNo", statisticsRTGTrackDTO.getMachNo())
                        .addTag("machType",machType)
                ));
        String fileTemplate = String.format("/Users/liqi/Desktop/twins模拟测试数据/%sPLC.txt", statisticsRTGTrackDTO.getMachNo());
        BufferedWriter bw = FileUtil.getWriter(fileTemplate, "utf-8", true);

        List<IecsQcDeviceInfoDTO> iecsQcDeviceInfoDTOS = new ArrayList<>();
        QueryDatapointsResponse response = TsdbProdClientConfig.getPrdClient().queryDatapoints(queryList);
        JSONObject resultJson = null;
        for (Result result : response.getResults()) {
            for (Group group : result.getGroups()) {
                for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                    resultJson = JSONObject.parseObject(timeAndValue.getStringValue());
                    bw.append(LocalDateTimeUtil.of(timeAndValue.getTime()).toString()).append("    ");
                    // bw.append("SOP_MHRUNNING:").append(resultJson.getString("SOP_MHRUNNING")).append("    ");
                    // bw.append("SOP_MTRUNNING:").append(resultJson.getString("SOP_MTRUNNING")).append("   ");
                    // bw.append("SOP_MGRUNNING:").append(resultJson.getString("SOP_MGRUNNING")).append("   ");

                    bw.append("\n");
                    bw.flush();
                }
            }
        }
        commonDownLoadUtil.downLoad(httpServletResponse, fileTemplate);
    }


    @Operation(summary = "统计（一天）RTG,QCPLC一段时间的数据")
    @PostMapping("/statisticsQCRTGPLCOneDay")
    public void statisticsQCRTGPLCOneDay(@RequestBody StatisticsRTGTrackDTO statisticsRTGTrackDTO) throws Throwable {
        Long startTime = DateUtil.parseDateTime(statisticsRTGTrackDTO.getStartTime()).getTime();
        Long endTime = DateUtil.parseDateTime(statisticsRTGTrackDTO.getEndTime()).getTime();
        String machNo = statisticsRTGTrackDTO.getMachNo();
        String machType = "";
        if (machNo.startsWith("H1")) {
            machType = "QC";
        } else {
            machType = "RTG";
        }
        long count = (endTime - startTime) / DateUnit.HOUR.getMillis();
        List<StatisticsQCRTGPLCOneDayDTO> dataList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Long startTime1 = startTime;
            Long endTime1 = startTime1 + DateUnit.HOUR.getMillis();
            startTime = endTime1;
            List<Query> queryList = Arrays.asList(new Query()
                    .withMetric("TRAJECTORY")
                    .withField(TsdbConstants.ItkField)
                    .withFilters(new Filters()
                            .withAbsoluteStart(startTime1)
                            .withAbsoluteEnd(endTime1)
                            .addTag("pid", "12")
                            .addTag("machNo", statisticsRTGTrackDTO.getMachNo())
                            .addTag("machType",machType)
                    ));
            QueryDatapointsResponse response = TsdbProdClientConfig.getPrdClient().queryDatapoints(queryList);
            for (Result result : response.getResults()) {
                for (Group group : result.getGroups()) {
                    for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                        StatisticsQCRTGPLCOneDayDTO statisticsQCRTGPLCOneDayDTO = new StatisticsQCRTGPLCOneDayDTO();
                        statisticsQCRTGPLCOneDayDTO.set时间(LocalDateTimeUtil.of(timeAndValue.getTime()).toString());
                        statisticsQCRTGPLCOneDayDTO.set报文(timeAndValue.getStringValue());
                        dataList.add(statisticsQCRTGPLCOneDayDTO);
                    }
                }
            }
        }
        String fileTemplate = String.format("/Users/liqi/Desktop/twins模拟测试数据/%sPLC.csv", statisticsRTGTrackDTO.getMachNo());
        CsvWriter writer = CsvUtil.getWriter(fileTemplate, CharsetUtil.CHARSET_UTF_8);
        writer.writeBeans(dataList);
    }


    @Operation(summary = "统计车辆进出区域触发事件")
    @PostMapping("/statisticsVJCEvent")
    public void statisticsVJCEvent(@RequestBody StatisticsRTGTrackDTO statisticsRTGTrackDTO) throws Throwable {
        long startTime = DateUtil.parseDateTime(statisticsRTGTrackDTO.getStartTime()).getTime();
        long endTime = DateUtil.parseDateTime(statisticsRTGTrackDTO.getEndTime()).getTime();
        String machNo = statisticsRTGTrackDTO.getMachNo();
        String machType = "";
        if (machNo.startsWith("H8")) {
            machType = "AIV";
        } else {
            machType = "ITK";
        }
        List<MacEvent> macEventList = new ArrayList<>();
        List<Query> queryList = Arrays.asList(new Query()
                .withMetric(machType)
                .withField(TsdbConstants.ItkField)
                .withFilters(new Filters()
                        .withAbsoluteStart(startTime)
                        .withAbsoluteEnd(endTime)
                        .addTag("pid", "613")
                        .addTag("machNo", statisticsRTGTrackDTO.getMachNo())
                ));
        String fileTemplate = String.format("/Users/liqi/Desktop/twins模拟测试数据/%sEvent.txt", statisticsRTGTrackDTO.getMachNo());
        BufferedWriter bw = FileUtil.getWriter(fileTemplate, "utf-8", false);

        QueryDatapointsResponse response = TsdbProdClientConfig.getPrdClient().queryDatapoints(queryList);
        for (Result result : response.getResults()) {
            for (Group group : result.getGroups()) {
                for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                    MacEvent macEvent = JSONObject.parseObject(timeAndValue.getStringValue(), MacEvent.class);
                    bw.append(LocalDateTimeUtil.of(timeAndValue.getTime()).toString()).append("    ");
                    bw.append("事件:").append(macEvent.getEventType()).append("    ");
                    bw.append("\n");
                    bw.flush();
                }
            }
        }
        commonDownLoadUtil.downLoad(httpServletResponse, fileTemplate);
    }


    @Operation(summary = "统计一段时间人员定位数据")
    @PostMapping("/statisticsPersonLoc")
    public void statisticsPersonLoc(@RequestBody StatisticsRTGTrackDTO statisticsRTGTrackDTO) throws Throwable {
        long startTime = DateUtil.parseDateTime(statisticsRTGTrackDTO.getStartTime()).getTime();
        long endTime = DateUtil.parseDateTime(statisticsRTGTrackDTO.getEndTime()).getTime();
        String machNo = statisticsRTGTrackDTO.getMachNo();
        String machType = "ITK";
        List<Query> queryList = Arrays.asList(new Query()
                .withMetric(machType)
                .withField(TsdbConstants.ItkField)
                .withFilters(new Filters()
                        .withAbsoluteStart(startTime)
                        .withAbsoluteEnd(endTime)
                        .addTag("pid", "23")
                        .addTag("machNo", statisticsRTGTrackDTO.getMachNo())
                ));
        String fileTemplate = String.format("/Users/liqi/Desktop/twins模拟测试数据/%sPersonLoc.txt", statisticsRTGTrackDTO.getMachNo());
        BufferedWriter bw = FileUtil.getWriter(fileTemplate, "utf-8", false);

        QueryDatapointsResponse response = TsdbProdClientConfig.getPrdClient().queryDatapoints(queryList);
        for (Result result : response.getResults()) {
            for (Group group : result.getGroups()) {
                for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                    PeoPleDeviceDTO peoPleDeviceDTO = JSONObject.parseObject(timeAndValue.getStringValue(), PeoPleDeviceDTO.class);
                    bw.append(LocalDateTimeUtil.of(timeAndValue.getTime()).toString()).append("    ");
                    bw.append(peoPleDeviceDTO.getDeviceList().get(0).getCoordinate()).append("    ");
                    bw.append("\n");
                    bw.flush();
                }
            }
        }
        commonDownLoadUtil.downLoad(httpServletResponse, fileTemplate);
    }



    @Operation(summary = "转移生产车辆定位数据到测试环境")
    @PostMapping("/transferVehicle")
    public ApiData transferVehicle() throws Exception {
        Map<Object, Object> entries = redisTemplate01.opsForHash().entries(VehicleConstant.VEHICLE_REAL_TIME_DATA);
        redisTemplate01.opsForHash().putAll(VehicleConstant.VEHICLE_REAL_TIME_DATA, entries);
        return ApiData.OPERATION_SUCCESS;
    }


    public TsdbTestAreaVehicleResDTO queryPassAreaDataPoint(String areaNo, Long startTime, Long endTime, String vehicleType) {
        TsdbTestAreaVehicleResDTO tsdbTestAreaVehicleResDTO = new TsdbTestAreaVehicleResDTO();
        AtomicReference<Long> countNum = new AtomicReference<>(0L);
        List<Query> queryList = Arrays.asList(new Query()
                .withMetric(vehicleType)
                .withField("business_data")
                .withFilters(new Filters()
                        .withAbsoluteStart(startTime)
                        .withAbsoluteEnd(endTime)
                        .addTag("pid", "610")
                ));
        Long start = System.currentTimeMillis();
        QueryDatapointsResponse response = TsdbProdClientConfig.getPrdClient().queryDatapoints(queryList);
        Long end = System.currentTimeMillis();
        for (Result result : response.getResults()) {
            for (Group group : result.getGroups()) {
                try {
                    for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                        String vehicleArea = JSONObject.parseObject(timeAndValue.getStringValue()).getString("vehicleArea");
                        Optional.ofNullable(vehicleArea).ifPresent(s -> {
                            countNum.getAndSet(countNum.get() + 1);
                            if (s.contains(areaNo)) {
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        tsdbTestAreaVehicleResDTO.setDataPointNum(countNum.get());
        tsdbTestAreaVehicleResDTO.setConsumeTime(end - start);
        return tsdbTestAreaVehicleResDTO;
    }


    public String statisticsOriginVehiclesTsdb() throws Exception {
        Set<String> machNoSet = new HashSet<>();
        // 构建查询对象
        List<Query> queryList = Arrays.asList(new Query()
                .withMetric(TsdbConstants.ItkMetric)
                .withField(TsdbConstants.ItkField)
                .withFilters(new Filters()
                        .withAbsoluteStart(System.currentTimeMillis() - 5000)
                        .addTag("pid", "510")
                ));
        QueryDatapointsResponse response = TsdbProdClientConfig.getPrdClient().queryDatapoints(queryList);
        for (Result result : response.getResults()) {
            for (Group group : result.getGroups()) {
                for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                    JSONObject jsonObject = JSONObject.parseObject(timeAndValue.getStringValue());
                    machNoSet.add(jsonObject.getString("vehicleNo"));
                }
            }
        }
        String machNos = machNoSet.stream().collect(Collectors.joining(","));
        return machNos;
    }


    public String getOrignAivPositionFromProd(String vehicleNo, Long startTime, Long endTime) throws Exception {
        LinkedHashSet<String> pointSet = new LinkedHashSet();
        List<AivDTO> aivDTOS = new ArrayList<>();

        String metric = null;
        String pid = null;
        if (StrUtil.isNotBlank(vehicleNo)) {
            if (vehicleNo.startsWith("H8")) {
                metric = "REAL";
                pid = "418";
            } else {
                metric = TsdbConstants.ItkMetric;
                pid = "510";
            }
        } else {
            metric = TsdbConstants.ItkMetric;
            pid = "510";
        }
        Filters filters = new Filters()
                .withAbsoluteStart(startTime)
                .withAbsoluteEnd(endTime)
                .addTag("pid", pid);
        if (StrUtil.isNotBlank(vehicleNo)) {
            filters.addTag("machNo", vehicleNo);
        }

        // 构建查询对象
        List<Query> queryList = Arrays.asList(new Query()
                .withMetric(metric)
                .withField(TsdbConstants.ItkField)
                .withFilters(filters));
        QueryDatapointsResponse response = TsdbProdClientConfig.getPrdClient().queryDatapoints(queryList);
        for (Result result : response.getResults()) {
            for (Group group : result.getGroups()) {
                for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                    JSONObject jsonObject = JSONObject.parseObject(timeAndValue.getStringValue());
//                    LngLatPoint point = ZhtMapUtils.CoordEncrypt(jsonObject.getDouble("longitude"),jsonObject.getDouble("latitude"));
//                    String sourcecoor = point.getLng() + "," + point.getLat();
                    String curTime = jsonObject.getString("curTime");
                    AivDTO aivDTO = new AivDTO();
                    aivDTO.setMachNo(jsonObject.getString("machNo"));
                    aivDTO.setCurTime(jsonObject.getString("curTime"));
                    aivDTO.setLongitude(jsonObject.getString("longitude"));
                    aivDTO.setLatitude(jsonObject.getString("latitude"));
                    aivDTO.setDeviceNo(jsonObject.getString("deviceNo"));
                    aivDTO.setRefreshStatus(jsonObject.getString("refreshStatus"));
                    aivDTO.setJsonString(timeAndValue.getStringValue());
                    aivDTO.setCourseAngle(jsonObject.getString("courseAngle"));
                    aivDTOS.add(aivDTO);
                }
            }
        }
//        aivDTOS = aivDTOS.stream().filter(aivDTO -> StrUtil.isNotBlank(aivDTO.getCurTime()) && StrUtil.isNotBlank(aivDTO.getLongitude())).collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getLongitude() + "," + o.getLatitude()))), ArrayList::new));
        aivDTOS.sort((o1, o2) -> Long.valueOf(o1.getCurTime()).compareTo(Long.valueOf(o2.getCurTime())));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//        for (AivDTO aivDTO : aivDTOS) {
//            aivDTO.setCurTime(formatter.format(new Date(Long.valueOf(aivDTO.getCurTime()))));
//        }
        String fileTemplate = String.format("/Users/liqi/Desktop/twins模拟测试数据/%sPoints_origin.txt", vehicleNo);
        BufferedWriter bw = FileUtil.getWriter(fileTemplate, "utf-8", false);
        for (AivDTO aivDTO : aivDTOS) {
            bw.append(formatter.format(new Date(Long.valueOf(aivDTO.getCurTime()))) + ":" + aivDTO.getLongitude() + "," + aivDTO.getLatitude() + "," + aivDTO.getCourseAngle()).append("\n");
//             bw.append(formatter.format(new Date(Long.valueOf(aivDTO.getCurTime())))).append(",");
//             bw.append(aivDTO.getMachNo()).append(",");
//            bw.append(aivDTO.getDeviceNo());
//             bw.append(aivDTO.getLongitude() + "," + aivDTO.getLatitude());
//             bw.append(aivDTO.getVehicleArea()).append(",");
//             bw.append(aivDTO.getRefreshStatus()).append(",");
//             bw.append(aivDTO.getJsonString());
//             bw.append("\n");
            bw.flush();
        }
        return fileTemplate;
    }


    public String getAivPositionFromProd(String vehicleNo, Long startTime, Long endTime) throws Exception {
        LinkedHashSet<String> pointSet = new LinkedHashSet();
        List<AivDTO> aivDTOS = new ArrayList<>();
        String metric = null;
        if (vehicleNo.startsWith("H8")) {
            metric = TsdbConstants.AivMetric;
        } else if (Test.isContainChinese(vehicleNo))
            metric = "OTK";
        else {
            metric = TsdbConstants.ItkMetric;
        }
        // 构建查询对象
        List<Query> queryList = Arrays.asList(new Query()
                .withMetric(metric)
                .withField(TsdbConstants.ItkField)
                .withFilters(new Filters()
                                .withAbsoluteStart(startTime)
                                .withAbsoluteEnd(endTime)
                                .addTag("pid", TsdbConstants.ItkPid).addTag("machNo", vehicleNo)
//                        .addTagFilter(new TagFilter().withTag("pid").addIn(TsdbConstants.ItkPid)).addTagFilter(new TagFilter().withTag("machNo").addIn("H352"))
                ));
        QueryDatapointsResponse response = TsdbProdClientConfig.getPrdClient().queryDatapoints(queryList);
        for (Result result : response.getResults()) {
            for (Group group : result.getGroups()) {
                for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                    AivDTO aivDTO = JSONObject.parseObject(timeAndValue.getStringValue(), AivDTO.class);
                    aivDTO.setCurTime(aivDTO.getCurTime());
                    aivDTOS.add(aivDTO);
                }
            }
        }
//        aivDTOS = aivDTOS.stream().filter(aivDTO -> StrUtil.isNotBlank(aivDTO.getCurTime()) && StrUtil.isNotBlank(aivDTO.getLongitude())).collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getLongitude() + "," + o.getLatitude()))), ArrayList::new));
        aivDTOS.sort((o1, o2) -> Long.valueOf(o1.getCurTime()).compareTo(Long.valueOf(o2.getCurTime())));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//        for (AivDTO aivDTO : aivDTOS) {
//            aivDTO.setCurTime(formatter.format(new Date(Long.valueOf(aivDTO.getCurTime()))));
//        }
        String fileTemplate = String.format("/Users/liqi/Desktop/twins模拟测试数据/%sPoints.txt", vehicleNo);
        BufferedWriter bw = FileUtil.getWriter(fileTemplate, "utf-8", false);
        for (AivDTO aivDTO : aivDTOS) {
//            bw.append(aivDTO.getCurTime() +":" +aivDTO.getLongitude()+","+aivDTO.getLatitude()).append("\n");
            bw.append(formatter.format(new Date(Long.valueOf(aivDTO.getCurTime())))).append(":     ");
            bw.append(aivDTO.getLongitude() + "," + aivDTO.getLatitude()).append("   ");
//            bw.append(aivDTO.getScopeCoordinate() + "       ");
//            bw.append(aivDTO.getScopeCoordinate()).append("    ");
           bw.append(aivDTO.getVehicleArea()).append("   ");
//            bw.append(aivDTO.getAreaType()).append("   ");

            // bw.append(aivDTO.getVehicleHeading()).append("   ");
//            bw.append(aivDTO.getCourseAngle());
            //周边车辆StrUtil.sub
            List<AroundVehicleDTO> aroundVehicleList = aivDTO.getAroundVehicleList();
            List<TrafficSignal> trafficList = aivDTO.getTrafficList();
//            bw.append(JSONObject.toJSONString(trafficList)).append("     ");
//            if (CollUtil.isNotEmpty(aroundVehicleList)) {
//                bw.append("   ");
//                for (int i = 0; i < aroundVehicleList.size(); i++) {
//                    AroundVehicleDTO aroundVehicleDTO = aroundVehicleList.get(i);
//                    String vehicleType = aroundVehicleDTO.getVehicleType();
//                    if ("RTG".equals(vehicleType)) {
//                        bw.append(aroundVehicleDTO.getVehicleNo()).append("|").append(aroundVehicleDTO.getCoordinateTime());
//                    }
//                    if (i != aroundVehicleList.size() - 1) {
//                        bw.append(";");
//                    }
//                }
//            }
//            }
            bw.append("\n");
            bw.flush();
        }
        return fileTemplate;
    }

    public static void main(String[] args) {

        String sourcecoor = "121.65459233,31.33631713";
        String[] split = sourcecoor.split(",");
        LngLatPoint point1 = JavaTrans.CoordEncrypt(UrlStringUtils.toDouble(split[0]), UrlStringUtils.toDouble(split[1]), 50);
        String point1Str = point1.getLng() + "," + point1.getLat();
        LngLatPoint point2 = JavaTrans.CoordEncrypt(UrlStringUtils.toDouble(split[0]), UrlStringUtils.toDouble(split[1]), 50);
        String point2Str = point2.getLng() + "," + point2.getLat();
        System.out.println(point1Str + " " + point2Str);

        System.out.println("ahah");

//        Set<String> s1 = Sets.newHashSet("liqi", "hello","liqi");
//        Set<String> s2 = Sets.newHashSet("liqi");
        Set<String> s1 = new HashSet<String>();
        s1.add("H243");
        s1.add("H244");
        Set<String> s2 = new HashSet<String>();
        s2.add("H243");
        s2.add("H245");
        Sets.SetView<String> difference = Sets.difference(s2, s1);
        difference.isEmpty();
        System.out.println(Sets.difference(s1, s2));

        //箱区分配的权重Map01
        Map<String, Long> priorityMap01 = new HashMap<>();
        priorityMap01.put("12",2L);
        priorityMap01.put("13",5L);
        priorityMap01.put("14",1L);
        List<String> xqPriorityList = Lists.<String>newArrayList();
        priorityMap01.entrySet().stream().sorted((o1, o2) -> o1.getValue().compareTo(o2.getValue())).forEach(entry -> {
            xqPriorityList.add(entry.getKey());
        });
        System.out.println(JSONObject.toJSONString(xqPriorityList));

        log.info("堆存计划数据,箱区选择优先级排序后:{}", JSONObject.toJSONString(xqPriorityList));
        List<String> sortAreaLIst = Lists.newArrayList("12","13","14");

        int count = sortAreaLIst.size();
        Collections.sort(sortAreaLIst, ((o1, o2) -> {
            int io1 = xqPriorityList.indexOf(o1);
            int io2 = xqPriorityList.indexOf(o2);
            if (io1 != -1) {
                io1 = count - io1;
            }
            if (io2 != -1) {
                io2 = count - io2;
            }
            return io2 - io1;
        }));
        System.out.println(JSONObject.toJSONString(sortAreaLIst));

        Set<String> s3 = new HashSet<String>();
        s3.add("H246");
        s3.add("H246");
        System.out.println(s3);
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("2", "3");

        JSONObject.toJSONString(null);

    }

}
