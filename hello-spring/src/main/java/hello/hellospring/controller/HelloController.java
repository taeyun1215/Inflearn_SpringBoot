package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // 웹 서버 링크) http://localhost:8080/hello
    public String hello(Model model) {
        model.addAttribute("data","hello!"); // data = hello!
        return "hello"; // hello.html에 랜더링 시켜라, resources/templates/{ViewName}.html 기본적인 위치임.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // RequestParam은 안에 있는 매개변수를 받아라는 뜻, 링크에 들어가는 변수임
        model.addAttribute("name", name); // addAttributeName은 밑에 hello-template,html에 있는 name으로 렌더링 됨
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "gello spring
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // json 방식
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}