package senet;

import java.util.ArrayList;
import java.util.Random;

public class Dice {
		private ArrayList<Integer> sticks = new ArrayList<>();
		private Random rand = new Random();
	
		public int throwSticks() {
			int amount = 0;
			for(int i=0;i<4;i++){
				int r = rand.nextInt(2);
				sticks.add(i,r);
			}
			for(int s: sticks){
				amount = amount+s;
			}
			if(amount == 0){
				amount = 6;
			}
			sticks.clear();
			return amount;
		}
}
