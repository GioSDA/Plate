package main.src;

import java.util.Scanner;

import main.src.plate.Plate;

public class Main {

	public static void main(String[] args) {
		if (args.length == 0) {
			Scanner s = new Scanner(System.in);
			
			String[] input = s.next().split(" ");
			String code = input[0];
			
			if (input.length > 1) {
				Plate p = new Plate(code, removeIndexOne(input));
				p.run();
			} else {
				Plate p = new Plate(code);
				p.run();
			}
			
			s.close();
			
		} else {
			Plate p = (args.length > 1) ? new Plate(args[0]) : new Plate(args[0],removeIndexOne(args));
			p.run();
		}
	}

	public static String[] removeIndexOne(String[] array) {
		String[] newA = new String[1024];
		
		for (int i = 1; i < array.length; i++) {
			newA[i-1] = array[i];
		}
		
		return newA;
	}
	
}
