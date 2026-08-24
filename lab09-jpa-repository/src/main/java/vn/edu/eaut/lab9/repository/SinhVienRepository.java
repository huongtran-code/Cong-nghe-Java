package vn.edu.eaut.lab9.repository;

import jakarta.persistence.EntityManager;
import vn.edu.eaut.lab9.config.JPAUtil;
import vn.edu.eaut.lab9.model.SinhVien;
import java.util.List;

public class SinhVienRepository extends BaseRepository<SinhVien, Integer> {
    public SinhVienRepository() {
        super(SinhVien.class);
    }

    public List<SinhVien> searchAndPaginate(String keyword, int page, int pageSize) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            String jpql = "SELECT s FROM SinhVien s WHERE LOWER(s.hoTen) LIKE :kw OR LOWER(s.maSinhVien) LIKE :kw ORDER BY s.id DESC";
            return em.createQuery(jpql, SinhVien.class)
                    .setParameter("kw", "%" + (keyword == null ? "" : keyword.toLowerCase()) + "%")
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public long countSearch(String keyword) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            String jpql = "SELECT COUNT(s) FROM SinhVien s WHERE LOWER(s.hoTen) LIKE :kw OR LOWER(s.maSinhVien) LIKE :kw";
            return em.createQuery(jpql, Long.class)
                    .setParameter("kw", "%" + (keyword == null ? "" : keyword.toLowerCase()) + "%")
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}