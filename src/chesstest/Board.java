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
import java.util.*;
public class Board extends Piece{


int CHESS_BOARD_SIZE=8;
@SuppressWarnings("UseOfObsoleteCollectionType")
        Piece[][] board=new Piece[8][8];//();
	Board parent;
	boolean turn;
	HashMap<PieceType,Short> w_pieces_number=new HashMap<PieceType,Short>();
	HashMap<PieceType,Short> b_pieces_number=new HashMap<PieceType,Short>();
	
public Board(){

}
	
	public void initialize() {
		Piece x;
		turn = false; //white's turn in the beginning

		//initialize the board configuration
		// put white pieces
		x = new Rook(PieceColor.White);
		board[0][0] = x;
		x = new Knight(PieceColor.White);
		board[0][1] = x;
		x = new Bishop(PieceColor.White);
		board[0][2] = x;
		x = new Queen(PieceColor.White);
		board[0][3] = x;
		x = new King(PieceColor.White);
		board[0][4] = x;
		x = new Bishop(PieceColor.White);
		board[0][5] = x;
		x = new Knight(PieceColor.White);
		board[0][6] = x;
		x = new Rook(PieceColor.White);
		board[0][7] = x;
		for (int i = 0; i < CHESS_BOARD_SIZE; ++i) {
			x = new Pawn(PieceColor.White);
			board[1][i] = x;
		}

		//put spaces
		for (int i = 2; i < 6; ++i) {
			for (int j = 0; j < CHESS_BOARD_SIZE; ++j) {
				board[i][j] = null;
			}
		}

		//put black pieces
		x = new Rook(PieceColor.Black);
		board[7][0] = x;
		x = new Knight(PieceColor.Black);
		board[7][1] = x;
		x = new Bishop(PieceColor.Black);
		board[7][2] = x;
		x = new Queen(PieceColor.Black);
		board[7][3] = x;
		x = new King(PieceColor.Black);
		board[7][4] = x;
		x = new Bishop(PieceColor.Black);
		board[7][5] = x;
		x = new Knight(PieceColor.Black);
		board[7][6] = x;
		x = new Rook(PieceColor.Black);
		board[7][7] = x;
		for (int i = 0; i < CHESS_BOARD_SIZE; ++i) {
			x = new Pawn(PieceColor.Black);
			board[6][i] = x;
		}
	//	w_pieces_number.setSize(6);
		w_pieces_number.put(PieceType.pawn,new Short((short)8));
		w_pieces_number.put(PieceType.rook,new Short((short)2));
		w_pieces_number.put(PieceType.bishop,new Short((short)2));
		w_pieces_number.put(PieceType.knight,new Short((short)2));                
		w_pieces_number.put(PieceType.queen,new Short((short)1));               
		w_pieces_number.put(PieceType.king,new Short((short)1));
		

//		b_pieces_number.setSize(6);
		b_pieces_number.put(PieceType.pawn,new Short((short)8));
		b_pieces_number.put(PieceType.rook,new Short((short)2));
		b_pieces_number.put(PieceType.bishop,new Short((short)2));
		b_pieces_number.put(PieceType.knight,new Short((short)2));                
		b_pieces_number.put(PieceType.queen,new Short((short)1));               
		b_pieces_number.put(PieceType.king,new Short((short)1));
		
	}
	public Board(Board b) {
		//allocate the vector
		
		
		for (int i = 0; i < CHESS_BOARD_SIZE; ++i) {
			for (int j = 0; j < CHESS_BOARD_SIZE; ++j) {
				set_piece((short)i, (short)j,b.get_piece((short)i, (short)j));
			}
		}
		//set parent of the new object
		set_parent(b);
		//set turn of the new object
		turn = !b.get_turn();

		//set piece numbers
	//	w_pieces_number.resize(6);
                w_pieces_number.put(PieceType.pawn,b.get_piece_number(PieceType.pawn, PieceColor.White));
                w_pieces_number.put(PieceType.rook,b.get_piece_number(PieceType.rook, PieceColor.White));
                w_pieces_number.put(PieceType.bishop,b.get_piece_number(PieceType.bishop, PieceColor.White));
                w_pieces_number.put(PieceType.knight,b.get_piece_number(PieceType.knight, PieceColor.White));
                w_pieces_number.put(PieceType.queen,b.get_piece_number(PieceType.queen, PieceColor.White));
                w_pieces_number.put(PieceType.king,b.get_piece_number(PieceType.king, PieceColor.White));
		
                b_pieces_number.put(PieceType.pawn,b.get_piece_number(PieceType.pawn, PieceColor.Black));
                b_pieces_number.put(PieceType.rook,b.get_piece_number(PieceType.rook, PieceColor.Black));
                b_pieces_number.put(PieceType.bishop,b.get_piece_number(PieceType.bishop, PieceColor.Black));
                b_pieces_number.put(PieceType.knight,b.get_piece_number(PieceType.knight, PieceColor.Black));
                b_pieces_number.put(PieceType.queen,b.get_piece_number(PieceType.queen, PieceColor.Black));
                b_pieces_number.put(PieceType.king,b.get_piece_number(PieceType.king, PieceColor.Black));
		

		
	}
	public short get_piece_number(PieceType type, PieceColor color) {
		if (color == PieceColor.White) {
			return w_pieces_number.get(type);
		} else {
			return b_pieces_number.get(type);
		}
	}
	public void set_piece_number(PieceType type, PieceColor color, int value) {
		if (color == PieceColor.White) {
                    short x=(short)(w_pieces_number.get(type)+value);
                w_pieces_number.put(type,new Short(x));
		} else {
			short x=(short)(b_pieces_number.get(type)+value);
                b_pieces_number.put(type,new Short(x));
		}
	}

