package org.uma.bdbio2018.benchmark.connections;

import java.io.IOException;
import java.util.Properties;
import org.uma.bdbio2018.benchmark.BenchmarkException;
import org.uma.bdbio2018.benchmark.contracts.DBConnection;
import org.uma.bdbio2018.benchmark.util.RESTConsumer;

/**
 * Implementation of eXistDB connection using http request to the REST API.
 *
 * @author Miguel González <sosa@uma.es>
 **/
public class RESTEXistDBConnection extends DBConnection {

    private static final String URL_CONNECTION = "http://%s:%s/exist/rest/db/";
    private static final String QUERY_SKELETON =
            "<query xmlns=\"http://exist.sourceforge.net/NS/exist\">\n"
                    + "    <text>\n"
                    + "%s"
                    + "    </text>\n"
                    + "</query>";
    private RESTConsumer httpClient;

    public RESTEXistDBConnection(Properties props) throws BenchmarkException {
        super("existdb", props);
        int codeResponse = 0;
        try {
            httpClient = new RESTConsumer(String.format(URL_CONNECTION, getHost(), getPort()),
                    String.format("%s:%s", getUser(), getPass()));
            codeResponse = this.httpClient.testConnection();
        } catch (IOException e) {
            throw new BenchmarkException(e);
        }
        if (codeResponse != 200) {
            throw new BenchmarkException(
                    String.format("Could not establish connection to '%s'.\nResponse code: %d",
                            String.format(URL_CONNECTION, getHost(), getPort()),
                            codeResponse));
        }
    }

    @Override
    public void executeQuery(String query) throws BenchmarkException {
        try {
            this.httpClient.doRequest(String.format(QUERY_SKELETON, query));
        } catch (IOException e) {
            throw new BenchmarkException(e);
        }
    }

    public String getHost() {
        return props.getProperty(this.driver + "host");
    }

    public String getPort() {
        return props.getProperty(this.driver + "port");
    }

    public String getUser() {
        return props.getProperty(this.driver + "user");
    }

    public String getPass() {
        return props.getProperty(this.driver + "pass");
    }

    @Override
    public void close() {
        this.httpClient.close();
    }
}
