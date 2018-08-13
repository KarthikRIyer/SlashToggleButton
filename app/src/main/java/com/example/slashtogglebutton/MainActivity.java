package com.example.slashtogglebutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SlashToggleButton slashToggleButton = new SlashToggleButton(getApplicationContext());
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.baseline_screen_rotation_black_48);
//        slashToggleButton.setIconBitmap(bitmap);
//        LinearLayout linearLayout = findViewById(R.id.constraintLayout);
//        linearLayout.addView(slashToggleButton);

        final SlashToggleButton slashToggleButton = findViewById(R.id.slash);

        slashToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slashToggleButton.toggle();
            }
        });


    }
}
