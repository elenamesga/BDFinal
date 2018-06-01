package org.uma.bdbio2018.benchmark;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import org.uma.bdbio2018.benchmark.connections.MySQLConnection;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class DBBenchmarkTest {

    private Properties props;

    @Before
    public void setUp() {
        this.props = new Properties();

        try (InputStream input = new FileInputStream(
                "./src/main/resources/databases.properties")) {
            this.props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pings a database connection.
     * @throws BenchmarkException
     */
    @Test
    public void shouldDBBenckmarkExecuteQuery() throws BenchmarkException {
        MySQLConnection mysqlc = new MySQLConnection(this.props, false);
        DBBenchmark.Executor e = new DBBenchmark.Executor(mysqlc);
        e.executeQuery("SELECT 1");
    }

}