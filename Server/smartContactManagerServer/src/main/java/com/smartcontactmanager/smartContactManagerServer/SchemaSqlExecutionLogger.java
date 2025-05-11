// package com.smartcontactmanager.smartContactManagerServer;

// import java.sql.Connection;

// import javax.sql.DataSource;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.core.io.Resource;
// import org.springframework.jdbc.datasource.init.ScriptUtils;
// import org.springframework.stereotype.Component;


//@Raj: This class is created to check the creation of tables from "schema.sql" file. 



// @Component
// public class SchemaSqlExecutionLogger implements CommandLineRunner {
//     private static final Logger logger = LoggerFactory.getLogger(SchemaSqlExecutionLogger.class);

//     @Value("classpath:schema.sql")
//     private Resource schemaSql;

//     private final DataSource dataSource;

//     public SchemaSqlExecutionLogger(DataSource dataSource) {
//         this.dataSource = dataSource;
//     }

//     @Override
//     public void run(String... args) throws Exception {
//         logger.info("Attempting to execute schema.sql.");

//         try (Connection connection = dataSource.getConnection()) {
//             ScriptUtils.executeSqlScript(connection, schemaSql);
//             logger.info("schema.sql executed successfully.");
//         } catch (Exception e) {
//             logger.error("Error executing schema.sql: " + e.getMessage(), e);
//         }
//     }
// }
