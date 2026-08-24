package vn.edu.eaut.lab9.repository;

import jakarta.persistence.EntityManager;
import vn.edu.eaut.lab9.model.LopHoc;
import java.util.List;

public class LopHocRepository extends BaseRepository<LopHoc, Integer> {
    public LopHocRepository() {
        super(LopHoc.class);
    }

    public List<LopHoc> findByPage(String keyword, int page, int pageSize) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT l FROM LopHoc l WHERE (:kw IS NULL OR LOWER(l.tenLop) LIKE LOWER(:kw)) ORDER BY l.id DESC";
            var query = em.createQuery(jpql, LopHoc.class);
            query.setParameter("kw", (keyword == null || keyword.trim().isEmpty()) ? null : "%" + keyword.trim() + "%");
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public long countTotal(String keyword) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT COUNT(l) FROM LopHoc l WHERE (:kw IS NULL OR LOWER(l.tenLop) LIKE LOWER(:kw))";
            var query = em.createQuery(jpql, Long.class);
            query.setParameter("kw", (keyword == null || keyword.trim().isEmpty()) ? null : "%" + keyword.trim() + "%");
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
}