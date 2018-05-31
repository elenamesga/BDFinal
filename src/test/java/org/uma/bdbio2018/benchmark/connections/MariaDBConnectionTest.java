package org.uma.bdbio2018.benchmark.connections;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uma.bdbio2018.benchmark.BenchmarkException;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class MariaDBConnectionTest {

    private Properties props;
    private MariaDBConnection mdbc;

    @BeforeClass
    public void init() throws BenchmarkException {
        props = new Properties();

        try (InputStream input = new FileInputStream(
                "./src/main/resources/databases.properties")) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mdbc = new MariaDBConnection(props, false);
    }

    @Test
    public void shouldMySQLConnectionExecuteQuery() throws BenchmarkException {
        mdbc.executeQuery("SELECT 1");
    }

}