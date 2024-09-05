package com.example.demo;

import com.example.mapper.SysUserMapper;
import com.example.model.SysUser;
import com.example.service.SysUserService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisPlusApplicationTests {

    @Autowired
    private SysUserService userService;


    @Test
    public void contextLoads() {

        // 所有用户
        List<SysUser> users = userService.selectList(null);
        System.out.println(users);


    }

}
