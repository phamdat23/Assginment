package vn.edu.poly.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.poly.assignment.DAO.DAO_Khoan_Chi;
import vn.edu.poly.assignment.DAO.ThongKeChi;
import vn.edu.poly.assignment.DAO.ThongKeThu;
import vn.edu.poly.assignment.DTO.DTO_ThongKeChi;
import vn.edu.poly.assignment.DTO.ViewHoderChi;
import vn.edu.poly.assignment.R;

public class AdapterThongKeChi extends RecyclerView.Adapter<ViewHoderChi> {
    Context context;
    ThongKeChi thongKeChi;
    String start, end;
    DAO_Khoan_Chi daoKhoanChi;
    ArrayList<DTO_ThongKeChi> listThongKeChi;

    public AdapterThongKeChi(Context context, ThongKeChi thongKeChi, String start, String end, DAO_Khoan_Chi daoKhoanChi, ArrayList<DTO_ThongKeChi> listThongKeChi) {
        this.context = context;
        this.thongKeChi = thongKeChi;
        this.start = start;
        this.end = end;
        this.daoKhoanChi = daoKhoanChi;
        this.listThongKeChi = listThongKeChi;
    }

    @NonNull
    @Override
    public ViewHoderChi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.item_thong_ke_chi, parent, false);
        ViewHoderChi viewHoderChi = new ViewHoderChi(row);
        context = parent.getContext();
        daoKhoanChi= new DAO_Khoan_Chi(context);
        return viewHoderChi;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderChi holder, int position) {
        daoKhoanChi.opend();
        thongKeChi= new ThongKeChi(context);
        int index = position;
        listThongKeChi = thongKeChi.ThongKe(context,start,end);
        DTO_ThongKeChi obj = listThongKeChi.get(index);
        holder.tvDateThongKe.setText("Ngày: "+obj.getNgay_chi()+"");
        holder.tvNameKhoanThongKe.setText("Tên khoản thu: "+obj.getName_khoan_chi()+"");
        holder.tvTienKhoanThongKe.setText(obj.getSo_tien()+"$");

    }

    @Override
    public int getItemCount() {
        return listThongKeChi.size();
    }
}
