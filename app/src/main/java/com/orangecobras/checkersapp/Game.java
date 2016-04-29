package com.orangecobras.checkersapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import CheckersFramework.Board;
import CheckersFramework.Status;

public class Game extends AppCompatActivity implements CheckersFramework.View {

    private Status status;
    private Board board;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gridView = (GridView) findViewById(R.id.gridViewGame);
    }

    public void finish(View view) {
        finish();
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void invalidate() {
        // TODO
    }
}
