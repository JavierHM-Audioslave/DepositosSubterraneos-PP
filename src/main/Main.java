package main;

import java.io.File;

import edu.unlam.pa.EjercicioOIA;

public class Main {

	public static void main(String[] args) {
		
		File archIn = new File("in2.txt");
		File archSal = new File("out2.txt");
		EjercicioOIA ej = new ConjuntoDeDepositos(archIn,archSal);
		ej.resolver();
	}

}
