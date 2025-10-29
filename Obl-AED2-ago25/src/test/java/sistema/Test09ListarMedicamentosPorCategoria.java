package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test09ListarMedicamentosPorCategoria {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void ListarMedicamentoError01(){
        retorno = s.listarMedicamentosPorCategoría(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void ListarMedicamentoOK(){
        retorno = s.listarMedicamentosPorCategoría(Categoria.VENTA_LIBRE);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
}
