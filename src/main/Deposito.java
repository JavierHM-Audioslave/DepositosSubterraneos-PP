package main;

public class Deposito {
	
	private int base;
	private int prof;
	
	public Deposito(int base, int prof)
	{
		this.base = base;
		this.prof = prof;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public int getProf() {
		return prof;
	}

	public void setProf(int prof) {
		this.prof = prof;
	}
	
	public int cantidadDeDepositosSiguientesDeMismaProfundidad(Deposito[] depositos, int index) // "index" es el indice donde tengo que empezar a buscar en el array "depositos". //
	{
		int cant=0;
		int i = index;
		while(prof == depositos[i].prof && i<=depositos.length)
		{
			cant++;
			i++;
		}
		
		return cant;
	}
	
	
	public int obtenerVolumenALlenar(int difDeNivel)
	{
		return difDeNivel*base;
	}

}
