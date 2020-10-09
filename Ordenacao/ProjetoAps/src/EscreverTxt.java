import java.io.*;

public class EscreverTxt {

    public boolean write(String txt,String caminho, boolean cadastra)//se for cadastrar um novo registro, true.
    {
        try{        	//C:\Users\Hugo Tavares\Documents\ProjetoAps\nome.txt
            FileWriter arq        = new FileWriter(caminho, cadastra);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.write(txt);
            gravarArq.close();
            arq.close();
            return true;
        }catch(IOException err){
            System.err.println(err.getMessage());
            return false;
        }       
    }
}//class
