package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.nio.channels.Pipe;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
//    MemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // 메소드가 실행이 끝날 때 마다 실행 되는 메소드
    public void afterEach() {
        repository.clearStore();    // 하는 이유는 전체 메소드 실행 할 때 오류가 남(객체에 이미 저장돼 있기 때문)
    }   // 테스트 메소드는 서로 의존관계 없이 설계가 되어야 함

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);   // 7번째 줄에서 임포트 시켜줌
        // Assertions.assertEquals(member, result);
        // System.out.println("result = " + (result == member));
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll(); // 받을 때도 findAll함수에서 리턴되는 변수 타입으로 객체 생성하기

        assertThat(result.size()).isEqualTo(2);
    }
}
