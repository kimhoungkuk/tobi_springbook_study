package springbook.user.test;

import org.junit.runner.JUnitCore;

import springbook.user.dao.UserDaoTest;

public class userJunitTest {

	public static void main(String[] args) {
		
	    /*
	     * gets Tester class' name and stores it as a String
	     */
	    String className = UserDaoTest.class.getName();
	    
	    System.out.println(className);
	    
		// TODO Auto-generated method stub
		try{
			JUnitCore.main(className);
		}catch(Exception e){
			System.out.println("AAAAAAAAA : " + e);
		}
		//String currDir = .class.getResource(".").getPath();
	}

}
