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

import vn.edu.poly.assignment.DAO.DAO_Khoan_Thu;
import vn.edu.poly.assignment.DAO.DAO_Loai_Thu;
import vn.edu.poly.assignment.DTO.DTO_Khoan_Thu;
import vn.edu.poly.assignment.DTO.DTO_Loai_Thu;
import vn.edu.poly.assignment.DTO.ViewHoderThu;
import vn.edu.poly.assignment.R;

public class Adapter_Khoan_Thu extends RecyclerView.Adapter<ViewHoderThu> {
    ArrayList<DTO_Khoan_Thu> listKthu;
    DAO_Khoan_Thu daoKhoanThu;
    EditText etNameKhoanThu, etFullNameThu, etMoneyThu, etNoteThu, etDateThu;
    Spinner spinnerLoaiThu;
    ArrayList<DTO_Loai_Thu> listLoaiThu;
    DAO_Loai_Thu daoLoaiThu;
    Context context;
    String newDate;
    public Adapter_Khoan_Thu(ArrayList<DTO_Khoan_Thu> listKthu, DAO_Khoan_Thu daoKhoanThu) {
        this.listKthu = listKthu;
        this.daoKhoanThu = daoKhoanThu;
    }

    @NonNull
    @Override
    public ViewHoderThu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.item_khoan_thu, parent, false);
        ViewHoderThu viewHoderThu = new ViewHoderThu(row);
        daoKhoanThu = new DAO_Khoan_Thu(parent.getContext());
        daoLoaiThu = new DAO_Loai_Thu(parent.getContext());
        context = parent.getContext();
        return viewHoderThu;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderThu holder, int position) {
        DTO_Khoan_Thu obj = listKthu.get(position);
        final int index = position;
        daoKhoanThu.opend();
        holder.tvDate.setText("Ngày:"+obj.getNgay_thu());
        holder.tvFullName.setText(obj.getHo_ten());
        holder.tvMoney.setText(obj.getSo_tien() + "$");
        holder.tvNameKhoanThu.setText(obj.getName_khoan_thu());
        holder.imgDeleteKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(dialog.getContext());
                builder.setMessage("Bạn có muốn xóa " + obj.getName_khoan_thu() + " không");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int res = daoKhoanThu.delete_khoan_thu(obj);
                        if (res > 0) {
                            listKthu.remove(index);
                            notifyDataSetChanged();
                            Toast.makeText(v.getContext(), "Xóa " + obj.getName_khoan_thu() + " thành công", Toast.LENGTH_SHORT).show();
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
        holder.imgUpdateKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_update_khoan_thu);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog_update);
                etNameKhoanThu = dialog.findViewById(R.id.et_name_khoan_thu);
                etMoneyThu = dialog.findViewById(R.id.et_money_thu);
                etFullNameThu = dialog.findViewById(R.id.et_fullname_thu);
                etNoteThu = dialog.findViewById(R.id.et_note_thu);
                etDateThu = dialog.findViewById(R.id.et_date_thu);
                spinnerLoaiThu = dialog.findViewById(R.id.spinner_loai_thu);
                ImageView imgDateThu = dialog.findViewById(R.id.img_date_thu);
                Button btnUpdateThu = dialog.findViewById(R.id.btn_update_thu);
                etNameKhoanThu.setText(obj.getName_khoan_thu());
                etMoneyThu.setText(obj.getSo_tien() + "");
                etDateThu.setText(obj.getNgay_thu());
                etFullNameThu.setText(obj.getHo_ten());
                etNoteThu.setText(obj.getGhi_chu());
                daoLoaiThu.opend();
                listLoaiThu = daoLoaiThu.getAll_Loai_thu();
                AdapterSpinnerLoaiThu adapterSpinnerLoaiThu = new AdapterSpinnerLoaiThu(listLoaiThu);
                spinnerLoaiThu.setAdapter(adapterSpinnerLoaiThu);
                for (int i = 0; i < listLoaiThu.size(); i++) {
                    DTO_Loai_Thu objLoaiThu = listLoaiThu.get(i);
                    if (objLoaiThu.getIdLT() == obj.getId_loai_thu()) {
                        spinnerLoaiThu.setSelection(i);
                        spinnerLoaiThu.setSelected(true);
                    }
                }
                imgDateThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogDate();


                    }
                });
                DTO_Loai_Thu  objs = (DTO_Loai_Thu) spinnerLoaiThu.getSelectedItem();
                btnUpdateThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkLoi() == true) {
                            Date objdate = new Date(System.currentTimeMillis());
                            android.text.format.DateFormat dateFormat = new DateFormat();
                            String dates = etDateThu.getText().toString();
                            SimpleDateFormat Format = new SimpleDateFormat("dd-mm-yyyy");
                            try {
                                Date obj = Format.parse(dates);
                                newDate = (String) dateFormat.format("yyyy-mm-dd", obj);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            obj.setName_khoan_thu(etNameKhoanThu.getText().toString());
                            obj.setHo_ten(etFullNameThu.getText().toString());
                            obj.setId_loai_thu(objs.getIdLT());
                            obj.setGhi_chu(etNoteThu.getText().toString());
                            obj.setNgay_thu(newDate+"");
                            obj.setSo_tien(Double.parseDouble(etMoneyThu.getText().toString()));
                            int res = daoKhoanThu.update_khoan_thu(obj);
                            if (res > 0) {
                                listKthu.set(index, obj);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Một số trường vẫn còn trống hãy nhập vào", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.show();
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
                tvDates.setText("Ngày: "+obj.getNgay_thu());
                tvNameLoai.setText("ID loại: " + obj.getId_loai_thu());
                tvMoneys.setText(obj.getSo_tien() + "$");
                fullNames.setText("Tên: "+obj.getHo_ten());
                tvNote.setText("Ghi chú: "+obj.getGhi_chu());
                tvNameKhoan.setText(obj.getName_khoan_thu()+":");
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKthu == null ? 0 : listKthu.size();
    }

    public boolean checkLoi() {
        boolean a = true;
        if (etNameKhoanThu.getText().toString().trim().equals("")) {
            a = false;
        } else {
            a = true;
        }
        if (etMoneyThu.getText().toString().trim().equals("")) {
            a = false;
        } else {
            a = true;
        }
        if (etFullNameThu.getText().toString().trim().equals("")) {
            a = false;
        } else {
            a = true;
        }
        return a;
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
                etDateThu.setText(days+ "-" + (months + 1) + "-" +years);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

}
