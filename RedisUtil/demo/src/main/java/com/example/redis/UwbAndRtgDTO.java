package com.example.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liqi
 * create  2021-07-14 15:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UwbAndRtgDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Boolean uwb;
    private Boolean rtg;


}
