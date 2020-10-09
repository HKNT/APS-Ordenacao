import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class LerTxt {
	
    protected ArrayList<String> arqTxt;

    public LerTxt()
    {
        arqTxt     = new ArrayList<>();
    }

    public void lerArquivo(String caminho)
    {    	
        String linha   = "";
        try{
            FileReader arq        = new FileReader(caminho);
            BufferedReader buffer = new BufferedReader(arq);
            while (buffer.ready()){
                linha = buffer.readLine();
                arqTxt.add(linha);
            }//while
            arq.close();
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
    }//testeLer

    //Precisa criar um arraylist para receber este arraylist
    public ArrayList<String> getArqTxt() 
    {
        if(arqTxt.size() != 0){
            return arqTxt;
        }else {
            System.err.println("Vetor de leitura inexistente");
        }
        return null;
    }

//    public static void main(String rs[]){
//        LerTxt obj = new LerTxt();
//
//        ArrayList<String> t = new ArrayList<>();
//
//        obj.lerArquivo();
//        t = obj.getArqTxt();
//        System.out.println(t.toString());
//    }
    
    public String linhasDocumento(int linha){
        String informacao = "";

        if(linha >= 0){
            informacao = arqTxt.get(linha);
        }else{
            System.out.println("Valor digitado é menor que 0!");
        }
        return informacao;
    }

}//class
