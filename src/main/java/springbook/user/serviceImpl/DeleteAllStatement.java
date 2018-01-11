package springbook.user.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import springbook.user.service.StatementStrategy;

public class DeleteAllStatement implements StatementStrategy{
	public PreparedStatement makePreparedStatement(Connection c) throws SQLException{
		PreparedStatement ps = c.prepareStatement("delete from users");
		return ps;
	}
}
