package org.uma.bdbio2018.benchmark.contracts;

import java.util.Properties;
import org.uma.bdbio2018.benchmark.BenchmarkException;

/**
 * Interface for DBMS connections.
 *
 * @author Miguel González <sosa@uma.es>
 */
public abstract class DBConnection implements Closeable {

    protected Properties props;
    protected String driver;

    public DBConnection(String d, Properties p) {
        driver = d;
        props = p;
    }

    /**
     * Executes a query.
     *
     * @param query String-represented query statement.
     * @throws BenchmarkException when query execution fails.
     */
    public abstract void executeQuery(String query) throws BenchmarkException;
}
