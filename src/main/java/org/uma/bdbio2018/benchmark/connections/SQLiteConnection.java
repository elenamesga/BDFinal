package org.uma.bdbio2018.benchmark.connections;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.uma.bdbio2018.benchmark.BenchmarkException;
import org.uma.bdbio2018.benchmark.contracts.SQLConnection;
import java.sql.DriverManager;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class SQLiteConnection extends SQLConnection {

    private final String URL_SQLITE_CONNECTION = "jdbc:sqlite:%s";

    public SQLiteConnection(Properties p) throws BenchmarkException {
        super("sqlite", p);
    }

    @Override
    protected Connection getConnection() throws BenchmarkException {
        try {
            return DriverManager.getConnection(String.format(URL_SQLITE_CONNECTION,
                    props.getProperty(this.driver + "name")
            ));
        } catch (SQLException e) {
            throw new BenchmarkException(e);
        }
    }

}
