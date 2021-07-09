package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    
    private int linhas;
    private int colunas;
    private int minas;

    private List<Campo> campos = new ArrayList<Campo>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        armarMinas();
    }

    private void gerarCampos() {
        for(int linha = 0; linha < linhas; linha++){
            for (int coluna = 0; coluna < colunas; coluna++){
                campos.add(new Campo(linha, coluna));
            }
        }
    }

    private void associarVizinhos() {
        for(Campo c1: campos){
            for(Campo c2: campos){
                c1.adicionarVizinho(c2);
            }
        }
    }

    private void armarMinas() {

        long minasArmadas = 0;

        do{
            minasArmadas = campos.stream()
                .filter(campo -> campo.isMinado())
                .count();

            int aleatorio = (int) (campos.size() * Math.random());

            campos.get(aleatorio).minar();

        }while(minasArmadas < minas);
    }

    public boolean tabuleiroMapeado() {

        return campos.stream()
            .allMatch(campo -> campo.isObjetivoRealizado());
    }

    public void reiniciarJogo(){

        campos.stream()
            .forEach(campo -> campo.reiniciar());

        armarMinas();
    }
    
    public String toString(){

        StringBuilder sb = new StringBuilder();

        int i = 0;

        for(int linha = 0; linha < linhas; linha++){
            for(int coluna = 0; coluna < colunas; coluna++){
                
                sb.append(" ");
                sb.append(campos.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void abrirCampo(int linha, int coluna){
        campos.stream()
            .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
            .findFirst()
            .ifPresent(c -> c.abrir());
    }

    public void alterarMarcacaoCampo(int linha, int coluna){
        campos.stream()
            .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
            .findFirst()
            .ifPresent(c -> c.alterarMarcado());
    }
}
