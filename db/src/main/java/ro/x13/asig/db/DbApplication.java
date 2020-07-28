package ro.x13.asig.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
//@ComponentScan(basePackages={"com.baeldung.crud"})
//@EnableJpaRepositories(basePackages="com.baeldung.crud.repositories")
//@EnableTransactionManagement
//@EntityScan(basePackages="com.baeldung.crud.entities")
@EnableAspectJAutoProxy
public class DbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbApplication.class, args);
	}


}
