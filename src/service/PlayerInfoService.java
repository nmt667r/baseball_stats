package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Player;
import dao.PlayerInfoDao;

public class PlayerInfoService {
	public List<Player> insert(List<Player> players) {
		Connection connection = null;
		try {
			connection = getConnection();

			new PlayerInfoDao().insert(connection, players);
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
