import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Service {
    private static CsvToDBInserter csvToDBInserter;
    private static UpdatingQuery updatingQuery;
    private static AggreagtingQuery aggreagtingQuery;

    private static void init(){
        csvToDBInserter = new CsvToDBInserter();
        updatingQuery = new UpdatingQuery();
        aggreagtingQuery = new AggreagtingQuery();
    }
    public static void main(String args[]) throws IOException {
        init();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        String path = "/Users/bhuvan.taneja/Documents/Products.csv";
        csvToDBInserter.createTable(path);

        while(true) {
            System.out.println("give update query as input or press enter to exit");
            String query = reader.readLine();
            if(query.equals(""))
                break;
            updatingQuery.updateTable(query);
        }

        aggreagtingQuery.aggreagateTable();
    }
}
