package br.com.alura.leilao.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import br.com.alura.leilao.builder.LeilaoBuilder;
import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;

import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario USUARIO_ALEX = new Usuario("Alex");

    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     *  Templates de métodos
     *  [Nome do Metodo]_[Estado de Teste]_[Resultado Esperado]     -   getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescente_DevolveMaiorLance
     *  [deve]_[resultado esperado]_[estado de teste]               -   deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente
     *
     *  1. criar cenário de teste
     *  2. executar ação esperada
     *  3. testar resultado esperado
     */

    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {

        String descricaoDevolvida = CONSOLE.getDescricao();

        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance(){

        CONSOLE.propoe(new Lance(USUARIO_ALEX, 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){

        CONSOLE.propoe(new Lance(USUARIO_ALEX, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 500.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(500.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLance(){

        CONSOLE.propoe(new Lance(USUARIO_ALEX, 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(200.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){

        CONSOLE.propoe(new Lance(USUARIO_ALEX, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 500.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances(){

        CONSOLE.propoe(new Lance(USUARIO_ALEX, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 300.0));
        CONSOLE.propoe(new Lance(USUARIO_ALEX, 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLancesDevolvidos();

        assertEquals(3, tresMaioresLancesDevolvidos.size());

        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances(){
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLancesDevolvidos();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance(){

        CONSOLE.propoe(new Lance(USUARIO_ALEX, 200.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLancesDevolvidos();

        assertEquals(1, tresMaioresLancesDevolvidos.size());

        assertEquals(200.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);

    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasDoisLances(){
        CONSOLE.propoe(new Lance(USUARIO_ALEX, 300.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLancesDevolvidos();

        assertEquals(2, tresMaioresLancesDevolvidos.size());

        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances(){
        final Usuario usuario_fran = new Usuario("Fran");

        CONSOLE.propoe(new Lance(USUARIO_ALEX, 300.0));
        CONSOLE.propoe(new Lance(usuario_fran, 400.0));
        CONSOLE.propoe(new Lance(USUARIO_ALEX, 500.0));
        CONSOLE.propoe(new Lance(usuario_fran, 600.0));

        final List<Lance> tresMaioresLancesDevolvidosParaQuatroLances = CONSOLE.tresMaioresLancesDevolvidos();

        assertEquals(3, tresMaioresLancesDevolvidosParaQuatroLances.size());

        assertEquals(600.0, tresMaioresLancesDevolvidosParaQuatroLances.get(0).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesDevolvidosParaQuatroLances.get(1).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidosParaQuatroLances.get(2).getValor(), DELTA);

        CONSOLE.propoe(new Lance(USUARIO_ALEX, 700.0));

        final List<Lance> tresMaioresLancesDevolvidosParaCincoLances = CONSOLE.tresMaioresLancesDevolvidos();

        assertEquals(3, tresMaioresLancesDevolvidosParaCincoLances.size());

        assertEquals(700.0, tresMaioresLancesDevolvidosParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(600.0, tresMaioresLancesDevolvidosParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesDevolvidosParaCincoLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMaiorLance_QuandoNaoTiverLances(){
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public  void deve_DevolverValorZeroParaMenorLance_QuandoNaoTiverLances(){
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForMenorQueOMaiorLance(){
        exception.expect(LanceMenorQueUltimoLanceException.class);

        CONSOLE.propoe((new Lance(USUARIO_ALEX, 500.0)));
        CONSOLE.propoe((new Lance(new Usuario("Fran"), 400.0)));
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForOMesmoUsuarioDoUltimoLance(){
        exception.expect(LanceSeguidoDoMesmoUsuarioException.class);
        CONSOLE.propoe(new Lance(USUARIO_ALEX, 500.0));
        CONSOLE.propoe(new Lance(new Usuario("Alex"), 600.0));
    }

    @Test(expected = UsuarioJaDeuCincoLancesException.class)
    public void naoDeve_AdicionarLance_QuandoOUsuarioDerCincoLances(){

        final Usuario usuarioFran = new Usuario("Fran");

        Leilao console = new LeilaoBuilder("Console")
                            .lance(USUARIO_ALEX, 100.0)
                            .lance(usuarioFran, 200.0)
                            .lance(USUARIO_ALEX, 300.0)
                            .lance(usuarioFran, 400.0)
                            .lance(USUARIO_ALEX, 500.0)
                            .lance(usuarioFran, 600.0)
                            .lance(USUARIO_ALEX, 700.0)
                            .lance(usuarioFran, 800.0)
                            .lance(USUARIO_ALEX, 900.0)
                            .lance(usuarioFran, 1000.0)
                            //.lance(USUARIO_ALEX, 1100.0)
                            .build();


            console.propoe(new Lance(USUARIO_ALEX, 1100));



    }

}