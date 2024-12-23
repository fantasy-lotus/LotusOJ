package com.lotus.LOJ.model.dto.question;

import com.lotus.LOJ.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 查询请求
 *

 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 搜索词
     */
    private String searchText;

    /**
     * 难度 0 - noob 1 - normal 2 - hard -1 - undefined
     */
    private Integer level;

    private static final long serialVersionUID = 1L;
}