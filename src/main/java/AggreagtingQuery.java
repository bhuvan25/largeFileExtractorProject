
import java.sql.*;

public class AggreagtingQuery {
    public static void main(String[] args) {

        DBCongif congif = new DBCongif();
        congif.setConnectionProperties();

        Connection connection;


        try {
            long start = System.currentTimeMillis();

            connection = DriverManager.getConnection(congif.dbURL, congif.connectionProperties);
            connection.setAutoCommit(false);


            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO aggregatedTable " + "SELECT name,count(sku) " + "FROM productTable GROUP BY name; ");


            connection.commit();
            connection.close();

            long end = System.currentTimeMillis();
            System.out.println("Execution Time: " + (end - start));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
