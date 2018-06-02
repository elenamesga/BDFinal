package org.uma.bdbio2018.benchmark.connections;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import net.xqj.basex.BaseXXQDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class BaseXConnectionTest {

    private XQConnection conn;

    @Before
    public void setUp() throws XQException {
        XQDataSource source = new BaseXXQDataSource();
        conn = source.getConnection("admin", "admin");
    }

    @Test
    public void shouldBaseXConnectionConnectAndExecuteQuery() throws XQException {
        XQPreparedExpression expr = conn.prepareExpression("'Hello world!'");
        XQResultSequence result = expr.executeQuery();
        while(result.next()) {
            System.out.println(result.getItemAsString(null));
        }
        expr.close();
    }

    @After
    public void tearDown() throws XQException {
        conn.close();
    }
}
