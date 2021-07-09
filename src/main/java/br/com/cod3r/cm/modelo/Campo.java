package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class Campo {
    
    private final int linha;
    private final int coluna;

    private boolean aberto;
    private boolean minado;
    private boolean marcado;

    private List<Campo> vizinhos = new ArrayList<Campo>();

    Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    boolean adicionarVizinho(Campo vizinho){

        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;

        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        
        int delta = deltaLinha + deltaColuna;
        
        if(diagonal && delta == 2){
            vizinhos.add(vizinho);
            return true;
        }
        
        if(!diagonal && delta == 1){
            vizinhos.add(vizinho);
            return true;
        }

        return false;
    }

    boolean isVizinhancaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    void alterarMarcado(){

        if(!aberto){
            marcado = !marcado;
        }
    }

    void minar(){
        minado = true;
    }

    boolean abrir(){

        if(!aberto && !marcado){

            aberto = true;
        
            if(minado){
                throw new ExplosaoException();
            }
        
            if(isVizinhancaSegura()){
                vizinhos.forEach(v -> v.abrir());
            }

            return true;

        }

        return false;

    }

    boolean isObjetivoRealizado(){

        boolean desvendado = !minado && aberto;
        boolean identificado = minado && marcado;

        return desvendado || identificado;
    }

    long countMinasVizinhanca(){
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    void reiniciar(){
        aberto = false;
        marcado = false;
        minado = false;
    }

    public String toString(){
        
        if(marcado){
            return "x";
        }
        
        if(aberto){
            return ".";
        }

        if(minado && aberto){
            return "*";
        }

        if(aberto && countMinasVizinhanca() > 0){
            return Long.toString(countMinasVizinhanca());
        }

        return "?";
    }

    public boolean isMarcado(){
        return marcado;
    }

    public boolean isMinado(){
        return minado;
    }

    public boolean isAberto(){
        return aberto;
    }

    public boolean isFechado(){
        return !isAberto();
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
}
