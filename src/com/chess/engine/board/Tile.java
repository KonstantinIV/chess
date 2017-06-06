package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Konstantin on 01-Jun-17.
 */



//An abstract method is a method that is declared, but contains no implementation.

public abstract class Tile {
 protected final int tileCoordinate;

 private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles(){
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }

        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

 private Tile(final int tileCoordinate){
     this.tileCoordinate = tileCoordinate;
 }

 public abstract boolean isTileOccupied();

 public abstract Piece getPiece();

public int getTileCoordinate(){
    return this.tileCoordinate;
}





 //Emptytile
 public static final class EmptyTile extends Tile{
     EmptyTile(int coordinate){
         super(coordinate);

     }


     @Override
     public String toString(){
         return "-";

     }

     @Override
     public boolean isTileOccupied(){
         return false;

     }

     @Override
     public Piece getPiece(){
         return null;

     }

 }

 //Occtile
 public static final class OccupiedTile extends Tile{
     private final Piece pieceOnTile;

     private OccupiedTile(final int tileCoordinate, Piece pieceOnTile){
         super(tileCoordinate);
         this.pieceOnTile = pieceOnTile;
     }
     @Override
     public String toString(){
         return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() :
                 getPiece().toString();

     }

     @Override
     public boolean isTileOccupied(){
         return true;

     }

     @Override
     public Piece getPiece(){
         return this.pieceOnTile;

     }


 }

}
