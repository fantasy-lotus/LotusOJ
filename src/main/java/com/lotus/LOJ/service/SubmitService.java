package com.lotus.LOJ.service;

import com.lotus.LOJ.model.dto.submit.SubmitRequest;
import com.lotus.LOJ.model.entity.Submit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lotus.LOJ.model.entity.User;

/**
* @author 31964
* @description 针对表【submit(提交表)】的数据库操作Service
* @createDate 2024-11-09 13:56:31
*/
public interface SubmitService extends IService<Submit> {

    Long doQuestionSubmit(SubmitRequest submitRequest, User loginUser);
}
