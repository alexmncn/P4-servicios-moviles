package com.alexmncn.practica4;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Historico extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historico);

		final ListView lista = findViewById(R.id.lista);

		Db database = new Db(this);

		ArrayList<String> location;
		location = database.readLocation();
		ArrayAdapter<String> lista_fija = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, location);
		lista.setAdapter(lista_fija);
	}
}
