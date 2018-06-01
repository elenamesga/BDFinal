package org.uma.bdbio2018.benchmark.connections;

import java.util.Properties;
import org.uma.bdbio2018.benchmark.BenchmarkException;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class MySQLConnection extends SQLServerConnection {

    public MySQLConnection(Properties p, boolean optimized) throws BenchmarkException {
        super("mysql", p, optimized);
    }

}
