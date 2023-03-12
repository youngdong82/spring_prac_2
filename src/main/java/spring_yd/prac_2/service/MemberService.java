package spring_yd.prac_2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_yd.prac_2.domain.Member;
import spring_yd.prac_2.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository repository;

    //회원가입!!!!
    @Transactional
    public Long join(Member member){
        repository.save(member);
        return member.getId();
    }

    //전체 조회
    public List<Member> findAllMembers(){
        return repository.findAll();
    }

    //id로 조회
    public Member findMemberById(Long memberId){
        return repository.findById(memberId);
    }

    //이름으로 조회
    public List<Member> findMembersByName(String name){
        return repository.findByName(name);
    }
    //수정

    //탈퇴
    @Transactional
    public void deleteMember(Long memberId){
        Member findMember = repository.findById(memberId);
        repository.delete(findMember);
    }
}
