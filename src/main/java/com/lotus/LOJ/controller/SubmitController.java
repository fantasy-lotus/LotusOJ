package com.lotus.LOJ.controller;

import com.lotus.LOJ.common.BaseResponse;
import com.lotus.LOJ.common.ErrorCode;
import com.lotus.LOJ.common.ResultUtils;
import com.lotus.LOJ.exception.BusinessException;
import com.lotus.LOJ.model.dto.postthumb.PostThumbAddRequest;
import com.lotus.LOJ.model.dto.submit.SubmitRequest;
import com.lotus.LOJ.model.entity.User;
import com.lotus.LOJ.service.PostThumbService;
import com.lotus.LOJ.service.SubmitService;
import com.lotus.LOJ.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping("/submit")
public class SubmitController {
    @Resource
    private SubmitService submitService;
    @Resource
    private UserService userService;

    /**
     * 提交代码
     *
     * @param submitRequest
     * @param request
     * @return resultNum 提交记录id
     */
    @PostMapping("/")
    public BaseResponse<Long> doSubmit(@RequestBody SubmitRequest submitRequest,
                                         HttpServletRequest request) {
        if (submitRequest == null || submitRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 提交
        final User loginUser = userService.getLoginUser(request);
        Long result = submitService.doQuestionSubmit(submitRequest,loginUser);
        return ResultUtils.success(result);
    }

}
