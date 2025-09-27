package sistema;

import interfaz.*;
import dominio.*;

public class    ImplementacionSistema implements Sistema  {
    private int maxFarmacias;
    private ListaSE<Farmacia> listaFarmacias;
    private ListaSE<Medicamento> listaMedicamentos;

    // return Retorno.noImplementada();

    @Override
    public Retorno inicializarSistema(int maxFarmacias) {
        if(maxFarmacias <= 3){
            return Retorno.error1("El maximo de farmacias debe ser mayor que 3");
        }

        this.maxFarmacias = maxFarmacias;
        this.listaFarmacias = new ListaSE<>();

        return Retorno.ok();
    }

    @Override
    public Retorno registrarMedicamento(String codigo, String nombre, String fechaVencimiento, Categoria categoria) {
        if(codigo == null || nombre == null || fechaVencimiento == null || categoria == null || codigo.trim().isEmpty() || nombre.trim().isEmpty() || fechaVencimiento.trim().isEmpty()){
            return Retorno.error1("Alguno de los datos ingresados fue nulo.");
        }

        String patronFecha = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!fechaVencimiento.matches(patronFecha)) {
            return Retorno.error2("La fecha debe tener el formato AAAA-MM-DD.");
        }

        if(listaMedicamentos == null){
            this.listaMedicamentos = new ListaSE<>();
        }

        for (int i = 0; i < listaMedicamentos.getCantidad(); i++) {
            Medicamento m = listaMedicamentos.obtener(i);
            if (m.getCodigo().equals(codigo)) {
                return Retorno.error3("Ya existe un medicamento con ese codigo.");
            }
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                return Retorno.error4("Ya existe un medicamento con ese nombre.");
            }
        }

        Medicamento nuevoMedicamento = new Medicamento(codigo, nombre, fechaVencimiento, categoria);
        this.listaMedicamentos.agregar(nuevoMedicamento);

        return Retorno.ok();
    }

    @Override
    public Retorno buscarMedicamentoPorCodigo(String codigo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarMedicamentosPorCodigoAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarMedicamentosPorCodigoDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarMedicamentoPorNombre(String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarMedicamentosPorNombreAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarMedicamentosPorNombreDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarMedicamentosPorCategoría(Categoria unaCategoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarFarmacia(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoOrigen, String codigoDestino, int tiempo, int distancia, int costo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno redFarmaciasPorCantidadDeConexiones(String codigoOrigen, int cantidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno analizarFarmacia(String codigoOrigen) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno calcularRutaMenorTiempo(String codigoOrigen, String codigoDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno calcularRutaMenorDistancia(String codigoOrigen, String codigoDestino) {
        return Retorno.noImplementada();
    }




}
