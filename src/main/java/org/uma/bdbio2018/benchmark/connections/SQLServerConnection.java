package org.uma.bdbio2018.benchmark.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.uma.bdbio2018.benchmark.BenchmarkException;
import org.uma.bdbio2018.benchmark.contracts.SQLConnection;

/**
 * Class representing a connection with a server DBMS.
 * @author Miguel González <sosa@uma.es>
 **/
public class SQLServerConnection extends SQLConnection {

    private static final String URL_CONNECTION = "jdbc:%s://%s:%s/%s?user=%s&password=%s";

    public SQLServerConnection(String driver, Properties properties, boolean optimized)
            throws BenchmarkException {
        super(driver, properties, optimized);
    }

    @Override
    protected Connection getConnection() throws BenchmarkException {
        try {
            return DriverManager.getConnection(String.format(URL_CONNECTION,
                    this.driver,
                    getHost(),
                    getPort(),
                    getName(),
                    getUser(),
                    getPass()
            ));
        } catch (SQLException e) {
            throw new BenchmarkException(e);
        }
    }

    public String getName() {
        return optimized ? props.getProperty(this.driver + "name_opt")
                : props.getProperty(this.driver + "name");
    }

    public String getHost() {
        return props.getProperty(this.driver + "host");
    }

    public String getPort() {
        return props.getProperty(this.driver + "port");
    }

    public String getUser() {
        return props.getProperty(this.driver + "user");
    }

    public String getPass() {
        return props.getProperty(this.driver + "pass");
    }
}
