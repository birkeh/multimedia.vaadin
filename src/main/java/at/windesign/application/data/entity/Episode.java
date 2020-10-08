package at.windesign.application.data.entity;

public class Episode
{
	private int	seriesID;
	private String seriesName;
	private int seriesFirstAired;
	private String seriesResolution;
	private boolean seriesCliffhanger;
	private String seriesStatus;
	private String seriesDownload;
	private int seasonNumber;
	private int episodeNumber;
	private Integer episodeState;

	public Episode(int seriesID, String seriesName, int seriesFirstAired, String seriesResolution, boolean seriesCliffhanger, String seriesStatus, String seriesDownload, int seasonNumber, int episodeNumber, Integer episodeState)
	{
		super();
		this.seriesID			= seriesID;
		this.seriesName			= seriesName;
		this.seriesFirstAired	= seriesFirstAired;
		this.seriesResolution	= seriesResolution;
		this.seriesCliffhanger	= seriesCliffhanger;
		this.seriesStatus		= seriesStatus;
		this.seriesDownload		= seriesDownload;
		this.seasonNumber		= seasonNumber;
		this.episodeNumber		= episodeNumber;
		this.episodeState		= episodeState;
	}

	public Episode()
	{
	}

	public int getSeriesID()
	{
		return seriesID;
	}

	public void setSeriesID(int seriesID)
	{
		this.seriesID = seriesID;
	}

	public String getSeriesName()
	{
		return seriesName;
	}

	public void setSeriesName(String seriesName)
	{
		this.seriesName = seriesName;
	}

	public int getSeriesFirstAired()
	{
		return seriesFirstAired;
	}

	public void setSeriesFirstAired(int seriesFirstAired)
	{
		this.seriesFirstAired = seriesFirstAired;
	}

	public String getSeriesResolution()
	{
		return seriesResolution;
	}

	public void setSeriesResolution(String seriesResolution)
	{
		this.seriesResolution = seriesResolution;
	}

	public boolean getSeriesCliffhanger()
	{
		return seriesCliffhanger;
	}

	public void setSeriesCliffhanger(boolean seriesCliffhanger)
	{
		this.seriesCliffhanger = seriesCliffhanger;
	}

	public String getSeriesStatus()
	{
		return seriesStatus;
	}

	public void setSeriesStatus(String seriesStatus)
	{
		this.seriesStatus = seriesStatus;
	}

	public String getSeriesDownload()
	{
		return seriesDownload;
	}

	public void setSeriesDownload(String seriesDownload)
	{
		this.seriesDownload = seriesDownload;
	}

	public int getSeasonNumber()
	{
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber)
	{
		this.seasonNumber = seasonNumber;
	}

	public int getEpisodeNumber()
	{
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber)
	{
		this.episodeNumber = episodeNumber;
	}

	public Integer getEpisodeState()
	{
		return episodeState;
	}

	public void setEpisodeState(Integer episodeState)
	{
		this.episodeState = episodeState;
	}

	@Override
	public String toString()
	{
		return String.valueOf(seriesID);
	}
}
