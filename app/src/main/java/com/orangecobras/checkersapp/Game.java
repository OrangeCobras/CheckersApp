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
    private GridView gridView;
    private BoardAdapter adapter;
    private TextView turnText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        adapter = new BoardAdapter(this);
        gridView = (GridView) findViewById(R.id.gridViewGame);
        gridView.setAdapter(adapter);
        turnText = (TextView) findViewById(R.id.textViewTurn);
    }

    public void finish(View view) {
        finish();
    }

    @Override
    public void setBoard(Board board) {
        adapter.setBoard(board);
    }

    @Override
    public void setStatus(Status status) {
        String text;
        switch (status) {
            case TurnBlackPlayer: text = "Turn: White Player"; break;
            case TurnWhitePlayer: text = "Turn: White Player"; break;
            default: text = "Game Ended";
        }
        turnText.setText(text);
    }

    @Override
    public void invalidate() {
        gridView.invalidate();
        turnText.invalidate();
    }
}

class BoardAdapter extends BaseAdapter {

    private Context context;
    private Board board;

    public BoardAdapter(Context context) {
        this.context = context;
        this.board = new Board(10);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public int getCount() {
        return board.getSize() * board.getSize();
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
        int image = R.drawable.checkersboard;
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
