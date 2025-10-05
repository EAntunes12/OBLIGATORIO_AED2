package dominio.ABB;

import dominio.Medicamento;

public class ABBPorNombre extends ABB<Medicamento> {
    @Override
    protected String getClave(Medicamento m){
        return m.getNombre();
    }

    public ABBPorNombre(){}
}
