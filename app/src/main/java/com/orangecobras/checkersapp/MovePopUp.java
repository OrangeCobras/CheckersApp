package com.orangecobras.checkersapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MovePopUp extends AppCompatActivity {

    EditText Position;
    EditText Direction;
    EditText Scalar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_pop_up);
        Position = (EditText)findViewById(R.id.editTextPos);
        Direction = (EditText)findViewById(R.id.editTextDir);
        Scalar = (EditText)findViewById(R.id.editTextSca);
    }


}
