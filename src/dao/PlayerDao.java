package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Player;
import exception.SQLRuntimeException;

public class PlayerDao {
	public List<Player> select(Connection connection,int LIMIT_NUM) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM players";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<Player> players = toPlayers(rs);
			return players;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	private List<Player> toPlayers(ResultSet rs) throws SQLException {

		List<Player> players = new ArrayList<Player>();
		try {
			while (rs.next()) {
				Player player = new Player();
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
}
