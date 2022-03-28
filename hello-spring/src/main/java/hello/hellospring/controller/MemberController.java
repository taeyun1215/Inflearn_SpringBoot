package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 외부 요청을 받음
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // url 입력 방식, method="get"
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")    // method="post" 방식
    public String create(MemberForm form) { // controller폴더 안에 MemberForm 파일로 넘어감
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName()); // url 에서 입력 받으면 콘솔창에 뜸
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
    
}
