package org.uma.bdbio2018.benchmark.contracts;

import org.uma.bdbio2018.benchmark.BenchmarkException;

/**
 * Interface for DBMS connections.
 *
 * @author Miguel González <sosa@uma.es>
 */
public interface DBConnection extends Closeable {

    /**
     * Executes a query.
     *
     * @param query String-represented query statement.
     * @throws BenchmarkException when query execution fails.
     */
    void executeQuery(String query) throws BenchmarkException;
}
