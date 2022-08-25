package vn.edu.poly.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.assignment.DTO.DTOAC;
import vn.edu.poly.assignment.Database.MyHelper;

public class DAO_AC {
    SQLiteDatabase db;
    MyHelper myHelper;


    public DAO_AC(Context context) {
        myHelper = new MyHelper(context);

    }
    public void opend(){
        db = myHelper.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    public ArrayList<DTOAC> getALLAC(){
        ArrayList<DTOAC> list_ac= new ArrayList<>();
        String select ="Select * from tb_ac";
        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                DTOAC dtoac = new DTOAC();
                dtoac.setId(cursor.getInt(0));
                dtoac.setUserName(cursor.getString(1));
                dtoac.setPassWord(cursor.getString(2));
                dtoac.setRePass(cursor.getString(3));
                list_ac.add(dtoac);
                cursor.moveToNext();
            }
        }
        return list_ac;
    }
    public long insert_ac(DTOAC dtoac){
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", dtoac.getUserName());
        contentValues.put("pass",dtoac.getPassWord());
        contentValues.put("repass", dtoac.getRePass());
        return db.insert("tb_ac",null, contentValues);
    }
    public int update_ac(DTOAC dtoac){
        ContentValues contentValues = new ContentValues();
        contentValues.put("pass",dtoac.getPassWord());
        contentValues.put("repass", dtoac.getRePass());
        return db.update("tb_ac",contentValues, "id=?", new String[]{dtoac.getId()+""});
    }
}
