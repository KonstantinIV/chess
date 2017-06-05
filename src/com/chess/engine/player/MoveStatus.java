package com.chess.engine.player;

/**
 * Created by Konstantin on 06-Jun-17.
 */
public enum MoveStatus {



    DONE{
        @Override
        boolean isDone(){
            return  true;
        }
    },
    ILLEGAL_MOVE {
        @Override
        boolean isDone() {
            return false;
        }
    },
    LEAVES_PLAYER_IN_CHECK {
        @Override
        boolean isDone() {
            return false;
        }
    };

    abstract boolean isDone();

}
