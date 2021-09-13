package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository // added annotation
public class CommentRepository {


    @PersistenceUnit(unitName = "imageHoster")

    private EntityManagerFactory emf;

    public Comment createComment(Comment comment){

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return comment;
    }

    //Execution of JPQL query to get all comments
    public List<Comment> getAllComments(Integer imageId) {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Comment> typedQuery = em.createQuery("SELECT c from Comment c where c.image.id =:imageId", Comment.class).setParameter("imageId", imageId);
            List<Comment> resultList = typedQuery.getResultList();
            return resultList;
        } catch (NoResultException nre){
            return null;
        }
    }

}





