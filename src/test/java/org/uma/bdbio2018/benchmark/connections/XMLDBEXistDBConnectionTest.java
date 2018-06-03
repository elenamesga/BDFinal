package org.uma.bdbio2018.benchmark.connections;

import org.junit.Test;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;
import javax.xml.transform.OutputKeys;
import org.exist.xmldb.DatabaseImpl;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class XMLDBEXistDBConnectionTest {

    @Test
    public void shouldExistDBConnectAndExecuteQuery() throws XMLDBException {
        Database database = new DatabaseImpl();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        Collection col = null;
        try {
            col = DatabaseManager.getCollection("xmldb:exist://172.17.0.1:9080/exist/xmlrpc/db");
            col.setProperty(OutputKeys.INDENT, "no");
            XMLResource res = (XMLResource)col.getResource("bdbio40.xml");
            if(res == null) {
                System.out.println("document not found!");
            } else {
                System.out.println(res.getContent());
            }
        } finally {
            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

    }

}
