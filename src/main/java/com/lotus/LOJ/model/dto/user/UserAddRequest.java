package com.lotus.LOJ.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户创建请求
 *

 */
@Data
public class UserAddRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 账号
     */
    private String tel;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户角色: user, admin
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}