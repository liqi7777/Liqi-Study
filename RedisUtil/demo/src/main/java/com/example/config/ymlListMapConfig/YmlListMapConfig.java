package com.example.config.ymlListMapConfig;

import com.example.ymlListMap.TriggerConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Sky
 * create  2020-11-06 14:22
 * email sky.li@ixiaoshuidi.com
 */
@Component
@Data
//@ConfigurationProperties(prefix = "bcop.quartz")
public class YmlListMapConfig {

    private Map<String, List<TriggerConfig>> triggers;
}
