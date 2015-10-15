/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.ia.se;

import alice.tuprolog.Int;
import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoMoreSolutionException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import alice.tuprolog.Var;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author geanpeixoto
 */
public class Engine extends Prolog {

    private static final Logger logger = LoggerFactory.getLogger(Engine.class);
    private static final String THEORY = "./theory.pl";
    private static final ClassLoader loader = Thread.currentThread().getContextClassLoader();

    public Engine() throws IOException, InvalidTheoryException {
        super();
        Theory theory = new Theory(loader.getResourceAsStream(THEORY));
        this.setTheory(theory);
    }

    List<Plano> getAnswer(String tipoConexao, Integer numeroUsuarios, Integer tempoEstimado, Set<String> servicos, Integer valorMaximo) throws MalformedGoalException, NoSolutionException {
        List<Plano> planos = new ArrayList<>();

        Term s[] = new Term[servicos.size()];
        Iterator<String> it = servicos.iterator();
        for (int i = 0; i < servicos.size(); i++) {
            s[i] = new Struct(it.next());
        }

        List<SolveInfo> infos = new ArrayList<>();
        
        // run(Servicos, NumeroPessoas, QuantidadeHoras, PrecoMaximo, Conexao, Operadora, Plano, Preco)
        Term terms[] = {
            new Struct(s),
            new Int(numeroUsuarios),
            new Int(tempoEstimado),
            new Int(valorMaximo),
            new Struct(tipoConexao),
            new Var("Operadora"),
            new Var("Plano"),
            new Var("Preco")
        };

        SolveInfo info = this.solve(new Struct("run", terms));
        do {

            if (info.isSuccess()) {
                infos.add(info);
            }

            try {
                info = this.solveNext();
            } catch (NoMoreSolutionException ex) {
                break;
            }
        } while (true);

        for ( SolveInfo i: infos ) {
            planos.add(getPlano(
                    i.getVarValue("Operadora"),
                    i.getVarValue("Plano"),
                    i.getVarValue("Preco")
            ));
        }
        
        Collections.sort(planos, new Comparator<Plano>() {
            @Override
            public int compare(Plano o1, Plano o2) {
                return o1.preco.compareTo(o2.preco);
            }
        });

        return planos;
    }

    private Plano getPlano(Term operadora, Term nome, Term preco) throws NoSolutionException {
//    plano(Operadora, Conexao, Nome, Valor, Franquia, FUnidade, Download, DUnidade, Upload, UUnidade)
        Term terms[] = {
            operadora,
            new Var("Conexao"),
            nome,
            preco,
            new Var("Franquia"),
            new Var("FUnidade"),
            new Var("Download"),
            new Var("DUnidade"),
            new Var("Upload"),
            new Var("UUnidade")
        };

        SolveInfo info = this.solve(new Struct("plano", terms));

        Plano plano = new Plano();
        plano.operadora = getValue(operadora);
        plano.nome = getValue(nome);
        plano.preco = Float.parseFloat(getValue(preco));

        if (info.isSuccess()) {
            plano.download = getValue(info.getVarValue("Download")) + getValue(info.getVarValue("DUnidade"));
            plano.upload = getValue(info.getVarValue("Upload")) + getValue(info.getVarValue("UUnidade"));
            plano.franquia = getValue(info.getVarValue("Franquia")) + getValue(info.getVarValue("FUnidade"));
        }

        return plano;
    }

    private String getValue(Term term) {
        return term.toString().replaceAll("\"", "").replaceAll("\'", "");
    }

    List<String> getMessages(String tipoConexao, Integer numeroUsuarios, Integer tempoEstimado, Set<String> servicos, Integer valorMaximo) throws NoSolutionException {
        List<String> messages = new ArrayList<>();

        Term s[] = new Term[servicos.size()];
        Iterator<String> it = servicos.iterator();
        for (int i = 0; i < servicos.size(); i++) {
            s[i] = new Struct(it.next());
        }

        // run(Servicos, NumeroPessoas, QuantidadeHoras, PrecoMaximo, Conexao, Operadora, Plano, Preco)
        Term terms[] = {
            new Struct(s),
            new Int(numeroUsuarios),
            new Int(tempoEstimado),
            new Int(valorMaximo),
            new Struct(tipoConexao),
            new Var("Message"),
        };

        SolveInfo info = this.solve(new Struct("message", terms));
        do {

            if (info.isSuccess()) {
                messages.add(getValue(
                    info.getVarValue("Message")
                ));
            }

            try {
                info = this.solveNext();
            } catch (NoMoreSolutionException ex) {
                break;
            }
        } while (true);

        return messages;
    }

    @Override
    public SolveInfo solve(Term term) {
        logger.debug(term.toString());
        return super.solve(term); 
    }
    
    
}
