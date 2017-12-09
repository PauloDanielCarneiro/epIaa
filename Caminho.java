package teste;

import java.util.ArrayList;
import java.util.List;

public class Caminho{
    public List<int[]> percurso;
    public int quantItens;
    public int pesoItens;
    public int valorItens;

    public Caminho(){
        this.percurso = new ArrayList<int[]>();
        this.quantItens = 0;
        this.pesoItens = 0;
        this.valorItens = 0;
    }
}