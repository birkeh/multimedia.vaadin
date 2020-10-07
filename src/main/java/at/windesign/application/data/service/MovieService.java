package at.windesign.application.data.service;

import at.windesign.application.data.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieService
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Movie> findAll()
	{
		try
		{
			return jdbcTemplate.query("SELECT movieID, movieTitle, originalTitle, releaseDate, overview, state, resolution FROM movie ORDER BY movieTitle",
					(rs, rowNum) -> new Movie(rs.getInt("movieID"), rs.getString("movieTitle"), rs.getString("originalTitle"), rs.getString("releaseDate"), rs.getString("overview"), rs.getString("state"), rs.getString("resolution")));
		} catch(Exception e)
		{
			return new ArrayList<Movie>();
		}
	}

	public int saveMovie(Movie movie)
	{
		return 0;
	}

	private int updateMovie(Movie movie)
	{
		return 0;
	}

	private int insertMovie(Movie movie)
	{
		return 0;
	}

	public int deleteMovie(Movie movie)
	{
		return 0;
	}
}
