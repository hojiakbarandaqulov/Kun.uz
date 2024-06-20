package dasturlash.uz.repository.customRepository;

import dasturlash.uz.dto.filter.CommentFilterDTO;
import dasturlash.uz.dto.response.FilterResponseDTO;
import dasturlash.uz.entity.CommentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentCustomRepository {
    private final EntityManager entityManager;

    public CommentCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public FilterResponseDTO<CommentEntity> filter(CommentFilterDTO filter, int page, int size) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder query = new StringBuilder();
//        if (filter.getId() !=null){
//            query.append(" and id = :id ");
//            params.put("id", filter.getId());
//        }
        if (filter.getCreated_date() != null){
            query.append(" and created_date = :created_date ");
            params.put("created_date", filter.getCreated_date());
        }

        if (filter.getUpdate_date() != null){
            query.append(" and updated_date = :updated_date ");
            params.put("updated_date", filter.getUpdate_date());
        }

        if (filter.getProfile_id() != null){
            query.append(" and profile_id = :profile_id ");
            params.put("profile_id", filter.getProfile_id());
        }

        if (filter.getContent() != null){
            query.append(" and comment like :comment ");
            params.put("comment", filter.getContent());
        }

        if (filter.getArticle_id() != null){
            query.append(" and article_id = :article_id ");
            params.put("article_id", filter.getArticle_id());
        }

        if (filter.getReply_id() != null){
            query.append(" and reply_id = :reply_id ");
            params.put("reply_id", filter.getReply_id());
        }

        if (filter.getVisible() != null){
            query.append(" and visible = :visible ");
            params.put("visible", filter.getVisible());
        }

        StringBuilder selectSql=new StringBuilder("From CommentEntity c where c.visible = true ");
        StringBuilder countSql=new StringBuilder("select count(c) from CommentEntity c where c.visible = true ");

        selectSql.append(query);
        countSql.append(query);

        Query selectQuery = entityManager.createQuery(selectSql.toString());
        Query countQuery = entityManager.createQuery(countSql.toString());

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            selectQuery.setParameter(entry.getKey(), entry.getValue());
            countQuery.setParameter(entry.getKey(), entry.getValue());
        }
        selectQuery.setFirstResult(page * size);
        selectQuery.setMaxResults(size);
        List commentList = selectQuery.getResultList();

        Long count = (Long) countQuery.getSingleResult();
        return new FilterResponseDTO<CommentEntity>(commentList, count);

    }

}
