package at.windesign.application.views.Series;

import at.windesign.application.data.entity.Episode;
import at.windesign.application.data.entity.Serie;
import at.windesign.application.data.service.EpisodeService;
import at.windesign.application.views.main.MainView;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.component.dialog.Dialog;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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

	private Integer stateWidth  = 4;
	private Integer stateHeight = 20;

	private SeriesEditDialog seriesEditDialog;

	public SeriesView()
	{
		setId("series-view");

		this.getElement().getStyle().set( "background-image" , "url('images/logo.png')" );

		serie = new Grid<>();
		serie.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		serie.setHeightFull();
		serie.addColumn(TemplateRenderer.<Serie>of("<div style$=\"[[item.style]]\">[[item.name]]</div>")
				.withProperty("name", Serie::getSeriesName)
				.withProperty("style", Serie::getSeriesStyle))
				.setHeader("Series Name").setFrozen(true);
		serie.addColumn(Serie::getSeriesFirstAired).setHeader("First Aired").setFrozen(true);
		serie.addColumn(Serie::getSeriesResolution).setHeader("Resolution");

		serie.addItemDoubleClickListener(
				event ->
				{
					UI.getCurrent().getPage().retrieveExtendedClientDetails(details ->
					{
						seriesEditDialog = new SeriesEditDialog(details.getBodyClientWidth(), details.getBodyClientHeight(), event.getItem());
						seriesEditDialog.open();
					});
				});

		serie.getColumns().forEach(col -> col.setAutoWidth(true));

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
					serieList.add(new Serie(lastEpisode.getSeriesID(), lastEpisode.getSeriesName(), lastEpisode.getSeriesFirstAired(), lastEpisode.getSeriesResolution(), lastEpisode.getSeriesCliffhanger(), lastEpisode.getSeriesStatus(), lastEpisode.getSeriesDownload(), lastEpisode.getSeriesPoster(), lastEpisode.getSeriesBackdrop(), episodeState, lastEpisode.getMinSeason(), lastEpisode.getMaxSeason()));
					episodeState = new TreeMap<>();
				}

				lastEpisode = e;
				oldID = e.getSeriesID();
			}
			episodeState.put((e.getSeasonNumber() << 8) + e.getEpisodeNumber(), e.getEpisodeState());
		}
		if(oldID != -1)
		{
			serieList.add(new Serie(lastEpisode.getSeriesID(), lastEpisode.getSeriesName(), lastEpisode.getSeriesFirstAired(), lastEpisode.getSeriesResolution(), lastEpisode.getSeriesCliffhanger(), lastEpisode.getSeriesStatus(), lastEpisode.getSeriesDownload(), lastEpisode.getSeriesPoster(), lastEpisode.getSeriesBackdrop(), episodeState, lastEpisode.getMinSeason(), lastEpisode.getMaxSeason()));
		}

		serie.setItems(serieList);
	}

	private InputStream getImageInputStream(Integer curSeason, SortedMap<Integer, Integer> state)
	{
		ByteArrayOutputStream       imagebuffer = null;
		Integer                     season      = -1;
		Integer                     episode     = -1;
		SortedMap<Integer, Integer> seasonState = new TreeMap<>();

		for(Integer key : state.keySet())
		{
			season = key >> 8;
			episode = key & 0xFF;

			if(season == curSeason)
				seasonState.put(episode, state.get(key));
			else if(season > curSeason)
				break;
		}

		if(seasonState.size() == 0)
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

		BufferedImage image    = new BufferedImage(stateWidth * seasonState.lastKey(), stateHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D    drawable = image.createGraphics();
		Color         colGrey  = new Color(192, 192, 192);
		Color         colBlue  = new Color(0, 0, 192);
		Color         colGreen = new Color(0, 192, 0);
		Color         colBlack = new Color(0, 0, 0);
		Color         col      = colBlack;

		drawable.setColor(colBlack);
		drawable.fillRect(0, 0, stateWidth * seasonState.lastKey(), stateHeight);

		for(Integer key : seasonState.keySet())
		{
			switch(seasonState.get(key))
			{
				case 0:
					col = colBlack;
					break;
				case 1:
					col = colGrey;
					break;
				case 2:
					col = colBlue;
					break;
				case 3:
					col = colGreen;
					break;
			}

			drawable.setColor(col);
			drawable.fillRect((key - 1) * stateWidth, 0, stateWidth - 1, stateHeight);
		}

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
