package vn.edu.eaut.lab5.bus;

import vn.edu.eaut.lab5.dal.HoaDonDAL;
import vn.edu.eaut.lab5.model.ChiTietHoaDon;

import java.sql.SQLException;
import java.util.List;

public class HoaDonBUS {
    private final HoaDonDAL hoaDonDAL = new HoaDonDAL();

    public int lapHoaDon(int maKh, List<ChiTietHoaDon> chiTietList) throws SQLException {
        if (maKh <= 0) {
            throw new IllegalArgumentException("Vui lòng chọn khách hàng!");
        }
        if (chiTietList == null || chiTietList.isEmpty()) {
            throw new IllegalArgumentException("Hóa đơn phải có ít nhất 1 sản phẩm!");
        }
        return hoaDonDAL.insertHoaDon(maKh, chiTietList);
    }
}
