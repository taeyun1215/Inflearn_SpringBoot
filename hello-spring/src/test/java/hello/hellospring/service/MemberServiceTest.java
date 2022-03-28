package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService memberService = new MemberService();  // 하나로는 부족해서 밑에 객체를 하나 만들어줌
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository(); // 메모리 클리어를 위해 생성함
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 각 메소드를 실행 하기 전에 실행 됨
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach  // 메소드가 실행이 끝날 때 마다 실행 되는 메소드
    public void afterEach() {
        memberRepository.clearStore();    // 하는 이유는 전체 메소드 실행 할 때 오류가 남(객체에 이미 저장돼 있기 때문)
    }   // 테스트 메소드는 서로 의존관계 없이 설계가 되어야 함

    @Test
    void 회원가입() {   // 단숨함, 예외 플로우가 중요함
        //given(데이터)
        Member member = new Member();
        member.setName("spring");

        //when(검증)
        Long saveId = memberService.join(member);   // 예외 처리(중복된 이름)도 확인을 해야함 그건 밑에 test에서 함

        //then(검증부?)
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
/*
        try {
            memberService.join(member1);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}