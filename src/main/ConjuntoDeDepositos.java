package main;

import java.io.*;
import java.util.Scanner;

import edu.unlam.pa.EjercicioOIA;

public class ConjuntoDeDepositos extends EjercicioOIA{
	
	private Deposito[] depositos;
	private int vol;
	
	public ConjuntoDeDepositos(File archIn, File archOut)
	{
		super(archIn,archOut);
		
		//File archIn;
		Scanner sc;
		try
		{
			//archIn = new File("C:\\Users\\Audioslave\\Desktop\\in.txt");
			sc = new Scanner(entrada);
			
			int cantDepositos = sc.nextInt();
			depositos = new Deposito[cantDepositos];
			//sc.nextLine(); // Para vaciar el buffer. //
			for(int i=0; i<cantDepositos; i++)
			{
				depositos[i] = new Deposito(sc.nextInt(),sc.nextInt());
			}
			vol = sc.nextInt();
			
			try
			{
				sc.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	
	public void resolver()
	{
		nucleoDeLaSolucion();
	}
	
	
	private void nucleoDeLaSolucion()
	{
		int i;
		int volAux = 0;
		int difDeNivel = 0;
		for( i = 0; i < depositos.length && vol>0; i++ )
		{
			int igualesSiguientes = 0;
			if(i < depositos.length-1) // Si no se entra en este if, significa que ya estamos en el ultimo deposito. //
			{
				igualesSiguientes = depositos[i].cantidadDeDepositosSiguientesDeMismaProfundidad(depositos, i+1); // Con esto sé la cantidad de depositos siguientes son de misma profundidad que el actual. //
			}
			difDeNivel = 0;
			if(i < depositos.length-1 && i+igualesSiguientes < depositos.length-1) // Si no entra aca significa que no hay que sacar la diferencia entre niveles porque estamos en el ultimo deposito. //
			{
				difDeNivel = depositos[i].getProf()-depositos[i+1+igualesSiguientes].getProf(); // Obtengo la diferencia de nivel entre el deposito actual y el siguiente que tenga profundidad (nivel) distinta.
			}
			else
			{
				difDeNivel = depositos[i].getProf();
			}
	
			
			volAux = 0;
			for(int j = 0; j <= i+igualesSiguientes && vol>0; j++)
			{
				volAux += depositos[j].obtenerVolumenALlenar(difDeNivel);
			}
			
			//if(volAux<=vol)
			//{
				vol-=volAux;
			//}
			
			if(igualesSiguientes!=0)
			{
				i+=igualesSiguientes;
			}
		}
		
		if(vol<0) // Si se entra aca significa que el "volumen" no llega a completar depositos enteros y, por ende, aca hago la logica para saber en que nivel de llenado quedan los depositos. //
		{
			//int h;
			//if(i==1)
			//{
				//h=i-1;
				//int profundidadANivelDelPiso = Math.abs(vol); 
			//}
			//else
			//{
				//h=i-1;
				float proporcionVacio = Math.abs(vol) / volAux;
				int profundidadANivelDelPiso = (int)((float)depositos[i-1].getProf()-((float)difDeNivel-(difDeNivel*proporcionVacio)));
				generarArchDeSalidaNoDdesborde( i , profundidadANivelDelPiso );
			//}		
		}
		
		if(vol==0)
		{
			generarArchDeSalidaNoDdesborde(i,0);
		}
		
		if(vol>0)
		{
			generarArchDeSalidaSiDesborde(vol);
		}		
	}
	
	
	
	private void generarArchDeSalidaNoDdesborde(int cantDepositosLlenos, int profundidadDesdeElSuelo) // "profundidadDesdeElSuelo hace referencia a la distancia que hay entre el piso y el nivel de agua de los depositos llenados. //
	{
		FileWriter fw;
		PrintWriter pw;
		try
		{
			fw = new FileWriter(salida);
			pw = new PrintWriter(fw);
			pw.println(cantDepositosLlenos);
			pw.println(profundidadDesdeElSuelo);
			try
			{
				fw.close();
				pw.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	private void generarArchDeSalidaSiDesborde(int volumenRebasado)
	{
		FileWriter fw;
		PrintWriter pw;
		try
		{
			fw = new FileWriter(salida);
			pw = new PrintWriter(fw);
			pw.println("Rebasan: " + volumenRebasado);
			try
			{
				fw.close();
				pw.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	
	
	
	

}
