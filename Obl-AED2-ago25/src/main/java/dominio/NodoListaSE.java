package dominio;

public class NodoListaSE<T> {
    private T dato;
    private NodoListaSE<T> sig;

    public NodoListaSE(T dato) {
        this.dato = dato;
        this.sig = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoListaSE<T> getSig() {
        return sig;
    }

    public void setSig(NodoListaSE<T> sig) {
        this.sig = sig;
    }
}
