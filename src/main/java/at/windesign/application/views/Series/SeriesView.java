package at.windesign.application.views.Series;

import at.windesign.application.data.entity.Episode;
import at.windesign.application.data.entity.Serie;
import at.windesign.application.data.service.EpisodeService;
import at.windesign.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

@Route(value = "series", layout = MainView.class)
@PageTitle("TV Shows")
@CssImport("styles/views/series/series-view.css")
public class SeriesView extends Div implements AfterNavigationObserver
{
	@Autowired
	private EpisodeService episodeService;

	private Grid<Serie> serie;

	private TextField seriesName       = new TextField();
	private TextField seriesFirstAired = new TextField();

	private Integer minSeason = 0;
	private Integer maxSeason = 0;

	private boolean isInitialized = false;

	public SeriesView()
	{
		setId("series-view");

		serie = new Grid<>();
		serie.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		serie.setHeightFull();
		serie.addColumn(Serie::getSeriesName).setHeader("Series Name");
		serie.addColumn(Serie::getSeriesFirstAired).setHeader("First Aired");
		serie.addColumn(Serie::getSeriesResolution).setHeader("Resolution");
		serie.addColumn(Serie::getSeriesCliffhanger).setHeader("Cliffhanger");

		serie.addItemDoubleClickListener(
				event ->
				{
					Notification.show("Clicked Item: " + event.getItem());
				});

		add(serie);
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event)
	{
		if(!isInitialized)
		{
			minSeason = episodeService.minSeason();
			maxSeason = episodeService.maxSeason();

			if(minSeason < 1)
				minSeason = 1;

			for(Integer s = minSeason; s <= maxSeason; s++)
			{
				final Integer sFinal = s;
				serie.addComponentColumn(i ->
				{
//					StreamResource r   = new StreamResource("image.png", () -> getImageInputStream(sFinal, i.getEpisodeState()));
//					Image          img = new Image(r, "");
//					return img;
					return new Image(new StreamResource("image.png", () -> getImageInputStream(sFinal, i.getEpisodeState())), "");
				}).setHeader("Season " + sFinal);
			}
			isInitialized = true;
		}

		List<Episode>               episodeList  = episodeService.findAll();
		List<Serie>                 serieList    = new ArrayList<>();
		Episode                     lastEpisode  = null;
		SortedMap<Integer, Integer> episodeState = new TreeMap<>();
		int                         oldID        = -1;

		for(Episode e : episodeList)
		{
			if(e.getSeriesID() != oldID)
			{
				if(oldID != -1)
				{
					serieList.add(new Serie(lastEpisode.getSeriesID(), lastEpisode.getSeriesName(), lastEpisode.getSeriesFirstAired(), lastEpisode.getSeriesResolution(), lastEpisode.getSeriesCliffhanger(), lastEpisode.getSeriesStatus(), lastEpisode.getSeriesDownload(), episodeState, lastEpisode.getMinSeason(), lastEpisode.getMaxSeason()));
					episodeState = new TreeMap<>();
				}

				lastEpisode = e;
				oldID = e.getSeriesID();
			}
			episodeState.put((e.getSeasonNumber() << 8) + e.getEpisodeNumber(), e.getEpisodeState());
		}
		if(oldID != -1)
			serieList.add(new Serie(lastEpisode.getSeriesID(), lastEpisode.getSeriesName(), lastEpisode.getSeriesFirstAired(), lastEpisode.getSeriesResolution(), lastEpisode.getSeriesCliffhanger(), lastEpisode.getSeriesStatus(), lastEpisode.getSeriesDownload(), episodeState, lastEpisode.getMinSeason(), lastEpisode.getMaxSeason()));

		serie.setItems(serieList);
	}

	private InputStream getImageInputStream(Integer curSeason, SortedMap<Integer, Integer> state)
	{
		ByteArrayOutputStream imagebuffer = null;
		Integer               season      = -1;
		Integer               episode     = -1;
		boolean               found       = false;

		for(Integer key : state.keySet())
		{
			season = key >> 8;
			episode = key & 0xFF;

			if(season == curSeason)
			{
				found = true;
				break;
			}
		}

		if(!found)
		{
			BufferedImage image    = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
			Graphics2D    drawable = image.createGraphics();

			drawable.setColor(Color.WHITE);
			drawable.fillRect(0, 0, 1, 1);

			try
			{
				imagebuffer = new ByteArrayOutputStream();
				ImageIO.write(image, "png", imagebuffer);
				return new ByteArrayInputStream(imagebuffer.toByteArray());
			} catch(IOException e)
			{
				return null;
			}
		}

		BufferedImage image    = new BufferedImage(100, 10, BufferedImage.TYPE_INT_RGB);
		Graphics2D    drawable = image.createGraphics();

		drawable.setColor(Color.WHITE);
		drawable.fillRect(0, 0, 100, 10);
		drawable.setColor(Color.BLACK);
		drawable.drawString("S" + season + "E" + episode, 5, 10);

		try
		{
			imagebuffer = new ByteArrayOutputStream();
			ImageIO.write(image, "png", imagebuffer);

			return new ByteArrayInputStream(imagebuffer.toByteArray());
		} catch(IOException e)
		{
			return null;
		}
	}
}
