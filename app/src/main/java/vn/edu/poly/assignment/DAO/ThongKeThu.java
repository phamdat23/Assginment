package vn.edu.poly.assignment.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import vn.edu.poly.assignment.DTO.DTO_Khoan_Thu;
import vn.edu.poly.assignment.DTO.DTO_ThongKeThu;
import vn.edu.poly.assignment.Database.MyHelper;

public class ThongKeThu {
    DAO_Khoan_Thu daoKhoanThu;
    SQLiteDatabase db;
    MyHelper myHelper;

    public ThongKeThu(Context context) {
        myHelper = new MyHelper(context);
        db = myHelper.getWritableDatabase();
    }

    public ArrayList ThongKe(Context context, String start, String end) {
        daoKhoanThu = new DAO_Khoan_Thu(context);
        daoKhoanThu.opend();
        ArrayList<DTO_ThongKeThu> list_thongKeThu = new ArrayList<>();
        String starts = start;
        String ends = end;
        String Select_thongKe = "SELECT* FROM tb_khoan_thu WHERE ngay_thu >= " + "'" + starts + "'" + " AND " + "ngay_thu<=" + "'" + ends + "'";
        Cursor cursor = db.rawQuery(Select_thongKe, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                DTO_ThongKeThu obj = new DTO_ThongKeThu();
                obj.setId(cursor.getInt(0));
                obj.setId_loai_thu(cursor.getInt(1));
                obj.setName_khoan_thu(cursor.getString(2));
                obj.setNgay_thu(cursor.getString(3));
                obj.setSo_tien(cursor.getDouble(4));
                obj.setGhi_chu(cursor.getString(5));
                obj.setHo_ten(cursor.getString(6));
                list_thongKeThu.add(obj);
                cursor.moveToNext();
            }
        }
        return list_thongKeThu;
    }

    public int SoBanGhi(String start, String end) {
        String starts = start;
        String ends = end;
        String Count = "SELECT COUNT(*) FROM tb_khoan_thu WHERE ngay_thu >= " + "'" + starts + "'" + " AND " + "ngay_thu<=" + "'" + ends + "'";
        Cursor cursor = db.rawQuery(Count, null);
        DTO_ThongKeThu obj = new DTO_ThongKeThu();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                obj.setCountThu(cursor.getInt(0));
                cursor.moveToNext();
            }
        }
        return obj.getCountThu();
    }

    public double TongTien(String start, String end) {
        String starts = start;
        String ends = end;
        String sum = "SELECT sum(so_tien) FROM tb_khoan_thu WHERE ngay_thu >= " + "'" + starts + "'" + " AND " + "ngay_thu<=" + "'" + ends + "'";
        Cursor cursor = db.rawQuery(sum, null);
        DTO_ThongKeThu obj = new DTO_ThongKeThu();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                obj.setSumThu(cursor.getDouble(0));
                cursor.moveToNext();
            }
        }
        return obj.getSumThu();
    }


}
