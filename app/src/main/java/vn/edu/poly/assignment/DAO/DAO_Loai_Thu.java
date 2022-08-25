package vn.edu.poly.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.assignment.DTO.DTO_Loai_Thu;
import vn.edu.poly.assignment.Database.MyHelper;

public class DAO_Loai_Thu {
    SQLiteDatabase db;
    MyHelper myHelper;
    public DAO_Loai_Thu(Context context){
        myHelper = new MyHelper(context);
    }
    public void opend(){
        db = myHelper.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    public ArrayList<DTO_Loai_Thu> getAll_Loai_thu(){
        ArrayList<DTO_Loai_Thu> list_loai_thu = new ArrayList<>();
        String select = "select*from tb_loai_thu";
        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                DTO_Loai_Thu dto_loai_thu = new DTO_Loai_Thu();
                dto_loai_thu.setIdLT(cursor.getInt(0));
                dto_loai_thu.setName(cursor.getString(1));
                list_loai_thu.add(dto_loai_thu);
                cursor.moveToNext();
            }
        }
        return list_loai_thu;
    }
    public long insert_loai_thu(DTO_Loai_Thu dto_loai_thu){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", dto_loai_thu.getName());
        return db.insert("tb_loai_thu", null, contentValues);
    }
    public int update_loai_thu(DTO_Loai_Thu obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", obj.getName());
        return db.update("tb_loai_thu", contentValues, "id_loaithu=?", new String[]{obj.getIdLT()+""});
    }
    public int delete_loai_thu(DTO_Loai_Thu dto_loai_thu){
        return db.delete("tb_loai_thu","id_loaithu=?", new String[]{dto_loai_thu.getIdLT()+""} );
    }
}
