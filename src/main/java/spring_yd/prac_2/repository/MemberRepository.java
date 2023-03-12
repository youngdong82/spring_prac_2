package spring_yd.prac_2.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring_yd.prac_2.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    //save
    public void save(Member member){
        em.persist(member);
    }

    //findAll
    public List<Member> findAll(){
        String query = "select m from Member m";
        return em.createQuery(query, Member.class)
                .getResultList();
    }

    //findById
    public Member findById(Long memberId){
        return em.find(Member.class, memberId);
    }

    //findByName
    public List<Member> findByName(String name){
        String query = "select m from Member m where m.name = :name";
        return em.createQuery(query, Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    //update

    //delete
    public void delete(Member member){
        em.remove(member);
    }

}
