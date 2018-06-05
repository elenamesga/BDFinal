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
public class XQJEXistDBConnectionTest {

    private XQJEXistDBConnection edbc;

    @Before
    public void setUp() throws BenchmarkException {
        Properties props = new Properties();

        try (InputStream input = new FileInputStream(
                "./src/main/resources/databases.properties")) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        edbc = new XQJEXistDBConnection(props);
    }

    @Test
    public void shouldExistDBConnectAndExecuteAQuery() throws BenchmarkException {
        edbc.executeQuery("let $db := doc(\"bdbio40.xml\")/root\n"
                + "let $org := $db/organisms/record[kingdom = \"Metazoa\"]\n"
                + "let $fb := $db/formed_by/record[organismID = $org/organismID]\n"
                + "let $proteins := $db/proteins/record[entry = $fb/proteinEntry]\n"
                + "return $proteins");
    }

    @After
    public void tearDown() throws BenchmarkException {
        edbc.close();
    }
}
