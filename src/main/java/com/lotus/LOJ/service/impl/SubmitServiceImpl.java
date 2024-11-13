package com.lotus.LOJ.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lotus.LOJ.common.ErrorCode;
import com.lotus.LOJ.exception.BusinessException;
import com.lotus.LOJ.exception.ThrowUtils;
import com.lotus.LOJ.model.dto.submit.SubmitRequest;
import com.lotus.LOJ.model.entity.Submit;
import com.lotus.LOJ.model.entity.User;
import com.lotus.LOJ.service.QuestionService;
import com.lotus.LOJ.service.SubmitService;
import com.lotus.LOJ.mapper.SubmitMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 31964
* @description 针对表【submit(提交表)】的数据库操作Service实现
* @createDate 2024-11-09 13:56:31
*/
@Service
public class SubmitServiceImpl extends ServiceImpl<SubmitMapper, Submit>
    implements SubmitService{

    @Resource
    private QuestionService questionService;
    /**
     * 提交代码
     *
     * @param submitRequest
     * @param loginUser
     * @return
     */
    @Override
    public Long doQuestionSubmit(SubmitRequest submitRequest, User loginUser) {
        if(loginUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        String language = submitRequest.getLanguage();
        if(!"java".equals(language)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"目前只支持java语言");
        }
        if(questionService.getById(submitRequest.getQuestionId()) == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"题目不存在");
        }
        Submit submit = new Submit();
        submit.setQuestionId(submitRequest.getQuestionId());
        submit.setCode(submitRequest.getCode());
        submit.setLanguage(language);
        submit.setUserId(loginUser.getId());
        boolean save = this.save(submit);
        ThrowUtils.throwIf(!save,ErrorCode.SYSTEM_ERROR,"提交失败");
        return submit.getId();
    }

}




