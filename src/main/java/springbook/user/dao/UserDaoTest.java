package springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
@DirtiesContext //테스트 메소드에서 에플리케이션 컨텍스트의 구성이나 상태를 변경한다는 것을 테스트 컨텍스트 프레임워크에 알려준다.
public class UserDaoTest {
	
	@Autowired
	ApplicationContext context;
	@Autowired
	UserDao userdao;
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setUp(){
		//ApplicationContext context = new GenericXmlApplicationContext("classpath:ApplicationContext.xml");
		System.out.println(this.context);
		System.out.println(this);
		//this.userdao = context.getBean("userDao",UserDao.class);
		

		this.user1 = new User("kimhk1","김형국1","kimhoung1"); 
		this.user2 = new User("kimhk2","김형국2","kimhoung2");		
		this.user3 = new User("kimhk3","김형국3","kimhoung3");		
		
		DataSource dataSource = new SConnectionMaker("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
		userdao.setDataSource(dataSource);
	}
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {
		//ApplicationContext context = new GenericXmlApplicationContext("classpath:ApplicationContext.xml");
		//UserDao userdao = context.getBean("userDao",UserDao.class);

		//User user1 = new User("kimhk1","김형국1","kimhoung1"); 
		//User user2 = new User("kimhk2","김형국2","kimhoung2");		
		
		userdao.deleteAll();
		assertThat(userdao.getCount(), is(0));
			
		userdao.add(user1);
		userdao.add(user2);
		assertThat(userdao.getCount(), is(2));
		
		System.out.println("등록 성공()");
		
		//user.setName("백기선4");
		
		User userget1 = userdao.get(user1.getId());
		assertThat(userget1.getName(), is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));

		User userget2 = userdao.get(user2.getId());
		assertThat(userget2.getName(), is(user2.getName()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));
		
		//System.out.println(user2.getId() + "조회 성공()");
	}
	

	@Test
	public void count() throws SQLException, ClassNotFoundException {
		//ApplicationContext context = new GenericXmlApplicationContext("classpath:ApplicationContext.xml");
		
		//UserDao userdao = context.getBean("userDao",UserDao.class);

		//User user1 = new User("kimhk1","김형국1","kimhoung1"); 
		//User user2 = new User("kimhk2","김형국2","kimhoung2"); 
		//User user3 = new User("kimhk3","김형국3","kimhoung3"); 
		
		userdao.deleteAll();
		assertThat(userdao.getCount(), is(0));
			
		userdao.add(user1);
		assertThat(userdao.getCount(), is(1));
		
		userdao.add(user2);
		assertThat(userdao.getCount(), is(2));
		
		userdao.add(user3);
		assertThat(userdao.getCount(), is(3));
		
		System.out.println(user3.getId() + "등록 성공()");
		
		//user.setName("백기선4");
		
/*		User user4 = new User();
		
		user4 = userdao.get(user1.getId());

		assertThat(user4.getName(), is(user1.getName()));
		assertThat(user4.getPassword(), is(user1.getPassword()));*/
		
		//System.out.println(user2.getId() + "조회 성공()");
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws ClassNotFoundException, SQLException{
		//ApplicationContext ctx = new GenericXmlApplicationContext("classpath:ApplicationContext.xml");
		
		//UserDao userdao = ctx.getBean("userDao",UserDao.class);
		userdao.deleteAll();
		assertThat(userdao.getCount(), is(0));
		
		userdao.get("unknown_id"); // 이 메소드 실행 중에 예외가 발생해야 한다. 예외가 발생하지 않으면 테스트가 실패한다.
	}
	
}
