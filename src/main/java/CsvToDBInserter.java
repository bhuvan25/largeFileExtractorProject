import java.io.*;
import java.sql.*;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class CsvToDBInserter {

    public static void main(String[] args) {

        DBCongif congif = new DBCongif();
        congif.setConnectionProperties();

        String csvFilePath = "/Users/bhuvan.taneja/Documents/Products.csv";

        int batchSize = 40;

        Connection connection = null;

        ICsvBeanReader beanReader;
        CellProcessor[] processors = new CellProcessor[] {
                new NotNull(), //  name
                new NotNull(), // sku
                new NotNull() // description
        };

        try {
            long start = System.currentTimeMillis();

            connection = DriverManager.getConnection(congif.dbURL, congif.connectionProperties);
            connection.setAutoCommit(false);

            String sql = "INSERT INTO productTable (name, sku, description) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            beanReader = new CsvBeanReader(new FileReader(csvFilePath),
                    CsvPreference.STANDARD_PREFERENCE);

            beanReader.getHeader(true); // skip header line

            String[] header = {"name", "sku", "description"};
            Products bean;

            int count = 0;

            while ((bean = beanReader.read(Products.class, header, processors)) != null) {
                String name = bean.getName();
                String sku = bean.getSku();
                String description = bean.getDescription();

                statement.setString(1, name);
                statement.setString(2, sku);

                statement.setString(3, description);

                statement.addBatch();
                count++;
                if (count % batchSize == 0) {
                    System.out.println("started");
                    statement.executeBatch();
                }
            }

            beanReader.close();
            statement.executeBatch();

            connection.commit();
            connection.close();

            long end = System.currentTimeMillis();
            System.out.println("Execution Time: " + (end - start));
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}