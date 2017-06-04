package com.chess.engine;

import com.chess.engine.board.Board;

/**
 * Created by Konstantin on 05-Jun-17.
 */
public class JChess {

    public static void main(String[] args){
        Board board  = Board.createStandardBoard();

        System.out.println(board);

    }


}
