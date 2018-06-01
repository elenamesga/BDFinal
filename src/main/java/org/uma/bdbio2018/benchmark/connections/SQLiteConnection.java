package org.uma.bdbio2018.benchmark.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.uma.bdbio2018.benchmark.BenchmarkException;
import org.uma.bdbio2018.benchmark.contracts.SQLConnection;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class SQLiteConnection extends SQLConnection {

    private final String URL_SQLITE_CONNECTION = "jdbc:sqlite:%s";

    public SQLiteConnection(Properties p, boolean optimized) throws BenchmarkException {
        super("sqlite", p, optimized);
    }

    @Override
    protected Connection getConnection() throws BenchmarkException {
        try {
            return DriverManager.getConnection(String.format(URL_SQLITE_CONNECTION,
                    getName()
            ));
        } catch (SQLException e) {
            throw new BenchmarkException(e);
        }
    }

    public String getName() {
        return optimized ? props.getProperty(this.driver + "name")
                : props.getProperty(this.driver + "name_opt");
    }

}
