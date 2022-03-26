package publicServices;

import com.mysql.cj.MysqlConnection;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class ConnectionService {
    private static ConnectionService instance;

    private Connection connection;

    private ConnectionService(){
        /**
         * 1.Реализовать подключение к БД.
         * 2.Подключить в pom ломбок, mysqlConnector, SLF4J
          */

        String connectionString = "jdbc:mysql://localhost:3306/smart_home?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
        log.debug(connectionString);
        try {
            connection = DriverManager.getConnection(connectionString, "root", "123");
        } catch (SQLException e){
            log.error("Ошибка подключения к БД", e);
        }
    }

    public static ConnectionService getInstance(){
        ConnectionService localInstance = instance;
        if(localInstance == null){
            synchronized (ConnectionService.class){
                localInstance = instance;
                if (localInstance == null){
                    localInstance = instance = new ConnectionService();
                }
            }
        }
        return localInstance;
    }

    public Connection getConnection() {
        return connection;
    }
}
