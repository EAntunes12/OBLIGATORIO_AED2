package dominio;

public class RecorridosBusqueda {
    private Medicamento medicamento;
    private int recorridos;

    public RecorridosBusqueda(Medicamento medicamento, int recorridos) {
        this.medicamento = medicamento;
        this.recorridos = recorridos;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public int getRecorridos() {
        return recorridos;
    }
}
