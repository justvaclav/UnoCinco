package com.unocinco;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class SettingsActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    TextView textViewTime;
    SharedPreferences mSettings;
    int time = 0;
    int vibr = 0;
    final String TIME = "80085";
    final String VIBR = "1";
    private CheckBox checkBoxVibrate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSettings = getSharedPreferences("myTime", MODE_PRIVATE);
        String timeTesting = mSettings.getString(String.valueOf(TIME), "");
        if (timeTesting == "") {
            time = 10;
        } else {
            time = parseInt(mSettings.getString(String.valueOf(TIME), ""));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        checkBoxVibrate = (CheckBox) findViewById(R.id.checkBoxVibrate);
        seekBar.setOnSeekBarChangeListener(this);
        textViewTime = (TextView)findViewById(R.id.textView8);
        textViewTime.setText("Время игры:" + time);
        checkBoxVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    vibr = 1;
                    mSettings = getSharedPreferences("vibrate", MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putString(VIBR, String.valueOf(1));
                    editor.apply();
                } else {

                }
            }
        });
        Log.d("checkBoxVibrate:", String.valueOf(checkBoxVibrate.isChecked()));


    }

    public void onCheckboxClicked(View view) {
        CheckBox select = (CheckBox) view;
        boolean checked = select.isChecked();
        switch(view.getId()) {
            case R.id.checkBoxVibrate:
                if (checked){
                    vibr = 0;
                    mSettings = getSharedPreferences("vibrate", MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putString(VIBR, String.valueOf(0));
                    editor.apply();
                }
                else {
                    vibr = 0;
                    mSettings = getSharedPreferences("vibrate", MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putString(VIBR, String.valueOf(0));
                    editor.apply();
                }
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        time = seekBar.getProgress();
        textViewTime.setText("Время игры:" + time);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        time = seekBar.getProgress();
        textViewTime.setText("Время игры:" + time);
        mSettings = getSharedPreferences("myTime", MODE_PRIVATE);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(TIME, String.valueOf(time));
        editor.apply();
    }

    public void onDestroy() {
        mSettings = getSharedPreferences("myTime", MODE_PRIVATE);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(TIME, String.valueOf(time));
        editor.apply();
        mSettings = getSharedPreferences("vibrate", MODE_PRIVATE);
        editor.putString(VIBR, String.valueOf(0));
        editor.apply();
        super.onDestroy();
    }

}
