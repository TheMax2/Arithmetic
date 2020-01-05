package backEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Timer;

public class Calculator {

	private int myDifficulty;
	private int myAnswer;
	private int myNumb;
	private int myTurns;
	private int maxTurns;
	private byte mySign;
	
	public Calculator() {
		myDifficulty = 5;
		maxTurns = 10;
		initialize();
	}
	
	public Calculator(int theDifficulty, int theNumbOfTurns) {
		myDifficulty = theDifficulty;
		maxTurns = theNumbOfTurns;
		initialize();
	}
	
	private void initialize() {
		myAnswer = 0;
		myNumb = 0;
		myTurns = 0;
		mySign = 1;
	}
	
	public void startRound() {
		
	}
	
	public void makeEasyMove() {
		myTurns++;
		if((int) Math.floor(Math.random() * 2 ) == 1) {
			mySign = 1;
		} else {
			mySign = -1;
		}
		myAnswer = 0;
		myNumb = 0;
		System.out.print(getSignToString()+" ");
		System.out.println(myNumb+" = "+myAnswer);
	}
	
	public void makeMove() {

		myTurns++;
		int randomNumb = (int) Math.floor(Math.random() * (myDifficulty + 1));
		if((int) Math.floor(Math.random() * 2 ) == 1) {
			mySign = 1;
		} else {
			mySign = -1;
		}
		while(randomNumb * mySign + myAnswer < 0 || randomNumb == myNumb) {
			randomNumb = (int) Math.floor(Math.random() * (myDifficulty + 1));
			if((int) Math.floor(Math.random() * 2 ) == 1) {
				mySign = 1;
			} else {
				mySign = -1;
			}		
		}
		myNumb = randomNumb;
		
		myAnswer += myNumb * mySign;
		System.out.print(getSignToString()+" ");
		System.out.println(myNumb+" = "+myAnswer);

	}
	
	public int getTurns() {
		return myTurns;
	}
	
	public int getNumb() {
		return myNumb;
	}
	
	public int getAnswer() {
		return myAnswer;
	}
	
	public int getMaxTurns() {
		return maxTurns;
	}
	
	public String getSignToString() {
		if (mySign == -1) return "-";
		return "+";
	}
	
	public int getDifficulty() {
		return myDifficulty;
	}
	
	public void setMaxTurns(int theMaxTurns) {
		maxTurns = theMaxTurns;
	}
}
