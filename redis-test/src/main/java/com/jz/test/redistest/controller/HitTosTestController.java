package com.jz.test.redistest.controller;

import com.jz.test.redistest.redissonDelay.DelayJobProducer;
import com.jz.test.redistest.redissonDelay.DeplayJobDTO;
import com.jz.test.redistest.redissonDelay.ParamDTO;
import com.jz.test.redistest.redissonDelay.TestDelayJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author liqi
 * create  2022/6/27 12:16 下午
 */
@Slf4j
@RestController
@RequestMapping("/tos")
public class HitTosTestController {
    @Autowired
    private DelayJobProducer delayJobProducer;

    // BJML返回例子
    public static final String bjmcRes = "<BJML><tid>990</tid><datetime>20230310100411</datetime><id>BJML</id><type>1</type><recordNo>1</recordNo><records><record><moveType>2</moveType><ascId>990</ascId><jobId>031010041002141</jobId><jobPriority>2</jobPriority><jobRefid>CYGR</jobRefid><fromJobPos></fromJobPos><toJobPos/><fromOffset/><toOffset/><fromChassisPos/><toChassisPos/><fromChassisSize/><toChassisSize/><fromChassisProfile/><toChassisProfile/><containerId>JLCN20233100</containerId><containerIso>2020</containerIso><containerLength>6096</containerLength><containerHeight>2438</containerHeight><containerType>0</containerType><fromTruckType>1</fromTruckType><fromTruckId>JLXT001</fromTruckId><fromTruckRoleType>1</fromTruckRoleType><fromChassisType/><fromBatNumber/><toTruckType/><toTruckId/><toTruckRoleType/><toChassisType/><toBatNumber/><fromBlock>RT6</fromBlock><fromStack>1</fromStack><fromLane>0</fromLane><fromTier/><fromStackingMode/><fromStackPileInd/><toBlock>RT6</toBlock><toStack>1</toStack><toLane>5</toLane><toTier>1</toTier><toStackingMode>2</toStackingMode><toStackPileInd>1</toStackPileInd><fromStackProfile/><fromTopTierContainerTypeProfile/><toStackProfile>000000000000000000000000004877</toStackProfile><toTopTierContainerTypeProfile>000000</toTopTierContainerTypeProfile><containerIdFinal/><truckIdFinal/><cntrStatus>F</cntrStatus><dgCodes/><eCodes/><spCodes/><plugOnInd>N</plugOnInd></record></records><block/><stack/><lane/><targetCompletionTime/></BJML>";

    public static final String BSYERes = "<BSYE><tid>H243</tid><datetime>20241108150718</datetime><id>BSYE</id><status>1</status><message>TEST</message></BSYE>\n";

    @PostMapping("/services/message/adsiService")
    public String test01(@RequestBody String body) {
        return BSYERes;
    }
}
