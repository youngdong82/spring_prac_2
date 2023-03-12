package spring_yd.prac_2.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring_yd.prac_2.domain.Team;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeamRepository {

    private final EntityManager em;

    //team 생성

    //team 모두 조회

    //team 이름으로 조회
    public Team getTeamByName(String name){
        String query = "select t from Team t where t.name = :name";
        List<Team> all = em.createQuery(query, Team.class)
                .setParameter("name", name)
                .getResultList();
        return all.get(0);
    }

    //team 제거
}
