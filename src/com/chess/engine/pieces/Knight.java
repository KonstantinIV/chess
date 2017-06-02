package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.Move.*;

/**
 * Created by Konstantin on 01-Jun-17.
 */
public class Knight extends Piece {
    private final static int[] CANDIATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10 , 15, 17};

   Knight(final int piecePosition, final Alliance pieceAlliance){
        super(piecePosition, pieceAlliance);

    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board){

       final List<Move> legalMoves = new ArrayList<>();

       for(final int candidateOffset : CANDIATE_MOVE_COORDINATES){
           final int candidateDestinationCoordinate = this.piecePosition + candidateOffset;

           if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
               if(isFirstColumnExclusion(this.piecePosition, candidateOffset) ||
                       isSecondColumnExclusion(this.piecePosition, candidateOffset ) ||
                               isSeventhColumnExclusion(this.piecePosition, candidateOffset) ||
                                       isEightColumnExclusion(this.piecePosition, candidateOffset )){
                   continue;
               }



               final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

               if(!candidateDestinationTile.isTileOccupied()){
                   legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));


               }else{
                   final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                   final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();


                   if(this.pieceAlliance != pieceAlliance){
                       legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                   }
               }
           }

       }

        return ImmutableList.copyOf(legalMoves);

    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 || candidateOffset == 6 || candidateOffset == 15);
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == -6);
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
    }

    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == -15 || candidateOffset == -6 || candidateOffset == 10 || candidateOffset == 17);
    }




}
