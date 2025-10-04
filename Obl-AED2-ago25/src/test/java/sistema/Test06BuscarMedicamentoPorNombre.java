package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test06BuscarMedicamentoPorNombre {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void buscarMedicamentoOk() {

        s.registrarMedicamento("COD01", "Medicamento01", "2026-09-01", Categoria.VENTA_LIBRE);
        retorno = s.buscarMedicamentoPorNombre("Medicamento01");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(1, retorno.getValorInteger());
        assertEquals(Salidas.MED01, retorno.getValorString());
    }

    @Test
    void buscarMedicamentoError1(){
        retorno = s.buscarMedicamentoPorNombre("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.buscarMedicamentoPorNombre("      ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.buscarMedicamentoPorNombre(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void buscarMedicamentoError2(){
        retorno = s.buscarMedicamentoPorNombre("Medicamento99");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        s.registrarMedicamento("COD01", "Medicamento01", "2026-09-01", Categoria.VENTA_LIBRE);
        retorno = s.buscarMedicamentoPorNombre("Medicamento99");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
}
