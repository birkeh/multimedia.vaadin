package at.windesign.application.data.entity;

public class Episode
{
	private int     m_seriesID;
	private String  m_seriesName;
	private int     m_seriesFirstAired;
	private String  m_seriesResolution;
	private boolean m_seriesCliffhanger;
	private String  m_seriesStatus;
	private String  m_seriesDownload;
	private String  m_seriesPoster;
	private String  m_seriesBackdrop;
	private int     m_seasonNumber;
	private int     m_episodeNumber;
	private Integer m_episodeState;
	private int     m_minSeason;
	private int     m_maxSeason;

	public Episode(int seriesID, String seriesName, int seriesFirstAired, String seriesResolution, boolean seriesCliffhanger, String seriesStatus, String seriesDownload, String seriesPoster, String seriesBackdrop, int seasonNumber, int episodeNumber, Integer episodeState, int minSeason, int maxSeason)
	{
		super();
		m_seriesID = seriesID;
		m_seriesName = seriesName;
		m_seriesFirstAired = seriesFirstAired;
		m_seriesResolution = seriesResolution;
		m_seriesCliffhanger = seriesCliffhanger;
		m_seriesStatus = seriesStatus;
		m_seriesDownload = seriesDownload;
		m_seriesPoster = seriesPoster;
		m_seriesBackdrop = seriesBackdrop;
		m_seasonNumber = seasonNumber;
		m_episodeNumber = episodeNumber;
		m_episodeState = episodeState;
		m_minSeason = minSeason;
		m_maxSeason = maxSeason;
	}

	public Episode()
	{
	}

	public int getSeriesID()
	{
		return m_seriesID;
	}

	public void setSeriesID(int seriesID)
	{
		m_seriesID = seriesID;
	}

	public String getSeriesName()
	{
		return m_seriesName;
	}

	public void setSeriesName(String seriesName)
	{
		m_seriesName = seriesName;
	}

	public int getSeriesFirstAired()
	{
		return m_seriesFirstAired;
	}

	public void setSeriesFirstAired(int seriesFirstAired)
	{
		m_seriesFirstAired = seriesFirstAired;
	}

	public String getSeriesResolution()
	{
		return m_seriesResolution;
	}

	public void setSeriesResolution(String seriesResolution)
	{
		m_seriesResolution = seriesResolution;
	}

	public boolean getSeriesCliffhanger()
	{
		return m_seriesCliffhanger;
	}

	public void setSeriesCliffhanger(boolean seriesCliffhanger)
	{
		m_seriesCliffhanger = seriesCliffhanger;
	}

	public String getSeriesStatus()
	{
		return m_seriesStatus;
	}

	public void setSeriesStatus(String seriesStatus)
	{
		m_seriesStatus = seriesStatus;
	}

	public String getSeriesDownload()
	{
		return m_seriesDownload;
	}

	public void setSeriesDownload(String seriesDownload)
	{
		m_seriesDownload = seriesDownload;
	}

	public String getSeriesPoster()
	{
		return m_seriesPoster;
	}

	public void setSeriesPoster(String seriesPoster)
	{
		m_seriesPoster = seriesPoster;
	}

	public String getSeriesBackdrop()
	{
		return m_seriesBackdrop;
	}

	public void setSeriesBackdrop(String seriesBackdrop)
	{
		m_seriesBackdrop = seriesBackdrop;
	}

	public int getSeasonNumber()
	{
		return m_seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber)
	{
		m_seasonNumber = seasonNumber;
	}

	public int getEpisodeNumber()
	{
		return m_episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber)
	{
		m_episodeNumber = episodeNumber;
	}

	public Integer getEpisodeState()
	{
		return m_episodeState;
	}

	public void setEpisodeState(Integer episodeState)
	{
		m_episodeState = episodeState;
	}

	public int getMinSeason()
	{
		return m_minSeason;
	}

	public void setMinSeason(int minSeason)
	{
		m_minSeason = minSeason;
	}

	public int getMaxSeason()
	{
		return m_maxSeason;
	}

	public void setMaxSeason(int maxSeason)
	{
		m_maxSeason = maxSeason;
	}

	@Override
	public String toString()
	{
		return String.valueOf(m_seriesID);
	}
}
