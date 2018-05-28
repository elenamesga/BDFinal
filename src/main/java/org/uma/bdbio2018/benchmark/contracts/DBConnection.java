package org.uma.bdbio2018.benchmark.contracts;

import java.io.Closeable;

/**
 * Interface for DBMS connections.
 *
 * @author Miguel González <sosa@uma.es>
 */
public interface DBConnection extends Closeable {
    void executeQuery(String query);
}
