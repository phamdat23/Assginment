package vn.edu.poly.assignment.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vn.edu.poly.assignment.DAO.DAO_Khoan_Chi;
import vn.edu.poly.assignment.DAO.DAO_Loai_Chi;
import vn.edu.poly.assignment.DTO.DTO_Khoan_Chi;
import vn.edu.poly.assignment.DTO.DTO_Loai_Chi;
import vn.edu.poly.assignment.DTO.ViewHoderChi;
import vn.edu.poly.assignment.R;

public class Adapter_Khoan_Chi extends RecyclerView.Adapter<ViewHoderChi> {
    ArrayList<DTO_Khoan_Chi> list_KChi;
    DAO_Khoan_Chi daoKhoanChi;
    private EditText etNameKhoanChiUp;
    private Spinner spinnerLoaiChiUp;
    private EditText etDateChiUp;
    private ImageView imgDateChiUp;
    private EditText etMoneyChiUp;
    private EditText etFullnameChiUp;
    private EditText etNoteChiUp;
    private Button btnUpdateKhoanChi;
    Context context;
    ArrayList<DTO_Loai_Chi> list_lChi;
    DAO_Loai_Chi daoLoaiChi;
    String newDate;

    public Adapter_Khoan_Chi(ArrayList<DTO_Khoan_Chi> list_KChi, DAO_Khoan_Chi daoKhoanChi) {
        this.list_KChi = list_KChi;
        this.daoKhoanChi = daoKhoanChi;
    }

