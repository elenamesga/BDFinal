package org.uma.bdbio2018.benchmark.connections;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uma.bdbio2018.benchmark.BenchmarkException;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class RESTEXistDBConnectionTest {
    private RESTEXistDBConnection exc;

    @Before
    public void setUp() throws BenchmarkException {
        Properties props = new Properties();

        try (InputStream input = new FileInputStream(
                "./src/main/resources/databases.properties")) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        exc = new RESTEXistDBConnection(props);
    }

    @Test
    public void shouldEXistDBConnectionDoARequest() throws BenchmarkException {
        exc.executeQuery("let $db := doc(\"bdbio40.xml\")/root\n"
                + "\tlet $org := $db/organisms/record[kingdom = \"Metazoa\"]\n"
                + "\tlet $fb := $db/formed_by/record[organismID = $org/organismID]\n"
                + "\tlet $proteinas := $db/proteins/record[entry = $fb/proteinEntry]\n"
                + "\treturn count($proteinas)");
    }

    @After
    public void tearDown() throws BenchmarkException {
        exc.close();
    }
}