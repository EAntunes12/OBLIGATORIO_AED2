package dominio.ABB;

import dominio.Medicamento;

public class ABBPorCodigo extends ABB<Medicamento>{
    @Override
    protected String getClave(Medicamento m){
        return m.getCodigo();
    }

    public ABBPorCodigo(){}
}
