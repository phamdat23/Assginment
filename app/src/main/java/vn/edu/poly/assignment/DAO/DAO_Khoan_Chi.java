package vn.edu.poly.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.assignment.DTO.DTO_Khoan_Chi;
import vn.edu.poly.assignment.Database.MyHelper;

public class DAO_Khoan_Chi {
    SQLiteDatabase db;
    MyHelper myHelper;
    public DAO_Khoan_Chi(Context context){
        myHelper = new MyHelper(context);
    }
    public void opend(){
        db = myHelper.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    public ArrayList<DTO_Khoan_Chi> getAlL_khoan_chi(){
        ArrayList<DTO_Khoan_Chi> list_khoan_chi= new ArrayList<>();
        String select="select* from tb_khoan_chi";
        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                DTO_Khoan_Chi obj = new DTO_Khoan_Chi();
                obj.setId(cursor.getInt(0));
                obj.setId_loai_chi(cursor.getInt(1));
                obj.setName_khoan_chi(cursor.getString(2));
                obj.setNgay_chi(cursor.getString(3));
                obj.setSo_tien(cursor.getInt(4));
                obj.setGhi_chu(cursor.getString(5));
                obj.setHo_ten(cursor.getString(6));
                list_khoan_chi.add(obj);
                 cursor.moveToNext();

            }
        }
        return list_khoan_chi;
    }
    public long insert_khoan_chi(DTO_Khoan_Chi obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_loai_chi", obj.getId_loai_chi());
        contentValues.put("name_khoan_chi", obj.getName_khoan_chi());
        contentValues.put("ngay_chi", obj.getNgay_chi());
        contentValues.put("so_tien", obj.getSo_tien());
        contentValues.put("ghi_chu", obj.getGhi_chu());
        contentValues.put("ho_ten", obj.getHo_ten());
        return db.insert("tb_khoan_chi", null, contentValues);
    }
    public int update_khoan_chi(DTO_Khoan_Chi obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_loai_chi", obj.getId_loai_chi());
        contentValues.put("name_khoan_chi", obj.getName_khoan_chi());
        contentValues.put("ngay_chi", obj.getNgay_chi());
        contentValues.put("so_tien", obj.getSo_tien());
        contentValues.put("ghi_chu", obj.getGhi_chu());
        contentValues.put("ho_ten", obj.getHo_ten());
        return db.update("tb_khoan_chi", contentValues, "id=?", new String[]{obj.getId()+""});
    }
    public int delete_khoan_chi(DTO_Khoan_Chi obj){
        return db.delete("tb_khoan_chi", "id=?", new String[]{obj.getId()+""});
    }
}
