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
	
	public void insert(Connection connection, List<Player> players) {

		PreparedStatement ps = null;
		try {
			for (Player player: players){
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO players ( ");
			sql.append("    name, ");
			sql.append("    battingAverage, ");
			sql.append("    games, ");
			sql.append("    atBats, ");
			sql.append("    plateAppearance, ");
			sql.append("    hits, ");
			sql.append("    doubles, ");
			sql.append("    triples, ");
			sql.append("    homeruns, ");
			sql.append("    totalBases, ");
			sql.append("    rbi, ");
			sql.append("    runs, ");
			sql.append("    strikeOuts, ");
			sql.append("    baseOnBalls, ");
			sql.append("    hitByPitches, ");
			sql.append("    sacrificeBunts, ");
			sql.append("    sacrificeFlies, ");
			sql.append("    stolenBases, ");
			sql.append("    stolenBaseDeath, ");
			sql.append("    doublePlay, ");
			sql.append("    onBasePercentage, ");
			sql.append("    sluggingPercentage, ");
			sql.append("    ops, ");
			sql.append("    scoringRangePercentage, ");
			sql.append("    errors, ");
			sql.append("    createddate, ");
			sql.append("    updateddate ");

			sql.append(") VALUES ( ");
			sql.append("    ?, "); // name
			sql.append("    ?, "); // battingAverage
			sql.append("    ?, "); // games
			sql.append("    ?, "); // atBats
			sql.append("    ?, "); // plateAppearance
			sql.append("    ?, "); // hits
			sql.append("    ?, "); // doubles
			sql.append("    ?, "); // triples
			sql.append("    ?, "); // homeruns
			sql.append("    ?, "); // totalBases
			sql.append("    ?, "); // rbi
			sql.append("    ?, "); // runs
			sql.append("    ?, "); // strikeOuts
			sql.append("    ?, "); // baseOnBalls
			sql.append("    ?, "); // hitByPitches
			sql.append("    ?, "); // sacrificeBunts
			sql.append("    ?, "); // sacrificeFlies
			sql.append("    ?, "); // stolenBases
			sql.append("    ?, "); // stolenBaseDeath
			sql.append("    ?, "); // doublePlay
			sql.append("    ?, "); // onBasePercentage
			sql.append("    ?, "); // sluggingPercentage
			sql.append("    ?, "); // ops
			sql.append("    ?, "); // scoringRangePercentage
			sql.append("    ?, "); // errors
			sql.append("    CURRENT_TIMESTAMP, "); // created_date
			sql.append("    CURRENT_TIMESTAMP "); // updated_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

				ps.setString(1, player.getName());
				ps.setDouble(2, player.getBattingAverage());
				ps.setInt(3, player.getGames());
				ps.setInt(4, player.getAtBats());
				ps.setInt(5, player.getPlateAppearance());
				ps.setInt(6, player.getHits());
				ps.setInt(7, player.getDoubles());
				ps.setInt(8, player.getTriples());
				ps.setInt(9, player.getHomeruns());
				ps.setInt(10, player.getTotalBases());
				ps.setInt(11, player.getRbi());
				ps.setInt(12, player.getRuns());
				ps.setInt(13, player.getStrikeOuts());
				ps.setInt(14, player.getBaseOnBalls());
				ps.setInt(15, player.getHitByPitches());
				ps.setInt(16, player.getSacrificeBunts());
				ps.setInt(17, player.getSacrificeFlies());
				ps.setInt(18, player.getStolenBases());
				ps.setInt(19, player.getStolenBaseDeath());
				ps.setInt(20, player.getDoublePlay());
				ps.setDouble(21, player.getOnBasePercentage());
				ps.setDouble(22, player.getSluggingPercentage());
				ps.setDouble(23, player.getOps());
				ps.setDouble(24, player.getScoringRangePercentage());
				ps.setInt(25, player.getErrors());

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
