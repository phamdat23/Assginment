package vn.edu.poly.assignment.DTO;

public class DTO_Khoan_Thu {
    private int id, id_loai_thu;
    private String name_khoan_thu, ngay_thu;
    private double so_tien;
    private String ghi_chu, ho_ten;

    public DTO_Khoan_Thu() {
    }

    public DTO_Khoan_Thu(int id, int id_loai_thu, String name_khoan_thu, String ngay_thu, double so_tien, String ghi_chu, String ho_ten) {
        this.id = id;
        this.id_loai_thu = id_loai_thu;
        this.name_khoan_thu = name_khoan_thu;
        this.ngay_thu = ngay_thu;
        this.so_tien = so_tien;
        this.ghi_chu = ghi_chu;
        this.ho_ten = ho_ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_loai_thu() {
        return id_loai_thu;
    }

    public void setId_loai_thu(int id_loai_thu) {
        this.id_loai_thu = id_loai_thu;
    }

    public String getName_khoan_thu() {
        return name_khoan_thu;
    }

    public void setName_khoan_thu(String name_khoan_thu) {
        this.name_khoan_thu = name_khoan_thu;
    }

    public String getNgay_thu() {
        return ngay_thu;
    }

    public void setNgay_thu(String ngay_thu) {
        this.ngay_thu = ngay_thu;
    }

    public double getSo_tien() {
        return so_tien;
    }

    public void setSo_tien(double so_tien) {
        this.so_tien = so_tien;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }
}
