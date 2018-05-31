package org.uma.bdbio2018.benchmark.contracts;

import org.uma.bdbio2018.benchmark.BenchmarkException;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public interface Closeable {

    /**
     * Closes the resource connection with the DBMS.
     */
    void close() throws BenchmarkException;
}
