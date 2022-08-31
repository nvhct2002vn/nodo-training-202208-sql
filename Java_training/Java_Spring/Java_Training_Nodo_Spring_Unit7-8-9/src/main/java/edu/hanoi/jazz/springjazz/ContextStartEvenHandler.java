package edu.hanoi.jazz.springjazz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ContextStartEvenHandler implements ApplicationListener<ContextStartedEvent> {
    private final static Logger LOGGER = Logger.getLogger(String.valueOf(ContextStartEvenHandler.class));

    @Autowired
    private DataSource dataSource;

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        LOGGER.info("context start application " + dataSource);
        try {
            createTable("HN_GROUP", "create table HN_GROUP(\n" +
                    "id int AUTO_INCREMENT primary key,\n" +
                    "name varchar(100)\n" +
                    ")");
            createTable("HN_USER", "create table HN_USER(\n" +
                    "username varchar(100) primary key,\n" +
                    "password varchar(100),\n" +
                    "email varchar(100),\n" +
                    "age int,\n" +
                    "groupId int,\n" +
                    "constraint ct_grId foreign key(groupId) references hn_group(id)\n" +
                    ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTable(String name, String script) throws SQLException {
        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        ResultSet rs = metaData.getTables(null, null, name, null);
        if (rs.next()) {
            LOGGER.info("Table " + rs.getString("TABLE_NAME") + " already exits!");
            return;
        }
        dataSource.getConnection().createStatement().executeUpdate(script);
    }
}
