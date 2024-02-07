public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Db.createDB();
        for (int i=7; i<12; i++) {
            Db.createTable(i);
        }
        
    }
}
