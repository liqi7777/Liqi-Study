package com.example.ymlListMap;

import lombok.Data;

@Data
public class TriggerConfig {
    private String className;
    private String cronExpression;
    private String jobDetailName;
    private String triggerName;
}
