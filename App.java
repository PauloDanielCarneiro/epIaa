package teste;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;

public class App {
    public static char[][] mapaChar;
    public static Celula cell;
    public static void main(String[] args) throws IOException {
        BufferedReader ler = ler(args[0]);
        String linha = ler.readLine();
        int linhas = Integer.parseInt(linha.split(" ")[0]);
        int colunas = Integer.parseInt(linha.split(" ")[1]);
        int numItens;
        int contador = 0;
        mapaChar = new char[linhas][colunas];

        //monta mapa
        for (linha = ler.readLine(); linha != null && contador < linhas; linha = ler.readLine()) {
            mapaChar[contador] = linha.toCharArray();
            contador++;
        }

        //define itens
        
        numItens = Integer.parseInt(linha);
        int[][] itens = new int[numItens][4];
        for (contador = 0, linha = ler.readLine(); linha != null && contador < numItens; linha = ler.readLine()) {
            String[] item = linha.split(" ");
            itens[contador][0] = Integer.parseInt(item[0]);
            itens[contador][1] = Integer.parseInt(item[1]);
            itens[contador][2] = Integer.parseInt(item[2]);
            itens[contador][2] = Integer.parseInt(item[3]);
            contador++;
        }
        //capacidade e posições
        int capacidade = Integer.parseInt(linha);
        String[] posicaoInicio = ler.readLine().split(" ");
        String[] posicaoFim = ler.readLine().split(" ");
        int posicaoInicioX = Integer.parseInt(posicaoInicio[0]);
        int posicaoInicioY = Integer.parseInt(posicaoInicio[1]);
        int posicaoFinalX = Integer.parseInt(posicaoFim[0]);
        int posicaoFinalY = Integer.parseInt(posicaoFim[1]);

        ler.close();

        boolean[][] mapaBool = TransformaBool(mapaChar, posicaoInicioX, posicaoInicioY, posicaoFinalX, posicaoFinalY);
        cell = new Celula(posicaoInicioX, posicaoInicioY);
        cell.addVizinha(verificaPosicao(mapaBool, posicaoInicioX, posicaoInicioY, posicaoFinalX, posicaoFinalY, ' '));
        for(int i = 0; i < cell.celulas.size(); i++) {
            System.out.println(cell.celulas.get(i));
        }
        System.out.println();

        /*if (args[1].equals("1")){
            List<Celula> path = findShortestPath(mapaBool, posicaoInicioX, posicaoInicioY, posicaoFinalX, posicaoFinalY);
            if (path == null)
            {
                System.out.println("No path possible");
                return;
            }
            int tamanho = 1 + path.size();
            System.out.println(tamanho);
            System.out.println(posicaoInicioX + " " + posicaoInicioY);
            for (Celula celula : path){
                String celulaString = celula.toString(); 
                System.out.println(celulaString);
            }
            System.out.println("0 0 0");
        }*/

    }

    public static BufferedReader ler(String path) {
        FileReader arq = null;
        try {
            arq = new FileReader(path);
        } catch (FileNotFoundException e) {
            System.err.printf("Erro ao abrir o arquivo\n", e.getMessage());
            System.exit(0);
        }
        return new BufferedReader(arq);
    }

    public static boolean[][] TransformaBool(char[][] mapa, int iniciox, int inicioy, int finalx, int finaly) {
        boolean[][] mapaBool = new boolean[mapa.length][mapa[0].length];
        for (int aux = 0; aux < mapa.length; aux++)
            for (int aux2 = 0; aux2 < mapa[0].length; aux2++)
                if (aux == iniciox && aux2 == inicioy) mapaBool[aux][aux2] = false;
                else if (aux == finalx && aux2 == finaly) mapaBool[aux][aux2] = false;
        else if (mapa[aux][aux2] == '.') mapaBool[aux][aux2] = false;
        else if (mapa[aux][aux2] == 'X') mapaBool[aux][aux2] = true;
        return mapaBool;

    }

