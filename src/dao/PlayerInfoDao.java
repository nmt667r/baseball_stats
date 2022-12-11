package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import beans.Player;
import exception.SQLRuntimeException;

public class PlayerInfoDao {
	public void insert(Connection connection, List<Player> players) {

		PreparedStatement ps = null;
		try {
			for (Player player : players) {
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
