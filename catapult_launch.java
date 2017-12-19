
/*
 * Dylan Cole Morgen
 * dmorgen2
 * Lab TA: NOAH HELTERBRAND
 * Project 1
 * Wed 12:30 Lab
 * I did not collaborate with anyone on this assignment.
 */

import java.util.Scanner;
import java.util.Random;

public class catapult_launch {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); // Init values
		GenerateHeightDistance rand = new GenerateHeightDistance(); // Init generator class
		double distance, height, olddist = 0, oldheight = 0, difheight, difdist, angle = 0, velocity, calcheight;
		// I need to init some values in order for the program to work
		double score = 20;// starting score
		char in;
		System.out.println(
		        "Welcome to the Projectile Game!\n\nThe point of this game is to fire a projectile over a wall using a catapult\nYour starting score is 20 points!"
		                + "\nMake it to 50 to win!\nIt costs one point per firing\nIf you come close to the wall without touching it, you gain 5 points. Hit the wall close to the top and you loose 2 points\n"
		                + "If you hit it far from the top, or hit the ground, you loose 4 points. If your projectile sails far and high above the wall, you get 3 points.");

		do { // do loop to play until the user quits or they win or loose
			System.out.println("Generating a random wall...\n");
			height = rand.GenerateValue();
			distance = rand.GenerateValue();
			System.out.printf("Your wall is %.2f meters away and %.2f meters high.\n", distance, height);
			if (olddist != 0) { // See if program has been run before.
				difheight = oldheight - height;
				difdist = olddist - distance;
				if (difheight > 0) System.out.printf("That's %.2f meters shorter than your last wall\n", difheight); // comparison
				                                                                                                     // values
				else System.out.printf("That's %.2f meters higher than your last wall\n", difheight * -1);
				if (difdist > 0) System.out.printf("That's %.2f meters closer than your last wall\n", difdist);
				else System.out.printf("That's %.2f meters farther than your last wall\n", difdist * -1);
			}
			System.out.printf( // allow the user to pass
			        "Are you up to the challenge? Or are you going to be a wimp and pass this round? (Press any key to continue, or press p in shame)");
			in = scan.next().charAt(0);
			if (in != 'p' && in != 'P') { // if they don't pass continue with the program.
				do {
					System.out.println("\nEnter an angle in degrees (1\u00b0-89\u00b0): ");
					angle = scan.nextDouble();
				}
				while (angle < 1 || angle > 89); //
				System.out.println("Enter a velocity(m/s): ");
				velocity = scan.nextDouble();
				System.out.println("\nFiring Projectile...\n");
				score -= 1;
				calcheight = (distance * Math.tan(Math.toRadians(angle)) - 9.8 * distance * distance / (2 * (Math.pow(
				        velocity * Math.cos(Math.toRadians(angle)), 2)))); // nice long complicated function
				// System.out.println(calcheight);
				if (calcheight > height) { // separate cases so it doesn't say over the wall by -3 meters
					difheight = calcheight - height;
					if (difheight <= 3) {
						System.out.printf("You made it! Over the wall by %.2f meters.", difheight);
						score += 5;
					}
					if (difheight > 3) {
						System.out.printf("Plenty of room! Over the wall by %.2f meters.", difheight);
						score += 3;
					}
				}
				if (height > calcheight) {
					difheight = height - calcheight;
					if (difheight <= .2 * height) {
						System.out.printf("Not quite over. You hit the wall %.2f meters from the top.", difheight);
						score -= 2;
					}
					else {
						System.out.println("Not even close.");
						score -= 4;
					}
				}
				if (score <= 0) {
					System.out.printf("Your Score is %.0f points. \nYou loose :(", score);
					in = 'q'; // force the user to quit.
				}
				if (score >= 50) {
					System.out.printf("Your Score is %.0f points! \nCongrats!!! You won!", score);
					in = 'q';
				}
				else {
					System.out.printf("Your Score is %.0f points. \nPress any key to play another round(or q to quit)",
					        score);
					in = scan.next().charAt(0);
					olddist = distance;
					oldheight = height;
				}

			}
			else {
				System.out.println("\nWow, you must be really afraid. Here's your next challenge.\n");
			}

		}
		while (in != 'q' && in != 'Q');
		scan.close();
	}
}
