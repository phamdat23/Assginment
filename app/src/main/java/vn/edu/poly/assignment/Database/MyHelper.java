package vn.edu.poly.assignment.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    static final String db_NAME = "database_1";
    static final int VERSION = 1;

    public MyHelper(Context context) {
        super(context, db_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_tb_ac ="CREATE TABLE tb_ac ( id INTEGER NOT NULL, user TEXT NOT NULL, pass TEXT NOT NULL, repass TEXT NOT NULL ,PRIMARY KEY(id AUTOINCREMENT))";
        db.execSQL(create_tb_ac);
        String create_tb_loai_thu="CREATE TABLE tb_loai_thu ( id_loaithu INTEGER NOT NULL, name TEXT, PRIMARY KEY(id_loaithu AUTOINCREMENT))";
        db.execSQL(create_tb_loai_thu);
        String create_tb_loai_chi="CREATE TABLE  tb_loai_chi  ( id  INTEGER NOT NULL, name  TEXT, PRIMARY KEY( id  AUTOINCREMENT))";
        db.execSQL(create_tb_loai_chi);
        String create_tb_khoan_chi="CREATE TABLE tb_khoan_chi ( id INTEGER NOT NULL, id_loai_chi INTEGER NOT NULL, name_khoan_chi TEXT, ngay_chi DATE NOT NULL, so_tien INTEGER NOT NULL DEFAULT 0, ghi_chu TEXT, ho_ten TEXT, FOREIGN KEY(id_loai_chi) REFERENCES tb_loai_chi(id), PRIMARY KEY(id AUTOINCREMENT))";
        db.execSQL(create_tb_khoan_chi);
        String create_tb_khoan_thu="CREATE TABLE  tb_khoan_thu  (  id  INTEGER NOT NULL,  id_loai_thu  INTEGER NOT NULL,  name_khoan_thu  TEXT,  ngay_thu  DATE NOT NULL,  so_tien  INTEGER NOT NULL DEFAULT 0,  ghi_chu  TEXT,  ho_ten  TEXT, FOREIGN KEY( id_loai_thu ) REFERENCES  tb_loai_thu ( id ), PRIMARY KEY( id  AUTOINCREMENT))";
        db.execSQL(create_tb_khoan_thu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
