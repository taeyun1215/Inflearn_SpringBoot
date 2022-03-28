package hello.hellospring.controller;

import java.security.Principal;

public class MemberForm {
    private String name;   // spring이 입력 됨

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
