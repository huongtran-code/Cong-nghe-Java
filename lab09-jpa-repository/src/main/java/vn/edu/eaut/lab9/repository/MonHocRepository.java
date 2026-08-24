package vn.edu.eaut.lab9.repository;

import jakarta.persistence.EntityManager;
import vn.edu.eaut.lab9.model.MonHoc;
import java.util.List;

public class MonHocRepository extends BaseRepository<MonHoc, Integer> {
    public MonHocRepository() {
        super(MonHoc.class);
    }

    public List<MonHoc> findByPage(String keyword, int page, int pageSize) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT m FROM MonHoc m WHERE (:kw IS NULL OR LOWER(m.maMon) LIKE LOWER(:kw) OR LOWER(m.tenMon) LIKE LOWER(:kw)) ORDER BY m.id DESC";
            var query = em.createQuery(jpql, MonHoc.class);
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
            String jpql = "SELECT COUNT(m) FROM MonHoc m WHERE (:kw IS NULL OR LOWER(m.maMon) LIKE LOWER(:kw) OR LOWER(m.tenMon) LIKE LOWER(:kw))";
            var query = em.createQuery(jpql, Long.class);
            query.setParameter("kw", (keyword == null || keyword.trim().isEmpty()) ? null : "%" + keyword.trim() + "%");
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
}