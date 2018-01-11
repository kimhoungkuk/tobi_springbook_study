package springbook.user.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JUnitTest {
	
	@Autowired
	ApplicationContext context; // 테스트 컨택스트가 매번 주입해주는 에플리케이션 컨텍스트는 항상 같은 오브젝트인지 테스트로 확인해 본다.
	
	static Set<JUnitTest> testObject = new HashSet<JUnitTest>();
	static ApplicationContext contextObject = null;
	
	@Test
	public void test1(){
		assertThat(testObject, is(not(hasItem(this))));
		testObject.add(this);
		assertThat(contextObject == null || contextObject == this.context , is(true));
		contextObject = this.context;
	}

	@Test
	public void test2(){
		assertThat(testObject, is(not(hasItem(this))));
		testObject.add(this);
		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
	}
	
	@Test
	public void test3(){
		assertThat(testObject, is(not(hasItem(this))));
		testObject.add(this);
		//assertThat(contextObject,either(is(nullValue())).or(is(this.context)));
		contextObject = this.context;
	}	
	
}
