package in.nit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableTransactionManagement //eables HtX
@EnableWebMvc // Spring MVC Activated
//load properties into Spring container
@PropertySource("classpath:app.properties")
//all layered classes common package name
@ComponentScan("in.nit")
@Import(SecurityConfig.class)
public class AppConfig  implements WebMvcConfigurer{
	@Autowired
	private Environment env;

	//1. DataSource
	@Bean
	public DataSource ds() {
		BasicDataSource d=new BasicDataSource();
		d.setDriverClassName(env.getProperty("db.driver"));
		d.setUrl(env.getProperty("db.url"));
		d.setUsername(env.getProperty("db.user"));
		d.setPassword(env.getProperty("db.password"));
		return d;
	}
	//2. SessionFactrory
	@Bean
	public LocalSessionFactoryBean sf() {
		LocalSessionFactoryBean s=new LocalSessionFactoryBean();
		s.setDataSource(ds());
		s.setHibernateProperties(props());
		//s.setAnnotatedClasses(ShipmentType.class);
		s.setPackagesToScan("in.nit.model");
		return s;
	}
	@Bean
	public Properties props() {
		Properties p=new Properties();
		p.put("hibernate.dialect", env.getProperty("orm.dialect"));
		p.put("hibernate.show_sql", env.getProperty("orm.showsql"));
		p.put("hibernate.format_sql", env.getProperty("orm.fmtsql"));
		p.put("hibernate.hbm2ddl.auto", env.getProperty("orm.ddlauto"));
		return p;
	}

	//3. HT
	@Bean
	public HibernateTemplate ht() {
		HibernateTemplate h=new HibernateTemplate();
		h.setSessionFactory(sf().getObject());
		return h;
	}
	//4. HtxM
	@Bean
	public HibernateTransactionManager htx() {
		HibernateTransactionManager htm=new HibernateTransactionManager();
		htm.setSessionFactory(sf().getObject());
		return htm;
	}
	//5. ViewResovler
	@Bean
	public InternalResourceViewResolver ivr() {
		InternalResourceViewResolver v=new InternalResourceViewResolver();
		v.setPrefix(env.getProperty("mvc.prefix"));
		v.setSuffix(env.getProperty("mvc.suffix"));
		return v;
	}

	//6. Activate CMF
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}

	//7. Mail Config Bean
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl jms=new JavaMailSenderImpl();
		jms.setHost(env.getProperty("mail.host"));
		jms.setPort(env.getProperty("mail.port",Integer.class));
		jms.setUsername(env.getProperty("mail.user"));
		jms.setPassword(env.getProperty("mail.pwd"));
		jms.setJavaMailProperties(mailProps());
		return jms;
	}


	@Bean
	public Properties mailProps() {
		Properties p=new Properties();
		p.put("mail.smtp.starttls.enable", env.getProperty("mail.secure.enable")); //HTTPS
		p.put("mail.smtp.auth", env.getProperty("mail.auth.enable")); //HTTPS
		return p;
	}

	@Bean
	public BCryptPasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}



	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	
}





