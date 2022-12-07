package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Result;
import dao.PlayerResultDao;

public class PlayerResultService {
	public List<Result> select(){
		final int LIMIT_NUM = 10;
		Connection connection = null;
		try {
			connection = getConnection();

			List<Result> players = new PlayerResultDao().select(connection, LIMIT_NUM);
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
	
	public List<Result> insert(List<Result> players){
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
