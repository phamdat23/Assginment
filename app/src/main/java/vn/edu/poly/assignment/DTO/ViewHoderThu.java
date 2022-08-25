package vn.edu.poly.assignment.DTO;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.poly.assignment.R;

public class ViewHoderThu extends RecyclerView.ViewHolder {
    public TextView tvNameLoaiThu, tvidLoaiThu;
    public ImageView imgUpdateLoaiThu;
    public ImageView imgDeleteThu;

    public TextView tvDate;
    public TextView tvNameKhoanThu;
    public TextView tvMoney;
    public TextView tvFullName;
    public ImageView imgUpdateKhoan;
    public ImageView imgDeleteKhoan;
    public ImageView imgChiTiet;

    public TextView tvDateThongKe;
    public TextView tvNameKhoanThongKe;
    public TextView tvTienKhoanThongKe;

    public ViewHoderThu(@NonNull View itemView) {
        super(itemView);
        //item loại thu
        tvidLoaiThu = itemView.findViewById(R.id.tv_id_loai_thu);
        tvNameLoaiThu = (TextView) itemView.findViewById(R.id.tv_name_loai_thu);
        imgUpdateLoaiThu = (ImageView) itemView.findViewById(R.id.img_update_thu);
        imgDeleteThu = (ImageView) itemView.findViewById(R.id.img_delete_thu);


        // item khoản thu
        tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        tvNameKhoanThu = (TextView) itemView.findViewById(R.id.tv_name_khoan_thu);
        tvMoney = (TextView) itemView.findViewById(R.id.tv_money);
        tvFullName = (TextView) itemView.findViewById(R.id.tv_fullName);
        imgUpdateKhoan = (ImageView) itemView.findViewById(R.id.img_update_khoan);
        imgDeleteKhoan = (ImageView) itemView.findViewById(R.id.img_delete_khoan);
        imgChiTiet = (ImageView) itemView.findViewById(R.id.img_chi_tiet);

        // item thống kê thu
        tvDateThongKe = itemView.findViewById(R.id.tv_date_thong_ke);
        tvNameKhoanThongKe = itemView.findViewById(R.id.tv_name_khoan_thong_ke);
        tvTienKhoanThongKe = itemView.findViewById(R.id.tv_tien_khoan_thong_ke);

    }
}
