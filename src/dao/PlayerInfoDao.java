package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.PlayerBeans;
import exception.SQLRuntimeException;

public class PlayerInfoDao {
	public List<PlayerBeans> select(Connection connection, int LIMIT_NUM, String playerName) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM players ");
			sql.append("WHERE players.name LIKE ? ");
			sql.append(";");
			
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, playerName);
			
			ResultSet rs = ps.executeQuery();
			List<PlayerBeans> players = toPlayers(rs);
			return players;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	private List<PlayerBeans> toPlayers(ResultSet rs) throws SQLException {

		List<PlayerBeans> players = new ArrayList<PlayerBeans>();
		try {
			while (rs.next()) {
				PlayerBeans player = new PlayerBeans();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("name"));
				//player.setCreatedDate(rs.getTimestamp("created_date"));
				//player.setUpdatedDate(rs.getTimestamp("updated_date"));
				players.add(player);
			}
			return players;
		} finally {
			close(rs);
		}
	}
	
	public void insert(Connection connection, List<PlayerBeans> players) {
		PreparedStatement ps = null;
		try {
			for (PlayerBeans player : players) {
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO players ( ");
				sql.append("    name, ");
				sql.append("    league, ");
				sql.append("    team, ");
				sql.append("    createddate, ");
				sql.append("    updateddate ");
				sql.append(") VALUES ( ");
				sql.append("    ?, "); // name
				sql.append("    ?, "); // league
				sql.append("    ?, "); // team
				sql.append("    CURRENT_TIMESTAMP, "); // created_date
				sql.append("    CURRENT_TIMESTAMP "); // updated_date
				sql.append(")");

				ps = connection.prepareStatement(sql.toString());

				ps.setString(1, player.getName());
				ps.setString(2, player.getLeague());
				ps.setString(3, player.getTeam());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
