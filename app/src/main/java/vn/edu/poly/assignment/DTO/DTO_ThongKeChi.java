package vn.edu.poly.assignment.DTO;

public class DTO_ThongKeChi {
    private int id, id_loai_chi;
    private String name_khoan_chi, ngay_chi;
    private double so_tien;
    private String ghi_chu, ho_ten;
    private int countChi;
    private double sumChi;

    public DTO_ThongKeChi() {
    }

    public DTO_ThongKeChi(int id, int id_loai_chi, String name_khoan_chi, String ngay_chi, double so_tien, String ghi_chu, String ho_ten, int countChi, double sumChi) {
        this.id = id;
        this.id_loai_chi = id_loai_chi;
        this.name_khoan_chi = name_khoan_chi;
        this.ngay_chi = ngay_chi;
        this.so_tien = so_tien;
        this.ghi_chu = ghi_chu;
        this.ho_ten = ho_ten;
        this.countChi = countChi;
        this.sumChi = sumChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_loai_chi() {
        return id_loai_chi;
    }

    public void setId_loai_chi(int id_loai_chi) {
        this.id_loai_chi = id_loai_chi;
    }

    public String getName_khoan_chi() {
        return name_khoan_chi;
    }

    public void setName_khoan_chi(String name_khoan_chi) {
        this.name_khoan_chi = name_khoan_chi;
    }

    public String getNgay_chi() {
        return ngay_chi;
    }

    public void setNgay_chi(String ngay_chi) {
        this.ngay_chi = ngay_chi;
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

    public int getCountChi() {
        return countChi;
    }

    public void setCountChi(int countChi) {
        this.countChi = countChi;
    }

    public double getSumChi() {
        return sumChi;
    }

    public void setSumChi(double sumChi) {
        this.sumChi = sumChi;
    }
}
