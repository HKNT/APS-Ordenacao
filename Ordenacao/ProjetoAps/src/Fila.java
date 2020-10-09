public class Fila 
{
	int inicio;
	int fim;
	int tamanho;
	int qntdeElementos;
	String f[];
	
	public Fila()
	{
		inicio = fim = -1;
		tamanho = 10;
		f = new String[tamanho];
		qntdeElementos = 0;
	}
	
	public boolean estaVazia() 
	{
		if(qntdeElementos == 0)
		{
			return true;
		}
		return false;
	}
	
	public boolean estaCheia() 
	{
		if(qntdeElementos == tamanho-1) 
		{
			return true;
		}
		return false;
	}
	
	public void enqueue(String e)
	{
		if(!estaCheia()) 
		{
			if(inicio == -1) 
			{
				inicio = 0;
			}
			fim++;
			f[fim] = e;
			qntdeElementos++;
		}
	}
	
	public void dequeue() 
	{
		if(!estaVazia())
		{
			inicio++;
			qntdeElementos--;
		}
	}
	
	public void mostrar() 
	{
		String elementos = "";
		
		for(int i=inicio;i<=fim;i++)
		{
			elementos += f[i] + "\n" ;
		}
		
		System.out.println(elementos);
	}
	
	public int tamanhoFila() 
	{
		return qntdeElementos;
	}

	public String listarElementos(int num) 
	{
		String elementos = f[num];
		
		return elementos;
	}
}
