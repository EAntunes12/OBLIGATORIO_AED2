package dominio.COLA;

public interface ICola<T> {
    void encolar(T dato);
    T desencolar();
    boolean esVacia();
    boolean estaLlena();
    int cantElementos();
    T frente();
    void imprimirDatos();
}
