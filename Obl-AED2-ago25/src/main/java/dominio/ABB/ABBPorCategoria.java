package dominio.ABB;

import dominio.Medicamento;

public class ABBPorCategoria extends ABB<Medicamento> {
    @Override
    protected String getClave(Medicamento m) {
        return m.getCategoria().toString();
    }

    @Override
    protected String getNombre(Medicamento m) {
        return m.getNombre();
    }
}
