package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    // [Nome do Metodo][Estado de Teste][Resultado Esperado]

    @Test
    public void getDescricaoQuandoRecebeDescricaoDevolveDescricao() {
        // criar cenário de teste
        Leilao console = new Leilao("Console");

        // executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void getMaiorLanceQuandoRecebeApenasUmLanceDevolveMaiorLance(){

        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Alex"), 200.0));

        double maiorLanceDevolvidoConsole = console.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvidoConsole, 0.0001);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescenteDevolveMaiorLance(){

        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Alex"), 100.0));
        computador.propoe(new Lance(new Usuario("Fran"), 500.0));

        double maiorLanceDevolvidoComputador = computador.getMaiorLance();

        assertEquals(500.0, maiorLanceDevolvidoComputador, 0.0001);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescenteDevolveMaiorLance(){

        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Alex"), 300.0));
        carro.propoe(new Lance(new Usuario("Fran"), 100.0));

        double maiorLanceDevolvidoCarro = carro.getMaiorLance();

        assertEquals(300.0, maiorLanceDevolvidoCarro, 0.0001);
    }

}