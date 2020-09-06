package no.maardal.fant;

import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ws.rs.Produces;

import static no.maardal.fant.DatasourceProducer.JNDI_NAME;

@Singleton
@DataSourceDefinition(
    name = JNDI_NAME,
    className = "org.h2.jdbcx.JdbcDataSource",
    url = "jdbc:h2:~/auth.db")
public class DatasourceProducer {
    public static final String JNDI_NAME = "java:app/jdbc/default";
    
    @Resource(lookup=JNDI_NAME)
    DataSource ds;
    
    @Produces
    public DataSource getDataSource() {
        return ds;
    }
}
