/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.ia.se;

import java.util.Objects;

/**
 *
 * @author geanpeixoto
 */
class Plano {
    public String operadora;
    public String nome;
    public Float preco;
    public String franquia;
    public String download;
    public String upload;

    @Override
    public String toString() {
        return "Plano{" + "operadora=" + operadora + ", nome=" + nome + ", preco=" + preco + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.operadora);
        hash = 41 * hash + Objects.hashCode(this.nome);
        hash = 41 * hash + Objects.hashCode(this.preco);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Plano other = (Plano) obj;
        if (!Objects.equals(this.operadora, other.operadora)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.preco, other.preco)) {
            return false;
        }
        return true;
    }
}
