package gr.atc.aniketos.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.core.RowMapper;

public class MainActivator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        System.out.println("Started...");
        
    	String databaseUrl = "jdbc:mysql://localhost:3306/repository";
    	String databaseUser = "root";
    	String databasePassword = "";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
            throw new IllegalStateException(
                    "Could not load JDBC driver class", ex);
        }

    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	dataSource.setUrl(databaseUrl);
    	dataSource.setUsername(databaseUser);
    	dataSource.setPassword(databasePassword);

    	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        Collection<String> names = jdbcTemplate.query("SELECT `items`.`Name` FROM `items`", 
                                                      new Object [0], new ServiceNameMapper());

        for(String name: names) {
            System.out.println(name);
        }
    }

    private final class ServiceNameMapper implements RowMapper<String> {

    	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString("Name");
        }
    }    
    
    public void stop(BundleContext context) throws Exception {

    }
}