CREATE DATABASE IF NOT EXISTS minishop_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE minishop_db;

CREATE TABLE IF NOT EXISTS san_pham (
    ma_sp INT AUTO_INCREMENT PRIMARY KEY,
    ten_sp VARCHAR(100) NOT NULL,
    don_gia DECIMAL(12,2) NOT NULL,
    so_luong INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS khach_hang (
    ma_kh INT AUTO_INCREMENT PRIMARY KEY,
    ten_kh VARCHAR(100) NOT NULL,
    sdt VARCHAR(10) NOT NULL,
    dia_chi VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS hoa_don (
    ma_hd INT AUTO_INCREMENT PRIMARY KEY,
    ngay_lap DATE NOT NULL,
    ma_kh INT NOT NULL,
    tong_tien DECIMAL(12,2) DEFAULT 0,
    FOREIGN KEY (ma_kh) REFERENCES khach_hang(ma_kh)
);

CREATE TABLE IF NOT EXISTS chi_tiet_hoa_don (
    ma_hd INT NOT NULL,
    ma_sp INT NOT NULL,
    so_luong INT NOT NULL,
    don_gia DECIMAL(12,2) NOT NULL,
    thanh_tien DECIMAL(12,2) NOT NULL,
    PRIMARY KEY (ma_hd, ma_sp),
    FOREIGN KEY (ma_hd) REFERENCES hoa_don(ma_hd),
    FOREIGN KEY (ma_sp) REFERENCES san_pham(ma_sp)
);

-- Insert sample data if the tables are empty
INSERT INTO san_pham(ten_sp, don_gia, so_luong) 
SELECT 'Ban phim Logitech K120', 180000, 50
WHERE NOT EXISTS (SELECT 1 FROM san_pham LIMIT 1);

INSERT INTO san_pham(ten_sp, don_gia, so_luong) 
SELECT 'Chuot khong day Rapoo', 220000, 40
WHERE NOT EXISTS (SELECT 1 FROM san_pham WHERE ma_sp = 2);

INSERT INTO san_pham(ten_sp, don_gia, so_luong) 
SELECT 'USB Kingston 32GB', 150000, 100
WHERE NOT EXISTS (SELECT 1 FROM san_pham WHERE ma_sp = 3);

INSERT INTO san_pham(ten_sp, don_gia, so_luong) 
SELECT 'Tai nghe Sony Basic', 350000, 30
WHERE NOT EXISTS (SELECT 1 FROM san_pham WHERE ma_sp = 4);


INSERT INTO khach_hang(ten_kh, sdt, dia_chi) 
SELECT 'Nguyen Van An', '0912345678', 'Ha Noi'
WHERE NOT EXISTS (SELECT 1 FROM khach_hang LIMIT 1);

INSERT INTO khach_hang(ten_kh, sdt, dia_chi) 
SELECT 'Tran Thi Binh', '0987654321', 'Bac Ninh'
WHERE NOT EXISTS (SELECT 1 FROM khach_hang WHERE ma_kh = 2);

INSERT INTO khach_hang(ten_kh, sdt, dia_chi) 
SELECT 'Le Van Cuong', '0901111222', 'Hai Duong'
WHERE NOT EXISTS (SELECT 1 FROM khach_hang WHERE ma_kh = 3);
