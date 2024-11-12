package com.lotus.LOJ.model.dto.user;

import com.lotus.LOJ.common.PageRequest;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户查询请求
 *

 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

//    /**
//     * 开放平台id
//     */
//    private String unionId;
//
//    /**
//     * 公众号openId
//     */
//    private String mpOpenId;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户角色：user/admin/ban
     */
    private Integer role;

    private static final long serialVersionUID = 1L;
}