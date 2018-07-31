package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    /**
     *  Templates de métodos
     *  [Nome do Metodo]_[Estado de Teste]_[Resultado Esperado]     -   getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescente_DevolveMaiorLance
     *  [deve]_[resultado esperado]_[estado de teste]               -   deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente
     *
     *  1. criar cenário de teste
     *  2. executar ação esperada
     *  3. testar resultado esperado
     */


    private Leilao console = new Leilao("Console");
    private Usuario usuarioAlex = new Usuario("Alex");
    private Usuario usuarioFran = new Usuario("Fran");


    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {

        String descricaoDevolvida = console.getDescricao();

        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance(){

        console.propoe(new Lance(usuarioAlex, 200.0));

        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){

        console.propoe(new Lance(usuarioAlex, 100.0));
        console.propoe(new Lance(usuarioFran, 500.0));

        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(500.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){

        console.propoe(new Lance(usuarioAlex, 300.0));
        console.propoe(new Lance(usuarioFran, 100.0));

        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(300.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLance(){

        console.propoe(new Lance(usuarioAlex, 200.0));

        double menorLanceDevolvido = console.getMenorLance();

        assertEquals(200.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){

        console.propoe(new Lance(usuarioAlex, 100.0));
        console.propoe(new Lance(usuarioFran, 500.0));

        double menorLanceDevolvido = console.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){

        console.propoe(new Lance(usuarioAlex, 300.0));
        console.propoe(new Lance(usuarioFran, 100.0));

        double menorLanceDevolvido = console.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, 0.0001);

    }

}