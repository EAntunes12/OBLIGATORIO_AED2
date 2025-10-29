package dominio.ABB;

import dominio.Medicamento;

public class ABBPorCodigo extends ABB<Medicamento>{
    @Override
    protected String getClave(Medicamento m){
        return m.getCodigo();
    }

    @Override
    protected String getNombre(Medicamento m) {
        return m.getNombre();
    }
}
