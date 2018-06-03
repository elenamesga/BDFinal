package org.uma.bdbio2018.benchmark.contracts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.uma.bdbio2018.benchmark.BenchmarkException;

/**
 * Represents a SQL DBMS connection.
 *
 * @author Miguel González <sosa@uma.es>
 **/
public abstract class SQLConnection extends DBConnection {

    protected Connection connection;
    protected boolean optimized;

    public SQLConnection(String driver, Properties properties, boolean optimized)
            throws BenchmarkException {
        super(driver, properties);
        this.optimized = optimized;
        connection = getConnection();
    }

    /**
     * Establish a database connection.
     *
     * @return {@code Connection}
     */
    protected abstract Connection getConnection() throws BenchmarkException;

    @Override
    public void executeQuery(String query) throws BenchmarkException {
        try {
            connection.createStatement().execute(query);
        } catch (SQLException e) {
            throw new BenchmarkException(e);
        }
    }

    @Override
    public void close() throws BenchmarkException {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new BenchmarkException(e);
        }
    }

    public boolean getOptimized() {
        return this.optimized;
    }
}
