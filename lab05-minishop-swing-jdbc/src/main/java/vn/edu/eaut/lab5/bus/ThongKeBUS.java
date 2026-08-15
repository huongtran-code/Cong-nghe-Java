package vn.edu.eaut.lab5.bus;

import vn.edu.eaut.lab5.dal.ThongKeDAL;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

public class ThongKeBUS {
    private final ThongKeDAL thongKeDAL = new ThongKeDAL();

    public BigDecimal tinhDoanhThu(LocalDate tuNgay, LocalDate denNgay) throws SQLException {
        if (tuNgay == null || denNgay == null) {
            throw new IllegalArgumentException("Vui lòng chọn đầy đủ từ ngày và đến ngày");
        }
        if (tuNgay.isAfter(denNgay)) {
            throw new IllegalArgumentException("Từ ngày không được lớn hơn đến ngày");
        }
        return thongKeDAL.tinhDoanhThu(tuNgay, denNgay);
    }

    public String layHoaDonCaoNhat() throws SQLException {
        return thongKeDAL.layHoaDonCaoNhat();
    }

    public String laySanPhamBanChayNhat() throws SQLException {
        return thongKeDAL.laySanPhamBanChayNhat();
    }
}
