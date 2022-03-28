package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.*;

//@Repository // 데이터 저장함
public class MemoryMemberRepository implements MemberRepository{    // implements는 interface 구현할 때만 사용함

    private static Map<Long, Member> store = new HashMap<>();   // 메모리 파일 이니까 어딘가 저장을 해야하므로 만듬
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // save 클래스를 이용하여 여러개의 store이 저장 되므로 배열로 저장함
    }

    public void clearStore() {
        store.clear();
    }
}