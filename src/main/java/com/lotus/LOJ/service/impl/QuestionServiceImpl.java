package com.lotus.LOJ.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.lotus.LOJ.common.ErrorCode;
import com.lotus.LOJ.exception.BusinessException;
import com.lotus.LOJ.exception.ThrowUtils;
import com.lotus.LOJ.model.entity.*;
import com.lotus.LOJ.model.vo.QuestionVO;
import com.lotus.LOJ.service.QuestionService;
import com.lotus.LOJ.mapper.QuestionMapper;
import com.lotus.LOJ.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 31964
 * @description 针对表【question(题目)】的数据库操作Service实现
 * @createDate 2024-11-09 13:56:25
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
        implements QuestionService {
    private final static Gson GSON = new Gson();

    @Resource
    private UserService userService;

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public void validQuestion(Question question, boolean add) {
        if (question == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String title = question.getTitle();
        String content = question.getContent();
        String judgeCase = question.getJudgeCase();
        String judgeConfig = question.getJudgeConfig();
        // 创建时，参数不能为空
        // 有参数则校验
        if (StringUtils.isNotBlank(title) && title.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题过长");
        }
        if (StringUtils.isNotBlank(content) && content.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
    }

    @Override
    public QuestionVO getQuestionVO(Question question, HttpServletRequest request) {
        // 1. 关联查询用户信息
        if (request == null || userService.getLoginUser(request) == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        QuestionVO questionVO = QuestionVO.objToVo(question);
        return questionVO;
    }

}




