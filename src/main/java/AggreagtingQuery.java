
import java.sql.*;

public class AggreagtingQuery {
    public void aggreagateTable() {

        DBCongif congif = new DBCongif();
        congif.setConnectionProperties();

        Connection connection;

        try {
            long start = System.currentTimeMillis();

            connection = DriverManager.getConnection(congif.dbURL, congif.connectionProperties);
            connection.setAutoCommit(false);
            System.out.println("Starting Aggregating process");


            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO aggregatedTable " + "SELECT name,count(sku) " + "FROM productTable GROUP BY name; ");


            connection.commit();
            connection.close();

            long end = System.currentTimeMillis();
            System.out.println("Time taken to aggregate :  " + (end - start));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
