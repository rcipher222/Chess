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
import java.io.*;
import java.util.*;
public class ChessTest{
    /**
     * @param args the command line arguments
     */
public static Board MinMax(Board board, short depth_limit) {
	return MaxMove(board, depth_limit, (short)0);
}
public static Board MaxMove(Board board, short depth_limit, short depth) {
	Vector<Board> moves;
	Board best_real_move = null;
	Board best_move = null;
	Board move = null;
        Piece y=new Piece();
	if (depth >= depth_limit) {//if depth limit is reached
		return board;
	} else {
		moves = board.list_all_moves();
                Iterator<Board> it=moves.iterator();
		while(it.hasNext()) {
                    Board x=it.next();
			move = MinMove(x, depth_limit, (short)(depth+1));
			if (best_move == null || move.evaluate_board(y.color.Black)
					> best_move.evaluate_board(y.color.Black)) {
				best_move = move;
				best_real_move = x;
			}
		}
		return best_real_move;
	}
}
public static Board MinMove(Board board, short depth_limit, short depth) {
	Vector<Board> moves;
	Board best_move = null;
	Board best_real_move = null;
	Board move = null;
        Piece y=new Piece();
	if (depth >= depth_limit) {//if depth limit is reached
		return board;
	} else {
		moves = board.list_all_moves();
		Iterator<Board> it=moves.iterator();
		while(it.hasNext()) {
                    Board x=it.next();
			move = MaxMove(x, depth_limit, (short)(depth+1));
			if (best_move == null || move.evaluate_board(y.color.White)
					< best_move.evaluate_board(y.color.White)) {
				best_move = move;
				best_real_move = x;
			}
		}
		return best_real_move;
	}
}

public static Board ABMinMax(Board board, short depth_limit) {
	return ABMaxMove(board, depth_limit, (short)1,(short) 0,(short) 0);
}
public static Board ABMaxMove(Board board, short depth_limit, short depth, int a, int b) {
	Vector<Board> moves;
         Piece y=new Piece();
	Board best_move = null;
	Board best_real_move = null;
	Board move = null;
	int alpha = a,beta = b;

	if (depth >= depth_limit) {//if depth limit is reached
		return board;
	} else {
		moves = board.list_all_moves();
                
		Iterator<Board> it=moves.iterator();
		while(it.hasNext()) {
                    Board x=it.next();
			move = ABMinMove(x, depth_limit, (short)(depth+1), alpha, beta);
			if (best_move == null || move.evaluate_board(y.color.Black)
					> best_move.evaluate_board(y.color.Black)) {
				best_move = move;
				best_real_move = x;
				alpha = move.evaluate_board(y.color.Black);
			}
			if(beta > alpha){
				return best_real_move;
			}
		}
		return best_real_move;
	}
}
public static Board ABMinMove(Board board, short depth_limit, short depth, int a, int b) {
	Vector<Board> moves;
	Board best_move = null;
	Board best_real_move = null;
	Board move = null;
	int alpha = a,beta = b;
         Piece y=new Piece();

	if (depth >= depth_limit) {//if depth limit is reached
		return board;
	} else {
		moves = board.list_all_moves();
		Iterator<Board> it=moves.iterator();
		while(it.hasNext()) {
                    Board x=it.next();
			move = ABMaxMove(x, depth_limit, (short)(depth+1), alpha, beta);
			if (best_move == null || move.evaluate_board(y.color.White)
					< best_move.evaluate_board(y.color.White)) {
				best_move = move;
				best_real_move = x;
				beta = move.evaluate_board(y.color.White);
			}
			if(beta < alpha){
				return best_real_move;
			}
		}
		return best_real_move;
	}
}
public static void main(String args[])throws IOException {

	System.out.println( "To play the game, basically when you move is asked enter your move as source letter source number destination letter destination number.\nFor example, 'a 2 a 3' will move your piece at a 2 to a 3.\n");
	
	Scanner sc=new Scanner(System.in);
	Board b = new Board();
	Board test;
	b.initialize();
	System.out.println( "Board:");
	b.print1();
	char src_x = 0, src_y = 0, dest_x = 0, dest_y = 0;

  
	while (true) {
		System.out.println("Enter your move: ");
		
                src_y=sc.next().charAt(0);
                src_x=sc.next().charAt(0);
                dest_y=sc.next().charAt(0);
                dest_x=sc.next().charAt(0);
		System.out.println();
		//integerize
		src_x -= '1';
		dest_x -= '1';

		src_y -= 'a';
		dest_y -= 'a';
              test = b.move((short)src_x,(short) src_y,(short) dest_x,(short) dest_y);
		if (test == null) {
			System.out.println("Invalid move. Try again.");// << endl << endl;
		} else {
			b = test;
	//		b.print();
                         b.print1();
		}
		//System.out.println("White: " + b.evaluate_board(White));
		//System.out.println("Black: " + b.evaluate_board(Black));

		//enemy turn

		b = ABMinMax(b,(short)6);
	
		if(b!=null){
			b.print1();
		}
		else{
			System.out.println("null dondu exiting");
			break;
		}
	}

	
}

}
    
    

