package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.SQLRuntimeException;

/**
 * DB(コネクション関係)のユーティリティー
 */
public class DBUtil {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/postgres/baseball_stats";
	private static final String USER = "postgres";
	private static final String PASSWORD = "postgres";

	static {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * コネクション取得
	 * @return
	 */
	public static Connection getConnection() {

		try {
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			connection.setAutoCommit(false);
			return connection;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	/**
	 * コミット
	 * @param connection
	 */
	public static void commit(Connection connection) {

		try {
			connection.commit();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	/**
	 * ロールバック
	 * @param connection
	 */
	public static void rollback(Connection connection) {

		if (connection == null) {
			return;
		}

		try {
			connection.rollback();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}
}
