import java.io.*;

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


        String path = "src/main/resources/products.csv";
        csvToDBInserter.createTable(path);

        File file = new File("src/main/resources/updateQuery.txt");
        FileReader fr=new FileReader(file);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        String query;

        while((query=br.readLine())!=null)
        {
            updatingQuery.updateQuery(query);

        }
//        while(true){
//            String q =reader.readLine();
//            if(q.equals(""))
//                break;
//            updatingQuery.updateTable();
//        }

//        updatingQuery.updateQuery();


        aggreagtingQuery.aggreagateTable();
    }
}
