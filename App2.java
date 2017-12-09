package teste;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class App2{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader ler = ler(args[0]);
        String linha = ler.readLine();
        int linhas, numItens, contador;
    }
    
    public static BufferedReader ler(String path){
        FileReader arq = null;
		try {
			arq = new FileReader(path);
		} catch (FileNotFoundException e) {
			System.err.printf("Erro ao abrir o arquivo\n", e.getMessage());
            System.exit(0);
		}
        return new BufferedReader(arq);
    }
}