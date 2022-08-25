package vn.edu.poly.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.assignment.DTO.DTO_Loai_Chi;
import vn.edu.poly.assignment.DTO.DTO_Loai_Thu;
import vn.edu.poly.assignment.Database.MyHelper;

public class DAO_Loai_Chi {
    SQLiteDatabase db;
    MyHelper myHelper;
    public DAO_Loai_Chi(Context context){
        myHelper = new MyHelper(context);
    }
    public void opend(){
        db = myHelper.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    public ArrayList<DTO_Loai_Chi> getAll_Loai_chi(){
        ArrayList<DTO_Loai_Chi> list_loai_chi = new ArrayList<>();
        String select = "select*from tb_loai_chi";
        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                DTO_Loai_Chi dto_loai_chi = new DTO_Loai_Chi();
                dto_loai_chi.setId(cursor.getInt(0));
                dto_loai_chi.setName(cursor.getString(1));
                list_loai_chi.add(dto_loai_chi);
                cursor.moveToNext();
            }
        }
        return list_loai_chi;
    }
    public long insert_loai_chi(DTO_Loai_Chi dto_loai_chi){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", dto_loai_chi.getName());
        return db.insert("tb_loai_chi", null, contentValues);
    }
    public int update_loai_chi(DTO_Loai_Chi dto_loai_chi){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", dto_loai_chi.getName());
        return db.update("tb_loai_chi", contentValues, "id=?", new String[]{dto_loai_chi.getId()+""});
    }
    public int delete_loai_chi(DTO_Loai_Chi dto_loai_chi){
        return db.delete("tb_loai_chi","id=?", new String[]{dto_loai_chi.getId()+""} );
    }
}
