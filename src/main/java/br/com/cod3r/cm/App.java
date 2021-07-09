package br.com.cod3r.cm;

import br.com.cod3r.cm.modelo.Tabuleiro;

public class App {
    
    public static void main(String[] args){

    Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);

    tabuleiro.abrirCampo(3,3);
    tabuleiro.alterarMarcacaoCampo(1, 1);
    tabuleiro.alterarMarcacaoCampo(4, 1);

    System.out.println(tabuleiro);

    }
}
 