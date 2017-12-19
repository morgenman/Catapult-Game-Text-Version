
/*
 * Dylan Cole Morgen
 * dmorgen2
 * Lab TA: NOAH HELTERBRAND
 * Project 1
 * Wed 12:30 Lab
 * I did not collaborate with anyone on this assignment.
 */

import java.util.Random;

public class GenerateHeightDistance {
	Random			rand	= new Random();
	private double	height, distance;

	public double GenerateValue() {
		double value = rand.nextFloat() * 50;
		return value;
	}

}
