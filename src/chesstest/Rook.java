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
public class Rook extends Piece {
    
	static short eval_value = 500;
   public Rook(PieceColor color) {
		this.type = PieceType.rook;
		this.color = color;
	}
    public void print() {
		if (get_color() == PieceColor.White) {
			System.out.print("♖ ");
			
		} else if (get_color() == PieceColor.Black) {
			System.out.print("♜ ");
		
		}
	}
    
}

	
