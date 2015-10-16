package com.coolweather.apps.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
	
	/*
	 * Provice表建立语句
	 */
	public final static String CREATE_PROVINCE = "create table Province ("
			+ "id integer primary key autoincrement, " + "provice_name text, "
			+ "provice_code text)";
	
	/*
	 * City表建立语句
	 */
	public final static String CREATE_CITY = "create table City ("
			+ "id integer primary key autoincrement, " + "city_name text, "
			+ "city_code text, " + "provice_id integer)";
	
	/*
	 * County表建立语句
	 */
	public final static String CREATE_COUNTY = "create table County ("
			+ "id integer primary key autoincrement, " + "county_name text, "
			+ "county_code text, " + "city_id integer)";
	
	

	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
		arg0.execSQL(CREATE_PROVINCE); // 创建Provice表
		arg0.execSQL(CREATE_CITY);   // 创建City表
		arg0.execSQL(CREATE_COUNTY);  // 创建County表

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
