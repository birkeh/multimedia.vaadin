package at.windesign.application.data.entity;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.server.StreamResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.SortedMap;

public class Serie
{
	private int                         seriesID;
	private String                      seriesName;
	private int                         seriesFirstAired;
	private String                      seriesResolution;
	private boolean                     seriesCliffhanger;
	private String                      seriesStatus;
	private String                      seriesDownload;
	private SortedMap<Integer, Integer> episodeState;
	private int                         minSeason;
	private int                         maxSeason;

	public Serie(int seriesID, String seriesName, int seriesFirstAired, String seriesResolution, boolean seriesCliffhanger, String seriesStatus, String seriesDownload, SortedMap<Integer, Integer> episodeState, int minSeason, int maxSeason)
	{
		super();
		this.seriesID = seriesID;
		this.seriesName = seriesName;
		this.seriesFirstAired = seriesFirstAired;
		this.seriesResolution = seriesResolution;
		this.seriesCliffhanger = seriesCliffhanger;
		this.seriesStatus = seriesStatus;
		this.seriesDownload = seriesDownload;
		this.episodeState = episodeState;
		this.minSeason = minSeason;
		this.maxSeason = maxSeason;
	}

	public Serie()
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

	public SortedMap<Integer, Integer> getEpisodeState()
	{
		return episodeState;
	}

	public void setEpisodeState(SortedMap<Integer, Integer> episodeState)
	{
		this.episodeState = episodeState;
	}

	public int getMinSeason()
	{
		return minSeason;
	}

	public void setMinSeason(int minSeason)
	{
		this.minSeason = minSeason;
	}

	public int getMaxSeason()
	{
		return maxSeason;
	}

	public void setMaxSeason(int maxSeason)
	{
		this.maxSeason = maxSeason;
	}

	@Override
	public String toString()
	{
		return String.valueOf(seriesID);
	}
}
