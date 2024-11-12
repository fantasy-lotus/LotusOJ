package com.lotus.LOJ.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户更新个人信息请求
 *

 */
@Data
public class UserUpdateMyRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 简介
     */
    private String profile;

    private static final long serialVersionUID = 1L;
}