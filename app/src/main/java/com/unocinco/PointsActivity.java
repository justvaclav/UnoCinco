package com.unocinco;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class PointsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        int id = getResources().getIdentifier("com.unocinco:drawable/" + "quest", null, null);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView3.setImageResource(id);
    }
}
