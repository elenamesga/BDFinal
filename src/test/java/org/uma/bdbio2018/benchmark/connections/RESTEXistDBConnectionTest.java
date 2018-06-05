package org.uma.bdbio2018.benchmark.connections;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uma.bdbio2018.benchmark.BenchmarkException;
import org.uma.bdbio2018.benchmark.DBConnectionFactory;
import org.uma.bdbio2018.benchmark.contracts.DBConnection;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class RESTEXistDBConnectionTest {
    private DBConnection exc;

    @Before
    public void setUp() throws BenchmarkException {
        Properties props = new Properties();

        try (InputStream input = new FileInputStream(
                "./src/main/resources/databases.properties")) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        exc = (new DBConnectionFactory(props)).makeConnection("existdb", true);
    }

    @Test
    public void shouldEXistDBConnectionDoARequest() throws BenchmarkException {
        exc.executeQuery("let $db := doc(\"bdbio40.xml\")\n"
                + "let $dateof := $db//date_of/record[lastModification < '2018-03-1']\n"
                + "return $db//proteins/record[entry = $dateof/proteinEntry]");
    }

    @After
    public void tearDown() throws BenchmarkException {
        exc.close();
    }
}