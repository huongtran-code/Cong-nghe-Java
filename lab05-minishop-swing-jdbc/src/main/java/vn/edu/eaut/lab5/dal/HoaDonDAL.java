package vn.edu.eaut.lab5.dal;

import vn.edu.eaut.lab5.config.DBHelper;
import vn.edu.eaut.lab5.model.ChiTietHoaDon;
import vn.edu.eaut.lab5.model.HoaDon;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAL {

    public int insertHoaDon(int maKh, List<ChiTietHoaDon> chiTietList) throws SQLException {
        String sqlHoaDon = "INSERT INTO hoa_don(ngay_lap, ma_kh, tong_tien) VALUES (?, ?, ?)";
        String sqlChiTiet = 
            "INSERT INTO chi_tiet_hoa_don(ma_hd, ma_sp, so_luong, don_gia, thanh_tien) " +
            "VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            conn.setAutoCommit(false);
            
            BigDecimal tongTien = tinhTongTien(chiTietList);
            int maHd;
            
            try (PreparedStatement ps = conn.prepareStatement(sqlHoaDon, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDate(1, Date.valueOf(LocalDate.now()));
                ps.setInt(2, maKh);
                ps.setBigDecimal(3, tongTien);
                ps.executeUpdate();
                
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        maHd = rs.getInt(1);
                    } else {
                        throw new SQLException("Khong lay duoc ma hoa don");
                    }
                }
            }
            
            try (PreparedStatement ps = conn.prepareStatement(sqlChiTiet)) {
                for (ChiTietHoaDon ct : chiTietList) {
                    ps.setInt(1, maHd);
                    ps.setInt(2, ct.getMaSp());
                    ps.setInt(3, ct.getSoLuong());
                    ps.setBigDecimal(4, ct.getDonGia());
                    ps.setBigDecimal(5, ct.getThanhTien());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            conn.commit();
            return maHd;
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
    
    private BigDecimal tinhTongTien(List<ChiTietHoaDon> chiTietList) {
        BigDecimal tong = BigDecimal.ZERO;
        for (ChiTietHoaDon ct : chiTietList) {
            tong = tong.add(ct.getThanhTien());
        }
        return tong;
    }
    
    public List<HoaDon> findAll() throws SQLException {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT hd.ma_hd, hd.ngay_lap, hd.ma_kh, kh.ten_kh, hd.tong_tien " +
                     "FROM hoa_don hd JOIN khach_hang kh ON hd.ma_kh = kh.ma_kh";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHd(rs.getInt("ma_hd"));
                hd.setNgayLap(rs.getDate("ngay_lap").toLocalDate());
                hd.setMaKh(rs.getInt("ma_kh"));
                hd.setTenKh(rs.getString("ten_kh"));
                hd.setTongTien(rs.getBigDecimal("tong_tien"));
                list.add(hd);
            }
        }
        return list;
    }
}
