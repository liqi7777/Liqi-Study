package com.jz.test.redistest.redissonDelay;

import com.jz.test.redistest.test.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liqi
 * create  2024/11/4 4:15 下午
 */
@Component
@Slf4j
public class TestDelayJob implements DelayJob<ParamDTO> {

    @Override
    public void execute(DeplayJobDTO<ParamDTO> deplayJobDTO) {
        ParamDTO paramDTO = deplayJobDTO.getParam();
        log.info("TestDelayJobService starting paramDTO={}", paramDTO);
    }
}
