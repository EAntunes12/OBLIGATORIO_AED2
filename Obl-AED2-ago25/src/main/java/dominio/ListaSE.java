package dominio;

public class ListaSE<T> {
    private NodoListaSE<T> inicio;
    private int cantidad;

    public ListaSE() {
        this.inicio = null;
        this.cantidad = 0;
    }

    public void agregarInicio(T dato) {
        NodoListaSE<T> nuevo = new NodoListaSE<>(dato);
        nuevo.sig = inicio;
        inicio = nuevo;
        cantidad++;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public int getCantidad() {
        return cantidad;
    }

    public NodoListaSE<T> getInicio() {
        return inicio;
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= cantidad) {
            return null;
        }

        NodoListaSE<T> actual = inicio;
        for (int i = 0; i < indice; i++) {
            actual = actual.sig;
        }

        return actual.getDato();
    }

    public void agregar(T dato) {
        NodoListaSE<T> nuevo = new NodoListaSE<>(dato);

        if (estaVacia()) {
            this.inicio = nuevo;
        } else {
            NodoListaSE<T> actual = this.inicio;
            while (actual.sig != null) {
                actual = actual.sig;
            }

            actual.sig = nuevo;
        }

        this.cantidad++;
    }
}
