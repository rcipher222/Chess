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
    public class Pawn extends Piece {
    public static short eval_value = 100;
    public Pawn(PieceColor color) {
		this.type = PieceType.pawn;
		this.color = color;
	}
	
	public void print(){
		if (get_color() == PieceColor.White) {
			System.out.print("♙ ");
			
		} else if (get_color() == PieceColor.Black) {
			
			System.out.print("♟ ");
		}
	}
    
}




