package sistema;

import interfaz.*;
import dominio.*;
import dominio.ABB.*;

public class    ImplementacionSistema implements Sistema  {
    private int maxFarmacias;
    private ListaSE<Farmacia> listaFarmacias;
    private ABBMedicamento arbolMedicamentos;

    // return Retorno.noImplementada();

    @Override
    public Retorno inicializarSistema(int maxFarmacias) {
        if(maxFarmacias <= 3){
            return Retorno.error1("El maximo de farmacias debe ser mayor que 3");
        }

        this.maxFarmacias = maxFarmacias;
        this.listaFarmacias = new ListaSE<>();
        this.arbolMedicamentos = new ABBMedicamento();
        //TENGO QUE INICIALIZAR EL RESTO DE LAS ESTRUCTURAS ACA;
        return Retorno.ok();
    }

    @Override
    public Retorno registrarMedicamento(String codigo, String nombre, String fechaVencimiento, Categoria categoria) {

        if(codigo == null || nombre == null || fechaVencimiento == null || categoria == null ){
            return Retorno.error1("Alguno de los datos ingresados fue nulo.");
        }

        final String codigoLimpio = codigo.trim();
        final String nombreLimpio = nombre.trim();
        final String fechaVencimientoLimpio = fechaVencimiento.trim();

        if(codigoLimpio.isEmpty() || nombreLimpio.isEmpty() || fechaVencimientoLimpio.isEmpty()){
            return Retorno.error1("Alguno de los datos ingresados fue nulo.");
        }

        String patronFecha = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!fechaVencimientoLimpio.matches(patronFecha)) {
            return Retorno.error2("La fecha debe tener el formato AAAA-MM-DD.");
        }

        if(arbolMedicamentos == null){
            this.arbolMedicamentos = new ABBMedicamento();
        }

        if (this.arbolMedicamentos.obtener(codigoLimpio) != null) {
            return Retorno.error3("Ya existe un medicamento con ese código.");
        }

        if (this.arbolMedicamentos.buscarPorNombre(nombreLimpio) != null) {
            return Retorno.error4("Ya existe un medicamento con ese nombre.");
        }

        Medicamento nuevoMedicamento = new Medicamento(codigoLimpio, nombreLimpio, fechaVencimientoLimpio, categoria);
        this.arbolMedicamentos.insertar(nuevoMedicamento);

        return Retorno.ok();
    }

    private String formatearCategoria(Categoria c) {
        //Metodo auxiliar para buscarMedicamentoPorCodigo

        switch (c) {
            case VENTA_LIBRE: return "Venta libre";
            case RECETA_COMUN: return "Receta comun";
            case RECETA_CONTROLADA: return "Receta controlada";

            default: return c.toString();
        }
    }

    @Override
    public Retorno buscarMedicamentoPorCodigo(String codigo) {
        if(codigo == null || codigo.trim().isEmpty()){
            return Retorno.error1("El codigo no puede ser vacio o nulo.");
        }

        RecorridosBusqueda recorridos = this.arbolMedicamentos.BuscarConContador(codigo);

        if(recorridos.getMedicamento() == null){
            return Retorno.error2("El medicamento no existe.");
        }

        Medicamento m =  recorridos.getMedicamento();
        String salida = m.getCodigo() + ";" +
                        m.getNombre() + ";" +
                        m.getFechaVencimiento() + ";" +
                        formatearCategoria(m.getCategoria());

        return Retorno.ok(recorridos.getRecorridos(), salida);
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
