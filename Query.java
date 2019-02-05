import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class Query implements IQuery {
    private Connection connection;
    private BufferedWriter bufferedWriter;

    public Query(){
        startup();
    }

    public static void main(String... args) {
        QueryAsLambdas query = new QueryAsLambdas();

        System.out.println(query.executeSQL01());
        System.out.println(query.executeSQL02());
        System.out.println(query.executeSQL03());
        System.out.println(query.executeSQL04());
        System.out.println(query.executeSQL05());
        System.out.println(query.executeSQL06());
        System.out.println(query.executeSQL07());
        System.out.println(query.executeSQL08());
        System.out.println(query.executeSQL09());
        System.out.println(query.executeSQL10());
        System.out.println(query.executeSQL11());
        System.out.println(query.executeSQL12());
        System.out.println(query.executeSQL13());
        System.out.println(query.executeSQL14());

        query.shutdown();
    }

    public void startup() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String driverName = "jdbc:hsqldb:";
            String databaseURL = driverName + Configuration.instance.dataPath + "customer.db";
            String username = "sa";
            String password = "";
            connection = DriverManager.getConnection(databaseURL, username, password);

            bufferedWriter = new BufferedWriter(new FileWriter(Configuration.instance.logPath + "query.log"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeLogfile(String message) {
        try {
            bufferedWriter.append(message).append("\n");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public String dump(ResultSet resultSet) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int maximumNumberColumns = resultSetMetaData.getColumnCount();
            int i;
            Object object;

            for (; resultSet.next(); ) {
                for (i = 0; i < maximumNumberColumns; ++i) {
                    object = resultSet.getObject(i + 1);
                    stringBuilder.append(object.toString()).append(" ");
                }
                stringBuilder.append(" \n");
            }

            return stringBuilder.toString();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return "-1";
    }

    public synchronized ResultSet queryDump(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            writeLogfile(sqlStatement);
            //writeLogfile(dump(resultSet));
            statement.close();
            return resultSet;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return null;
        }
    }

    public List<List<Object>> enumerateRS(ResultSet resultSet) {
        try {
            List<List<Object>> entries = new LinkedList<>();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                List<Object> row = new LinkedList<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getObject(i));
                }
                entries.add(row);
            }
            return entries;
        } catch (SQLException ex) {
            System.out.println(ex);
            return new LinkedList<>();
        }
    }

    public Set<Customer> getAllCustomersFromDB() {
        return Customer.getCustomersFromRow(enumerateRS(queryDump("Select * from data")));
    }

    public void shutdown() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
