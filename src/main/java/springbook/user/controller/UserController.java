package springbook.user.controller;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserController {
	  
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
	
		UserDao dao = context.getBean("userdao2", UserDao.class);
		    
		User user = new User(); 
		user.setId("kimhk");
		user.setName("김형국");
		user.setPassword("kimhoung0");
		
		//dao.add(user);
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
	
	}

}
