package com.example.unogamestateinclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView gameText = findViewById(R.id.gameText);
        Button playButton = findViewById(R.id.playTurns);

        playButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                gameText.setText("You pushed the button...");
            }
        });


    }


}

