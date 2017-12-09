package teste;

public class Coordenada{
    private int x;
    private int y;

    public Coordenada(){
        this.x = 0;
        this.y = 0;
    }
    public Coordenada(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setCoordenada(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
}