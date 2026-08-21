package vn.edu.eaut.lab7.model;

public class DiemSinhVien {
    private int id;
    private String maSV;
    private String hoTen;
    private double diemChuyenCan;
    private double diemGiuaKy;
    private double diemCuoiKy;
    private double diemTongKet;
    private String xepLoai;

    public DiemSinhVien() {}

    public DiemSinhVien(int id, String maSV, String hoTen, double diemChuyenCan, double diemGiuaKy, double diemCuoiKy) {
        this.id = id;
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.diemChuyenCan = diemChuyenCan;
        this.diemGiuaKy = diemGiuaKy;
        this.diemCuoiKy = diemCuoiKy;
        tinhTongKet();
    }

    public void tinhTongKet() {
        this.diemTongKet = Math.round((diemChuyenCan * 0.1 + diemGiuaKy * 0.3 + diemCuoiKy * 0.6) * 100.0) / 100.0;
        if (this.diemTongKet >= 8.5) {
            this.xepLoai = "A";
        } else if (this.diemTongKet >= 7.0) {
            this.xepLoai = "B";
        } else if (this.diemTongKet >= 5.5) {
            this.xepLoai = "C";
        } else if (this.diemTongKet >= 4.0) {
            this.xepLoai = "D";
        } else {
            this.xepLoai = "F";
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public double getDiemChuyenCan() { return diemChuyenCan; }
    public void setDiemChuyenCan(double diemChuyenCan) { this.diemChuyenCan = diemChuyenCan; tinhTongKet(); }

    public double getDiemGiuaKy() { return diemGiuaKy; }
    public void setDiemGiuaKy(double diemGiuaKy) { this.diemGiuaKy = diemGiuaKy; tinhTongKet(); }

    public double getDiemCuoiKy() { return diemCuoiKy; }
    public void setDiemCuoiKy(double diemCuoiKy) { this.diemCuoiKy = diemCuoiKy; tinhTongKet(); }

    public double getDiemTongKet() { return diemTongKet; }
    public String getXepLoai() { return xepLoai; }
}
