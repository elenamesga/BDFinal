package org.uma.bdbio2018.benchmark.util;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class RESTConsumerTest {

    private RESTConsumer baseXConsumer;

    @Before
    public void setUp() throws IOException {
        baseXConsumer = new RESTConsumer("http://localhost:8984/rest/bdbio/", "admin:admin");
    }

    @Test
    public void shouldBaseXRESTConsumerConnect() throws IOException {
        assertEquals(200, baseXConsumer.testConnection());
    }

    @Test
    public void shouldBaseXRESTConsumerDoARequest() throws IOException {
        String response = baseXConsumer.doRequest("<query>\n"
                + "  <text>\n"
                + "\tlet $org := //organisms/record[kingdom = \"Metazoa\"]\n"
                + "\tlet $fb := //formed_by/record[organismID = $org/organismID]\n"
                + "\tlet $proteinas := //proteins/record[entry = $fb/proteinEntry]\n"
                + "\treturn $proteinas\n"
                + "  </text>\n"
                + "</query>");
        assertTrue(!response.equals(""));
    }
}