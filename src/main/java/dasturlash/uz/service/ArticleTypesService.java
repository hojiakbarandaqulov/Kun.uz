package dasturlash.uz.service;

import dasturlash.uz.entity.ArticleTypesEntity;
import dasturlash.uz.repository.ArticleTypesRepository;
import dasturlash.uz.repository.ArticleTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ArticleTypesService {

    @Autowired
    private ArticleTypesRepository articleTypesRepository;


    public void create(String articleId, List<Integer> typesList) {
        for (Integer typesId : typesList) {
            create(articleId, typesId);
        }
    }

    public void create(String articleId, Integer typesId) {
        ArticleTypesEntity entity = new ArticleTypesEntity();
        entity.setArticleId(articleId);
        entity.setTypesId(typesId);
        articleTypesRepository.save(entity);
    }

    public void merge(String articleId, List<Integer> newList) {
        // newList  3,4,5
        // oldList  1,2,3,4
        /*articleTypesRepository.deleteAllByArticleId(articleId);
        create(articleId, newList);*/
        Objects.requireNonNull(newList);
        List<Integer> oldLists = articleTypesRepository.findAllTypesIdByArticleId(articleId);
        oldLists.forEach(oldId -> {
            if (!newList.contains(oldId)) {
                articleTypesRepository.deleteByArticleIdAndTypesId(articleId, oldId);
            }
        });
        newList.forEach(newId -> {
            if (!oldLists.contains(newId)) {
                create(articleId, newId);
            }
        });
    }

}
