package springbook.user.controller;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.dao.CountingConnectionMaker;
import springbook.user.dao.CountingDaoFactory;
import springbook.user.dao.UserDao;

public class UserDaoConnectionCountingTest {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		context.getBean("userDao",UserDao.class);
		//DL(의존관계 검색을 사용하면 이름을 이용해 어떤 빈이든 가져올수 있다.)
		CountingConnectionMaker ccm = context.getBean("connectionMaker",CountingConnectionMaker.class);
		System.out.println("Connection count : " + ccm.getCounter());
		
	}
}
