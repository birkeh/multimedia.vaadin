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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

	ByteArrayOutputStream imagebuffer = null;
	StreamResource        resource;

	public SeriesView()
	{
		resource = new StreamResource("image.png", () -> getImageInputStream());

		setId("series-view");

		serie = new Grid<>();
		serie.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		serie.setHeightFull();
		serie.addColumn(Serie::getSeriesName).setHeader("Series Name");
		serie.addColumn(Serie::getSeriesFirstAired).setHeader("First Aired");
		serie.addColumn(Serie::getSeriesResolution).setHeader("Resolution");
		serie.addColumn(Serie::getSeriesCliffhanger).setHeader("Cliffhanger");
		serie.addComponentColumn(i -> new Image(resource, "alt text")).setHeader("Preview");
//		serie.addComponentColumn(i -> new Image(new StreamResource("image.png", () -> getImageInputStream(Serie::getEpisodeState)), "alt text")).setHeader("Preview");


		//when a row is selected or deselected, populate form
		serie.asSingleSelect().addValueChangeListener(event -> populateForm(event.getValue()));

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
		List<Episode>          episodeList  = episodeService.findAll();
		List<Serie>            serieList    = new ArrayList<>();
		Episode                lastEpisode  = null;
		SortedMap<Integer, Integer> episodeState = new TreeMap<>();
		int                    oldID        = -1;

		for(Episode e : episodeList)
		{
			if(e.getSeriesID() != oldID)
			{
				if(oldID != -1)
				{
					serieList.add(new Serie(lastEpisode.getSeriesID(), lastEpisode.getSeriesName(), lastEpisode.getSeriesFirstAired(), lastEpisode.getSeriesResolution(), lastEpisode.getSeriesCliffhanger(), lastEpisode.getSeriesStatus(), lastEpisode.getSeriesDownload(), episodeState));
					episodeState.clear();
				}

				lastEpisode = e;
				oldID = e.getSeriesID();
			}
			episodeState.put(e.getSeasonNumber() << 8 + e.getEpisodeNumber(), e.getEpisodeState());
		}
		if(oldID != -1)
			serieList.add(new Serie(lastEpisode.getSeriesID(), lastEpisode.getSeriesName(), lastEpisode.getSeriesFirstAired(), lastEpisode.getSeriesResolution(), lastEpisode.getSeriesCliffhanger(), lastEpisode.getSeriesStatus(), lastEpisode.getSeriesDownload(), episodeState));

		serie.setItems(serieList);
	}

	private void populateForm(Serie value)
	{
		if(value == null)
		{
			value = new Serie();
		}
	}

//	private InputStream getImageInputStream(SortedMap<Integer, Integer> state)
	private InputStream getImageInputStream()
	{
//		int           reloads  = state.firstKey();
		int           reloads  = 1;
		BufferedImage image    = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
		Graphics2D    drawable = image.createGraphics();

		// Draw something static
		drawable.setStroke(new BasicStroke(5));
		drawable.setColor(Color.WHITE);
		drawable.fillRect(0, 0, 400, 400);
		drawable.setColor(Color.BLACK);
		drawable.drawOval(50, 50, 300, 300);

		// Draw something dynamic
		drawable.setFont(new Font("Montserrat",
				Font.PLAIN, 48));
		drawable.drawString("Reloads=" + reloads, 75, 216);
		reloads++;
		drawable.setColor(new Color(0, 165, 235));
		int x = (int) (200 - 10 + 150 * Math.sin(reloads * 0.3));
		int y = (int) (200 - 10 + 150 * Math.cos(reloads * 0.3));
		drawable.fillOval(x, y, 20, 20);

		try
		{
			// Write the image to a buffer
			imagebuffer = new ByteArrayOutputStream();
			ImageIO.write(image, "png", imagebuffer);

			// Return a stream from the buffer
			return new ByteArrayInputStream(
					imagebuffer.toByteArray());
		} catch(IOException e)
		{
			return null;
		}
	}
}
