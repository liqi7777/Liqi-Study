package com.example.easyexceltest.financeExcelTest;

import com.example.util.HttpClientUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author Sky
 * create 2020/07/07
 * email sky.li@ixiaoshuidi.com
 **/
@RestController
@RequestMapping("financeExcel")
public class FinanceExcelController {


    @GetMapping("download")
    public void tradeOrderQueryExport(QueryConditionModel queryConditionModel, HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("交易记录.xlsx", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        queryConditionModel.setPayWay("");
        queryConditionModel.setData("");
        QueryConditionModel queryConditionModel1=new QueryConditionModel();
        queryConditionModel1.setPayWay("");
        queryConditionModel1.setTradeStatus("S");
        queryConditionModel1.setData("");
        queryConditionModel1.setUserId(800000000);
        queryConditionModel1.setPage(1);
        queryConditionModel1.setPageSize(10);
        queryConditionModel1.setStartTime("2020-01-01");
        queryConditionModel1.setEndTime("2020-07-07");

        String resut = HttpClientUtil.doPost("http://127.0.0.1:7001/trade/order/backStageFundInQuery", queryConditionModel1);
        System.out.println(resut);
    }
}
