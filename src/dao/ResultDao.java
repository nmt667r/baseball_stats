package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ResultBeans;
import exception.SQLRuntimeException;

public class ResultDao {
	public List<ResultBeans> select(Connection connection, int LIMIT_NUM) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM results";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<ResultBeans> results = toPlayers(rs);
			return results;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<ResultBeans> toPlayers(ResultSet rs) throws SQLException {

		List<ResultBeans> results = new ArrayList<ResultBeans>();
		try {
			while (rs.next()) {
				ResultBeans result = new ResultBeans();
				result.setId(rs.getInt("id"));
				//result.setCreatedDate(rs.getTimestamp("created_date"));
				//result.setUpdatedDate(rs.getTimestamp("updated_date"));
				results.add(result);
			}
			return results;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, List<ResultBeans> results) {

		PreparedStatement ps = null;
		try {
			for (ResultBeans result : results) {
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO results ( ");
				sql.append("    playerId, ");
				sql.append("    year, ");
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
//				sql.append("    scoringRangePercentage, ");
//				sql.append("    errors, ");
				sql.append("    createddate, ");
				sql.append("    updateddate ");
				sql.append(") VALUES ( ");
				sql.append("    ?, "); // playerId
				sql.append("    ?, "); // year
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
//				sql.append("    ?, "); // scoringRangePercentage
//				sql.append("    ?, "); // errors
				sql.append("    CURRENT_TIMESTAMP, "); // created_date
				sql.append("    CURRENT_TIMESTAMP "); // updated_date
				sql.append(")");

				ps = connection.prepareStatement(sql.toString());

				ps.setInt(1, result.getPlayerId());
				ps.setInt(2, result.getYear());
				ps.setDouble(3, result.getBattingAverage());
				ps.setInt(4, result.getGames());
				ps.setInt(5, result.getAtBats());
				ps.setInt(6, result.getPlateAppearance());
				ps.setInt(7, result.getHits());
				ps.setInt(8, result.getDoubles());
				ps.setInt(9, result.getTriples());
				ps.setInt(10, result.getHomeruns());
				ps.setInt(11, result.getTotalBases());
				ps.setInt(12, result.getRbi());
				ps.setInt(13, result.getRuns());
				ps.setInt(14, result.getStrikeOuts());
				ps.setInt(15, result.getBaseOnBalls());
				ps.setInt(16, result.getHitByPitches());
				ps.setInt(17, result.getSacrificeBunts());
				ps.setInt(18, result.getSacrificeFlies());
				ps.setInt(19, result.getStolenBases());
				ps.setInt(20, result.getStolenBaseDeath());
				ps.setInt(21, result.getDoublePlay());
				ps.setDouble(22, result.getOnBasePercentage());
				ps.setDouble(23, result.getSluggingPercentage());
				ps.setDouble(24, result.getOps());
//				ps.setDouble(24, result.getScoringRangePercentage());
//				ps.setInt(25, result.getErrors());
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
