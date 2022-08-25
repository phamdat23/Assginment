package vn.edu.poly.assignment.Fragment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vn.edu.poly.assignment.Adapter.AdapterSpinnerLoaiThu;
import vn.edu.poly.assignment.Adapter.Adapter_Khoan_Thu;
import vn.edu.poly.assignment.DAO.DAO_Khoan_Thu;
import vn.edu.poly.assignment.DAO.DAO_Loai_Thu;
import vn.edu.poly.assignment.DTO.DTO_Khoan_Thu;
import vn.edu.poly.assignment.DTO.DTO_Loai_Thu;
import vn.edu.poly.assignment.R;

public class Fragment_Khoan_Thu extends Fragment {
    ArrayList<DTO_Khoan_Thu> listKThu;
    DAO_Khoan_Thu daoKhoanThu;
    Adapter_Khoan_Thu adapterKhoanThu;
    private RecyclerView rcvListKThu;
    private EditText etNameKhoanThuInsert;
    private Spinner spinnerLoaiThuInsert;
    private EditText etDateThuInsert;
    private ImageView imgDateThuInsert;
    private EditText etMoneyThuInsert;
    private EditText etFullnameThuInsert;
    private EditText etNoteThuInsert;
    private Button btnInsertKthu;
    private FloatingActionButton fabInsertKhoanThu;
    ArrayList<DTO_Loai_Thu> listLoaiThu;
    DAO_Loai_Thu daoLoaiThu;
    String newDate;


    public Fragment_Khoan_Thu() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragemt_khoan_thu, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvListKThu = (RecyclerView) view.findViewById(R.id.rcv_list_KThu);
        daoKhoanThu = new DAO_Khoan_Thu(getContext());
        daoLoaiThu= new DAO_Loai_Thu(getContext());
        daoKhoanThu.opend();
        listKThu = daoKhoanThu.getAll_khoan_thu();
        adapterKhoanThu = new Adapter_Khoan_Thu(listKThu, daoKhoanThu);
        rcvListKThu.setAdapter(adapterKhoanThu);
        DTO_Khoan_Thu obj = new DTO_Khoan_Thu();
        fabInsertKhoanThu = (FloatingActionButton) view.findViewById(R.id.fab_insert_khoan_thu);
        fabInsertKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_insert_khoan_thu);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog_update);
                etNameKhoanThuInsert = (EditText) dialog.findViewById(R.id.et_name_khoan_thu_insert);
                spinnerLoaiThuInsert = (Spinner) dialog.findViewById(R.id.spinner_loai_thu_insert);
                etDateThuInsert = (EditText) dialog.findViewById(R.id.et_date_thu_insert);
                imgDateThuInsert = (ImageView) dialog.findViewById(R.id.img_date_thu_insert);
                etMoneyThuInsert = (EditText) dialog.findViewById(R.id.et_money_thu_insert);
                etFullnameThuInsert = (EditText) dialog.findViewById(R.id.et_fullname_thu_insert);
                etNoteThuInsert = (EditText) dialog.findViewById(R.id.et_note_thu_insert);
                btnInsertKthu = (Button) dialog.findViewById(R.id.btn_insert_Kthu);
                daoLoaiThu.opend();
                listLoaiThu= daoLoaiThu.getAll_Loai_thu();
                AdapterSpinnerLoaiThu adapterSpinnerLoaiThu = new AdapterSpinnerLoaiThu(listLoaiThu);
                spinnerLoaiThuInsert.setAdapter(adapterSpinnerLoaiThu);
                imgDateThuInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogDate();

                    }
                });
                DTO_Loai_Thu  objs = (DTO_Loai_Thu) spinnerLoaiThuInsert.getSelectedItem();
                btnInsertKthu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkLoi();
                        if (checkLoi() == true) {
                            Date objdate = new Date(System.currentTimeMillis());
                            android.text.format.DateFormat dateFormat = new DateFormat();
                            String dates = etDateThuInsert.getText().toString();
                            SimpleDateFormat Format = new SimpleDateFormat("dd-mm-yyyy");
                            try {
                                Date obj = Format.parse(dates);
                                newDate = (String) dateFormat.format("yyyy-mm-dd", obj);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            obj.setName_khoan_thu(etNameKhoanThuInsert.getText().toString());
                            obj.setHo_ten(etFullnameThuInsert.getText().toString());
                            obj.setId_loai_thu(objs.getIdLT());
                            obj.setGhi_chu(etNoteThuInsert.getText().toString());
                            obj.setNgay_thu(newDate+"");
                            obj.setSo_tien(Double.parseDouble(etMoneyThuInsert.getText().toString()));
                            long res = daoKhoanThu.insert_khoan_thu(obj);
                            if(res>0){
                                listKThu.clear();
                                listKThu.addAll(daoKhoanThu.getAll_khoan_thu());
                                adapterKhoanThu.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Thêm mới thành công",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(getContext(), "Lỗi",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getContext(), "Một số trường vẫn còn trống hãy nhập vào", Toast.LENGTH_SHORT).show();
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int days = dayOfMonth;
                int months = month;
                int years = year;
                etDateThuInsert.setText(days+ "-" + (months + 1) + "-" +years);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
    public boolean checkLoi() {
        boolean a = true;
        if (etNameKhoanThuInsert.getText().toString().trim().equals("")) {
            a = false;
        } else {
            a = true;
        }
        if(etDateThuInsert.getText().toString().equals("")){
            a=false;
        }else{
            a= true;
        }
        if (etMoneyThuInsert.getText().toString().trim().equals("")) {
            a = false;
        } else {
            a = true;
        }
        if (etFullnameThuInsert.getText().toString().trim().equals("")) {
            a = false;
        } else {
            a = true;
        }

        return a;
    }
}
