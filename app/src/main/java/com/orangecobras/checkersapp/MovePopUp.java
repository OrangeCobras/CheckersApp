package com.orangecobras.checkersapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import static java.lang.Integer.*;

public class MovePopUp extends AppCompatActivity {

    EditText PositionX;
    EditText PositionY;
    EditText Direction;
    EditText Scalar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_pop_up);
        PositionX = (EditText)findViewById(R.id.editTextPosX);
        Direction = (EditText)findViewById(R.id.editTextDirX);
        PositionY = (EditText)findViewById(R.id.editTextPosY);
        Scalar = (EditText)findViewById(R.id.editTextSca);

    }



    public void close_diaglogue(){

        Game.scalar = Integer.parseInt( Scalar.getText().toString());
        Game.posX = Integer.parseInt( PositionX.getText().toString());
        Game.posY = Integer.parseInt( PositionY.getText().toString());
        Game.dirX = Integer.parseInt( Direction.getText().toString());

        finish();
    }
}
