package springbook.user.controller;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserXmlController {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("classpath:ApplicationContext.xml");
		
		UserDao userdao = context.getBean("userDao",UserDao.class);
		
		User user = new User(); 
		user.setId("user");
		user.setName("백기선");
		user.setPassword("kimhhkks");
		
		userdao.add(user);	
		
		System.out.println(user.getId() + "등록 성공()");
		
		User user2 = userdao.get(user.getId());
		
		if(!user.getName().equals(user2.getName()))
		{
			System.out.println("테스트 실패 (name)");
		}else if(!user.getPassword().equals(user2.getPassword()))
		{
			System.out.println("테스트 실패 (password)");
		}else{
			System.out.println(user2.getId() + "조회 성공()");
		}
	}
	
}
