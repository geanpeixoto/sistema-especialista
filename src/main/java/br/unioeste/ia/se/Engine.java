/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.ia.se;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Prolog;
import alice.tuprolog.Theory;
import java.io.IOException;

/**
 *
 * @author geanpeixoto
 */
public class Engine extends Prolog {
    
    private static final String THEORY = "./theory.pl";
    private static final ClassLoader loader = Thread.currentThread().getContextClassLoader();
    
    public Engine() throws IOException, InvalidTheoryException {
        super();
        Theory theory = new Theory(loader.getResourceAsStream(THEORY));
        this.setTheory(theory);
    }
}
