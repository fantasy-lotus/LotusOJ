package com.lotus.LOJ.model.vo;

import cn.hutool.json.JSONUtil;
import com.google.gson.reflect.TypeToken;
import com.lotus.LOJ.model.dto.question.JudgeConfig;
import com.lotus.LOJ.model.entity.Question;
import com.lotus.LOJ.model.entity.Question;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class QuestionVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 题干
     */
    private String content;

    /**
     * 难度 0 - noob 1 - normal 2 - hard -1 - undefined
     */
    private Integer level;

    /**
     * 判题限制 - JudgeConfig对象
     */
    private JudgeConfig judgeConfig;

    /**
     * 通过人数
     */
    private Integer acceptNum;

    /**
     * 提交人数
     */
    private Integer submitNum;

    private static final long serialVersionUID = 1L;

    public static Question voToObj(QuestionVO questionVO) {
        if (questionVO == null) {
            return null;
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionVO, question);
        JudgeConfig judgeConfig = questionVO.getJudgeConfig();
        if (judgeConfig != null) {
            question.setJudgeConfig(JSONUtil.toJsonStr(judgeConfig));
        }
        return question;
    }

    /**
     * 对象转包装类
     *
     * @param question
     * @return
     */
    public static QuestionVO objToVo(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        String judgeConfig = question.getJudgeConfig();
        if (StringUtils.isNotBlank(judgeConfig)) {
            questionVO.setJudgeConfig(JSONUtil.toBean(judgeConfig, JudgeConfig.class));
        }
        return questionVO;
    }
}
