package vn.edu.poly.assignment.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
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

import vn.edu.poly.assignment.DAO.DAO_Loai_Chi;
import vn.edu.poly.assignment.DTO.DTO_Loai_Chi;
import vn.edu.poly.assignment.DTO.ViewHoderChi;
import vn.edu.poly.assignment.R;

public class Adapter_Loai_Chi extends RecyclerView.Adapter<ViewHoderChi> {
    ArrayList<DTO_Loai_Chi> list_laoi_chi = new ArrayList<>();
    DAO_Loai_Chi daoLoaiChi;
    Context context;

    public Adapter_Loai_Chi(ArrayList<DTO_Loai_Chi> list_laoi_chi, DAO_Loai_Chi daoLoaiChi) {
        this.list_laoi_chi = list_laoi_chi;
        this.daoLoaiChi = daoLoaiChi;
    }

    @NonNull
    @Override
    public ViewHoderChi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.item_loai_chi, parent, false);
        ViewHoderChi viewHoderLoaiChi = new ViewHoderChi(row);
        daoLoaiChi = new DAO_Loai_Chi(parent.getContext());
        context = parent.getContext();
        return viewHoderLoaiChi;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderChi holder, int position) {
        daoLoaiChi.opend();
        final int vitri = position;
        list_laoi_chi = daoLoaiChi.getAll_Loai_chi();
        DTO_Loai_Chi obj = list_laoi_chi.get(vitri);
        holder.tvNameLoaiChi.setText(obj.getName());
        holder.tvIdLoaiChi.setText(obj.getId()+"");
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(dialog.getContext());
                builder.setMessage("Bạn có muốn xóa "+obj.getName()+" không");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int res =daoLoaiChi.delete_loai_chi(obj);
                        if(res>0){
                            list_laoi_chi.remove(vitri);
                            notifyDataSetChanged();
                            Toast.makeText(v.getContext(), "Xóa "+obj.getName()+" thành công", Toast.LENGTH_SHORT).show();
                        }else{
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
        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_update_loai_chi);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog_update);
                EditText et_name_chi_update = dialog.findViewById(R.id.et_name_chi_update);
                TextView tv_thong_bao_loi = dialog.findViewById(R.id.tv_thong_bao_loi);
                Button btn_update_chi = dialog.findViewById(R.id.btn_update_chi);
                et_name_chi_update.setText(obj.getName());
                btn_update_chi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!et_name_chi_update.getText().toString().equals("")){
                            tv_thong_bao_loi.setText("");
                            obj.setName(et_name_chi_update.getText().toString());
                            int res = daoLoaiChi.update_loai_chi(obj);
                            if(res>0){
                                list_laoi_chi.set(vitri, obj);
                                notifyDataSetChanged();
                                Toast.makeText(v.getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(v.getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            tv_thong_bao_loi.setText("Tên loại chi không được để trống");
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_laoi_chi==null?0: list_laoi_chi.size();
    }

}