    private static List<Celula> findShortestPath(boolean[][] maze, int iniciox, int inicioy, int finalx, int finaly) {
        int[][] nivelMatrix = new int[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; ++i){
            for (int j = 0; j < maze[0].length; ++j) {
                nivelMatrix[i][j] = (maze[i][j] == true ? -1 : 0);
                System.out.print(nivelMatrix[i][j] + " ");
            }
            System.out.println();
        }
        LinkedList <Celula> queue = new LinkedList <Celula> ();
        Celula start = new Celula(iniciox, inicioy);
        Celula end = new Celula(finalx, finaly);
        queue.add(start);
        nivelMatrix[start.row][start.col] = 1;

        while (!queue.isEmpty()) {
            Celula celula = queue.poll();
            if (celula == end)
                break;
            int nivel = nivelMatrix[celula.row][celula.col];
            Celula[] proxCelulas = new Celula[4];
            proxCelulas[3] = new Celula(celula.row, celula.col - 1);
            proxCelulas[2] = new Celula(celula.row - 1, celula.col);
            proxCelulas[1] = new Celula(celula.row, celula.col + 1);
            proxCelulas[0] = new Celula(celula.row + 1, celula.col);

            for (Celula proxCelula: proxCelulas) {
                if (proxCelula.row < 0 || proxCelula.col < 0)
                    continue;
                if (proxCelula.row == maze.length ||
                    proxCelula.col == maze[0].length)
                    continue;
                if (nivelMatrix[proxCelula.row][proxCelula.col] == 0) {
                    queue.add(proxCelula);
                    nivelMatrix[proxCelula.row][proxCelula.col] = nivel + 1;
                }
            }
        }
        if (nivelMatrix[end.row][end.col] == 0)
            return null;
        LinkedList <Celula> path = new LinkedList <Celula> ();
        Celula celula = end;
        while (!celula.equals(start)) {
            path.push(celula);
            int nivel = nivelMatrix[celula.row][celula.col];
            Celula[] proxCelulas = new Celula[4];
            proxCelulas[0] = new Celula(celula.row + 1, celula.col);
            proxCelulas[1] = new Celula(celula.row, celula.col + 1);
            proxCelulas[2] = new Celula(celula.row - 1, celula.col);
            proxCelulas[3] = new Celula(celula.row, celula.col - 1);
            for (Celula proxCelula: proxCelulas) {
                if (proxCelula.row < 0 || proxCelula.col < 0)
                    continue;
                if (proxCelula.row == maze.length ||
                    proxCelula.col == maze[0].length)
                    continue;
                if (nivelMatrix[proxCelula.row][proxCelula.col] == nivel - 1) {
                    celula = proxCelula;
                    break;
                }
            }
        }
        return path;
    }

    public static Celula verificaPosicao(boolean[][] maze, int iniciox, int inicioy, int finalx, int finaly, char flag){
        if (iniciox == finalx && inicioy == finaly || !seTrataDeUmaParede(maze, iniciox, inicioy)){
            return null;
        }
        if (!verificaCoordenada(maze, iniciox, inicioy, finalx, finaly)) 
            return null;
        Celula proxCelulas = new Celula(iniciox, inicioy);
        if (inicioy - 1 >= 0 && flag != 'E'){ 
            proxCelulas.addVizinha(verificaPosicao( maze, iniciox, inicioy - 1, finalx, finaly, 'D'));
        }
        if (iniciox - 1 >= 0 && flag != 'C') {
            proxCelulas.addVizinha(verificaPosicao(maze, iniciox - 1, inicioy, finalx, finaly, 'B'));
        }
        if (inicioy + 1 < maze[0].length && flag != 'D') {
            proxCelulas.addVizinha(verificaPosicao(maze, iniciox, inicioy + 1, finalx, finaly, 'E'));
        }
        if (iniciox + 1 < maze.length && flag != 'B') {
            proxCelulas.addVizinha(verificaPosicao(maze, iniciox + 1, inicioy, finalx, finaly, 'C'));
        }
        return proxCelulas;
    }

    public static boolean verificaCoordenada(boolean[][] maze, int iniciox, int inicioy, int finalx, int finaly){
        if(iniciox >= 0 && inicioy >= 0 && finalx < maze.length && finaly < maze[0].length) return true;
        return false;
    }

    public static boolean seTrataDeUmaParede(boolean[][] maze, int x, int y){
        return !maze[x][y];
    }
    
    private static void printMaze(boolean[][] maze) {
        for (int i = 0; i < maze.length; ++i) {
            for (int j = 0; j < maze[i].length; ++j) {
                if (maze[i][j])
                    System.out.print("X");
                else
                    System.out.print("_");
            }
            System.out.println();
        }
    }



    private static class Celula {
        public int row;
        public int col;
        public List<Celula> celulas;

        public Celula(int row, int column) {
            this.row = row;
            this.col = column;
            celulas = new ArrayList<Celula>();
        }

        public void addVizinha(Celula c){
            if (c != null){
                this.celulas.add(c);
            }
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if ((obj == null) || (obj.getClass() != this.getClass()))
                return false;
            Celula celula = (Celula) obj;
            if (row == celula.row && col == celula.col)
                return true;
            return false;
        }

        @Override
        public String toString() {
            return  row + " " + col;
        }
    }
}