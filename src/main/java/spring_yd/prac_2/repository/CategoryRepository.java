package spring_yd.prac_2.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import spring_yd.prac_2.domain.Category;

import javax.persistence.EntityManager;

@Repository
@AllArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    //id로 조회
    public Category getCategoryById(Long categoryId){
        return em.find(Category.class, categoryId);
    }

}
