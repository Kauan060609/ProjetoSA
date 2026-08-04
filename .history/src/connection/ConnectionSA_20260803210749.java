package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionSA {
    private static final String URL = "jdbc:mysql://mysql-14b97700-estudante-d3ec.g.aivencloud.com:13219/projeto_sa?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_8FXfQJe4CzKlgwh1jP5";
    private static Connection instance;

    public static Connection connect(){
        try {
            if (instance == null || instance.isClosed()) {
                instance = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return instance;
        }catch (Exception e) {
            System.out.println("Erro na conexão: " + e.getMessage());
            return null;
        }
    }
}