	public void print1() {
		
		System.out.println("  a b c d e f g h");// 
		for (int i = CHESS_BOARD_SIZE - 1; i >= 0; --i) {
			System.out.print(i + 1+ " ");
			for (int j = 0; j < CHESS_BOARD_SIZE; ++j) {
				if (board[i][j] != null) {
					board[i][j].print();
				} else {
					System.out.print("- ");
				}
			}
			if (i == 6 && get_turn()) { // black's turn
	                   System.out.print("       B");
                                                       
			} else if (i == 1 && !get_turn()) { //white's turn
				System.out.print("       W");
			}
			System.out.println();
		}
		System.out.println();
	}

	Piece get_piece(short x, short y) {
		return board[x][y];
	}
	void set_piece(short x, short y, Piece p) {
		board[x][y] = p;
	}
	boolean get_turn() {
		return turn;
	}
	Piece[][] get_board() {
		return board;
	}
	Board move_piece(short src_x, short src_y, short dest_x,
			short dest_y) {
		Board new_board = new Board(this);

		Piece src = new_board.get_piece(src_x, src_y);
		Piece dest = new_board.get_piece(dest_x, dest_y);
		if (dest != null) { //in case of a yime
			new_board.set_piece_number(dest.get_type(), dest.get_color(), -1);
		}

		new_board.set_piece(dest_x, dest_y, src);
		new_board.set_piece(src_x, src_y, null);

		return new_board;
	}
	Board just_move_piece(short src_x, short src_y, short dest_x,
			short  dest_y) { //to set up a conf
		Board new_board = new Board(this);
		new_board.set_piece(dest_x, dest_y, new_board.get_piece(src_x, src_y));
		new_board.set_piece(src_x, src_y, null);
		return new_board;
	}
	Board move(short  src_x, short  src_y, short  dest_x,
			short  dest_y) {
		//Board new_board = new Board(this);
		Piece src = get_piece(src_x, src_y);
		Piece dest = get_piece(dest_x, dest_y);

		if (src == null) {
			System.out.println("Source is null.");
			return null;
		}

		if (src_x == dest_x && src_y == dest_y) {
			System.out.println("Source is equal to destination.");
			return null;
		}

		//turn check
		if (!get_turn() && src.get_color() != PieceColor.White) { //white's turn black cannot move
			System.out.println("It's White's turn wait yours.");
			return null;
		} else if (get_turn() && src.get_color() != PieceColor.Black) {
			System.out.println("It's Black's turn wait yours.."); 
			return null;
		}

		if (src.get_type() == PieceType.pawn) {
			if (src.get_color() == PieceColor.White) {
				if ((src_x - dest_x) == -1) {// move forward one step
					if (src_y == dest_y && dest == null) {// if there is no horizontal move and there is no piece then this is valid.
						return move_piece(src_x, src_y, dest_x, dest_y);
					} else if (Math.abs(src_y - dest_y) == 1 && dest != null
							&& dest.get_color() == PieceColor.Black) { //if there is a enemy in the diameter is valid.
						return move_piece(src_x, src_y, dest_x, dest_y);
					} else {
						//						 "Pawn cannot move.Err:1" ;
						return null;
					}
				} else {
					//					"Pawn cannot move.Err:2" ;
					return null;
				}
			} else {//if black
				if ((src_x - dest_x) == 1) {// move forward one step
					if (src_y == dest_y && dest == null) {// if there is no horizontal move and there is no piece then this is valid.
						return move_piece(src_x, src_y, dest_x, dest_y);
					} else if (Math.abs(src_y - dest_y) == 1 && dest != null
							&& dest.get_color() == PieceColor.White) { //if there is a enemy in the diameter is valid.
						return move_piece(src_x, src_y, dest_x, dest_y);
					} else {
						//						"Pawn cannot move.Err:3" 
						return null;
					}
				} else {
					//					 "Pawn cannot move.Err:4" 
					return null;
				}
			}
		} else if (src.get_type() == PieceType.rook) {
			//black and white is the same
			if (src_x == dest_x) {//if horizontal
				if (dest_y > src_y) { //move to right
					for (int i = src_y + 1; i < dest_y; ++i) {
						if (get_piece(src_x, (short)i) != null) {//if there is a piece between the dest and src
							//							 "Rook cannot move.Err:3" 
							return null;
						}
					}
				} else if (dest_y < src_y) { // move to left
					for (int i = src_y - 1; i > dest_y; --i) {
						if (get_piece(src_x, (short)i) != null) {//if there is a piece between the dest and src
							//							 "Rook cannot move.Err:4" 
							return null;
						}
					}
				}
				//then no piece in between
				//if the dest is enemy then eat it =D
				if (dest == null) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else if (dest.get_color() != src.get_color()) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else {//cannot eat the same color
					//					 "Rook cannot move.Err:5" 
					return null;
				}
			} else if (src_y == dest_y) {//if vertical
				if (dest_x > src_x) { //move up
					for (int i = src_x + 1; i < dest_x; ++i) {
						if (get_piece((short)i, src_y) != null) {//if there is a piece between the dest and src
							//							 "Rook cannot move.Err:6" 
							return null;
						}
					}
				} else if (dest_x < src_x) { //move down
					for (int i = src_x - 1; i > dest_x; --i) {
						if (get_piece((short)i, src_y) != null) {//if there is a piece between the dest and src
							//							 "Rook cannot move.Err:7" 
							return null;
						}
					}
				}
				//then no piece in between
				//if the dest is enemy then eat it =D
				if (dest == null) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else if (dest.get_color() != src.get_color()) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else {//cannot eat the same color
					//					 "Rook cannot move.Err:8" 
					return null;
				}
			} else {
				//				 "Rook cannot move. Err:3" 
				return null;
			}
		} else if (src.get_type() == PieceType.bishop) {
			//black or white it it the same
			if (Math.abs(src_x - dest_x) == Math.abs(src_y - dest_y)) { //difference should be the same
				if (dest_x > src_x) {
					if (dest_y > src_y) {//move right-top
						for (int i = 1; i < Math.abs(dest_x - src_x); ++i) {
							if (get_piece((short)(src_x + i),(short)(src_y + i)) != null) {//there is a piece in between
								//								 "Bishop cannot move. Err:1" 
								return null;
							}
						}
					} else if (dest_y < src_y) {// move right-bottom
						for (int i = 1; i < Math.abs(dest_x - src_x); ++i) {
							if (get_piece((short)(src_x + i),(short)(src_y - i)) != null) {//there is a piece in between
								//								 "Bishop cannot move. Err:2" 
								return null;
							}
						}
					} else {
						//						 "Bishop cannot move. Err:3" 
						return null;
					}
					//then no piece in between
					//if the dest is enemy then eat it =D
					if (dest == null) {
						return move_piece(src_x, src_y, dest_x, dest_y);
					} else if (dest.get_color() != src.get_color()) {
						return move_piece(src_x, src_y, dest_x, dest_y);
					} else {//cannot eat the same color
						//						 "Bishop cannot move.Err:4" 
						return null;
					}
				} else if (dest_x < src_x) {
					if (dest_y > src_y) {//move left-top
						for (int i = 1; i < Math.abs(dest_x - src_x); ++i) {
							if (get_piece((short)(src_x - i),(short)(src_y + i)) != null) {//there is a piece in between
								//								 "Bishop cannot move. Err:5" 
								return null;
							}
						}
					} else if (dest_y < src_y) {// move left-bottom
						for (int i = 1; i < Math.abs(dest_x - src_x); ++i) {
							if (get_piece((short)(src_x - i), (short)(src_y - i)) != null) {//there is a piece in between
								//								 "Bishop cannot move. Err:6" 
								return null;
							}
						}
					} else {
						//						 "Bishop cannot move. Err:7" 
						return null;
					}
					//then no piece in between
					//if the dest is enemy then eat it =D
					if (dest == null) {
						return move_piece(src_x, src_y, dest_x, dest_y);
					} else if (dest.get_color() != src.get_color()) {
						return move_piece(src_x, src_y, dest_x, dest_y);
					} else {//cannot eat the same color
						//						 "Bishop cannot move.Err:8" 
						return null;
					}
				} else {
					//					 "Bishop cannot move. Err:9" 
					return null;
				}
			} else {
				//				 "Bishop cannot move. Err:10" 
				return null;
			}
		} else if (src.get_type() == PieceType.knight) {
			if (Math.abs(dest_x - src_x) == 2 && Math.abs(dest_y - src_y) == 1) {
				if (dest == null) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else if (dest.get_color() != src.get_color()) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else {// cannot eat the same color
					//					 "Knight cannot move. Err:9" 
					return null;
				}
			} else if (Math.abs(dest_x - src_x) == 1 && Math.abs(dest_y - src_y) == 2) {
				if (dest == null) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else if (dest.get_color() != src.get_color()) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else {// cannot eat the same color
					//					 "Knight cannot move. Err:10" 
					return null;
				}
			} else {// invalid move for knight
				//				 "Knight cannot move. Err:11" 
				return null;
			}
		} else if (src.get_type() == PieceType.queen) {
			if (src_x == dest_x) {//if horizontal
				if (dest_y > src_y) { //move to right
					for (int i = src_y + 1; i < dest_y; ++i) {
						if (get_piece(src_x, (short)i) != null) {//if there is a piece between the dest and src
							//							 "Queen cannot move.Err:1" 
							return null;
						}
					}
				} else if (dest_y < src_y) { // move to left
					for (int i = src_y - 1; i > dest_y; --i) {
						if (get_piece(src_x, (short)i) != null) {//if there is a piece between the dest and src
							//							 "Queen cannot move.Err:2" 
							return null;
						}
					}
				}
				//then no piece in between
				//if the dest is enemy then eat it =D
				if (dest == null) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else if (dest.get_color() != src.get_color()) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else {//cannot eat the same color
					//					 "Queen cannot move.Err:3" 
					return null;
				}
			} else if (src_y == dest_y) {//if vertical
				if (dest_x > src_x) { //move up
					for (int i = src_x + 1; i < dest_x; ++i) {
						if (get_piece((short)i, src_y) != null) {//if there is a piece between the dest and src
							//							 "Queen cannot move.Err:4" 
							return null;
						}
					}
				} else if (dest_x < src_x) { //move down
					for (int i = src_x - 1; i > dest_x; --i) {
						if (get_piece((short)i, src_y) != null) {//if there is a piece between the dest and src
							//							 "Queen cannot move.Err:5" 
							return null;
						}
					}
				}
				//then no piece in between
				//if the dest is enemy then eat it =D
				if (dest == null) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else if (dest.get_color() != src.get_color()) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else {//cannot eat the same color
					//					 "Queen cannot move.Err:6" 
					return null;
				}
			} //black or white it it the same
			else if (Math.abs(src_x - dest_x) == Math.abs(src_y - dest_y)) { //difference should be the same
				if (dest_x > src_x) {
					if (dest_y > src_y) {//move right-top
						for (int i = 1; i < Math.abs(dest_x - src_x); ++i) {
							if (get_piece((short)(src_x + i), (short)(src_y + i)) != null) {//there is a piece in between
								//								 "Queen cannot move. Err:7" 
								return null;
							}
						}
					} else if (dest_y < src_y) {// move right-bottom
						for (int i = 1; i < Math.abs(dest_x - src_x); ++i) {
							if (get_piece((short)(src_x + i),(short)(src_y - i)) != null) {//there is a piece in between
								//								 "Queen cannot move. Err:8" 
								return null;
							}
						}
					} else {
						//						 "Queen cannot move. Err:9" 
						return null;
					}
					//then no piece in between
					//if the dest is enemy then eat it =D
					if (dest == null) {
						return move_piece(src_x, src_y, dest_x, dest_y);
					} else if (dest.get_color() != src.get_color()) {
						return move_piece(src_x, src_y, dest_x, dest_y);
					} else {//cannot eat the same color
						//						 "Queen cannot move.Err:10" 
						return null;
					}
				} else if (dest_x < src_x) {
					if (dest_y > src_y) {//move left-top
						for (int i = 1; i < Math.abs(dest_x - src_x); ++i) {
							if (get_piece((short)(src_x - i),(short) (src_y + i)) != null) {//there is a piece in between
								//								 "Queen cannot move. Err:11" 
								return null;
							}
						}
					} else if (dest_y < src_y) {// move left-bottom
						for (int i = 1; i < Math.abs(dest_x - src_x); ++i) {
							if (get_piece((short)(src_x - i),(short) (src_y - i)) != null) {//there is a piece in between
								//								 "Queen cannot move. Err:12" 
								return null;
							}
						}
					} else {
						//						 "Queen cannot move. Err:13" 
						return null;
					}
					//then no piece in between
					//if the dest is enemy then eat it =D
					if (dest == null) {
						return move_piece(src_x, src_y, dest_x, dest_y);
					} else if (dest.get_color() != src.get_color()) {
						return move_piece(src_x, src_y, dest_x, dest_y);
					} else {//cannot eat the same color
						//						 "Queen cannot move.Err:14" 
						return null;
					}
				} else {
					//					 "Queen cannot move. Err:15" 
					return null;
				}
			} else {
				//				 "Queen cannot move. Err:16" 
				return null;
			}
		} else if (src.get_type() == PieceType.king) {
			if (Math.abs(dest_x - src_x) == 1 && Math.abs(dest_y - src_y) == 1) { //diagonal move
				if (dest == null || dest.get_color() != src.get_color()) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else {// cannot eat the same color
					//					 "King cannot move. Err:15" 
					return null;
				}
			} else if (Math.abs(dest_x - src_x) == 1 && dest_y - src_y == 0) { //vertical move
				if (dest == null || dest.get_color() != src.get_color()) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else {// cannot eat the same color
					//					 "King cannot move. Err:15" 
					return null;
				}
			} else if (Math.abs(dest_y - src_y) == 1 && dest_x - src_x == 0) {
				if (dest == null || dest.get_color() != src.get_color()) {
					return move_piece(src_x, src_y, dest_x, dest_y);
				} else {// cannot eat the same color
					//					 "King cannot move. Err:15" 
					return null;
				}
			} else {// invalid move for king
				//				 "King cannot move. Err:16" 
				return null;
			}
		} else {
			return null;
		}

		//return null;
	}
	//	vector<Board> list_all_moves(){
	//		return ;
	//	}
	public Board get_parent() {
		return parent;
	}
	public void set_parent(Board parent) {
		this.parent = parent;
	}
	public int evaluate_board(PieceColor color) {
		int eval = 0;
		eval += (Pawn.eval_value * get_piece_number(PieceType.pawn, color));
		eval += (Rook.eval_value * get_piece_number(PieceType.rook, color));
		eval += (Knight.eval_value * get_piece_number(PieceType.knight, color));
		eval += (Bishop.eval_value * get_piece_number(PieceType.bishop, color));
		eval += (Queen.eval_value * get_piece_number(PieceType.queen, color));
		eval += (King.eval_value * get_piece_number(PieceType.king, color));
		return eval;
	}
	Vector<Board> list_all_moves() {
		Vector<Board> moves = new Vector<Board> ();
		Board temp = null;
		PieceColor turn = get_turn() ? PieceColor.Black : PieceColor.White;

		for (int i = 0; i < CHESS_BOARD_SIZE; ++i) {
			for (int j = 0; j < CHESS_BOARD_SIZE; ++j) {
				if (get_piece((short)i, (short)j) != null && get_piece((short)i,(short) j).get_color()
						== turn) {
					for (int k = 0; k < CHESS_BOARD_SIZE; ++k) {
						for (int l = 0; l < CHESS_BOARD_SIZE; ++l) {
							if (i == k && j == l) {
								continue;
							}
							if ((temp = move((short)i,(short) j, (short)k, (short)l)) != null) {
								moves.add(temp);
							}
						}
					}
				}
			}
		}

		return moves;
	}

	
}


