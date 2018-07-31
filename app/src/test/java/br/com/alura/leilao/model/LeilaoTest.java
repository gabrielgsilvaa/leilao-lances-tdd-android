package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    @Test
    public void getDescricao() {
        // criar cenário de teste
        Leilao console = new Leilao("Console");

        // executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }


    @Test
    public void getMaiorLance(){

        /** Verifica se devolve o maior lance com apenas um lance **/
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Alex"), 200.0));

        double maiorLanceDevolvidoConsole = console.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvidoConsole, 0.0001);
            // delta = a diferença entre os valores com ponto flutuante
            // e se for maior siginifica que os valores são equivalentes

        /***********************/
        /** Verifica se devolve o maior lance em ordem crescente **/

        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Alex"), 100.0));
        computador.propoe(new Lance(new Usuario("Fran"), 500.0));

        double maiorLanceDevolvidoComputador = computador.getMaiorLance();

        assertEquals(500.0, maiorLanceDevolvidoComputador, 0.0001);

        /***********************/
        /** Verifica se devolve o maior lance em ordem decrescente **/

        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Alex"), 300.0));
        carro.propoe(new Lance(new Usuario("Fran"), 100.0));

        double maiorLanceDevolvidoCarro = carro.getMaiorLance();

        assertEquals(300.0, maiorLanceDevolvidoCarro, 0.0001);
    }

}