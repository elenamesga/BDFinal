package org.uma.bdbio2018.benchmark.connections;

import java.util.Properties;
import org.uma.bdbio2018.benchmark.BenchmarkException;
import org.uma.bdbio2018.benchmark.contracts.SQLConnection;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class MariaDBConnection extends SQLConnection {

    public MariaDBConnection(Properties p) throws BenchmarkException {
        super("mariadb", p);
    }

}
