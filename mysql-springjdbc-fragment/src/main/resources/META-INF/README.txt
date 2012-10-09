This bundle "attaches" MySQL JDBC driver to Spring JDBC package. Include it together with MySQL driver and Spring JDBC driver in your OSGi container.

In your implementation bundle make the org.springframework.jdbc as Require-Bundle. Then load the MySQL driver with this code:

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