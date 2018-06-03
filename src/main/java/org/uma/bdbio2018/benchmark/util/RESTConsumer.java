package org.uma.bdbio2018.benchmark.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;
import org.uma.bdbio2018.benchmark.contracts.Closeable;

/**
 * Naive, simple REST Client limited to POST requests.
 *
 * @author Miguel González <sosa@uma.es>
 **/
public class RESTConsumer implements Closeable {

    private URL endpoint;
    private String encodedAuth;
    private HttpURLConnection connection;

    public RESTConsumer(String endpoint) throws IOException {
        this.endpoint = new URL(endpoint);
    }

    public RESTConsumer(String endpoint, String authString) throws IOException {
        this(endpoint);
        setAuth(authString);
    }

    /**
     * Configures optional HTTP basic authorization.
     * @param authString 'user:pass' string.
     * @throws UnsupportedEncodingException
     */
    public void setAuth(String authString) throws UnsupportedEncodingException {
        this.encodedAuth = Base64.getEncoder().encodeToString(authString.getBytes("UTF-8"));
    }

    /**
     * Do a POST request with text data.
     *
     * @param query Body of the POST request.
     * @return Response of the request.
     */
    public String doRequest(String query) throws IOException {
        connection = (HttpURLConnection) this.endpoint.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "application/xml");
        connection.setDoOutput(true);
        applyAuth();
        OutputStream os = connection.getOutputStream();
        os.write(query.getBytes(Charset.forName("UTF-8")));
        os.flush();
        os.close();
        BufferedReader isr = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = isr.readLine()) != null) {
            sb.append(line);
            sb.append('\n');
        }
        sb.setLength(sb.length() - 1);
        isr.close();
        return sb.toString();
    }

    /**
     * Do HTTP basic authorization.
     */
    private void applyAuth() {
        if (this.encodedAuth == null) {
            return;
        }
        connection.setRequestProperty("Authorization",
                "Basic " + this.encodedAuth);
    }

    /**
     * Get response code requesting to endpoint.
     *
     * @return REsponse code.
     */
    public int testConnection() throws IOException {
        connection = (HttpURLConnection) this.endpoint.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        applyAuth();
        return this.connection.getResponseCode();
    }

    @Override
    public void close() {
        this.connection.disconnect();
    }
}
