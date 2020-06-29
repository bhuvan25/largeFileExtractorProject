import java.util.Properties;


public class DBCongif {
    String dbURL = "jdbc:postgresql://localhost:5432/testing";

    Properties connectionProperties ;


    public void setConnectionProperties() {
        connectionProperties = new Properties();
        connectionProperties.setProperty("Driver", "org.postgresql.Driver");
        connectionProperties.put("user", "postgres");
        connectionProperties.put("password", "abcd");
    }
}
