package com.lotus.LOJ.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lotus.LOJ.model.entity.Question;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class QuestionServiceTest {
    @Autowired
    QuestionService questionService;

    @Test
    void getQuestionByLevel() {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level", 0);
        log.info(questionService.list(queryWrapper).toString());
    }
}