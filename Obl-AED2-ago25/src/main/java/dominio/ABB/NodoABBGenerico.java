package dominio.ABB;

public class NodoABBGenerico<T> {
    private T dato;
    private NodoABBGenerico<T> izq;
    private NodoABBGenerico<T> der;

    public NodoABBGenerico(T dato) {
        this.dato = dato;
    }

    public T getDato() { return dato; }
    public NodoABBGenerico<T> getIzq() { return izq; }
    public NodoABBGenerico<T> getDer() { return der; }
    public void setIzq(NodoABBGenerico<T> izq) { this.izq = izq; }
    public void setDer(NodoABBGenerico<T> der) { this.der = der; }
}
