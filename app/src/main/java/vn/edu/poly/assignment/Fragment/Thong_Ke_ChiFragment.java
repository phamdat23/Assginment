package vn.edu.poly.assignment.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vn.edu.poly.assignment.Adapter.AdapterThongKeChi;
import vn.edu.poly.assignment.DAO.DAO_Khoan_Chi;
import vn.edu.poly.assignment.DAO.ThongKeChi;
import vn.edu.poly.assignment.DTO.DTO_ThongKeChi;
import vn.edu.poly.assignment.R;


public class Thong_Ke_ChiFragment extends Fragment {
    private EditText etDateStartChi;
    private ImageView imgDateStartChi;
    private EditText etDateEndChi;
    private ImageView imgDateEndChi;
    private Button btnFindChi;
    private TextView tvListTKSizeChi;
    private RecyclerView rcvThongKeChi;
    private TextView tvTongTienChi;
    ThongKeChi thongKeChi;
    ArrayList<DTO_ThongKeChi> listThongKeChi;
    Context context;
    DAO_Khoan_Chi daoKhoanChi;
    AdapterThongKeChi adapterThongKeChi;
    public String start;
    public String end;
    String newDate1, newDate2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_thong__ke__chi, container, false);
        context= getContext();
        daoKhoanChi = new DAO_Khoan_Chi(context);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etDateStartChi = view.findViewById(R.id.etDateStartChi);
        imgDateStartChi = view.findViewById(R.id.imgDateStartChi);
        etDateEndChi = view.findViewById(R.id.etDateEndChi);
        imgDateEndChi = view.findViewById(R.id.imgDateEndChi);
        btnFindChi = view.findViewById(R.id.btn_findChi);
        tvListTKSizeChi = view.findViewById(R.id.tv_listTKSizeChi);
        rcvThongKeChi = view.findViewById(R.id.rcv_thongKeChi);
        tvTongTienChi = view.findViewById(R.id.tv_tong_tien_Chi);
        daoKhoanChi.opend();
        thongKeChi= new ThongKeChi(context);
        listThongKeChi= thongKeChi.ThongKe(context, start,end);
        adapterThongKeChi = new AdapterThongKeChi(context, thongKeChi, start,end,daoKhoanChi,listThongKeChi);
        imgDateStartChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDateStart();
            }
        });
        imgDateEndChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDateEnd();
            }
        });
        btnFindChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date objdate1 = new Date(System.currentTimeMillis());
                android.text.format.DateFormat dateFormat1 = new DateFormat();
                String dates1 = etDateStartChi.getText().toString();
                SimpleDateFormat Format1 = new SimpleDateFormat("dd-mm-yyyy");
                try {
                    Date obj = Format1.parse(dates1);
                    newDate1 = (String) dateFormat1.format("yyyy-mm-dd", obj);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //
                Date objdate2 = new Date(System.currentTimeMillis());
                android.text.format.DateFormat dateFormat2 = new DateFormat();
                String dates2 = etDateEndChi.getText().toString();
                SimpleDateFormat Format2 = new SimpleDateFormat("dd-mm-yyyy");
                try {
                    Date obj = Format2.parse(dates2);
                    newDate2 = (String) dateFormat2.format("yyyy-mm-dd", obj);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                start= newDate1;
                end= newDate2;
                listThongKeChi= thongKeChi.ThongKe(context, start,end);
                adapterThongKeChi = new AdapterThongKeChi(context, thongKeChi, start,end,daoKhoanChi,listThongKeChi);
                rcvThongKeChi.setAdapter(adapterThongKeChi);
                tvListTKSizeChi.setText("Số bản ghi: "+thongKeChi.SoBanGhi(start,end)+"");
                tvTongTienChi.setText("Tổng tiền: "+thongKeChi.TongTien(start,end)+"$");
            }
        });
    }
    public void dialogDateStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int days = dayOfMonth;
                int months = month;
                int years = year;
                etDateStartChi.setText( days+ "-" + (months + 1) + "-" + years);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
    public void dialogDateEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int days = dayOfMonth;
                int months = month;
                int years = year;
                etDateEndChi.setText(days+ "-" + (months + 1) + "-" + years);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
}