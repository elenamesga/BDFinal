package org.uma.bdbio2018.benchmark.connections;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import org.uma.bdbio2018.benchmark.BenchmarkException;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class SQLiteConnectionTest {

    private Properties props;
    private SQLiteConnection sqlite;

    @Before
    public void init() throws BenchmarkException {
        props = new Properties();

        try (InputStream input = new FileInputStream(
                "./src/main/resources/databases.properties")) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlite = new SQLiteConnection(props, false);
    }

    @Test
    public void shouldMySQLConnectionExecuteQuery() throws BenchmarkException {
        sqlite.executeQuery("SELECT 1");
    }
}