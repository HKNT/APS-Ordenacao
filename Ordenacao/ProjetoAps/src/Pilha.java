
public class Pilha
{
	int inicio;
	int fim;
	int tamanho;
	int qntdeElementos;
	String p[];
	
	public Pilha()
	{
		inicio = fim = -1;
		tamanho = 10;
		p = new String[tamanho];
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
	
	public void push(String e)
	{
		if(!estaCheia()) 
		{
			if(inicio == -1) 
			{
				inicio = 0;
			}
			fim++;
			p[fim] = e;
			qntdeElementos++;
		}
	}
	
	public void pop() 
	{
		if(!estaVazia())
		{			
			fim--;
			qntdeElementos--;	
			System.out.println(fim);			
		}
	}
	
	public void mostrar() 
	{
		String elementos = "";
		
		for(int i=fim;i>=0;i--)
		{
			elementos += p[i] + "\n" ;
		}
	}
	
	public String listarElementos(int num) 
	{
		String elementos = p[num-1];
		
		return elementos;
	}
	
	public int tamanhoPilha() 
	{
		return qntdeElementos;
	}
}
