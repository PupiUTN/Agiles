package app.persistence.conection;

import com.mysql.jdbc.Connection;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by gabriellorenzatti on 10/6/17.
 */
public class MySqlGabi implements ConectionMySql {


    private String conexionGabi = "jdbc:mysql://localhost:3306/pupi";
    private String userGabi = "root";
    private String passwordGabi = "6732";
   private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public EntityManagerFactory conectionWithBd(EntityManagerFactory emf) {
        System.out.println("algo");
        System.out.println("============================= CONFIGURO local MYSQL gabi");
        Map<String, String> persistenceMap = new HashMap<>();
        persistenceMap.put("javax.persistence.jdbc.url", conexionGabi);
        persistenceMap.put("javax.persistence.jdbc.user", userGabi);
        persistenceMap.put("javax.persistence.jdbc.password", passwordGabi);
        persistenceMap.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
        persistenceMap.put("javax.persistence.schema-generation.database.action", "create-or-extend-tables");
        emf = Persistence.createEntityManagerFactory("PersistenceUnit", persistenceMap);
        return emf;
    }

    public  boolean isThisDataBaseOnline()
    {
        Connection connect;
        try {

            System.out.println("============  pruebo gabi");
            logger.info(conexionGabi+ userGabi+ passwordGabi);
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(conexionGabi , userGabi, passwordGabi);
            return true;

        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }

}
