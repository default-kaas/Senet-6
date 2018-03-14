package senet;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {
	HashMap<Integer, String> squares = new HashMap<Integer, String>();
	private int start;
	
	public void setStart(int start) {
		this.start=start;
	}
	
	public String squarSymbol(int number) {
		if(number == 0) {
			return "X";
		}else if(number == 1) {
			return "O";
		}else {
			return ".";
		}
	}
	
	public void startOptions() {
		if(start == 0) {
			for(int a1=1;a1<11;a1++) {
				if((a1 % 2)==0&& a1!= 10) {
					squares.put(a1, squarSymbol(0));
				}else if(a1==10) {
					squares.put(a1, squarSymbol(2));
					squares.put(11, squarSymbol(0));
				}else {
					squares.put(a1, squarSymbol(1));
				}
			}
			for(int a2=12;a2<31;a2++){
				squares.put(a2, squarSymbol(2));
			}
		}else if(start == 1) {
			for(int b1=1;b1<31;b1++) {
				switch(b1) {
				case 1: case 8: case 16: case 23:
					squares.put(b1, squarSymbol(0));
					break;
				case 2: case 4: case 6: case 10:
				case 12: case 14: case 17: case 18: case 20:
				case 21: case 24: case 25: case 26:
					squares.put(b1, squarSymbol(1));
					break;
				default:
					squares.put(b1, squarSymbol(2));
					break;
				}
			}
		}else if(start == 2) {
			for(int c1=1;c1<31;c1++) {
				switch(c1) {
				case 28:
					squares.put(c1, squarSymbol(0));
					break;
				case 22: case 23: case 24:
					squares.put(c1, squarSymbol(1));
					break;
				default:
					squares.put(c1, squarSymbol(2));
					break;
				}
			}
		}else if(start == 3) {
			for(int d1=1;d1<31;d1++) {
				switch(d1) {
				case 13: case 25: case 26: case 28: case 29:
					squares.put(d1, squarSymbol(0));
					break;
				case 6: case 18: case 22:
					squares.put(d1, squarSymbol(1));
					break;
				default:
					squares.put(d1, squarSymbol(2));
					break;
				}
			}
		}else if(start ==4) {
			for(int e1=1;e1<28;e1++) {
				squares.put(e1, squarSymbol(2));
			}
			squares.put(28, squarSymbol(0));
			squares.put(29, squarSymbol(1));
			squares.put(30, squarSymbol(2));
		}else {
			System.out.println("This option is not available");
		}
	}
	
	/*This is for testing problems in the board class */
	public String test() {
		String signMoveFrom="O";
		int playerMove = 1;
		if(signMoveFrom.equals(squarSymbol(playerMove))){
			return "Yes";
		}else {
			return "No";
		}
		
	}
	
	public String move(int number,int thrown, int playerMove, int playerN) {
		String signMoveFrom = squares.get(number);
		String squareBefore1Field;
		String squareAfter1Field;
		
		/*This checks of the player has a piece on this square*/
		 if(signMoveFrom.equals(squarSymbol(playerMove))){
			 int moveDistance = number + thrown;
			 String signMoveTo = squares.get(moveDistance);
			 
			 if(canMovePiece(thrown,playerMove,playerN)==true) {
				 if(signMoveTo == null) {
					 return "\n"+"You can't move past square 30";
				 }
				 
				 /*This checks of the player already has a piece on this square*/
				 if(signMoveTo.equals(squarSymbol(playerMove))) {
					 return "\n"+"One of your own pieces occupies square " + moveDistance;
					 
					 /*This checks of the other player has a piece on this square*/ 
				 }else if(signMoveTo.equals(squarSymbol(playerN))) {
					 /*These two get the signs of the two directly adjacent squares to the square where the player wants to place their piece*/
					 squareBefore1Field = squares.get(moveDistance-1);
					 squareAfter1Field = squares.get(moveDistance+1);
					 
					 /*This checks of the other player has a piece on the square before*/ 
					 if(squareBefore1Field.equals(squarSymbol(playerN))) {
						 return "\n"+"Attack on safe piece: " + moveDistance;
						 
					 /*This checks of the other player has a piece on the square after*/ 
					 }else if(squareAfter1Field.equals(squarSymbol(playerN))){
						 return "\n"+"Attack on safe piece: " + moveDistance;
					 }
				 }
				 
				 /*This is to check the amount of opponentsigns the piece jumps over, that are adjacent to each other*/
				 ArrayList<Integer> amountOfOpponentSigns = new ArrayList<Integer>();
				 String signPlayerN = squarSymbol(playerN);
				 for(int i=number;i<moveDistance;i++){
					 if(signPlayerN.equals(squarSymbol(i))) {
						 amountOfOpponentSigns.add(i);
						 if(amountOfOpponentSigns.size()==3) {
							 break;
						 }else {
							 amountOfOpponentSigns.clear();
						 } 
					 }
				 }
				
				if(amountOfOpponentSigns.size()==3) {
					return "\n"+"Attempt to jump over blockade";
				}else if(signMoveTo.equals(squarSymbol(playerN))){
					squares.put(number,squarSymbol(playerN));
					squares.put(moveDistance,squarSymbol(playerMove));
					return "";
				}else if(moveDistance==27) {
					String placeSquare =squarSymbol(2);
					int place=1;
					for(int j=1;j<30;j++) {
						if(placeSquare.equals(squares.get(j))) {
							place = j;
							break;
						}
					}
					squares.put(number, squarSymbol(2));
					squares.put(place,squarSymbol(playerMove));
					return "";
			 	}else if(moveDistance==30){
			 		String signPlayer = squarSymbol(playerMove);
			 		boolean allowedPiece = true;
			 		for(int x=1;x<21;x++) {
			 			if(signPlayer.equals(squares.get(x))) {
			 				allowedPiece = false;
			 				break;
			 			}
			 		}
			 		if(allowedPiece==true) {
			 			squares.put(number,squarSymbol(2));
			 			
			 			return "";
			 		}{
			 			return "\n"+"This piece can't move to square " + moveDistance+" because not all your pieces are at the last line";
			 		}
			 	}else if(moveDistance>30){
			 		return "\n"+"You can't move past square 30";
			 	}else{
					squares.put(number,squarSymbol(2));
					squares.put(moveDistance,squarSymbol(playerMove));
					return "";
				}
			 }else {
				 return "\n"+"You can't move any piece" ;
			 }
		 }else{
			return "\n"+"You don't have a piece on square " + number; 
		 }
	}
	
	public boolean canMovePiece(int thrown, int playerMove, int playerN) {
		ArrayList<Integer> piecePostion = new ArrayList<Integer>();
		String pieceSign = squarSymbol(playerMove);
		String squareBefore1Field;
		String squareAfter1Field;
		int totalAmount=0;
		
		/*This looks at all the pieces and their positions */
		for(int i=1;i<31;i++) {
			if(pieceSign.equals(squares.get(i))) {
				piecePostion.add(i);
			}
		}
		
		/*This remembers the total amount of piece of the player on the board */
		totalAmount=piecePostion.size();
		
		/*This loop controls the piece can move.*/
		for(int a=0;a<piecePostion.size();a++) {
			int moveDistance = piecePostion.get(a) + thrown;
			String signMoveTo = squares.get(moveDistance);
			if(signMoveTo == null) {
				piecePostion.remove(a);	 
			
			/*CHECKED*/
			/*This checks of the player already has a piece on this square*/
			}else if(signMoveTo.equals(squarSymbol(playerMove))) {
				piecePostion.remove(a);	 
			/*Problem is here*/
			/*This checks of the other player has a piece on this square*/ 
			}else if(signMoveTo.equals(squarSymbol(playerN))) {
				/*Problem is here^*/
				/*These two get the signs of the two directly adjacent squares to the square where the player wants to place their piece*/
				squareBefore1Field = squares.get(moveDistance-1);
				squareAfter1Field = squares.get(moveDistance+1);
						 
				/*This checks of the other player has a piece on the square before*/ 
				if(squareBefore1Field.equals(squarSymbol(playerN))) {
					piecePostion.remove(a);		 
							 
				/*This checks of the other player has a piece on the square after*/ 
				}else if(squareAfter1Field.equals(squarSymbol(playerN))){
					piecePostion.remove(a);		 
				}
		    }
				 
			/*This is to check the amount of opponentsigns the piece jumps over, that are adjacent to each other*/
			ArrayList<Integer> amountOfOpponentSigns = new ArrayList<Integer>();
			String signPlayerN = squarSymbol(playerN);
			if(a<piecePostion.size()) {
				if(piecePostion.get(a)!=null) {
				int startSquare = piecePostion.get(a);
					for(int b=startSquare;b<moveDistance;b++){
						if(signPlayerN.equals(squarSymbol(b))) {
								 amountOfOpponentSigns.add(b);
						 
								 if(amountOfOpponentSigns.size()==3) {
									 break;
								 }else {
									 amountOfOpponentSigns.clear();
								 } 
							 }
					}
					if(amountOfOpponentSigns.size()==3) {
					piecePostion.remove(a);
					}else if(moveDistance==30){
				 		String signPlayer = squarSymbol(playerMove);
				 		boolean allowedPiece = true;
				 		for(int x=1;x<21;x++) {
				 			if(signPlayer.equals(squares.get(x))) {
				 				allowedPiece = false;
				 				break;
				 			}
				 		} 
				 		if(allowedPiece==true) {
				 			
				 			/*return "";*/
				 		}else{
				 			piecePostion.remove(a);
				 			/*return "\n"+"This piece can't move to square " + moveDistance+" because not all your pieces are at the last line";*/
				 		}
				 	}else if(moveDistance>30){
				 		/*return "\n"+"You can't move past square 30";*/
				 			piecePostion.remove(a);
				 	}
						
				}
			}
		}
		if(piecePostion.size()==0) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean won(int player) {
		int leftOverPieces = 0;
		String playerSign = squarSymbol(player);
		for(int i=1;i<31;i++) {
			if(playerSign.equals(squares.get(i))) {
				leftOverPieces= leftOverPieces+1;
			}
		}
		if(leftOverPieces==0) {
			
			return true;
		}else {
			return false;
		}
	}
	
	public void print() {	
		System.out.println("+----------+");
		for(int a1=0; a1<12; a1++){
			if(a1==0||a1==11) {
				System.out.print("|");
			}else{
				System.out.print(squares.get(a1));
			}
		}
		System.out.println();
		for(int b1=21; b1>9; b1--){
			if(b1==21||b1==10) {
				System.out.print("|");
			}else{
				System.out.print(squares.get(b1));
			}
		}
		System.out.println();
		for(int c1=20; c1<32; c1++){
			if(c1==20||c1==31) {
				System.out.print("|");
			}else{
				System.out.print(squares.get(c1));
			}
		}
		System.out.println();
		System.out.println("+----------+");
	}	
		
}


