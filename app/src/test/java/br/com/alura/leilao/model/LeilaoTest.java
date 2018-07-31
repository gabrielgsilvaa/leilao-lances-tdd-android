package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    /** Templates **/
    // [Nome do Metodo]_[Estado de Teste]_[Resultado Esperado]
    // [deve] [resultado esperado] [estado de teste]

    @Test
    public void getDescricao_QuandoRecebeDescricao_DevolveDescricao() {
        // deve_DevolverDescricao_QuandoRecebeDescricao

        // criar cenário de teste
        Leilao console = new Leilao("Console");

        // executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void getMaiorLance_QuandoRecebeApenasUmLance_DevolveMaiorLance(){
        // deve_DevolverMaiorLance_QuandoRecebeApenasUmLance

        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Alex"), 200.0));

        double maiorLanceDevolvidoConsole = console.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvidoConsole, 0.0001);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescente_DevolveMaiorLance(){
        // deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente

        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Alex"), 100.0));
        computador.propoe(new Lance(new Usuario("Fran"), 500.0));

        double maiorLanceDevolvidoComputador = computador.getMaiorLance();

        assertEquals(500.0, maiorLanceDevolvidoComputador, 0.0001);
    }

    @Test
    public void getMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente_DevolveMaiorLance(){
        // deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente

        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Alex"), 300.0));
        carro.propoe(new Lance(new Usuario("Fran"), 100.0));

        double maiorLanceDevolvidoCarro = carro.getMaiorLance();

        assertEquals(300.0, maiorLanceDevolvidoCarro, 0.0001);
    }

}