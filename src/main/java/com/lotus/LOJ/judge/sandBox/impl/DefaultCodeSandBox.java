package com.lotus.LOJ.judge.sandBox.impl;
import com.lotus.LOJ.judge.sandBox.CodeSandBox;
import com.lotus.LOJ.judge.sandBox.model.ExecuteCodeRequest;
import com.lotus.LOJ.judge.sandBox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Service;

@Service
public class DefaultCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println(executeCodeRequest.toString());
        return null;
    }
}