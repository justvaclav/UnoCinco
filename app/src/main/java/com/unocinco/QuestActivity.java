package com.unocinco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Integer.parseInt;

public class QuestActivity extends AppCompatActivity {
    final String THEME = "80085";
    final String PRICE = "80085";
    final String TIME = "80085";
    final String VIBR = "0";
    final String GAMEMODE = "9";
    SharedPreferences mSettings;
    SharedPreferences mPrice;
    public QuestionDBHelperTva mDBHelper;
    public QuestionDBHelperTre mDBHelperTre;
    public SQLiteDatabase mDb;
    final Random Random = new Random();
    private int price;
    private int theme;
    private int time;
    private String mode;
    private int vibr = 0;
    public int rightAnswer;
    public int points;
    GregorianCalendar rightNow;
    public int month;
    public int day;
    public boolean stopped;
    long mills = 300L;
    Button button;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    TextView textView;
    TextView textView2;
    TextView textViewTimer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);
        points = 0;
        stopped = false;
        mSettings = getSharedPreferences("myPref", MODE_PRIVATE);
        theme = parseInt(mSettings.getString(String.valueOf(THEME), ""));
        mPrice = getSharedPreferences("myPrice", MODE_PRIVATE);
        price = parseInt(mPrice.getString(String.valueOf(PRICE), ""));
        mSettings = getSharedPreferences("myTime", MODE_PRIVATE);
        String timeTesting = mSettings.getString(String.valueOf(TIME), "");
        if (timeTesting == "") {
            time = 10;
        } else {
            time = parseInt(mSettings.getString(String.valueOf(TIME), ""));
        }
        mSettings = getSharedPreferences("myMode", MODE_PRIVATE);
        Log.wtf("mSettings mode:", (mSettings.getString(String.valueOf(GAMEMODE), "")));
        mode = (mSettings.getString(GAMEMODE, ""));
        //day = 21;
        //month = 5;
        //mSettings = getSharedPreferences("vibrate", MODE_PRIVATE);
        //vibr = parseInt(mSettings.getString(String.valueOf(VIBR), ""));
        if (time == 0) {time = 10;}
        if (theme == 900) {time = 300;}
        //theme = mSettings.getInt(PRICE, 0);
        Log.d("theme", String.valueOf(theme));
        Log.d("price", String.valueOf(price));
        Log.d("vibrate", String.valueOf(vibr));
        Log.d("mode", mode);
        Calendar rightNow = Calendar.getInstance();
        month = rightNow.MONTH;
        day = rightNow.DAY_OF_MONTH;
        Log.d("feb", String.valueOf(rightNow.JANUARY));
        Log.wtf("month", String.valueOf(month));
        Log.wtf("day", String.valueOf(day));
        /*DatabaseHelper = new QuestionDBHelper(this);
        displayDatabaseInfo();*/
        mDBHelper = new QuestionDBHelperTva(this);
        button = (Button) findViewById(R.id.buttonRefresh);
        answer1 = (Button) findViewById(R.id.button6);
        answer2 = (Button) findViewById(R.id.button7);
        answer3 = (Button) findViewById(R.id.button8);
        answer4 = (Button) findViewById(R.id.button9);
        textView = (TextView) findViewById(R.id.textViewQuest2);
        textViewTimer2 = (TextView) findViewById(R.id.textViewTimer);
        textView2 = (TextView) findViewById(R.id.textView2);
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mDBHelperTre = new QuestionDBHelperTre(this);


        if (theme != 900) {
            mDb = mDBHelper.getWritableDatabase();
            try {
                mDBHelper.updateDataBase();
            } catch (IOException mIOException) {
                throw new Error("UnableToUpdateDatabase");
            }
            try {
                mDb = mDBHelper.getWritableDatabase();
            } catch (SQLException mSQLException) {
                throw mSQLException;
            }}
        else if (theme == 900) {
            mDb = mDBHelperTre.getWritableDatabase();
            try {
                mDBHelperTre.updateDataBase();
            } catch (IOException mIOException) {
                throw new Error("UnableToUpdateDatabase");
            }
            try {
                mDb = mDBHelperTre.getWritableDatabase();
            } catch (SQLException mSQLException) {
                throw mSQLException;
            }}

        updateQuestion();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }});

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightAnswer == 1) {
                    points = points + price;
                    textView2.setText("Очки: " + points);
                    if (vibr == 1) {
                        //vibrator.vibrate(mills);
                    }
                    else if (theme == 900) {
                        Intent i = new Intent(QuestActivity.this, MainActivity.class);
                        startActivity(i);
                        Toast toast = Toast.makeText(getApplicationContext(), "Вы правильно ответили на вопрос дня! Возвращайтесь завтра за новым вопросом", Toast.LENGTH_LONG);
                        toast.show();
                        QuestActivity.super.finish();
                    }
                    else {}

                }
                else {
                    points = points - price;
                    textView2.setText("Очки: " + points);
                    if (theme == 900) {
                        Intent i = new Intent(QuestActivity.this, MainActivity.class);
                        startActivity(i);
                        Toast toast = Toast.makeText(getApplicationContext(), "Подумайте еще раз и попробуйте позже", Toast.LENGTH_LONG);
                        toast.show();
                        QuestActivity.super.finish();
                    }
                    else {}
                }
                updateQuestion();
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightAnswer == 2) {
                    points = points + price;
                    textView2.setText("Очки: " + points);
                    if (vibr == 1) {
                        //vibrator.vibrate(mills);
                    }
                    else if (theme == 900) {
                        Intent i = new Intent(QuestActivity.this, MainActivity.class);
                        startActivity(i);
                        Toast toast = Toast.makeText(getApplicationContext(), "Вы правильно ответили на вопрос дня! Возвращайтесь завтра за новым вопросом", Toast.LENGTH_LONG);
                        toast.show();
                        QuestActivity.super.finish();
                    }
                    else {}
                }
                else {
                    points = points - price;
                    textView2.setText("Очки: " + points);
                    if (theme == 900) {
                        Intent i = new Intent(QuestActivity.this, MainActivity.class);
                        startActivity(i);
                        Toast toast = Toast.makeText(getApplicationContext(), "Подумайте еще раз и попробуйте позже", Toast.LENGTH_LONG);
                        toast.show();
                        QuestActivity.super.finish();
                    }
                    else {}
                }
                updateQuestion();
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightAnswer == 3) {
                    points = points + price;
                    textView2.setText("Очки: " + points);
                    if (vibr == 1) {
                        //vibrator.vibrate(mills);
                        }
                    else if (theme == 900) {
                        Intent i = new Intent(QuestActivity.this, MainActivity.class);
                        startActivity(i);
                        Toast toast = Toast.makeText(getApplicationContext(), "Вы правильно ответили на вопрос дня! Возвращайтесь завтра за новым вопросом", Toast.LENGTH_LONG);
                        toast.show();
                        QuestActivity.super.finish();
                    }
                    else {}
                }
                else {
                    points = points - price;
                    textView2.setText("Очки: " + points);
                    if (theme == 900) {
                        Intent i = new Intent(QuestActivity.this, MainActivity.class);
                        startActivity(i);
                        Toast toast = Toast.makeText(getApplicationContext(), "Подумайте еще раз и попробуйте позже", Toast.LENGTH_LONG);
                        toast.show();
                        QuestActivity.super.finish();
                    }
                    else {}
                }
                updateQuestion();
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightAnswer == 4) {
                    points = points + price;
                    textView2.setText("Очки: " + points);
                    if (vibr == 1) {
                        //vibrator.vibrate(mills);
                    }
                    else if (theme == 900) {
                        Intent i = new Intent(QuestActivity.this, MainActivity.class);
                        startActivity(i);
                        Toast toast = Toast.makeText(getApplicationContext(), "Вы правильно ответили на вопрос дня! Возвращайтесь завтра за новым вопросом", Toast.LENGTH_LONG);
                        toast.show();
                        QuestActivity.super.finish();
                    }
                    else {}
                }
                else {
                    points = points - price;
                    textView2.setText("Очки: " + points);
                    if (theme == 900) {
                        Intent i = new Intent(QuestActivity.this, MainActivity.class);
                        startActivity(i);
                        Toast toast = Toast.makeText(getApplicationContext(), "Подумайте еще раз и попробуйте позже", Toast.LENGTH_LONG);
                        toast.show();
                        QuestActivity.super.finish();
                    }
                    else {}
                }
                updateQuestion();
            }
        });
        new CountDownTimer(time * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewTimer2.setText("Таймер: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                if (stopped == false) {
                    if (mode == "1") {
                        Intent i = new Intent(QuestActivity.this, ChoiceActivity.class);
                        startActivity(i);
                        Toast toast = Toast.makeText(getApplicationContext(), "Вы набрали "+points+" очков", Toast.LENGTH_LONG);
                            toast.show();
                            QuestActivity.super.finish();
                        Toast.makeText(getApplicationContext(), "Вы набрали "+points+" очков", Toast.LENGTH_LONG); }
                    else if (mode == "2") {
                        Intent i = new Intent(QuestActivity.this, ChoiceActivityTva.class);
                        startActivity(i);
                        Toast toast = Toast.makeText(getApplicationContext(), "Вы набрали "+points+" очков", Toast.LENGTH_LONG);
                        toast.show();
                        QuestActivity.super.finish();
                        Toast.makeText(getApplicationContext(), "Вы набрали "+points+" очков", Toast.LENGTH_LONG);
                    }
                else {Intent i = new Intent(QuestActivity.this, MainActivity.class);
                        startActivity(i);
                        Toast toast = Toast.makeText(getApplicationContext(), "Вы набрали "+points+" очков", Toast.LENGTH_LONG);
                        toast.show();
                        QuestActivity.super.finish();
                        Toast.makeText(getApplicationContext(), "Вы набрали "+points+" очков", Toast.LENGTH_LONG);}}
                else {}
            }

        }.start();
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void onStart() {
        super.onStart();
        //displayDatabaseInfo();
    }

    protected void onResume() {
        super.onResume();
    }

    public void updateQuestion() {String product = "";
        if (theme != 900) {Log.d("theme", String.valueOf(theme));
        Log.d("price", String.valueOf(price));
        int i = Random.nextInt(34)+1;
        //Log.d("theme:", String.valueOf(theme));
        //Cursor cursor = mDb.rawQuery("SELECT * FROM Questions where id = 2",null);
        Cursor cursor = mDb.rawQuery("SELECT * FROM Questions WHERE Theme = "+theme+" AND price = "+price+" ORDER BY RANDOM() LIMIT 1",null);
        //Cursor cursor = mDb.rawQuery("SELECT * FROM Questions where id = "+i,null);
        cursor.moveToFirst();
//                Log.d("question", cursor.getString(6));
//                answer1.setText(price);
        product = cursor.getString(2);
        answer1.setText(product);
        product = cursor.getString(3);
        answer2.setText(product);
        product = cursor.getString(4);
        answer3.setText(product);
        product = cursor.getString(5);
        answer4.setText(product);
        product = cursor.getString(6);
        textView.setText(product);
        product = cursor.getString(7);
        price = parseInt(product);
        Log.d("Price equals ", String.valueOf(price));
        product = cursor.getString(1);
        rightAnswer = parseInt(product);
        Log.d("RightAnswer equals ", String.valueOf(rightAnswer));
        cursor.close();
        }
        else if(theme == 900) {
            Calendar rightNow = Calendar.getInstance();
            Log.wtf("calendar", String.valueOf(rightNow));
            month = rightNow.get(Calendar.MONTH);
            day = rightNow.get(Calendar.DAY_OF_MONTH);
            Cursor cursor = mDb.rawQuery("SELECT * FROM history_day WHERE Day  = "+ day +" AND Month  = "+ (month+1),null);
            Log.d("month", String.valueOf(month));
            Log.d("day", String.valueOf(day));
            //Cursor cursor = mDb.rawQuery("SELECT * FROM Questions where id = "+i,null);
            cursor.moveToFirst();
//                Log.d("question", cursor.getString(6));
//                answer1.setText(price);
            product = cursor.getString(2);
            answer1.setText(product);
            product = cursor.getString(3);
            answer2.setText(product);
            product = cursor.getString(4);
            answer3.setText(product);
            product = cursor.getString(5);
            answer4.setText(product);
            product = cursor.getString(1);
            textView.setText(product);
            product = cursor.getString(7);
            price = 10;
            Log.d("Price equals ", String.valueOf(price));
            product = cursor.getString(8);
            rightAnswer = parseInt(product);
            Log.d("RightAnswer equals ", String.valueOf(rightAnswer));
            cursor.close();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        stopped = true;
    }

    protected void onStop() {
        super.onStop();
        stopped = true;
    }
    }