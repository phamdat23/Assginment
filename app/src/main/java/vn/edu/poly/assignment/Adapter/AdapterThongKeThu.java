package vn.edu.poly.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.poly.assignment.DAO.DAO_Khoan_Thu;
import vn.edu.poly.assignment.DAO.ThongKeThu;
import vn.edu.poly.assignment.DTO.DTO_ThongKeThu;
import vn.edu.poly.assignment.DTO.ViewHoderThu;
import vn.edu.poly.assignment.Fragment.Thong_Ke_ThuFragment;
import vn.edu.poly.assignment.R;

public class AdapterThongKeThu extends RecyclerView.Adapter<ViewHoderThu> {
    ArrayList<DTO_ThongKeThu> listThongKeThu;
    DAO_Khoan_Thu daoKhoanThu;
    Context context;
    ThongKeThu thongKeThu;
    String start, end;

    public AdapterThongKeThu(ArrayList<DTO_ThongKeThu> listThongKeThu, DAO_Khoan_Thu daoKhoanThu, Context context, ThongKeThu thongKeThu, String start, String end) {
        this.listThongKeThu = listThongKeThu;
        this.daoKhoanThu = daoKhoanThu;
        this.context = context;
        this.thongKeThu = thongKeThu;
        this.start = start;
        this.end = end;
    }

    @NonNull
    @Override
    public ViewHoderThu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.item_thong_ke, parent, false);
        ViewHoderThu viewHoderThu = new ViewHoderThu(row);
        daoKhoanThu = new DAO_Khoan_Thu(parent.getContext());
        context = parent.getContext();

        return viewHoderThu;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderThu holder, int position) {
        daoKhoanThu.opend();
        thongKeThu= new ThongKeThu( context);
        listThongKeThu = thongKeThu.ThongKe(context,start, end);
        final int index = position;
        DTO_ThongKeThu obj = listThongKeThu.get(index);
        holder.tvDateThongKe.setText("Ngày: "+obj.getNgay_thu()+"");
        holder.tvNameKhoanThongKe.setText("Tên khoản thu: "+obj.getName_khoan_thu()+"");
        holder.tvTienKhoanThongKe.setText(obj.getSo_tien()+"$");

    }

    @Override
    public int getItemCount() {
        return listThongKeThu.size();
    }
}