    @NonNull
    @Override
    public ViewHoderChi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.item_chi, parent, false);
        ViewHoderChi viewHoderChi = new ViewHoderChi(row);
        daoKhoanChi = new DAO_Khoan_Chi(parent.getContext());
        context = parent.getContext();
        daoLoaiChi= new DAO_Loai_Chi(context);
        return viewHoderChi;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderChi holder, int position) {
        DTO_Khoan_Chi obj = list_KChi.get(position);
        final int index = position;
        daoKhoanChi.opend();
        daoLoaiChi.opend();
        list_lChi= daoLoaiChi.getAll_Loai_chi();
        holder.tvDate.setText("Ngày:"+obj.getNgay_chi());
        holder.tvNameKhoanChi.setText(obj.getName_khoan_chi());
        holder.tvMoney.setText(obj.getSo_tien() +"$");
        holder.tvFullName.setText(obj.getHo_ten());
        holder.imgDeleteKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(dialog.getContext());
                builder.setMessage("Bạn có muốn xóa " + obj.getName_khoan_chi() + " không");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int res = daoKhoanChi.delete_khoan_chi(obj);
                        if (res > 0) {
                            list_KChi.remove(index);
                            notifyDataSetChanged();
                            Toast.makeText(v.getContext(), "Xóa " + obj.getName_khoan_chi() + " thành công", Toast.LENGTH_SHORT).show();
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
                dialog.setCancelable(false);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        holder.imgChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_chi_tiet_item);
                TextView tvDates = dialog.findViewById(R.id.tv_dates);
                TextView tvNameLoai = dialog.findViewById(R.id.tv_name_loai);
                TextView tvNameKhoan = dialog.findViewById(R.id.tv_name_khoan);
                TextView tvMoneys = dialog.findViewById(R.id.tv_moneys);
                TextView fullNames = dialog.findViewById(R.id.fullNames);
                TextView tvNote = dialog.findViewById(R.id.tv_note);
                tvDates.setText("Ngày:"+obj.getNgay_chi());
                tvNameLoai.setText("ID loại:" + obj.getId_loai_chi() + "");
                tvMoneys.setText(obj.getSo_tien() + "đ");
                fullNames.setText("Tên: "+obj.getHo_ten());
                tvNote.setText("Ghi chú: "+obj.getGhi_chu());
                tvNameKhoan.setText(obj.getName_khoan_chi()+":");
                dialog.show();
            }
        });
        holder.imgUpdateKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_update_khoan_chi);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog_update);
                etNameKhoanChiUp = (EditText) dialog.findViewById(R.id.et_name_khoan_chi_up);
                spinnerLoaiChiUp = (Spinner) dialog.findViewById(R.id.spinner_loai_chi_up);
                etDateChiUp = (EditText) dialog.findViewById(R.id.et_date_chi_up);
                imgDateChiUp = (ImageView) dialog.findViewById(R.id.img_date_chi_up);
                etMoneyChiUp = (EditText) dialog.findViewById(R.id.et_money_chi_up);
                etFullnameChiUp = (EditText) dialog.findViewById(R.id.et_fullname_chi_up);
                etNoteChiUp = (EditText) dialog.findViewById(R.id.et_note_chi_up);
                btnUpdateKhoanChi = (Button) dialog.findViewById(R.id.btn_update_khoan_chi);
                etNameKhoanChiUp.setText(obj.getName_khoan_chi());
                etMoneyChiUp.setText(obj.getSo_tien()+"");
                etNoteChiUp.setText(obj.getGhi_chu());
                etFullnameChiUp.setText(obj.getHo_ten());
                etDateChiUp.setText(obj.getNgay_chi());
                imgDateChiUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogDate();
                    }
                });
                AdapterSpinnerLoaiChi adapterSpinnerLoaiChi = new AdapterSpinnerLoaiChi(list_lChi);
                spinnerLoaiChiUp.setAdapter(adapterSpinnerLoaiChi);
                for(int i=0; i< list_lChi.size();i++){
                    if(obj.getId()==list_lChi.get(i).getId()){
                        spinnerLoaiChiUp.setSelection(i);
                        spinnerLoaiChiUp.setSelected(true);
                    }
                }
                DTO_Loai_Chi objLChi = (DTO_Loai_Chi) spinnerLoaiChiUp.getSelectedItem();
                btnUpdateKhoanChi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if( checkLoi()==true){
                            Date objdate2 = new Date(System.currentTimeMillis());
                            android.text.format.DateFormat dateFormat2 = new DateFormat();
                            String dates2 = etDateChiUp.getText().toString();
                            SimpleDateFormat Format2 = new SimpleDateFormat("dd-mm-yyyy");
                            try {
                                Date obj = Format2.parse(dates2);
                                newDate = (String) dateFormat2.format("yyyy-mm-dd", obj);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            obj.setName_khoan_chi(etNameKhoanChiUp.getText().toString());
                            obj.setHo_ten(etFullnameChiUp.getText().toString());
                            obj.setGhi_chu(etNoteChiUp.getText().toString());
                            obj.setSo_tien(Double.parseDouble(etMoneyChiUp.getText().toString()));
                            obj.setNgay_chi(newDate);
                            obj.setId_loai_chi(objLChi.getId());
                            int res = daoKhoanChi.update_khoan_chi(obj);
                            if(res>0){
                                list_KChi.set(index,obj);
                                notifyDataSetChanged();
                                Toast.makeText(context,"Thêm mới thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(context,"Thêm mới thành công", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(context,"Một số trường vẫn còn trống hãy nhập vào", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            dialog.show();
            }
        });
    }

    public void dialogDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int days = dayOfMonth;
                int months = month;
                int years = year;
                etDateChiUp.setText(days+ "-" + (months + 1) + "-"+ years);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public boolean checkLoi() {
        boolean a = true;
        if (etNameKhoanChiUp.getText().toString().trim().equals("")) {
            a = false;
        } else {
            a = true;
        }
        if (etMoneyChiUp.getText().toString().trim().equals("")) {
            a = false;
        } else {
            a = true;
        }
        if (etFullnameChiUp.getText().toString().trim().equals("")) {
            a = false;
        } else {
            a = true;
        }
        return a;
    }

    @Override
    public int getItemCount() {
        return list_KChi == null ? 0 : list_KChi.size();
    }
}
