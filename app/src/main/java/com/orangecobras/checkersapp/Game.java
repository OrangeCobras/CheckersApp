package com.orangecobras.checkersapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Janne on 27.04.2016.
 */
public class Game extends AppCompatActivity implements CheckersFramework.MoveGetter, CheckersFramework.View{

    private GridView gridView;
    private CheckersFramework.Board board;
    private CheckersFramework.Status status;
    private CheckersFramework.Game game;

    static int scalar;
    static int posX;
    static int posY;
    static int dirX;
    CheckersFramework.Direction d;
    CheckersFramework.Move m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new Adapter(this,board));
        game = new CheckersFramework.Game(this);
        game.start();
    }

    @Override
    public CheckersFramework.Move getMove(){
        Intent intent = new Intent(this, MovePopUp.class);
        startActivity(intent);
        switch(dirX){
            case 1: d = CheckersFramework.Direction.NorthEeast;
            case 2: d = CheckersFramework.Direction.NorthWest;
            case 3: d = CheckersFramework.Direction.SouthEeast;
            case 4: d = CheckersFramework.Direction.SouthWest;
        }
        return new CheckersFramework.Move(new CheckersFramework.Point(posX,posY), d,scalar);
    }


    public void setBoard(CheckersFramework.Board board){
        this.board = board;
    }

    public void setStatus(CheckersFramework.Status status){
        this.status = status;
    }

    /**
     * Invalidate the view.
     *
     * This means the view needs to be redrawn.
     */
    public void invalidate(){
        gridView.postInvalidate();
    }
}

class Piece{
    int imageId;

    Piece(int i){
        this.imageId = i;
    }
}


class Adapter extends BaseAdapter{

    Context context;
    CheckersFramework.Board board;

    Adapter(Context context, CheckersFramework.Board board){

        this.context = context;
        Resources res = context.getResources();
        this.board = board;
    }



    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int i) {
        CheckersFramework.Piece p = board.getPiece(new CheckersFramework.Point(i%9,i/9));
        return p;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    class ViewHolder {
        ImageView Piece;

        ViewHolder(View v) {
            Piece = (ImageView) v.findViewById(R.id.imageView);
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = null;
        if(row == null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_item,viewGroup,false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }
        switch(board.getPiece(new CheckersFramework.Point(i%9,i/9))){
            case Black: holder.Piece.setImageResource(R.drawable.blackpiece);
            case BlackKing:holder.Piece.setImageResource(R.drawable.blackpieceking);
            case White:holder.Piece.setImageResource(R.drawable.whitepiece);
            case WhiteKing:holder.Piece.setImageResource(R.drawable.whitepieceking);
        }
        return row;
    }
}
