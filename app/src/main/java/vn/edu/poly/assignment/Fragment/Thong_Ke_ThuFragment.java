package vn.edu.poly.assignment.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.util.Log;
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

import vn.edu.poly.assignment.Adapter.AdapterThongKeThu;
import vn.edu.poly.assignment.DAO.DAO_Khoan_Thu;
import vn.edu.poly.assignment.DAO.ThongKeThu;
import vn.edu.poly.assignment.DTO.DTO_ThongKeThu;
import vn.edu.poly.assignment.Database.MyHelper;
import vn.edu.poly.assignment.R;

public class Thong_Ke_ThuFragment extends Fragment {
    private EditText etDateStartThu;
    private ImageView imgDateStartThu;
    private EditText etDateEndThu;
    private ImageView imgDateEndThu;
    private Button btnFindThu;
    private TextView tvListTKSizeThu;
    private RecyclerView rcvThongKeThu;
    private TextView tvTongTienThu;
    ThongKeThu thongKeThu;
    Context context;
    DAO_Khoan_Thu daoKhoanThu;
    AdapterThongKeThu adapterThongKeThu;
    ArrayList<DTO_ThongKeThu> list_ThongKeThu= new ArrayList<>();
    public String start;
    public String end;
    String newDate, newDate2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_thong__ke__thu, container, false);
        context= getContext();
        daoKhoanThu = new DAO_Khoan_Thu(context);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        daoKhoanThu.opend();
        thongKeThu= new ThongKeThu( context);
        etDateStartThu = view.findViewById(R.id.etDateStartThu);
        imgDateStartThu = view.findViewById(R.id.imgDateStartThu);
        etDateEndThu = view.findViewById(R.id.etDateEndThu);
        imgDateEndThu = view.findViewById(R.id.imgDateEndThu);
        btnFindThu = view.findViewById(R.id.btn_findThu);
        tvListTKSizeThu = view.findViewById(R.id.tv_listTKSizeThu);
        rcvThongKeThu = view.findViewById(R.id.rcv_thongKeThu);
        tvTongTienThu = view.findViewById(R.id.tv_tong_tien_thu);

        imgDateStartThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDateStart();

            }
        });
        imgDateEndThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDateEnd();

            }
        });

        btnFindThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chuyển định dạng ngày start
                Date objdate = new Date(System.currentTimeMillis());
                android.text.format.DateFormat dateFormat = new DateFormat();

                String dates = etDateStartThu.getText().toString();
                SimpleDateFormat Format = new SimpleDateFormat("dd-mm-yyyy");
                try {
                    Date obj = Format.parse(dates);
                    newDate = (String) dateFormat.format("yyyy-mm-dd", obj);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // chuyển định dạng ngày end
                Date objdate2 = new Date(System.currentTimeMillis());
                android.text.format.DateFormat dateFormat2 = new DateFormat();
                String dates2 = etDateEndThu.getText().toString();
                SimpleDateFormat Format2 = new SimpleDateFormat("dd-mm-yyyy");
                try {
                    Date obj = Format2.parse(dates2);
                    newDate2 = (String) dateFormat2.format("yyyy-mm-dd", obj);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                start = newDate;
                end = newDate2;
                list_ThongKeThu= thongKeThu.ThongKe(context,start,end);
                adapterThongKeThu= new AdapterThongKeThu(list_ThongKeThu,daoKhoanThu,context,thongKeThu, start,end);
                tvTongTienThu.setText("Tổng tiền: "+thongKeThu.TongTien(start,end)+"$");
                tvListTKSizeThu.setText("Số bản ghi: "+thongKeThu.SoBanGhi(start,end)+"");
                rcvThongKeThu.setAdapter(adapterThongKeThu);
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
                etDateStartThu.setText(days+ "-" + (months + 1) + "-" +years);
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
                etDateEndThu.setText(days+ "-" + (months + 1) + "-" +years);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
}