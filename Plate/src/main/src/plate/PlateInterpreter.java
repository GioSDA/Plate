package main.src.plate;

import java.util.ArrayList;
import java.util.Arrays;

public class PlateInterpreter {
	
	int inputNum = -1;
	String[] input;
	
	ArrayList<Character> nilads = new ArrayList<Character>(Arrays.asList('i','π', 'a'));
	ArrayList<Character> monads = new ArrayList<Character>(Arrays.asList('|', 'p', 'n', '"', 'l', 's'));
	ArrayList<Character> dyads = new ArrayList<Character>(Arrays.asList('+','-','*','/', '^', '%'));
	ArrayList<Character> numbers = new ArrayList<Character>(Arrays.asList('᐀','ᐁ','ᐂ','ᐃ','ᐄ','ᐅ','ᐆ','ᐇ','ᐈ','ᐉ','ᐋ','ᐌ','ᐍ','ᐎ','ᐏ','ᐐ','ᐑ','ᐒ','ᐓ','ᐔ','ᐕ','ᐖ','ᐗ','ᐘ','ᐙ','ᐚ','ᐛ','ᐜ','ᐝ','ᐞ','ᐟ','ᐠ','ᐡ','ᐢ','ᐣ','ᐤ','ᐥ','ᐦ','ᐧ','ᐨ','ᐩ','ᐪ','ᐫ','ᐬ','ᐭ','ᐮ','ᐯ','ᐰ','ᐱ','ᐲ','ᐳ','ᐴ','ᐵ','ᐶ','ᐷ','ᐸ','ᐹ','ᐺ','ᐻ','ᐼ','ᐽ','ᐾ','ᐿ','ᑀ','ᑁ','ᑂ','ᑃ','ᑄ','ᑅ','ᑆ','ᑇ','ᑈ','ᑉ','ᑋ','ᑌ','ᑍ','ᑎ','ᑏ','ᑐ','ᑑ','ᑒ','ᑓ','ᑔ','ᑕ','ᑖ','ᑗ','ᑘ','ᑙ','ᑚ','ᑛ','ᑜ','ᑝ','ᑞ','ᑟ','ᑠ','ᑡ','ᑢ','ᑣ','ᑤ','ᑥ'));

	public PlateInterpreter(String[] input) {
		this.input=input;
	}
	
	public PlateInterpreter() {
		this.input = null;
	}

	public void interpret(String code) {
		if (!code.contains("p")) code = "p" + code;
		
		if (monads.contains(code.charAt(0)) || dyads.contains(code.charAt(0))) {
			eval(code);
		}
	}
	
	public Object eval(String code) {
		char c = code.charAt(0);
		if (numbers.contains(c)) {			
			double num = 0;
			for (int i = 0; i < code.length(); i++) {
				char ca = code.charAt(i);
				if (monads.contains(ca) || dyads.contains(ca) || ca == ',') {
					return num;
				} else {
					num *= 100;
					num += numbers.indexOf(ca);
				}
			}
			return num;
		} else if (monads.contains(c)) {
			switch (c) {
			case '|':
				return absolute(eval(code.substring(1)));
			case 'p':
				Object a = eval(code.substring(1));
				System.out.println(a);
				return a;
			case 'n':
				return negate(eval(code.substring(1)));
			case '"':
				return evalString(code.substring(1));
			case 'l':
				return initializeList(eval(code.substring(1)));
			case 's':
				return split(eval(code.substring(1)));
			}
		} else if (dyads.contains(c)) {
			switch (c) {
			case '+':
				return add(eval(code.substring(1)),eval(code.substring(evalLength(code.substring(1))+2)));
			case '-':
				return subtract(eval(code.substring(1)),eval(code.substring(evalLength(code.substring(1))+2)));
			case '*':
				return multiply(eval(code.substring(1)),eval(code.substring(evalLength(code.substring(1))+2)));
			case '/':
				return divide(eval(code.substring(1)),eval(code.substring(evalLength(code.substring(1))+2)));
			case '^':
				return exponentiate(eval(code.substring(1)),eval(code.substring(evalLength(code.substring(1))+2)));
			case '%':
				return modulo(eval(code.substring(1)),eval(code.substring(evalLength(code.substring(1))+2)));
			}
		} else if (nilads.contains(c)) {
			switch(c) {
			case 'i':
				inputNum++;
				return input();
			case 'π':
				return pi();
			case 'a':
				return initializeListEmpty();
			}
		}
		return new Object();
	}
	
