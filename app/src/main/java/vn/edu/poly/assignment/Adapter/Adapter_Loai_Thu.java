package vn.edu.poly.assignment.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.poly.assignment.DAO.DAO_Loai_Thu;
import vn.edu.poly.assignment.DTO.DTO_Loai_Thu;
import vn.edu.poly.assignment.DTO.ViewHoderThu;
import vn.edu.poly.assignment.R;

public class Adapter_Loai_Thu extends RecyclerView.Adapter<ViewHoderThu> {
    ArrayList<DTO_Loai_Thu> list_loai_thu = new ArrayList<>();
    DAO_Loai_Thu dao_loai_thu;

    public Adapter_Loai_Thu(ArrayList<DTO_Loai_Thu> list_loai_thu, DAO_Loai_Thu dao_loai_thu) {
        this.list_loai_thu = list_loai_thu;
        this.dao_loai_thu = dao_loai_thu;
    }

    @NonNull
    @Override
    public ViewHoderThu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.item_list, parent, false);
        ViewHoderThu viewHoderThu = new ViewHoderThu(row);
        dao_loai_thu = new DAO_Loai_Thu(parent.getContext());
        return viewHoderThu;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderThu holder, int position) {
        DTO_Loai_Thu obj = list_loai_thu.get(position);
        final int vitri = position;
        dao_loai_thu.opend();
        holder.tvidLoaiThu.setText(obj.getIdLT() + "");
        holder.tvNameLoaiThu.setText(obj.getName());
        holder.imgDeleteThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(dialog.getContext());
                builder.setMessage("Bạn có muốn xóa " + obj.getName() + " không");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int res = dao_loai_thu.delete_loai_thu(obj);
                        if (res > 0) {
                            list_loai_thu.remove(vitri);
                            notifyDataSetChanged();
                            Toast.makeText(v.getContext(), "Xóa " + obj.getName() + " thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(v.getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setCancelable(true);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        holder.imgUpdateLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_update_loai_thu);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog_update);
                EditText et_name_thu_update = dialog.findViewById(R.id.et_name_thu_update);
                Button btn_update_thu = dialog.findViewById(R.id.btn_update_thu);
                TextView thongBaoLoi = dialog.findViewById(R.id.tv_thong_bao_loi);
                et_name_thu_update.setText(obj.getName());
                btn_update_thu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = et_name_thu_update.getText().toString();
                        if (!s.equals("")) {
                            thongBaoLoi.setText("");
                            obj.setName(s);
                            int res = dao_loai_thu.update_loai_thu(obj);
                            if (res > 0) {
                                list_loai_thu.set(vitri, obj);
                                notifyDataSetChanged();
                                Toast.makeText(v.getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(v.getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            thongBaoLoi.setText("Tên loại thu không được để trống");
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_loai_thu.size();
    }
}
