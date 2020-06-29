import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvBeanReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class UpdatingQuery {
    public void updateQuery(String query) {


        DBCongif congif = new DBCongif();
        congif.setConnectionProperties();

        Connection connection;


        try {
            long start = System.currentTimeMillis();

            connection = DriverManager.getConnection(congif.dbURL, congif.connectionProperties);
            connection.setAutoCommit(false);

//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//            while(true) {
//                System.out.println("please enter sql query to update table or press enter to break ");
//                String updateQuery = reader.readLine();
//                if(updateQuery.equals(""))
//                    break;
//                Statement statement = connection.createStatement();
//                statement.execute(updateQuery);
//            }
            Statement statement = connection.createStatement();
            statement.execute(query);
//            statement.execute("UPDATE productTable "+ "SET name='bhuvan' " + "WHERE sku='step-onto' ;");


            connection.commit();
            connection.close();

            long end = System.currentTimeMillis();
            System.out.println("Execution Time: " + (end - start));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
