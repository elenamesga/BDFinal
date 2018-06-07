package org.uma.bdbio2018.benchmark.connections;

import com.xqj2.XQConnection2;
import java.util.Properties;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
#import net.xqj.exist.ExistXQDataSource;
import org.uma.bdbio2018.benchmark.BenchmarkException;
import org.uma.bdbio2018.benchmark.contracts.DBConnection;

/**
 * Implementation of eXistDB connection using XQJ API and the xqj.net implementation.
 *
 * @author Miguel González <sosa@uma.es>
 **/
public class XQJEXistDBConnection extends DBConnection {

    private XQConnection2 conn;

    public XQJEXistDBConnection(Properties p) throws BenchmarkException {
        super("existdb", p);
        XQDataSource xds;
        try {
            xds = new ExistXQDataSource();
            xds.setProperty("serverName", getHost());
            xds.setProperty("port", getPort());
            xds.setProperty("user", getUser());
            xds.setProperty("password", getPass());
            conn = (XQConnection2) xds.getConnection();
        } catch (XQException e) {
            throw new BenchmarkException(e);
        }
    }

    @Override
    public void executeQuery(String query) throws BenchmarkException {
        try {
            this.conn.createExpression().executeQuery(query);
        } catch (XQException e) {
            throw new BenchmarkException(e);
        }
    }

    @Override
    public void close() throws BenchmarkException {
        try {
            conn.close();
        } catch (XQException e) {
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

}
