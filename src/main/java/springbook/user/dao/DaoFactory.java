package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {
	
	@Bean
	public ConnectionMaker connectionMaker(){
		return new DConnectionMaker();
	}
	
	@Bean
	public UserDao userdao(){
		UserDao userdao = new UserDao();
		userdao.setConnectionMaker(connectionMaker());
		return userdao;
	}
	
	@Bean 
	public DataSource dataSource() {
		return new SConnectionMaker();
	}

	@Bean
	public UserDao userdao2(){
		UserDao userdao = new UserDao();
		userdao.setDataSource(dataSource());
		return userdao;
	}
	
}
