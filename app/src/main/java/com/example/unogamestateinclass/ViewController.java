package com.example.unogamestateinclass;

import android.view.View;
import android.widget.TextView;

public class ViewController
        implements View.OnClickListener{

    private TextView gameText;

    public ViewController(TextView _gameText){
        gameText = _gameText;
    }

    @Override
    public void onClick(View view) {
        gameText.setText("You pushed the button...");
    }
}
