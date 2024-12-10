package com.alexmncn.practica4;

import java.util.ArrayList;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

public class Db extends SQLiteOpenHelper {

	private static final int VERSION_BASEDATOS = 1;

	// Nombre de nuestro archivo de base de datos
	private static final String NOMBRE_BASEDATOS = "SM-1";
	private static final String NOMBRE_TABLA = "gps_location";
	private static final String BBDD =
			"CREATE TABLE "+ NOMBRE_TABLA +
			" (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"user TEXT, " +
			"location TEXT)";
	
	// CONSTRUCTOR de la clase
	public Db(Context context) {
		super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("Mensaje", "creada");
		db.execSQL(BBDD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA);
		Log.i("Mensaje","actualizada");
		onCreate(db);
	}

	// Métodos CRUD (Create, Read, Update, Delete)

	public void createLocation(String user, String location) {
		SQLiteDatabase db = getWritableDatabase();

		if(db != null){
			ContentValues valores = new ContentValues();
			valores.put("user", user);
			valores.put("location", location);
			db.insert(NOMBRE_TABLA, null, valores);
			Log.i("Mensaje","escribiendo en tabla");
			db.close();
		}
	}
	

	public ArrayList<String> readLocation() {
		ArrayList<String> locations=new ArrayList<String>();
		SQLiteDatabase db = getReadableDatabase();
		String[] valores_recuperar = {"id", "user", "location"};
		Log.i("Mensaje","leyendo de tabla");
		Cursor c = db.query(NOMBRE_TABLA, valores_recuperar, null, null, null, null, "id DESC", null);
		// Si no hay valores en la tabla no los leo, puede dar error
		if(c!=null && c.getCount()>0) {
			c.moveToFirst();
			do {
				locations.add(c.getString(2));
			} while (c.moveToNext());
			c.close();
			db.close();
		} else Log.i("Mensaje", "tabla_vacia");

		return locations;
	}

	public void deleteLocations(){
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("delete from " + NOMBRE_TABLA);
	}
}
