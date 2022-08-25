package vn.edu.poly.assignment.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vn.edu.poly.assignment.Adapter.AdapterSpinnerLoaiChi;
import vn.edu.poly.assignment.Adapter.Adapter_Khoan_Chi;
import vn.edu.poly.assignment.DAO.DAO_Khoan_Chi;
import vn.edu.poly.assignment.DAO.DAO_Loai_Chi;
import vn.edu.poly.assignment.DTO.DTO_Khoan_Chi;
import vn.edu.poly.assignment.DTO.DTO_Loai_Chi;
import vn.edu.poly.assignment.R;


public class Fragment_Khoan_Chi extends Fragment {
    private RecyclerView rcvListKChi;
    ArrayList<DTO_Khoan_Chi> list_KChi;
    ArrayList<DTO_Loai_Chi> listLChi;
    DAO_Khoan_Chi daoKhoanChi;
    DAO_Loai_Chi daoLoaiChi;
    Adapter_Khoan_Chi adapterKhoanChi;
    private EditText etNameKhoanChi;
    private Spinner spinnerLoaiChi;
    private EditText etDateChi;
    private ImageView imgDateChi;
    private EditText etMoneyChi;
    private EditText etFullnameChi;
    private EditText etNoteChi;
    private Button btnInsertChi;
    String newDate;
    private FloatingActionButton fabInsertKhoanChi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__khoan__chi, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvListKChi = (RecyclerView) view.findViewById(R.id.rcv_list_KChi);
        daoKhoanChi= new DAO_Khoan_Chi(getContext());
        daoLoaiChi = new DAO_Loai_Chi(getContext());
        daoKhoanChi.opend();
        list_KChi= daoKhoanChi.getAlL_khoan_chi();

        adapterKhoanChi = new Adapter_Khoan_Chi(list_KChi, daoKhoanChi);
        rcvListKChi.setAdapter(adapterKhoanChi);
        fabInsertKhoanChi = (FloatingActionButton) view.findViewById(R.id.fab_insert_khoan_chi);
        DTO_Khoan_Chi obj = new DTO_Khoan_Chi();
        fabInsertKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_insert_khoan_chi);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog_update);
                etNameKhoanChi = (EditText) dialog.findViewById(R.id.et_name_khoan_chi);
                spinnerLoaiChi = (Spinner) dialog.findViewById(R.id.spinner_loai_chi);
                etDateChi = (EditText) dialog.findViewById(R.id.et_date_chi);
                imgDateChi = (ImageView) dialog.findViewById(R.id.img_date_chi);
                etMoneyChi = (EditText) dialog.findViewById(R.id.et_money_chi);
                etFullnameChi = (EditText) dialog.findViewById(R.id.et_fullname_chi);
                etNoteChi = (EditText) dialog.findViewById(R.id.et_note_chi);
                btnInsertChi = (Button) dialog.findViewById(R.id.btn_insert_chi);
                daoLoaiChi.opend();
                listLChi= daoLoaiChi.getAll_Loai_chi();
                AdapterSpinnerLoaiChi adapterSpinnerLoaiChi = new AdapterSpinnerLoaiChi(listLChi);
                spinnerLoaiChi.setAdapter(adapterSpinnerLoaiChi);
                imgDateChi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogDate();
                    }
                });
                DTO_Loai_Chi objLCHI = (DTO_Loai_Chi) spinnerLoaiChi.getSelectedItem();
                btnInsertChi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkLoi();
                        if( checkLoi()==true){
                            Date objdate2 = new Date(System.currentTimeMillis());
                            android.text.format.DateFormat dateFormat2 = new DateFormat();
                            String dates2 = etDateChi.getText().toString();
                            SimpleDateFormat Format2 = new SimpleDateFormat("dd-mm-yyyy");
                            try {
                                Date obj = Format2.parse(dates2);
                                newDate = (String) dateFormat2.format("yyyy-mm-dd", obj);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            obj.setName_khoan_chi(etNameKhoanChi.getText().toString());
                            obj.setHo_ten(etFullnameChi.getText().toString());
                            obj.setGhi_chu(etNoteChi.getText().toString());
                            obj.setSo_tien(Double.parseDouble(etMoneyChi.getText().toString()));
                            obj.setNgay_chi(newDate);
                            obj.setId_loai_chi(objLCHI.getId());
                            long res = daoKhoanChi.insert_khoan_chi(obj);
                            if(res>0){
                                list_KChi.clear();
                                list_KChi.addAll(daoKhoanChi.getAlL_khoan_chi());
                                adapterKhoanChi.notifyDataSetChanged();
                                Toast.makeText(getContext(),"Thêm mới thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(getContext(),"Thêm mới thành công", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getContext(),"Một số trường vẫn còn trống hãy nhập vào", Toast.LENGTH_SHORT).show();
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
                etDateChi.setText(days + "-" + (months + 1) + "-" +years );
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
    public boolean checkLoi() {
        boolean a = true;
        if (etNameKhoanChi.getText().toString().trim().equals("")) {
            a = false;
        } else {
            a = true;
        }
        if(etDateChi.getText().toString().equals("")){
            a=false;
        }else{
            a= true;
        }
        if (etMoneyChi.getText().toString().trim().equals("")) {
            a = false;
        } else {
            a = true;
        }
        if (etFullnameChi.getText().toString().trim().equals("")) {
            a = false;
        } else {
            a = true;
        }

        return a;
    }
}