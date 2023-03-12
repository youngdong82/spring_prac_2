package spring_yd.prac_2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String desc;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<>();
}
