package StackOverflow;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaySong {
public static void main(String[] args ) {
	
    String[][] songList = new String[2][2];
    
    Scanner input = new Scanner(System.in);
    
    for(int i =0 ; i < songList.length ; i++) {
        
    	boolean songRateValidity = false;

        System.out.println("Enter song:");

        String songName = input.nextLine();

    do {
      try {

        System.out.println("Enter rate");

        float rate = input.nextFloat();
        
        input.nextLine();//I used this statement because otherwise program won't waits in the song name input after entering the song rate.

        songList[i][1]=Float.toString(rate);
        
        songRateValidity = true;

	      }catch(NumberFormatException | InputMismatchException e) {
	            System.out.println("Please enter a numerical value");
	            input.next();
	
	        }
        }while(!songRateValidity);


    }

}
}