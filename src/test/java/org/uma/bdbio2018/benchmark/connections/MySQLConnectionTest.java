package org.uma.bdbio2018.benchmark.connections;

import static org.junit.Assert.*;

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
public class MySQLConnectionTest {

    private Properties props;
    private MySQLConnection msqlc;

    @Before
    public void init() throws BenchmarkException {
        props = new Properties();

        try (InputStream input = new FileInputStream(
                "./src/main/resources/databases.properties")) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        msqlc = new MySQLConnection(props, false);
    }

    @Test
    public void shouldMySQLConnectionExecuteQuery() throws BenchmarkException {
        msqlc.executeQuery("SELECT 1");
    }
}