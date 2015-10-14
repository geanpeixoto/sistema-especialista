package br.unioeste.ia.se;

import alice.tuprolog.InvalidLibraryException;
import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Prolog;
import java.io.IOException;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author geanpeixoto
 */
public class Main {
    
    
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    
    
    public static void main(String[] args) throws IOException, InvalidTheoryException, MalformedGoalException, InvalidLibraryException, NoSolutionException {
        Prolog engine = new Engine();
        Console console = new Console(engine);
        
        String tipoConexao = console.getTipoConexao();
        Integer numeroUsuarios = 1;
        
        if ( "domestica".equals(tipoConexao) ) {
            numeroUsuarios = console.getNumeroUsuarios();
        }
        
        Integer tempoEstimado = console.getTempoEstimado();
        Set<String> servicos = console.getServicos();
        Integer valorMaximo;
    }
}
