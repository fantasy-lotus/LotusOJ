package com.lotus.LOJ.judge.sandBox.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExecuteCodeRequest {
    private String code;
    private String language;
    private List<String> input;
}
