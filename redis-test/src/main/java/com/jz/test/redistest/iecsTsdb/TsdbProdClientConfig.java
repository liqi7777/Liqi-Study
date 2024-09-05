package com.jz.test.redistest.iecsTsdb;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tsdb.TsdbClient;

/**
 * @author DAICX
 * @version 1.0
 * @Description
 * @date 2021/3/10 13:11
 */
public class TsdbProdClientConfig {
    private static TsdbClient devClient;
    private static TsdbClient prdClient;
    private static String devEndpoint = "122.112.220.88:18015";
    private static String devDbname = "iecstsdb1";
    private static String prdEndpoint = "10.140.20.201:8015";
    private static String prdDbname = "iecstsdb1";


    private static String ACCESS_KEY_ID = "b40e98dd0be34d7998569547af94a628";     // 用户的Access Key ID
    private static String SECRET_ACCESS_KEY = "e7430c36fbb04144ac2b9d666f486924"; // 用户的Secret Access Key

    public static TsdbClient getDevClient() {
        if (devClient == null) {
            BceClientConfiguration configuration = new BceClientConfiguration()
                    .withEndpoint(devEndpoint)
                    .withCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
            devClient = new TsdbClient(configuration, devDbname);
        }
        return devClient;
    }

    public static TsdbClient getPrdClient() {
        if (prdClient == null) {
            BceClientConfiguration configuration = new BceClientConfiguration()
                    .withEndpoint(prdEndpoint)
                    .withCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
            prdClient = new TsdbClient(configuration, prdDbname);
        }
        return prdClient;
    }
}
