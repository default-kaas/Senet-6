package senet;

import java.util.Scanner;

public class Senet {
	Scanner scanner = new Scanner(System.in);
	
	int gameMode = -1;
	
	Player p1 = new Player();
	Player p2 = new Player();
	private boolean name1Correct=false;
	private boolean name2Correct=false;
	
	Dice dice = new Dice();
	
	private boolean firstOne = false;
	
	private int thrown;
	
	private boolean throwAgain;
	
	private int move;
	
	Board board = new Board();
	
	private String result;
	
	private Boolean won=false;
	
	public void play() {
		
		System.out.println("Welcome to Senet!");
		gameMode();
		playerName();
		playerSign();
		setup();
		if(gameMode==0) {
			firstTurn();
		}
		
		while(won!=true){
			if(p1.getColorsign() == 0) {
				if(won = board.won(p1.getColorsign())==true) {
					break;
				}
				turnP1();
				if(won = board.won(p1.getColorsign())==true) {
					break;
				}
				turnP2();
				if(won = board.won(p2.getColorsign())==true) {
					break;
				}
			}else {
				if(won = board.won(p2.getColorsign())==true) {
					break;
				}
				turnP2();
				if(won = board.won(p2.getColorsign())==true) {
					break;
				}
				turnP1();
				if(won = board.won(p1.getColorsign())==true) {
					break;
				}
			}
		}
		
		if(board.won(p1.getColorsign())==true) {
			System.out.println("Congratulations on your victory "+p1.getName());
		}else {
			System.out.println("Congratulations on your victory "+p2.getName());
		}
		
	}
	
	public void gameMode() {
		while(gameMode==-1) {
			System.out.println("Would you like to start a normal game (0) or a test position (1-4)? ->");
			try {
				gameMode = scanner.nextInt();
				if(gameMode==0||gameMode==1||gameMode==2||gameMode==3||gameMode==4) {
				}else {
				gameMode=-1;
				System.out.println("You are only allowed to uses the numbers between 0 and 4");
				}
			}catch(Exception e){
				/*Hier doet die raar doordat die telkens al die dit uitprint dit mogelijk ziet als String inplaats van een nummer */
				System.out.println("You are only allowed to uses the numbers");
				scanner = new Scanner(System.in);
			}
		}
	}
	
	public void playerName() {
		while(name1Correct!=true) {
			System.out.print("Enter the name of the first player:");
			try {
				String name1 = scanner.next();
				p1.setName(name1);
				name1Correct=true;
			}catch(Exception e) {
				System.out.println("You are only allowed to write letters");
				scanner = new Scanner(System.in);
			}
		}
		
		while(name2Correct!=true) {
			System.out.print("Enter the name of the second player:");
			try {
				String name2 = scanner.next();
				p2.setName(name2);
				name2Correct=true;
			}catch(Exception e) {
				System.out.println("You are only allowed to write letters");
				scanner = new Scanner(System.in);
			}
		}
	}
	
	public void playerSign() {
		while(firstOne!=true) {
			thrown = dice.throwSticks();
			System.out.println(p1.getName()+" has thrown "+ thrown);
			if(thrown == 1) {
				firstOne = true;
				System.out.println(p1 + " starts the game");
				p1.setColorsign(0);
				p2.setColorsign(1);
				break;
			}
			
			thrown = dice.throwSticks();
			System.out.println(p2.getName()+" has thrown "+ thrown);
			if(thrown == 1) {
				firstOne = true;
				System.out.println(p2 + " starts the game");
				p1.setColorsign(1);
				p2.setColorsign(0);
				break;
			}
		}
	}
	
	public void setup() {
		board.setStart(gameMode);
		board.startOptions();
		
		System.out.println(p1.getName()+" has sign " + board.squarSymbol(p1.getColorsign()));
		System.out.println(p2.getName()+" has sign " + board.squarSymbol(p2.getColorsign()));
	}
	
	public void firstTurn() {
		move = 9;
		if(p1.getColorsign() == 0) {
			/*This is for when p1 is X and had the first move*/
			turnP2(move);
			while(throwAgain==true) {
				turnP2();
			}
		}else{
			/*This is for when p2 is X and had the first move*/
			turnP1(move);
			while(throwAgain==true) {
				turnP1();
		}
	}
	}
	
	public void turnP1() {
		board.print();
		System.out.println(p1.getName()+" "+ board.squarSymbol(p1.getColorsign())+", press <ENTER> to throw the dice");
		scanner.nextLine();
		thrown = dice.throwSticks();
		System.out.println(p1.getName()+" "+ board.squarSymbol(p1.getColorsign())+", you have thrown "+ thrown);
		moveOptionP1();
		throwAgainMethod();
		/*This will give you the option to move a different piece or select the correct piece*/
		while(throwAgain==true) {
			board.print();
			System.out.println(p1.getName()+" "+ board.squarSymbol(p1.getColorsign())+", press <ENTER> to throw the dice");
			scanner.nextLine();
			thrown = dice.throwSticks();
			System.out.println(p1.getName()+" "+ board.squarSymbol(p1.getColorsign())+", you have thrown "+ thrown);
			moveOptionP1();
			throwAgainMethod();
		}
	}
	
