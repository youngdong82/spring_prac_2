package spring_yd.prac_2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberType type;


    protected Member() {
    }
    public static Member createMember(String name, MemberType type) {
        Member member = new Member();
        member.setName(name);
        member.setType(type);
        member.setCreatedAt(LocalDateTime.now());
        return member;
    }

    //연관관계 편의 메소드
    public void assignTeam (Team team){
        this.team = team;
        team.getMembers().add(this);
        setUpdatedAt(LocalDateTime.now());
    }
}
