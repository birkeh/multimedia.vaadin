package at.windesign.application.data.entity;

/*
        $sql =  " SELECT	serie.seriesID seriesID," .
    			" 			serie.seriesName seriesName," .
    			"           YEAR(serie.firstAired) seriesFirstAired," .
    			"           serie.resolution seriesResolution," .
    			"           serie.cliffhanger seriesCliffhanger," .
    			"           serie.status seriesStatus," .
     			"           serie.download seriesDownload," .
    			"           season.seasonNumber seasonNumber," .
    			"           episode.episodeNumber episodeNumber," .
                "           episode.state episodeState" .
    			" FROM		serie" .
    			" LEFT JOIN	season ON serie.seriesID = season.seriesID" .
    			" LEFT JOIN	episode ON serie.seriesID = episode.seriesID AND season.seasonNumber = episode.seasonNumber" .
    			" WHERE     season.seasonNumber != 0 OR" .
    			"           serie.seriesID >= 1000000 " .
    			" ORDER BY	serie.seriesName," .
    			" 			serie.firstAired," .
    			" 			season.seasonNumber," .
    			" 			episode.episodeNumber;";

 */
public class Series
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
	private String episodeState;

	public Series(int seriesID, String seriesName, int seriesFirstAired, String seriesResolution, boolean seriesCliffhanger, String seriesStatus, String seriesDownload, int seasonNumber, int episodeNumber, String episodeState)
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

	public Series()
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

	public String getEpisodeState()
	{
		return episodeState;
	}

	public void setEpisodeState(String episodeState)
	{
		this.episodeState = episodeState;
	}

	@Override
	public String toString()
	{
		return String.valueOf(seriesID);
	}
}
