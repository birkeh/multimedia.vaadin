package at.windesign.application.data.entity;

public class Movie
{
	private int movieID;
	private String movieTitle;
	private String originalTitle;
	private String releaseDate;
	private String overview;
	private String state;
	private String resolution;

	public Movie(int movieID, String movieTitle, String originalTitle, String releaseDate, String overview, String state, String resolution)
	{
		super();
		this.movieID = movieID;
		this.movieTitle = movieTitle;
		this.originalTitle = originalTitle;
		this.releaseDate = releaseDate;
		this.overview = overview;
		this.state = state;
		this.resolution = resolution;
	}

	public Movie()
	{

	}

	public int getMovieID()
	{
		return movieID;
	}

	public void setMovieID(int movieID)
	{
		this.movieID = movieID;
	}

	public String getMovieTitle()
	{
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle)
	{
		this.movieTitle = movieTitle;
	}

	public String getOriginalTitle()
	{
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle)
	{
		this.originalTitle = originalTitle;
	}

	public String getReleaseDate()
	{
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate)
	{
		this.releaseDate = releaseDate;
	}

	public String getOverview()
	{
		return overview;
	}

	public void setOverview(String overview)
	{
		this.overview = overview;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getResolution()
	{
		return resolution;
	}

	public void setResolution(String resolution)
	{
		this.resolution = resolution;
	}

	@Override
	public String toString()
	{
		return String.valueOf(movieID);
	}
}
