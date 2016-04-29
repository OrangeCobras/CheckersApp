package com.orangecobras.checkersapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import CheckersFramework.Board;
import CheckersFramework.Status;
import CheckersFramework.Point;

public class Game extends AppCompatActivity implements CheckersFramework.View {

    private Status status;
    private Board board;
    private GridView gridView;

    TextView turnPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gridView = (GridView) findViewById(R.id.gridViewGame);
        board = new Board(10);
        gridView.setAdapter(new BoardAdapter(this, board));
        turnPlayerView = (TextView)findViewById(R.id.textView);
        turnPlayerView.setText("White Player's turn");
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
        switch(this.status){
            case TurnWhitePlayer: turnPlayerView.setText("White Player");
            case TurnBlackPlayer: turnPlayerView.setText("Black Player");
            default: turnPlayerView.setText("Game ended");
        }
        turnPlayerView.invalidate();
    }
}


class BoardAdapter extends BaseAdapter {

    private Context context;
    private Board board;

    public BoardAdapter(Context context, Board b) {
        this.context = context;
        this.board = b;
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int i) {
        return board.getPiece(new Point(i % 10, i / 10));
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder;
        int image = R.drawable.blackpiece;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_cell, viewGroup, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (board.getPiece(new Point(i % 10, i / 10)) != null) {
            switch (board.getPiece(new Point(i % 10, i / 10))) {
                case Black:
                    image = R.drawable.blackpiece;
                    break;
                case BlackKing:
                    image = R.drawable.blackpieceking;
                    break;
                case White:
                    image = R.drawable.whitepiece;
                    break;
                case WhiteKing:
                    image = R.drawable.whitepieceking;
                    break;
            }
        }
        holder.piece.setImageResource(image);
        return row;
    }

    class ViewHolder {

        ImageView piece;

        ViewHolder(View v) {
            this.piece = (ImageView) v.findViewById(R.id.imageView);
        }

    }

}
