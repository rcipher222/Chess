/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesstest;

/**
 *
 * @author rb
 */



public class Piece {
   public enum PieceType {
	pawn, rook, bishop, knight, queen, king
}
   public enum PieceColor {
	White, Black;
}
    public PieceType type;
    public PieceColor color;
    int PAWN=0,KNIGHT=2,BISHOP=3,QUEEN=4,KING=5;
    public static short eval_value;

	 void print(){};
	PieceColor get_color() {
		return color;
	}
	PieceType get_type() {
		return type;
	}
	
	
}

