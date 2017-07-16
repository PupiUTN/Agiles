package app.persistence.conection;

import com.mysql.jdbc.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gabriellorenzatti on 10/6/17.
 */
public class StrategyJose implements ConectionStrategy {


    private String connectionString = "jdbc:mysql://130.211.214.126:3306/pupi";
    private String username = "user";
    private String password = "user";
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    public EntityManagerFactory getEntityManagerFactory() {

        LOG.info("============================= CONFIGURO local MYSQL jose");
        Map<String, String> persistenceMap = new HashMap<>();
        persistenceMap.put("javax.persistence.jdbc.url", connectionString);
        persistenceMap.put("javax.persistence.jdbc.user", username);
        persistenceMap.put("javax.persistence.jdbc.password", password);
        persistenceMap.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
        persistenceMap.put("javax.persistence.schema-generation.database.action", "create-or-extend-tables");
        return Persistence.createEntityManagerFactory("PersistenceUnit", persistenceMap);
    }

    public  boolean isThisDataBaseOnline ()
    {
        try {
            LOG.info("============  ping jose");
            LOG.info(connectionString + username + password);
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = (Connection) DriverManager.getConnection(connectionString, username, password);
            return true;

        } catch (ClassNotFoundException | SQLException e) {
            LOG.error("============  this database is OFFLINE");
            return false;
        }
    }


}
