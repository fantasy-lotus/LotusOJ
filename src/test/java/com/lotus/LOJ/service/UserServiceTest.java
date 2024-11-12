package com.lotus.LOJ.service;

import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 用户服务测试
 *

 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void userRegister() {
        String tel = "lotus";
        String pwd = "";
        String checkPassword = "123456";
        try {
            long result = userService.userRegister(tel, pwd, checkPassword);
            Assertions.assertEquals(-1, result);
            tel = "yu";
            result = userService.userRegister(tel, pwd, checkPassword);
            Assertions.assertEquals(-1, result);
        } catch (Exception e) {

        }
    }
}
