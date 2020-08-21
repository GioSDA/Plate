package main.src.plate;

public class Plate {

	String code;
	String[] input;
	
	public Plate(String code, String[] input) {
		this.code=code;
		this.input=input;
	}
	
	public Plate(String code) {
		this.code = code;
		this.input = null;
	}
	
	public void run() {
		PlateInterpreter PI = new PlateInterpreter(input);
		PI.interpret(code);
	}
	
}
