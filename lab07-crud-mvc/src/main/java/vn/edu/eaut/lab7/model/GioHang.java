package vn.edu.eaut.lab7.model;

import java.util.ArrayList;
import java.util.List;

public class GioHang {
    private final List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(SanPham sp, int soLuong) {
        for (CartItem item : items) {
            if (item.getSanPham().getId() == sp.getId()) {
                item.setSoLuong(item.getSoLuong() + soLuong);
                return;
            }
        }
        items.add(new CartItem(sp, soLuong));
    }

    public void updateItem(int sanPhamId, int soLuong) {
        if (soLuong <= 0) {
            removeItem(sanPhamId);
            return;
        }
        for (CartItem item : items) {
            if (item.getSanPham().getId() == sanPhamId) {
                item.setSoLuong(soLuong);
                return;
            }
        }
    }

    public void removeItem(int sanPhamId) {
        items.removeIf(item -> item.getSanPham().getId() == sanPhamId);
    }

    public void clear() {
        items.clear();
    }

    public double getTongTien() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getThanhTien();
        }
        return total;
    }

    public int getTongSoLuong() {
        int count = 0;
        for (CartItem item : items) {
            count += item.getSoLuong();
        }
        return count;
    }
}
