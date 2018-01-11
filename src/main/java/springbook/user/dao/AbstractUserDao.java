package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;

abstract class AbstractUserDao { 
	
	private DataSource dataSource;
	
	// SimpleDriverDataSource 사용 -- org.springframework.jdbc-3. 0 .3.RELEASE,jar 파일 추가
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	private ConnectionMaker connectionMaker;
	
	//수정자 메소드 사용 ( set 메소드 )
	public void setConnectionMaker(ConnectionMaker connectionMaker){
		this.connectionMaker = connectionMaker;
	}
	
	//생성자 메소드
/*	public UserDao(ConnectionMaker connectionMaker){
		this.connectionMaker = connectionMaker;
	}
*/

	public void add(User user) throws ClassNotFoundException, SQLException{

		//Connection c = connectionMaker.makeConnection();
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) values (?,?,?)"); 
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeQuery();
		
		ps.close();
		c.close();
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException{
		
		//Connection c = connectionMaker.makeConnection();
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("select * from users where id = ? ");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		User user=null;
		
		if(rs.next()){
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		
		rs.close();
		ps.close();
		
		if( user == null) throw new EmptyResultDataAccessException(1);
		
		return user;
	}
	
	public void deleteAll() throws ClassNotFoundException, SQLException {
		//Connection c = connectionMaker.makeConnection();
		Connection c = null;
		PreparedStatement ps = null;
		
		try{ // 예외가 발생할 가능성이 있는 코드를 모두 try 블럭으로 묶어준다.
			c = dataSource.getConnection();
			ps = makeStatement(c);
			ps.executeQuery();
		}catch(SQLException e){ //예외가 발생했을 때 부가적인 작업을 해줄 수 있도록 catch 블록을 둔다. 아직은 예외를 다시 메소드 밖으로 던지는 것밖에 없다.
			throw e;
		}finally{
			if( ps != null ){
				try{
					ps.close();
				}catch(SQLException e){} // ps.close() 메소드에서도 SQLException이 발생할 수 있기 때문에 이를 잡아줘야 한다. 그렇지 않으면 Connection을 close() 하지 못하고 메소드를 빠져나갈 수 다.
			}
			if( c != null ){
				try{
					c.close();
				}catch(SQLException e){}
			}
		}
	
	}
	
/*	private PreparedStatement makeStatement(Connection c) throws SQLException {
			PreparedStatement ps;
			ps = c.prepareStatement("delete from users");
			return ps;
	}*/
	
	abstract protected PreparedStatement makeStatement(Connection c) throws SQLException;
	
	public int getCount() throws ClassNotFoundException, SQLException{
		//Connection c = connectionMaker.makeConnection();
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try{
			c = dataSource.getConnection();	
			ps = c.prepareStatement("SELECT COUNT(*) FROM USERS");
			rs = ps.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
			
			return count;		
		}catch(SQLException e){
			throw e;
		}finally{
			try{
				rs.close();
			}catch(SQLException e){}
				
			try{
				ps.close();
			}catch(SQLException e){}
			
			try{
				c.close();
			}catch(SQLException e){}
		}
	}
	
}
