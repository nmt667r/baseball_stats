package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.PlayerResultBeans;
import dao.PlayerResultDao;

public class PlayerResultService {
	public List<PlayerResultBeans> select() {
		final int LIMIT_NUM = 1000;
		Connection connection = null;
		try {
			connection = getConnection();
			List<PlayerResultBeans> players = new PlayerResultDao().select(connection, LIMIT_NUM, "");
			commit(connection);
			return players;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