	public void turnP1(int first) {
		board.print();
		System.out.println(p1.getName()+" "+ board.squarSymbol(p1.getColorsign())+", press <ENTER> to throw the dice");
		scanner.nextLine();
		thrown = dice.throwSticks();
		System.out.println(p1.getName()+" "+ board.squarSymbol(p1.getColorsign())+", you have thrown "+ thrown);
		moveOptionP1(first);
		throwAgainMethod();
		/*This will give you the option to move a different piece or select the correct piece*/
		while(throwAgain==true) {
			board.print();
			System.out.println(p1.getName()+" "+ board.squarSymbol(p1.getColorsign())+", press <ENTER> to throw the dice");
			scanner.nextLine();
			thrown = dice.throwSticks();
			System.out.println(p1.getName()+" "+ board.squarSymbol(p1.getColorsign())+", you have thrown "+ thrown);
			moveOptionP1();
			throwAgainMethod();
		}
	}
	
	public void turnP2() {
		board.print();
		System.out.println(p2.getName()+" "+ board.squarSymbol(p2.getColorsign())+", press <ENTER> to throw the dice");
		scanner.nextLine();
		thrown = dice.throwSticks();
		System.out.println(p2.getName()+" "+ board.squarSymbol(p2.getColorsign())+", you have thrown "+ thrown);
		moveOptionP2();
		throwAgainMethod();
		/*This will give you the option to move a different piece or select the correct piece*/
		while(throwAgain==true) {
			board.print();
			System.out.println(p2.getName()+" "+ board.squarSymbol(p2.getColorsign())+", press <ENTER> to throw the dice");
			scanner.nextLine();
			thrown = dice.throwSticks();
			System.out.println(p2.getName()+" "+ board.squarSymbol(p2.getColorsign())+", you have thrown "+ thrown);
			moveOptionP2();
			throwAgainMethod();
		}
	}
	
	public void turnP2(int first) {
		board.print();
		System.out.println(p2.getName()+" "+ board.squarSymbol(p2.getColorsign())+", press <ENTER> to throw the dice");
		scanner.nextLine();
		thrown = dice.throwSticks();
		System.out.println(p2.getName()+" "+ board.squarSymbol(p2.getColorsign())+", you have thrown "+ thrown);
		moveOptionP2(first);
		throwAgainMethod();
		/*This will give you the option to move a different piece or select the correct piece*/
		while(throwAgain==true) {
			board.print();
			System.out.println(p2.getName()+" "+ board.squarSymbol(p2.getColorsign())+", press <ENTER> to throw the dice");
			scanner.nextLine();
			thrown = dice.throwSticks();
			System.out.println(p2.getName()+" "+ board.squarSymbol(p2.getColorsign())+", you have thrown "+ thrown);
			moveOptionP2();
			throwAgainMethod();
		}
	}
	
	public void moveOptionP1(){
		boolean againMoveOption = true;
		System.out.println(p1.getName()+" "+ board.squarSymbol(p1.getColorsign())+", which piece do you want to move? ->");
		try {
		move = scanner.nextInt();
		result=board.move(move, thrown, p1.getColorsign(),p2.getColorsign());
		System.out.println(result);
		result =result.replaceAll("[0-9]","");
		switch(result) {
			case "\n"+"One of your own pieces occupies square ":
			case "\n"+"Attack on safe piece: ":
			case "\n"+"Attempt to jump over blockade":
			case "\n"+"This piece can't move to square " + " because not all your pieces are at the last line":
			case "\n"+"You can't move past square ":
			case "\n"+"You don't have a piece on square ":
				againMoveOption=true;
				break;
			default:
				againMoveOption=false;
				break;
		}
		}catch(Exception e) {
			System.out.println("Only numbers are allowed");
			scanner = new Scanner(System.in);
		}
		/*This will give you the option to move a different piece or select the correct piece*/
		while(againMoveOption==true) {
			System.out.println(p1.getName()+" "+ board.squarSymbol(p1.getColorsign())+", which piece do you want to move? ->");
			try {
				move = scanner.nextInt();
				result=board.move(move, thrown, p1.getColorsign(),p2.getColorsign());
				System.out.println(result);
				result =result.replaceAll("[0-9]","");
				switch(result) {
					case "\n"+"One of your own pieces occupies square ":
					case "\n"+"Attack on safe piece: ":
					case "\n"+"Attempt to jump over blockade":
					case "\n"+"This piece can't move to square " + " because not all your pieces are at the last line":
					case "\n"+"You can't move past square ":
					case "\n"+"You don't have a piece on square ":
						againMoveOption=true;
						break;
					default:
						againMoveOption=false;
						break;
				}
				}catch(Exception e) {
					System.out.println("Only numbers are allowed");
					scanner = new Scanner(System.in);
				}
		}	
	}
	
