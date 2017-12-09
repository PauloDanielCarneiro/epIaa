package teste;

import java.util.ArrayList;
import java.util.List;

public class Node{
    private Coordenada coordenada;
    private int valorItem;
    private int pesoItem;
    private List<Node> vizinhos;

    public Node(){
        this.valorItem = 0;
        this.pesoItem = 0;
        this.vizinhos = new ArrayList<Node>();
    }
    public Node(int x, int y, int valor, int peso){
        this.coordenada.setCoordenada(x, y);
        this.valorItem = valor;
        this.pesoItem = peso;
        this.vizinhos = new ArrayList<Node>();
    }

    public void setVizinho(Node vizinho){
        this.vizinhos.add(vizinho);
    }
    public void setValorItem(int valor){
        this.valorItem = valor;
    }
    public void setPesoItem(int peso){
        this.pesoItem = peso;
    }
    public void setCoordenada(int x, int y){
        this.coordenada.setCoordenada(x, y);
    }

    public Coordenada getCoordenada(){
        return this.coordenada;
    }
    public List<Node> getVizinhos(){
        return this.vizinhos;
    }
    public int getValorItem(){
        return this.valorItem;
    }
    public int getPesoItem(){
        return this.pesoItem;
    }
}