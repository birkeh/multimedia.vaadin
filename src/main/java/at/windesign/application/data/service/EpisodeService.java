package at.windesign.application.data.service;

import at.windesign.application.data.entity.Episode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EpisodeService
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Episode> findAll()
	{
		try
		{
			return jdbcTemplate.query("SELECT	serie.seriesID seriesID," +
							" 			serie.seriesName seriesName," +
							"           YEAR(serie.firstAired) seriesFirstAired," +
							"           serie.resolution seriesResolution," +
							"           serie.cliffhanger seriesCliffhanger," +
							"           serie.status seriesStatus," +
							"           serie.download seriesDownload," +
							"           season.seasonNumber seasonNumber," +
							"           episode.episodeNumber episodeNumber," +
							"           episode.state episodeState," +
							"           ( SELECT MIN(s.seasonNumber) FROM season s WHERE s.seriesID = serie.seriesID ) minSeason," +
							"           ( SELECT MAX(s.seasonNumber) FROM season s WHERE s.seriesID = serie.seriesID ) maxSeason" +
					" FROM		serie" +
							" LEFT JOIN	season ON serie.seriesID = season.seriesID" +
							" LEFT JOIN	episode ON serie.seriesID = episode.seriesID AND season.seasonNumber = episode.seasonNumber" +
							" WHERE     season.seasonNumber != 0 OR" +
							"           serie.seriesID >= 1000000 " +
							" ORDER BY	serie.seriesName," +
							" 			serie.firstAired," +
							" 			season.seasonNumber," +
							" 			episode.episodeNumber;",
					(rs, rowNum) -> new Episode(rs.getInt("seriesID"), rs.getString("seriesName"), rs.getInt("seriesFirstAired"), rs.getString("seriesResolution"), rs.getBoolean("seriesCliffhanger"), rs.getString("seriesStatus"), rs.getString("seriesDownload"), rs.getInt("seasonNumber"), rs.getInt("episodeNumber"), rs.getInt("episodeState"), rs.getInt("minSeason"), rs.getInt("maxSeason")));

		} catch(Exception e)
		{
			return new ArrayList<>();
		}
	}

	public Integer minSeason()
	{
		return jdbcTemplate.queryForObject("SELECT MIN(seasonNumber) FROM season;", Integer.class);
	}

	public Integer maxSeason()
	{
		return jdbcTemplate.queryForObject("SELECT MAX(seasonNumber) FROM season;", Integer.class);
	}

	public int saveEpisode(Episode Episode)
	{
		return 0;
	}

	private int updateEpisode(Episode Episode)
	{
		return 0;
	}

	private int insertEpisode(Episode Episode)
	{
		return 0;
	}

	public int deleteEpisode(Episode Episode)
	{
		return 0;
	}
}
