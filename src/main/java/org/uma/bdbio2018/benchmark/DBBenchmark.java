package org.uma.bdbio2018.benchmark;

import org.uma.bdbio2018.benchmark.contracts.DBConnection;

/**
 * Represents a generic database benchmark.
 *
 * @author Miguel González <sosa@uma.es>
 */
public class DBBenchmark {

    private long executingStatementDuration;

    private DBBenchmark(Executor executor) {
        this.executingStatementDuration = executor.executingStatementDuration;
    }

    public long getExecutingStatementDuration() {
        return this.executingStatementDuration;
    }

    /**
     * Orchestrator of the benchmark. Wraps the query execution in order to obtain information of
     * it.
     */
    public static class Executor {

        DBConnection connection;
        long executingStatementDuration;

        public Executor(DBConnection connection) {
            this.connection = connection;
        }

        /**
         * Executes the provided query computing its duration.
         *
         * @param query String-represented query statement.
         * @return DBBenchmark object with the information of the query execution.
         * @throws BenchmarkException when query execution fails.
         */
        public DBBenchmark executeQuery(String query) throws BenchmarkException {
            long beforeExec = System.currentTimeMillis();
            connection.executeQuery(query);
            long afterExec = System.currentTimeMillis();
            this.executingStatementDuration = afterExec - beforeExec;
            this.connection.close();
            return new DBBenchmark(this);
        }
    }
}
