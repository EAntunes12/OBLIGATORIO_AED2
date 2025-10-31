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
        nuevo.setSig(inicio);
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
            actual = actual.getSig();
        }

        return actual.getDato();
    }

    public void agregar(T dato) {
        NodoListaSE<T> nuevo = new NodoListaSE<>(dato);

        if (estaVacia()) {
            this.inicio = nuevo;
        } else {
            NodoListaSE<T> actual = this.inicio;
            while (actual.getSig() != null) {
                actual = actual.getSig();
            }

            actual.setSig(nuevo);
        }

        this.cantidad++;
    }

    public void ordenar() {
        if (inicio == null) return;

        boolean huboCambio;
        do {
            huboCambio = false;
            NodoListaSE<T> actual = inicio;

            while (actual.getSig() != null) {
                Comparable<T> datoActual = (Comparable<T>) actual.getDato();
                if (datoActual.compareTo(actual.getSig().getDato()) > 0) {
                    // Intercambiar
                    T temp = actual.getDato();
                    actual.setDato(actual.getSig().getDato());
                    actual.getSig().setDato(temp);
                    huboCambio = true;
                }
                actual = actual.getSig();
            }
        } while (huboCambio);
    }


}
