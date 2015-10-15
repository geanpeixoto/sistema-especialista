/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.ia.se;

import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoMoreSolutionException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geanpeixoto
 */
public class Console {

    private final Prolog engine;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Console(Prolog engine) {
        super();
        this.engine = engine;

//        SolveInfo info = engine.solve("tipo_conexao(Tipo).");
//        
//        do {
//            Term solution = info.getSolution();
//            
//            System.out.println(solution);
//            
//            
//            try {
//                info = engine.solveNext();
//            } catch (NoMoreSolutionException ex) {
//                break;
//            }
//        } while(true);
    }

    public String getTipoConexao() {
        do {
            System.out.println("\nQual o tipo de conexão desejada? {1:móvel, 2:doméstica}");
            System.out.print("R: ");

            Integer option;

            try {
                option = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException ex) {
                continue;
            }

            switch (option) {
                case 1:
                    return "movel";
                case 2:
                    return "domestica";
            }
        } while (true);
    }

    Integer getNumeroUsuarios() {
        do {
            System.out.println("\nQuantas pessas simultâneas geralmente utilizarão a conexão? [1-10]");
            System.out.print("R: ");

            try {
                Integer integer = Integer.parseInt(reader.readLine());
                
                if ( integer > 0 && integer <= 10 )
                    return integer;
            } catch (IOException | NumberFormatException ex) {
            }

        } while (true);
    }
    
    Integer getTempoEstimado() {
        do {
            System.out.println("\nEm média, quantas horas a conexão será utilizada por dia? [1-24]");
            System.out.print("R: ");

            try {
                Integer integer = Integer.parseInt(reader.readLine());
                
                if ( integer > 0 && integer <= 24 )
                    return integer;
            } catch (IOException | NumberFormatException ex) {
            }

        } while (true);
    }

    Set<String> getServicos() throws MalformedGoalException, NoSolutionException {
        Set<String> result = new HashSet<>();
        result.add("geral");

        SolveInfo info = engine.solve("servico(Key, Value).");
        do {

            String key = info.getVarValue("Key").toString();
            String value = info.getVarValue("Value").toString().replaceAll("\'", "");
            
            loop:do {
                System.out.printf("\nSerá utilizado %s? {sim, nao}\nR: ", value);
                
                try {
                    switch(reader.readLine()) {
                        case "sim":
                        case "yes":
                        case "s":
                        case "y":
                        case "1":
                            result.add(key);
                        case "nao":
                        case "no":
                        case "n":
                        case "0":
                            break loop;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } while(true);
            
            

            try {
                info = engine.solveNext();
            } catch (NoMoreSolutionException ex) {
                break;
            }
        } while (true);

        return result;
    }

    Integer getValorMaximo() {
        do {
            System.out.println("\nQual o valor máximo disposto a pagar?");
            System.out.print("R: ");

            try {
                Integer integer = Integer.parseInt(reader.readLine());
                return integer;
            } catch (IOException | NumberFormatException ex) {
            }

        } while (true);
    }

    void showAnswer(List<Plano> planos ) {
        System.out.println("\nPlanos encontrados: \n");
        for ( Plano plano : planos )
            System.out.println(plano);
    }
}
