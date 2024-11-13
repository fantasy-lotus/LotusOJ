package com.lotus.LOJ.judge.sandBox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class ExecuteCodeResponse {
    private List<String> output;
    private String time;
    private String memory;
    private String message;
}