	public String evalString(String code) {
		String a = "";
		for (int i = 0; i < code.length(); i++) {
			char c = code.charAt(i);
			if (c == ',' || c == '\n') return a;
			else a += c;
		}
		return a;
	}
	
	public int evalLength(String code) {
		int a = 0;
		for (int i = 0; i < code.length(); i++) {
			if (dyads.contains(code.charAt(i))) {
				a++;
			} else if (code.charAt(i) == ',') {
				a--;
			}
			
			if (a < 0) return i;
 		}
		return code.length();
	}
	
	////////INPUT////////
	
	public Object input() {
		String a = input[inputNum];
		if (a.matches("[0-9]+")) return Double.parseDouble(a);
		return a;
	}
	
	////////VALUES////////
	
	public Object pi() {
		return Math.PI;
	}

	public Object initializeListEmpty() {
		return new ArrayList<Object>();
	}
	
	////////SINGLE VALUE FUNCTIONS////////
	
	public Object absolute(Object n) {
		if (n instanceof Double) return Math.abs((double) n);
		return 0;
	}
	
	public Object negate(Object n) {
		if (n instanceof Double) return -1.0d * (double) n;
		return 0;
	}
	
	public Object initializeList(Object n) {
		return new ArrayList<Object>(Arrays.asList(n));
	}
	
	public Object split(Object n) {
		if (n instanceof String) return new ArrayList<Object>(Arrays.asList(((String) n).split("")));
		if (n instanceof Double) { 
			ArrayList<Object> al = new ArrayList<Object>();
			for (char a : ((Double) n).toString().toCharArray()) { if (a != '.') al.add(Character.getNumericValue(a)); }
			return al;
		}
		return 0;
	}
	
	////////DOUBLE VALUE EXPRESSIONS////////
	
	public Object add(Object a, Object b) {
		if (a instanceof Double && b instanceof Double) return (Double) ((double) a + (double) b);
		if (a instanceof String && b instanceof String) return (String) a + (String) b;
		if (a instanceof ArrayList && b instanceof ArrayList) {
			((ArrayList<Object>) a).addAll((ArrayList<Object>) b);
			return a;
		}
		if (a instanceof ArrayList && (b instanceof Double) || (b instanceof String)) {
			((ArrayList<Object>) a).add(b);
			return a;
		}
		return 0;
	}
	
	public Object subtract(Object a, Object b) {
		if (a instanceof Double && b instanceof Double) return (Double) ((double) a - (double) b);
		return 0;
	}
	
	public Object multiply(Object a, Object b) {
		if (a instanceof Double && b instanceof Double) return (Double) ((double) a * (double) b);
		if (a instanceof String && b instanceof Double) {
			String s = "";
			for (int i = 0; i < (Double) b; i++) {
				s += a;
			}
			return s;
		}
		return 0;
	}
	
	public Object divide(Object a, Object b) {
		if (a instanceof Double && b instanceof Double) return (Double) ((double) a / (double) b);
		return 0;
	}
	
	public Object exponentiate(Object a, Object b) {
		if (a instanceof Double && b instanceof Double) return (Double) Math.pow((double) a, (double) b);
		return 0;
	}
	
	public Object modulo(Object a, Object b) {
		if (a instanceof Double && b instanceof Double) return (Double) (double) a % (double) b;
		return 0;
	}
	
}
