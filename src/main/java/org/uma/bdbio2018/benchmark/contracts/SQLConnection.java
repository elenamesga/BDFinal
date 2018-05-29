package org.uma.bdbio2018.benchmark.contracts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.uma.bdbio2018.benchmark.BenchmarkException;
import org.uma.bdbio2018.benchmark.contracts.DBConnection;

/**
 * Represents a SQL DBMS connection.
 *
 * @author Miguel González <sosa@uma.es>
 **/
public abstract class SQLConnection implements DBConnection {

    private static final String URL_CONNECTION = "jdbc:%s://%s:%s/%s?user=%s&password=%s";

    protected String driver;
    protected Properties props;
    protected Connection connection;

    public SQLConnection (String driver, Properties properties) throws BenchmarkException {
        this.driver = driver;
        this.props = properties;
        connection = getConnection();
    }

    /**
     * Establish a database connection.
     * @return {@code Connection}
     * @throws BenchmarkException
     */
    protected Connection getConnection() throws BenchmarkException {
        try {
            return DriverManager.getConnection(String.format(URL_CONNECTION,
                    this.driver,
                    props.getProperty(this.driver + "host"),
                    props.getProperty(this.driver + "port"),
                    props.getProperty(this.driver + "name"),
                    props.getProperty(this.driver + "user"),
                    props.getProperty(this.driver + "pass")
            ));
        } catch (SQLException e) {
            throw new BenchmarkException(e);
        }
    }

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
}
