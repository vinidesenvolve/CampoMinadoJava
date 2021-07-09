package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class CampoTest {

    private Campo campo;

    @BeforeEach
    void iniciarCampo(){
        campo = new Campo(2, 2);
    }

    @Test
    public void testeVizinhoNorte(){
        Campo vizinho = new Campo(1, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    public void testeVizinhoSul(){
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    public void testeVizinhoLeste(){
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    public void testeVizinhoOeste(){
        Campo vizinho = new Campo(2, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }
    @Test
    public void testeVizinhoNoroeste(){
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    public void testeVizinhoNordeste(){
        Campo vizinho = new Campo(1, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    public void testeVizinhoSudoeste(){
        Campo vizinho = new Campo(3, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    public void testeVizinhoSudeste(){
        Campo vizinho = new Campo(3, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    public void testeNaoVizinho(){
        Campo vizinho = new Campo(4, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    public void testePadraoMarcado(){
        assertFalse(campo.isMarcado());
    }

    @Test
    public void testeAlterarMarcado(){ 
        campo.alterarMarcado();
        assertTrue(campo.isMarcado());
        campo.alterarMarcado();
        assertFalse(campo.isMarcado());
    }

    @Test
    public void testeAbrirCampoNaoMinadoNaoMarcado(){
        assertTrue(campo.abrir());
    }

    @Test
    public void testeAbrirCampoNaoMinadoMarcado(){
        campo.alterarMarcado();
        assertFalse(campo.abrir());
    }

    @Test
    public void testeAbrirCampoMinadoNaoMarcado(){
        campo.minar();
        assertThrows(ExplosaoException.class, () -> { 
            campo.abrir(); 
        });
    }

    @Test
    public void testeAbrirCampoMinadoMarcado(){
        campo.minar();
        campo.alterarMarcado();
        assertFalse(campo.abrir());
    }

    @Test
    public void testeAbrirCampoVizinho(){

        Campo campo11 = new Campo(1, 1);
        Campo campo00 = new Campo(0, 0);

        campo.adicionarVizinho(campo11);
        campo11.adicionarVizinho(campo00);    

        campo.abrir();

        assertTrue(campo11.isAberto() && campo00.isAberto());
    }

    @Test
    public void testeAbrirCampoVizinhoMinado(){

        Campo campo11 = new Campo(1, 1);
        Campo campo00 = new Campo(0, 0);

        campo00.minar();

        campo.adicionarVizinho(campo11);
        campo11.adicionarVizinho(campo00);    

        campo.abrir();

        assertTrue(campo11.isAberto() && campo00.isFechado());
    }

    @Test
    public void testeAbrirCampoVizinhoMinado2(){

        Campo campo11 = new Campo(1, 1);
        Campo campo00 = new Campo(0, 0);

        campo11.minar();

        campo.adicionarVizinho(campo11);
        campo11.adicionarVizinho(campo00);    

        campo.abrir();

        assertTrue(campo11.isFechado() && campo00.isFechado());
    }
}
