package com.example.jwtDemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sky
 * create 2019/06/17
 * email sky.li@ixiaoshuidi.com
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String Id;
    String username;
    String password;
}
