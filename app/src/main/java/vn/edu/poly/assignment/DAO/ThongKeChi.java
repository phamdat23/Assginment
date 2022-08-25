package vn.edu.poly.assignment.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import vn.edu.poly.assignment.DTO.DTO_Khoan_Chi;
import vn.edu.poly.assignment.DTO.DTO_ThongKeChi;
import vn.edu.poly.assignment.DTO.DTO_ThongKeThu;
import vn.edu.poly.assignment.Database.MyHelper;

public class ThongKeChi {
    DAO_Khoan_Thu daoKhoanThu;
    SQLiteDatabase db;
    MyHelper myHelper;
    public ThongKeChi(Context context) {
        myHelper = new MyHelper(context);
        db = myHelper.getWritableDatabase();
    }
    public ArrayList ThongKe(Context context, String start, String end) {
        daoKhoanThu = new DAO_Khoan_Thu(context);
        daoKhoanThu.opend();
        ArrayList<DTO_ThongKeChi> list_thongKeChi = new ArrayList<>();
        String starts = start;
        String ends = end;
        String Select_thongKe = "SELECT* FROM tb_khoan_chi WHERE ngay_chi >= " + "'" + starts + "'" + " AND " + "ngay_chi<=" + "'"+ ends+ "'";
        Cursor cursor = db.rawQuery(Select_thongKe, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                DTO_ThongKeChi obj = new DTO_ThongKeChi();
                obj.setId(cursor.getInt(0));
                obj.setId_loai_chi(cursor.getInt(1));
                obj.setName_khoan_chi(cursor.getString(2));
                obj.setNgay_chi(cursor.getString(3));
                obj.setSo_tien(cursor.getDouble(4));
                obj.setGhi_chu(cursor.getString(5));
                obj.setHo_ten(cursor.getString(6));
                list_thongKeChi.add(obj);
                cursor.moveToNext();
            }
        }
        return list_thongKeChi;
    }

    public int SoBanGhi(String start, String end) {
        String starts = start;
        String ends = end;
        String Count = "SELECT COUNT(*) FROM tb_khoan_chi WHERE ngay_chi >= " + "'" + starts + "'" + " AND " + "ngay_chi<=" + "'"+ ends+ "'";
        Cursor cursor = db.rawQuery(Count, null);
        DTO_ThongKeChi obj = new DTO_ThongKeChi();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                obj.setCountChi(cursor.getInt(0));
                cursor.moveToNext();
            }
        }
        return obj.getCountChi();
    }

    public double TongTien(String start, String end) {
        String starts = start;
        String ends = end;
        String Count = "SELECT sum(so_tien) FROM tb_khoan_chi WHERE ngay_chi >= " + "'" + starts + "'" + " AND " + "ngay_chi<=" + "'"+ ends+ "'";
        Cursor cursor = db.rawQuery(Count, null);
        DTO_ThongKeChi obj = new DTO_ThongKeChi();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                obj.setSumChi(cursor.getDouble(0));
                cursor.moveToNext();
            }
        }
        return obj.getSumChi();
    }

}
