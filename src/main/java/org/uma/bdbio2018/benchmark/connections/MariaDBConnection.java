package org.uma.bdbio2018.benchmark.connections;

import java.util.Properties;
import org.uma.bdbio2018.benchmark.BenchmarkException;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class MariaDBConnection extends SQLServerConnection {

    public MariaDBConnection(Properties p, boolean optimized) throws BenchmarkException {
            super("mariadb", p, optimized);
    }

}
