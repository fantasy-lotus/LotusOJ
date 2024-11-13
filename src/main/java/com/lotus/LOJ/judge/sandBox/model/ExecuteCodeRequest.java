package com.lotus.LOJ.judge.sandBox.model;

import lombok.Data;

import java.util.List;

@Data
public class ExecuteCodeRequest {
    private String code;
    private String language;
    private List<String> input;
}
