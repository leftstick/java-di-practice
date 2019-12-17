package com.example.services.atom.impls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.entities.User;
import com.example.services.atom.IUserService;

import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

	public List<User> list() {
		List<User> users = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:h2:mem:h2test;mode=mysql;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
					"sa", "");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user");
			while (rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
					// do nothing
				}

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					// do nothing
				}

				stmt = null;
			}

			try {
				conn.close();
			} catch (SQLException e) {
				// do nothing
			}
		}

		return users;
	}

}
