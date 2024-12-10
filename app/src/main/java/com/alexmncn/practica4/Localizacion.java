package com.alexmncn.practica4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Localizacion extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_localizacion);

		TextView longitud = findViewById(R.id.longitudeValueGPS);
		TextView latitud = findViewById(R.id.latitudeValueGPS);
		Button actualiza = findViewById(R.id.locationControllerGPS);

		final Db database = new Db(this);

		actualiza.setOnClickListener(view -> {
			double latA = 36.0;
			double latB = 43.5;
			double lonA = -9.0;
			double lonB =  3.0;
			double lat = latA + (latB-latA)* Math.random();
			double lon = lonA + (lonB-lonA)* Math.random();
			String latString = String.valueOf(lat);
			String lonString = String.valueOf(lon);
			latitud.setText(latString);
			longitud.setText(lonString);
			String texto = latString + ", " + lonString;
			database.createLocation("android", texto);
		});

	}
}
