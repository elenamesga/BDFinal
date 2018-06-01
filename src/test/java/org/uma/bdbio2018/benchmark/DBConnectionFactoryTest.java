package org.uma.bdbio2018.benchmark;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import org.uma.bdbio2018.benchmark.connections.MariaDBConnection;
import org.uma.bdbio2018.benchmark.connections.MySQLConnection;
import org.uma.bdbio2018.benchmark.connections.PostgreSQLConnection;
import org.uma.bdbio2018.benchmark.connections.SQLiteConnection;
import org.uma.bdbio2018.benchmark.contracts.DBConnection;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class DBConnectionFactoryTest {

    private DBConnectionFactory factory;

    @Before
    public void setUp() {
        Properties props = new Properties();

        try (InputStream input = new FileInputStream(
                "./src/main/resources/databases.properties")) {
            props.load(input);
            this.factory = new DBConnectionFactory(props);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldDBConnectionFactoryReturnMySQLConnection() throws BenchmarkException {
        DBConnection dbc = this.factory.makeConnection("mysql", false);
        assertThat(dbc, instanceOf(MySQLConnection.class));
    }

    @Test
    public void shouldDBConnectionFactoryReturnMariaDBConnection() throws BenchmarkException {
        DBConnection dbc = this.factory.makeConnection("mariadb", false);
        assertThat(dbc, instanceOf(MariaDBConnection.class));
    }

    @Test
    public void shouldDBConnectionFactoryReturnPostgreSQLConnection() throws BenchmarkException {
        DBConnection dbc = this.factory.makeConnection("postgresql", false);
        assertThat(dbc, instanceOf(PostgreSQLConnection.class));
    }

    @Test
    public void shouldDBConnectionFactoryReturnSQLiteConnection() throws BenchmarkException {
        DBConnection dbc = this.factory.makeConnection("sqlite", false);
        assertThat(dbc, instanceOf(SQLiteConnection.class));
    }

    @Test(expected = BenchmarkException.class)
    public void shouldDBConnectionFactoryRaiseAnExceptionWhenDriverIsNotRecognized() throws BenchmarkException {
        this.factory.makeConnection("Mysql", false);
    }

}