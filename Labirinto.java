package teste;

public class Labirinto{

    public static Node paraGrafo(int[][] mapa, char flag, int x, int y, int valor, int peso, Node node){

        if (mapa[x][y] != 0){
            if (flag != 'C'){
                Node vizinho = new Node(x, y, valor, peso);
                node.setVizinho(paraGrafo(mapa, 'D', x, y-1, valor, peso, vizinho));
                node.setVizinho(paraGrafo(mapa, 'C', x+1, y, valor, peso, vizinho));
                node.setVizinho(paraGrafo(mapa, 'E', x, y+1, valor, peso, vizinho));
            }
            if (flag != 'B'){
                Node vizinho = new Node(x, y, valor, peso);
                node.setVizinho(paraGrafo(mapa, 'D', x, y-1, valor, peso, vizinho));
                node.setVizinho(paraGrafo(mapa, 'B', x-1, y, valor, peso, vizinho));
                node.setVizinho(paraGrafo(mapa, 'E', x, y+1, valor, peso, vizinho));
            }
            if (flag != 'E'){
                Node vizinho = new Node(x, y, valor, peso);
                node.setVizinho(paraGrafo(mapa, 'D', x, y-1, valor, peso, vizinho));
                node.setVizinho(paraGrafo(mapa, 'C', x+1, y, valor, peso, vizinho));
                node.setVizinho(paraGrafo(mapa, 'B', x-1, y, valor, peso, vizinho));
            }
            if (flag != 'D'){
                Node vizinho = new Node(x, y, valor, peso);
                node.setVizinho(paraGrafo(mapa, 'B', x-1, y, valor, peso, vizinho));
                node.setVizinho(paraGrafo(mapa, 'C', x+1, y, valor, peso, vizinho));
                node.setVizinho(paraGrafo(mapa, 'E', x, y+1, valor, peso, vizinho));
            }
            return node;
        }
    }
}