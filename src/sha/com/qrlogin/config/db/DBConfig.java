package sha.com.qrlogin.config.db;

import org.apache.commons.dbcp2.BasicDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration 
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class DBConfig {
	
	@Autowired
    private Environment env;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
		lcemfb.setDataSource(getDataSource());
		lcemfb.setPersistenceUnitName("entityManagerFactoryBean");
		lcemfb.setPackagesToScan("sha.com.qrlogin.model");
		lcemfb.setJpaProperties(jpaProperties());
		return lcemfb;
	}
	
	@Bean
	public JpaVendorAdapter getJpaVendorAdapter() {
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		return adapter;
	}
	
	@Bean
	public DataSource getDataSource() {
	        BasicDataSource dataSource = new BasicDataSource();
	        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
	        dataSource.setUrl(env.getProperty("jdbc.url"));
	        dataSource.setUsername(env.getProperty("jdbc.username"));
	        dataSource.setPassword(env.getProperty("jdbc.password"));
	        return dataSource;
	}
	
	 private Properties jpaProperties() {
         Properties properties = new Properties();
         properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
         properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
         properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
         properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
         properties.put("hibernate.max_fetch_depth", env.getProperty("hibernate.max_fetch_depth"));
         properties.put("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
         properties.put("hibernate.cache.use_minimal_puts", env.getProperty("hibernate.cache.use_minimal_puts"));
         return properties;        
 }
	 
	    @Bean
	    public PlatformTransactionManager transactionManager() {
	        return new JpaTransactionManager(getEntityManagerFactoryBean().getObject());
	    }

}
