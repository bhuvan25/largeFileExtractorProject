import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvBeanReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class UpdatingQuery {
    public static void main(String[] args) {

        DBCongif congif = new DBCongif();
        congif.setConnectionProperties();

        Connection connection;


        try {
            long start = System.currentTimeMillis();

            connection = DriverManager.getConnection(congif.dbURL, congif.connectionProperties);
            connection.setAutoCommit(false);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while(true) {
                System.out.println("please enter sql query to update table or press enter to break ");
                String updateQuery = reader.readLine();
                if(updateQuery.equals(""))
                    break;
                PreparedStatement statement = connection.prepareStatement(updateQuery);
                statement.execute();
            }


            connection.commit();
            connection.close();

            long end = System.currentTimeMillis();
            System.out.println("Execution Time: " + (end - start));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
