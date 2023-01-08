package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import beans.PlayerBeans;
import dao.PlayerInfoDao;

public class PlayerInfoService {
	public List<PlayerBeans> insert(List<PlayerBeans> players) {
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
	public List<PlayerBeans> select(String playerName) {
		final int LIMIT_NUM = 1000;
		Connection connection = null;
		
		String searchName;
		if (StringUtils.isBlank(playerName)) {
			searchName = "%%";
		} else {
			searchName = "%" + playerName + "%";
		}
		
		try {
			connection = getConnection();
			List<PlayerBeans> players = new PlayerInfoDao().select(connection, LIMIT_NUM, searchName);
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
