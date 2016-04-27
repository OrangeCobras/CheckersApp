package com.orangecobras.checkersapp;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Janne on 27.04.2016.
 */
public class Game extends AppCompatActivity implements CheckersFramework.View , CheckersFramework.MoveGetter, AdapterView.OnItemClickListenerClickListener{

    private GridView gridView;
    private CheckersFramework.Board board;
    private CheckersFramework.Status status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new Adapter(this));
        CheckersFramework.Game.start();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i ,long l){

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

    }

}

class Piece{
    int imageId;

    Piece(int i){
        this.imageId = i;
    }
}


class Adapter extends BaseAdapter implements CheckersFramework.View{

    Context context;
    CheckersFramework.Board board;

    Adapter(Context context){

        this.context = context;
        Resources res = context.getResources();
        CheckersFramework.Game.board;
    }


    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int i) {
        CheckersFramework.Piece p = CheckersFramework.Game.board.getPiece(new CheckersFramework.Point(i%9,i/9));
        return (Java.Lang.Object)p;
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
        Piece p = board.getItem(i);
        switch(Game.board.getPiece(new CheckersFramework.Point(i%9,i/9)).getColor()){
            case CheckersFramework.Piece.Black: holder.Piece.setImageResource(R.drawable.BlackPiece);
            case CheckersFramework.Piece.BlackKing:holder.Piece.setImageResource(R.drawable.BlackPieceKing);
            case CheckersFramework.Piece.White:holder.Piece.setImageResource(R.drawable.WhitePiece);
            case CheckersFramework.Piece.WhiteKing:holder.Piece.setImageResource(R.drawable.WhitePieceKing);
        }
    }
}
