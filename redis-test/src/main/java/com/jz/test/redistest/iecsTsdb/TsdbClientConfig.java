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
public class TsdbClientConfig {

    private static String ACCESS_KEY_ID = "b40e98dd0be34d7998569547af94a628";     // 用户的Access Key ID
    private static String SECRET_ACCESS_KEY = "e7430c36fbb04144ac2b9d666f486924"; // 用户的Secret Access Key

//        private static String ENDPOINT = "122.112.220.88:18015";          // 用户的时序数据库域名，形式如databasename.tsdb.iot.gz.baidubce.com
    //线上tsdb·
    private static String ENDPOINT = "10.140.20.201:8015";          // 用户的时序数据库域名，形式如databasename.tsdb.iot.gz.baidubce.com
    private static String tsdbname = "iecstsdb1";


    private static TsdbClient tsdbClient;

    static {
        BceClientConfiguration configuration = new BceClientConfiguration()
                .withEndpoint(ENDPOINT)
                .withCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        tsdbClient = new TsdbClient(configuration, tsdbname);
    }

    public static TsdbClient getTsdbClient() {
        return tsdbClient;
    }
}
