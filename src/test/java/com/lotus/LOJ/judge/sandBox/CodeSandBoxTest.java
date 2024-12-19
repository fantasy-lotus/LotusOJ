package com.lotus.LOJ.judge.sandBox;

import com.lotus.LOJ.judge.sandBox.model.ExecuteCodeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAspectJAutoProxy(exposeProxy = true)
class CodeSandBoxTest {

    @Test
    void executeCode() {
        CodeSandBox codeSandBox = CodeSandBoxFactory.getCodeSandBox("default");
        codeSandBox.executeCode(ExecuteCodeRequest.builder()
                .code("public class Main{public static void main(String[] args){System.out.println(1+2);}}").
                language("java").
                input(null)
                .build());
    }

}