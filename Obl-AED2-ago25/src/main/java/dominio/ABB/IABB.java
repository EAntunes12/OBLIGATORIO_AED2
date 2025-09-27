package dominio.ABB;

import dominio.Medicamento;

public interface IABB {

    boolean insertar(Medicamento nuevoMedicamento);

    boolean pertenece(String codigo);

    void imprimirAsc();

    void imprimirDes();

    Medicamento obtener(String codigo);

    Medicamento buscarPorNombre(String nombre);



}
