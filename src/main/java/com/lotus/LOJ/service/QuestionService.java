package com.lotus.LOJ.service;

import com.lotus.LOJ.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lotus.LOJ.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author 31964
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2024-11-09 13:56:25
*/
public interface QuestionService extends IService<Question> {

    void validQuestion(Question question, boolean add);

    QuestionVO getQuestionVO(Question question, HttpServletRequest request);
}
