package springbook.user.dao;

import java.sql.*;

public class UserDaoDeleteAll extends AbstractUserDao {
	
	protected PreparedStatement makeStatement(Connection c) throws SQLException {
		PreparedStatement ps;
		ps = c.prepareStatement("delete from users");
		return ps;
	}
	
}
