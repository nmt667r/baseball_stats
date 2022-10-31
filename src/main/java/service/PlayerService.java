package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Player;
import dao.PlayerDao;

public class PlayerService {
	public List<Player> select(){
		final int LIMIT_NUM = 10;
		Connection connection = null;
		try {
			connection = getConnection();

			List<Player> players = new PlayerDao().select(connection, LIMIT_NUM);
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
