package com.example.easyexceltest;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sky
 * create 2019/07/24
 * email sky.li@ixiaoshuidi.com
 **/
public class easyexcelTest {
    public static void main(String[] args) throws FileNotFoundException {

//        InputStream inputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\新智能硬件\\比特\\宁巢水电表(1).xlsx"));
//        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(2, 0, BiTeReadExcelModel.class));
//        for (Object datum : data) {
//            System.out.println(datum.getClass());
//        }

//        ExcelReader reader = ExcelUtil.getReader("I:\\ningchao.xlsx");
//        List<BiTeReadExcelModel> biTeReadExcelModels = reader.readAll(BiTeReadExcelModel.class);
//        System.out.println(biTeReadExcelModels.toString());

        String filterName = "SRB-VO:JS57";
        String fileLocation="/Users/liqi/Desktop/实时计算效率分析/storm-supervisor-service.soms.svc.cluster.local-REMOTE_TOPOLOGY-38-1628177224-6700-worker (1).log";

        List<String> lineList = FileUtil.readLines(new File(fileLocation), "utf-8");
        lineList = lineList.stream().filter(s -> s.contains(filterName)).collect(Collectors.toList());
//        for (String s : lineList) {
//            String[] split = s.split("\\[INFO]");
//            System.out.println(split[1].trim());
//        }
        lineList = lineList.stream().map(s -> s.split("\\[INFO]")[1]).collect(Collectors.toList());

        FileUtil.writeLines(lineList,"/Users/liqi/Desktop/实时计算效率分析/JS57.log","utf-8");




    }
}
