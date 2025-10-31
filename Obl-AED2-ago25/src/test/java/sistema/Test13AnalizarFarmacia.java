package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test13AnalizarFarmacia {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(5);
    }

    @Test
    void AnalizarFarmaciaError01(){
        retorno = s.analizarFarmacia(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.analizarFarmacia("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void AnalizarFarmaciaError02(){
        retorno = s.analizarFarmacia("F99");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void AnalizarFarmaciaNoCritica(){
        s.registrarFarmacia("F01","Farmacia 1");
        s.registrarFarmacia("F02","Farmacia 2");
        s.registrarFarmacia("F03","Farmacia 3");
        s.registrarFarmacia("F04","Farmacia 4");

        s.registrarConexion("F01","F02",1,1,1);
        s.registrarConexion("F02","F03",1,1,1);
        s.registrarConexion("F01","F04",1,1,1);

        s.registrarConexion("F04","F02",1,1,1);

        Retorno r = s.analizarFarmacia("F01");

        assertEquals(Retorno.Resultado.OK, r.getResultado());
        assertEquals("NO", r.getValorString());
    }

    @Test
    void AnalizarFarmaciaCritica() {
        s.registrarFarmacia("F01","Farmacia 1");
        s.registrarFarmacia("F02","Farmacia 2");
        s.registrarFarmacia("F03","Farmacia 3");

        s.registrarConexion("F01","F02",1,1,1);
        s.registrarConexion("F02","F03",1,1,1);

        Retorno r = s.analizarFarmacia("F02");
        assertEquals(Retorno.Resultado.OK, r.getResultado());
        assertEquals("SI", r.getValorString());
    }
}
