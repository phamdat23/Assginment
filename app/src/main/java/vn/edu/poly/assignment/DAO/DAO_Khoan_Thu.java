package vn.edu.poly.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.assignment.DTO.DTO_Khoan_Thu;
import vn.edu.poly.assignment.Database.MyHelper;

public class DAO_Khoan_Thu {
    SQLiteDatabase db;
     MyHelper myHelper;
     public DAO_Khoan_Thu(Context context){
         myHelper = new MyHelper(context);
     }
     public void opend(){
         db = myHelper.getWritableDatabase();
     }
     public void close(){
         db.close();
     }
     public ArrayList<DTO_Khoan_Thu> getAll_khoan_thu(){
         ArrayList<DTO_Khoan_Thu> list_khoan_thu= new ArrayList<>();
         String select="SELECT *FROM tb_khoan_thu";
         Cursor cursor = db.rawQuery(select, null);
         if(cursor.moveToFirst()){
             while(!cursor.isAfterLast()){
                 DTO_Khoan_Thu obj = new DTO_Khoan_Thu();
                 obj.setId(cursor.getInt(0));
                 obj.setId_loai_thu(cursor.getInt(1));
                 obj.setName_khoan_thu(cursor.getString(2));
                 obj.setNgay_thu(cursor.getString(3));
                 obj.setSo_tien(cursor.getInt(4));
                 obj.setGhi_chu(cursor.getString(5));
                 obj.setHo_ten(cursor.getString(6));
                 list_khoan_thu.add(obj);
                 cursor.moveToNext();
             }
         }
         return list_khoan_thu;
     }
     public long insert_khoan_thu(DTO_Khoan_Thu obj){
         ContentValues contentValues = new ContentValues();
         contentValues.put("id_loai_thu", obj.getId_loai_thu());
         contentValues.put("name_khoan_thu", obj.getName_khoan_thu());
         contentValues.put("ngay_thu", obj.getNgay_thu());
         contentValues.put("so_tien", obj.getSo_tien());
         contentValues.put("ghi_chu", obj.getGhi_chu());
         contentValues.put("ho_ten", obj.getHo_ten());
         return db.insert("tb_khoan_thu",null, contentValues);
     }
     public int update_khoan_thu(DTO_Khoan_Thu obj){
         ContentValues contentValues = new ContentValues();
         contentValues.put("id_loai_thu", obj.getId_loai_thu());
         contentValues.put("name_khoan_thu", obj.getName_khoan_thu());
         contentValues.put("ngay_thu", obj.getNgay_thu());
         contentValues.put("so_tien", obj.getSo_tien());
         contentValues.put("ghi_chu", obj.getGhi_chu());
         contentValues.put("ho_ten", obj.getHo_ten());
         return db.update("tb_khoan_thu", contentValues,"id=?", new String[]{obj.getId()+""});
     }
     public int delete_khoan_thu(DTO_Khoan_Thu obj){
         return db.delete("tb_khoan_thu", "id=?", new String[]{obj.getId()+""});
     }

}