	public void moveOptionP1(int first){
		boolean againMoveOption;
		result=board.move(first, thrown, p1.getColorsign(),p2.getColorsign());
		System.out.println(result);
		result =result.replaceAll("[0-9]","");
		switch(result) {
			case "\n"+"One of your own pieces occupies square ":
			case "\n"+"Attack on safe piece: ":
			case "\n"+"Attempt to jump over blockade":
			case "\n"+"This piece can't move to square " + " because not all your pieces are at the last line":
			case "\n"+"You can't move past square ":
			case "\n"+"You don't have a piece on square ":
				againMoveOption=true;
				break;
			default:
				againMoveOption=false;
		}
		/*This will give you the option to move a different piece or select the correct piece*/
		while(againMoveOption==true) {
			System.out.println(p1.getName()+" "+ board.squarSymbol(p1.getColorsign())+", which piece do you want to move? ->");
			try {
				move = scanner.nextInt();
				result=board.move(move, thrown, p1.getColorsign(),p2.getColorsign());
				System.out.println(result);
				result =result.replaceAll("[0-9]","");
				switch(result) {
					case "\n"+"One of your own pieces occupies square ":
					case "\n"+"Attack on safe piece: ":
					case "\n"+"Attempt to jump over blockade":
					case "\n"+"This piece can't move to square " + " because not all your pieces are at the last line":
					case "\n"+"You can't move past square ":
					case "\n"+"You don't have a piece on square ":
						againMoveOption=true;
						break;
					default:
						againMoveOption=false;
						break;
				}
				}catch(Exception e) {
					System.out.println("Only numbers are allowed");
					scanner = new Scanner(System.in);
				}
		}	
	}
	
	public void moveOptionP2(){
		boolean againMoveOption = true;
		System.out.println(p2.getName()+" "+ board.squarSymbol(p2.getColorsign())+", which piece do you want to move? ->");
		try {
		move = scanner.nextInt();
		result=board.move(move, thrown, p2.getColorsign(),p1.getColorsign());
		System.out.println(result);
		result =result.replaceAll("[0-9]","");
		switch(result) {
			case "\n"+"One of your own pieces occupies square ":
			case "\n"+"Attack on safe piece: ":
			case "\n"+"Attempt to jump over blockade":
			case "\n"+"This piece can't move to square " + " because not all your pieces are at the last line":
			case "\n"+"You can't move past square ":
			case "\n"+"You don't have a piece on square ":
				againMoveOption=true;
				break;
			default:
				againMoveOption=false;
		}
		}catch(Exception e) {
			System.out.println("Only numbers are allowed");
			scanner = new Scanner(System.in);
		}
		while(againMoveOption==true) {
			System.out.println(p2.getName()+" "+ board.squarSymbol(p2.getColorsign())+", which piece do you want to move? ->");
			try {
				move = scanner.nextInt();
				result=board.move(move, thrown, p2.getColorsign(),p1.getColorsign());
				System.out.println(result);
				result =result.replaceAll("[0-9]","");
				switch(result) {
					case "\n"+"One of your own pieces occupies square ":
					case "\n"+"Attack on safe piece: ":
					case "\n"+"Attempt to jump over blockade":
					case "\n"+"This piece can't move to square " + " because not all your pieces are at the last line":
					case "\n"+"You can't move past square ":
					case "\n"+"You don't have a piece on square ":
						againMoveOption=true;
						break;
					default:
						againMoveOption=false;
				}
				}catch(Exception e) {
					System.out.println("Only numbers are allowed");
					scanner = new Scanner(System.in);
				}
		}	
	}
	
	public void moveOptionP2(int first){
		boolean againMoveOption;
		result=board.move(first, thrown, p2.getColorsign(),p1.getColorsign());
		System.out.println(result);
		result =result.replaceAll("[0-9]","");
		switch(result) {
			case "\n"+"One of your own pieces occupies square ":
			case "\n"+"Attack on safe piece: ":
			case "\n"+"Attempt to jump over blockade":
			case "\n"+"This piece can't move to square " + " because not all your pieces are at the last line":
			case "\n"+"You can't move past square ":
			case "\n"+"You don't have a piece on square ":
				againMoveOption=true;
				break;
			default:
				againMoveOption=false;
				break;
		}
		while(againMoveOption==true) {
			System.out.println(p2.getName()+" "+ board.squarSymbol(p2.getColorsign())+", which piece do you want to move? ->");
			try {
				move = scanner.nextInt();
				result=board.move(move, thrown, p2.getColorsign(),p1.getColorsign());
				System.out.println(result);
				result =result.replaceAll("[0-9]","");
				switch(result) {
					case "\n"+"One of your own pieces occupies square ":
					case "\n"+"Attack on safe piece: ":
					case "\n"+"Attempt to jump over blockade":
					case "\n"+"This piece can't move to square " + " because not all your pieces are at the last line":
					case "\n"+"You can't move past square ":
					case "\n"+"You don't have a piece on square ":
						againMoveOption=true;
						break;
					default:
						againMoveOption=false;
				}
			}catch(Exception e) {
					System.out.println("Only numbers are allowed");
					scanner = new Scanner(System.in);
			}
		}	
	}
	
	public void throwAgainMethod(){
		if(thrown==1||thrown==4||thrown==6) {
			throwAgain=true;
		}else {
			throwAgain=false;
		}
	}
	
	
}
