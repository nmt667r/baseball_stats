package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.PlayerResultBeans;
import exception.SQLRuntimeException;

public class PlayerResultDao {
	public List<PlayerResultBeans> select(Connection connection, int LIMIT_NUM, String playerName) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("    results.id as id");
			sql.append("    results.year as year, ");
			sql.append("    results.battingAverage as battingAverage, ");
			sql.append("    results.games as games, ");
			sql.append("    results.atBats as atBats, ");
			sql.append("    results.plateAppearance as plateAppearance, ");
			sql.append("    results.hits as hits, ");
			sql.append("    results.doubles as doubles, ");
			sql.append("    results.triples as triples, ");
			sql.append("    results.homeruns as homeruns, ");
			sql.append("    results.totalBases as totalBases, ");
			sql.append("    results.rbi as rbi, ");
			sql.append("    results.runs as runs, ");
			sql.append("    results.strikeOuts as strikeOuts, ");
			sql.append("    results.baseOnBalls as baseOnBalls, ");
			sql.append("    results.hitByPitches as hitByPitches, ");
			sql.append("    results.sacrificeBunts as sacrificeBunts, ");
			sql.append("    results.sacrificeFlies as sacrificeFlies, ");
			sql.append("    results.stolenBases as stolenBases, ");
			sql.append("    results.stolenBaseDeath as stolenBaseDeath, ");
			sql.append("    results.doublePlay as doublePlay, ");
			sql.append("    results.onBasePercentage as onBasePercentage, ");
			sql.append("    results.sluggingPercentage as sluggingPercentage, ");
			sql.append("    results.ops as opsops, ");
			sql.append("    results.scoringRangePercentage as scoringRangePercentage, ");
			sql.append("    players.name as name, ");
			sql.append("    players.league as league, ");
			sql.append("    players.team as team, ");
			sql.append("FROM results ");
			sql.append("INNER JOIN players ");
			sql.append("ON results.playerid = players.id ");
			sql.append("ORDER BY created_date DESC limit " + LIMIT_NUM);
			sql.append("WHERE players.name LIKE ? ");
			sql.append(";");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, playerName);

			ResultSet rs = ps.executeQuery();
			List<PlayerResultBeans> players = toPlayers(rs);
			return players;
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<PlayerResultBeans> toPlayers(ResultSet rs) throws SQLException {

		List<PlayerResultBeans> playersResults = new ArrayList<PlayerResultBeans>();
		try {
			while (rs.next()) {
				PlayerResultBeans playersResult = new PlayerResultBeans();
				playersResult.setId(rs.getInt("Id"));
				playersResult.setName(rs.getString("name"));
				playersResult.setLeague(rs.getString("league"));
				playersResult.setTeam(rs.getString("team"));
				playersResult.setYear(rs.getInt("year"));
				playersResult.setBattingAverage(rs.getDouble("battingAverage"));
				playersResult.setGames(rs.getInt("games"));
				playersResult.setAtBats(rs.getInt("atBats"));
				playersResult.setPlateAppearance(rs.getInt("plateAppearance"));
				playersResult.setHits(rs.getInt("hits"));
				playersResult.setDoublePlay(rs.getInt("doubles"));
				playersResult.setTriples(rs.getInt("triples"));
				playersResult.setHomeruns(rs.getInt("homeruns"));
				playersResult.setTotalBases(rs.getInt("totalBases"));
				playersResult.setRbi(rs.getInt("rbi"));
				playersResult.setRuns(rs.getInt("runs"));
				playersResult.setStrikeOuts(rs.getInt("strikeOuts"));
				playersResult.setBaseOnBalls(rs.getInt("baseOnBalls"));
				playersResult.setHitByPitches(rs.getInt("hitByPitches"));
				playersResult.setSacrificeBunts(rs.getInt("sacrificeBunts"));
				playersResult.setSacrificeBunts(rs.getInt("sacrificeFlies"));
				playersResult.setStolenBaseDeath(rs.getInt("stolenBases"));
				playersResult.setStolenBaseDeath(rs.getInt("stolenBaseDeath"));
				playersResult.setDoublePlay(rs.getInt("doublePlay"));
				playersResult.setOnBasePercentage(rs.getDouble("onBasePercentage"));
				playersResult.setOnBasePercentage(rs.getDouble("sluggingPercentage"));
				playersResult.setOps(rs.getDouble("ops"));
				playersResult.setScoringRangePercentage(rs.getDouble("scoringRangePercentage"));

				playersResults.add(playersResult);
			}
			return playersResults;
		} finally {
			close(rs);
			
		}
	}
}
