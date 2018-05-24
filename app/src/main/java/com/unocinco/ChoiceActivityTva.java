package com.unocinco;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.Random;

public class ChoiceActivityTva extends AppCompatActivity  {

    String[] names = { "Биология", "География", "История", "Культура", "Литература"};
    TextView textView9;
    final String THEME = "80085";
    final String GAMEMODE = "2";
    private SharedPreferences mSettings;
    private SharedPreferences mPrice;
    private SharedPreferences mMode;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_tva);
        mSettings = getSharedPreferences("myMode", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = mSettings.edit();
        editor1.putString(GAMEMODE, String.valueOf(2));
        editor1.apply();
        // находим список
        ListView listView = (ListView) findViewById(R.id.lvMain);
        textView9 = (TextView) findViewById(R.id.textView9);
        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);

        // присваиваем адаптер списку
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedItem = names[position];
                Log.d("item", selectedItem);
                // выбор нужной темы
                switch (selectedItem) {
                    case "География":
                        mSettings = getSharedPreferences("myPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = mSettings.edit();
                        editor.putString(THEME, String.valueOf(1));
                        editor.apply();
                        Intent i = new Intent(ChoiceActivityTva.this, QuestActivity.class);
                        startActivity(i);
                        ChoiceActivityTva.super.finish();
                    break;
                    case "История":
                        mSettings = getSharedPreferences("myPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = mSettings.edit();
                        editor2.putString(THEME, String.valueOf(2));
                        editor2.apply();
                        Intent i2 = new Intent(ChoiceActivityTva.this, QuestActivity.class);
                        startActivity(i2);
                        ChoiceActivityTva.super.finish();
                        break;
                    case "Литература":
                        mSettings = getSharedPreferences("myPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor3 = mSettings.edit();
                        editor3.putString(THEME, String.valueOf(3));
                        editor3.apply();
                        Intent i3 = new Intent(ChoiceActivityTva.this, QuestActivity.class);
                        startActivity(i3);
                        ChoiceActivityTva.super.finish();
                        break;
                    case "Биология":
                        mSettings = getSharedPreferences("myPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor4 = mSettings.edit();
                        editor4.putString(THEME, String.valueOf(4));
                        editor4.apply();
                        Intent i4 = new Intent(ChoiceActivityTva.this, QuestActivity.class);
                        startActivity(i4);
                        ChoiceActivityTva.super.finish();
                        break;
                    case "Культура":
                        mSettings = getSharedPreferences("myPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor5 = mSettings.edit();
                        editor5.putString(THEME, String.valueOf(5));
                        editor5.apply();
                        Intent i5 = new Intent(ChoiceActivityTva.this, QuestActivity.class);
                        startActivity(i5);
                        ChoiceActivityTva.super.finish();
                        break;
                }
            }
        });
    }
}