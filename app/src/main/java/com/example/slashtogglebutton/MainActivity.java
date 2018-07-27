package com.example.slashtogglebutton;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SlashToggleButton slashToggleButton = new SlashToggleButton(getApplicationContext());
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.baseline_screen_rotation_black_48);
//        slashToggleButton.setIconBitmap(bitmap);
//
//        LinearLayout linearLayout = findViewById(R.id.constraintLayout);
//        linearLayout.addView(slashToggleButton);

        SlashToggleButton slashToggleButton = findViewById(R.id.slash);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.baseline_screen_rotation_black_48);
//        slashToggleButton.setIconBitmap(bitmap);

    }
}
