package com.lotus.LOJ.judge.sandBox;

import com.lotus.LOJ.judge.sandBox.impl.DefaultCodeSandBox
public class CodeSandBoxFactory {

    public static CodeSandBox getCodeSandBox(String type) {
        switch (type) {
            case "example":
                return new DefaultCodeSandBox();
            default:
                return null;
        }
    }
}