package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.ResultBeans;
import dao.PlayerResultDao;

public class PlayerResultService {
	public List<ResultBeans> select() {
		final int LIMIT_NUM = 10;
		Connection connection = null;
		try {
			connection = getConnection();
			List<ResultBeans> players = new PlayerResultDao().select(connection, LIMIT_NUM);
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

	public List<ResultBeans> insert(List<ResultBeans> players) {
		Connection connection = null;
		try {
			connection = getConnection();

			new PlayerResultDao().insert(connection, players);
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
