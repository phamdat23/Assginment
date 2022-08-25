package vn.edu.poly.assignment.DTO;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.poly.assignment.R;

public class ViewHoderChi extends RecyclerView.ViewHolder {
    public TextView tvNameLoaiChi,tvIdLoaiChi;
    public ImageView imgUpdate;
    public ImageView imgDelete;
    public TextView tvDate;
    public TextView tvNameKhoanChi;
    public TextView tvMoney;
    public TextView tvFullName;
    public ImageView imgUpdateKhoan;
    public ImageView imgDeleteKhoan;
    public ImageView imgChiTiet;
    public TextView tvDateThongKe;
    public TextView tvNameKhoanThongKe;
    public TextView tvTienKhoanThongKe;
    public ViewHoderChi(@NonNull View itemView) {
        super(itemView);
        // item loại chi
        tvIdLoaiChi= itemView.findViewById(R.id.tv_id_loai_chi);
        tvNameLoaiChi = (TextView) itemView.findViewById(R.id.tv_name_loai_chi);
        imgUpdate = (ImageView) itemView.findViewById(R.id.img_update);
        imgDelete = (ImageView) itemView.findViewById(R.id.img_delete);
        // item khoản chi
        tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        tvNameKhoanChi = (TextView) itemView.findViewById(R.id.tv_name_khoan_chi);
        tvMoney = (TextView) itemView.findViewById(R.id.tv_money);
        tvFullName = (TextView) itemView.findViewById(R.id.tv_fullName);
        imgUpdateKhoan = (ImageView) itemView.findViewById(R.id.img_update_khoan);
        imgDeleteKhoan = (ImageView) itemView.findViewById(R.id.img_delete_khoan);
        imgChiTiet = (ImageView) itemView.findViewById(R.id.img_chi_tiet);
        //item thống kê chi
        tvDateThongKe = itemView.findViewById(R.id.tv_date_thong_ke);
        tvNameKhoanThongKe = itemView.findViewById(R.id.tv_name_khoan_thong_ke);
        tvTienKhoanThongKe = itemView.findViewById(R.id.tv_tien_khoan_thong_ke);

    }
}
