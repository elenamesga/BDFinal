package org.uma.bdbio2018.benchmark;


import java.util.Properties;
import org.uma.bdbio2018.benchmark.connections.MariaDBConnection;
import org.uma.bdbio2018.benchmark.connections.MySQLConnection;
import org.uma.bdbio2018.benchmark.connections.PostgreSQLConnection;
import org.uma.bdbio2018.benchmark.connections.RESTEXistDBConnection;
import org.uma.bdbio2018.benchmark.connections.SQLiteConnection;
import org.uma.bdbio2018.benchmark.contracts.DBConnection;


/**
 * Factory for making {@code DBConnection}s.
 * @author Miguel González <sosa@uma.es>
 **/
public class DBConnectionFactory {

    private Properties props;

    public DBConnectionFactory(Properties props) {
        this.props = props;
    }

    public DBConnection makeConnection(String driver, boolean optimized) throws BenchmarkException {
        switch (driver) {
            case "mysql":
                return new MySQLConnection(this.props, optimized);
            case "mariadb":
                return new MariaDBConnection(this.props, optimized);
            case "postgresql":
                return new PostgreSQLConnection(this.props, optimized);
            case "sqlite":
                return new SQLiteConnection(this.props, optimized);
            case "existdb":
                return new RESTEXistDBConnection(this.props);
        }
        throw new BenchmarkException("Unrecognized or unsupported driver.");
    }
}
