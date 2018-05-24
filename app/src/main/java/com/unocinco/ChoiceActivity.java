package com.unocinco;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class ChoiceActivity extends AppCompatActivity implements View.OnClickListener {
    final Random Random = new Random();
    final String THEME = "80085";
    final String PRICE = "80085";
    final String GAMEMODE = "5";
    private SharedPreferences mSettings;
    private SharedPreferences mPrice;
    private int theme = Random.nextInt(5) +1;
    Button btnUno;
    Button btnCinco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        TextView themeName = (TextView) findViewById(R.id.textView6);
        Button btnUno = (Button) findViewById(R.id.btnUno);
        btnUno.setOnClickListener(this);
        Button btnCinco = (Button) findViewById(R.id.btnCinco);
        btnCinco.setOnClickListener(this);
        Log.d("theme:", String.valueOf(theme));


        if (theme == 2) {
            themeName.setText("История");
            mSettings = getSharedPreferences("myPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(THEME, String.valueOf(2));
            editor.apply();
        }
        else if (theme == 1) {
            themeName.setText("География");
            mSettings = getSharedPreferences("myPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(THEME, String.valueOf(1));
            editor.apply();
        }
        else if (theme == 3) {
            themeName.setText("Литература");
            mSettings = getSharedPreferences("myPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(THEME, String.valueOf(3));
            editor.apply();

        }
        else if (theme == 4) {
            themeName.setText("Биология");
            mSettings = getSharedPreferences("myPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(THEME, String.valueOf(4));
            editor.apply();
        }
        else if (theme == 5) {
            themeName.setText("Культура");
            mSettings = getSharedPreferences("myPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(THEME, String.valueOf(5));
            editor.apply();
        }
        else {
            themeName.setText("непонятно что");
        }
        mSettings = getSharedPreferences("myMode", MODE_PRIVATE);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(GAMEMODE, String.valueOf(1));
        editor.apply();
        mSettings = getSharedPreferences("myMode", MODE_PRIVATE);
        Log.wtf("mode", mSettings.getString(GAMEMODE, ""));
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUno:
                mPrice = getSharedPreferences("myPrice", MODE_PRIVATE);
                SharedPreferences.Editor ed = mPrice.edit();
                ed.putString(PRICE, String.valueOf(1));
                ed.apply();
                Intent intent1 = new Intent(ChoiceActivity.this, QuestActivity.class);
                startActivity(intent1);
                ChoiceActivity.super.finish();
                break;
            case R.id.btnCinco:
                mPrice = getSharedPreferences("myPrice", MODE_PRIVATE);
                SharedPreferences.Editor editor2 = mPrice.edit();
                editor2.putString(PRICE, String.valueOf(5));
                editor2.apply();
                Intent intent2 = new Intent(ChoiceActivity.this, QuestActivity.class);
                startActivity(intent2);
                ChoiceActivity.super.finish();
                break;
            default:
                break;
        }
    }

    protected void onResume() {
        super.onResume();
    }
}
