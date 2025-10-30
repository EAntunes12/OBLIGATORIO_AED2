package dominio.GRAFO;

public class Conexion {
    private int tiempo;
    private int distancia;
    private int costo;

    public Conexion(int tiempo, int distancia, int costo) {
        this.tiempo = tiempo;
        this.distancia = distancia;
        this.costo = costo;
    }

    public int getTiempo() {
        return tiempo;
    }

    public int getDistancia() {
        return distancia;
    }

    public int getCosto() {
        return costo;
    }
}
