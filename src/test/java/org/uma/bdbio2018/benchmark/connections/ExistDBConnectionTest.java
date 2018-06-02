package org.uma.bdbio2018.benchmark.connections;

import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.xqj2.XQConnection2;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class ExistDBConnectionTest {

    private XQConnection2 conn;

    @Before
    public void setUp() throws XQException {
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "172.17.0.1");
        xqs.setProperty("port", "9080");
        xqs.setProperty("user", "admin");
        xqs.setProperty("password", "");
        conn = (XQConnection2) xqs.getConnection();
    }

    @Test
    public void shouldExistDBConnectAndExecuteAQuery() throws XQException {
        XQExpression xqe = conn.createExpression();
        XQResultSequence rs = xqe.executeQuery("let $db := doc(\"db/bdbio40.xml\")/root\n"
                + "let $org := $db/organisms/record[kingdom = \"Metazoa\"]\n"
                + "let $fb := $db/formed_by/record[organismID = $org/organismID]\n"
                + "let $proteins := $db/proteins/record[entry = $fb/proteinEntry]\n"
                + "return $proteins");
        while(rs.next()) {
            System.out.println(rs.getItemAsString(null));
        }
        xqe.close();
    }

    @After
    public void tearDown() throws XQException {
        conn.close();
    }
}
