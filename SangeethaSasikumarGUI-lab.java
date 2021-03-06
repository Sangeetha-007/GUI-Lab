package edu.cuny.brooklyn.cisc3120;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;


public class Gui {
	private static int maxInteger = 16;
	private static int limit = (int)(Math.log(maxInteger)/Math.log(2));
	private static Random rand = new Random();
	private static int target = rand.nextInt(maxInteger)+1;
	private static int guess = 0;
	private static int attempts = 0;
	private static String prevGuessesStr = "";
	//Initiates new Array of Size Limit
	public static int[] prevGuesses = new int [limit];

	private static JLabel label2 = new JLabel("Previous Guesses: (" + attempts + " of " + limit + "): " + prevGuessesStr);
	private static JLabel guessResult = new JLabel(" ");

	static class ButtonListener implements ActionListener {
		private int value;
		public void actionPerformed(ActionEvent e) {
			if (attempts < limit && guess != target) { //Game Continues if attempts != Limit
				guess=this.value;
				prevGuesses[attempts]=guess;
				
				if (attempts == 0) {
					prevGuessesStr = Integer.toString(guess);
				}
				else {
								prevGuessesStr = prevGuessesStr + ", " + Integer.toString(guess);
							}
							attempts++;

							label2.setText("Previous Guesses (" + attempts + " of " + limit + "): " + prevGuessesStr);


							if(guess==target) {
								guessResult.setText("You Win!");
							}
							else if (attempts == limit) {
								guessResult.setText("You Lose!");
							}
							else {
								if(guess>target){
									guessResult.setText("Too High!");
								}
								else{
									guessResult.setText("Too Low!");
								}
							}
						}
					}
					public ButtonListener (int value){
						this.value = value;
					}
				}


				public static void main(String[] args) {
					JFrame frame = new JFrame("The Guessing Game!");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




					JPanel topPanel = new JPanel();
					topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

					JLabel label1 = new JLabel("Guess a number between 1 & " + Integer.toString(maxInteger));
					label1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 60));
					topPanel.add(label1);


					topPanel.add(label2);


					guessResult.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
					topPanel.add(guessResult);


					// new panel for buttons
					JPanel buttonPanel = new JPanel(new GridLayout(4, 4));


					Button b;


					for (int i=1; i<=16; i++){
						b = new Button(Integer.toString(i));
						b.addActionListener(new ButtonListener(i));
						buttonPanel.add(b);
					}


					topPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
					frame.add(topPanel, BorderLayout.NORTH);
					buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
					frame.add(buttonPanel, BorderLayout.SOUTH);


					// Where on the screen do you want your window to show?
					frame.setLocation(300, 300);


					// Calling `pack` will resize the window to be just large enough to hold all of its components.
					frame.pack();
					// An alternative is to set the size manually:
					// frame.setSize(800, 600);


					// Your window is not visible until you explicitly tell swing to show it.
					// This allows you to do all of your setup before the user sees anything.
					frame.setVisible(true);
				}
			}


