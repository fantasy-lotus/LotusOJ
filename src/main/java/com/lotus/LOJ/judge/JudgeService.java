package com.lotus.LOJ.judge;


import com.lotus.LOJ.model.dto.submit.JudgeInfo;

public interface JudgeService {

    Long doJudge(long SubmitId);
}
