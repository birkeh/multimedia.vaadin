package at.windesign.application.views.Series;

import at.windesign.application.data.entity.Series;
import at.windesign.application.data.service.SerieService;
import at.windesign.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route(value = "series", layout = MainView.class)
@PageTitle("TV Shows")
@CssImport("styles/views/series/series-view.css")
public class SeriesView extends Div implements AfterNavigationObserver
{
	@Autowired
	private SerieService serieService;

	private Grid<Series> series;

	private TextField seriesName       = new TextField();
	private TextField seriesFirstAired = new TextField();

	public SeriesView()
	{
		setId("series-view");

		series = new Grid<>();
		series.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		series.setHeightFull();
		series.addColumn(Series::getSeriesName).setHeader("Series Name");
		series.addColumn(Series::getSeriesFirstAired).setHeader("First Aired");
		series.addColumn(Series::getSeriesResolution).setHeader("Resolution");
		series.addColumn(Series::getSeriesCliffhanger).setHeader("Cliffhanger");

		//when a row is selected or deselected, populate form
		series.asSingleSelect().addValueChangeListener(event -> populateForm(event.getValue()));

		series.addItemDoubleClickListener(
				event ->
				{
					Notification.show("Clicked Item: " + event.getItem());
				});

		add(series);
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event)
	{
		List<Series>	seriesList = serieService.findAll();
		List<Series>	seriesList1 = new ArrayList<Series>();

		int			oldID	= -1;

		for(Series s : seriesList)
		{
			if(s.getSeriesID() != oldID)
			{
				oldID	= s.getSeriesID();
				seriesList1.add(s);
			}
		};
		series.setItems(seriesList1);
	}

	private void populateForm(Series value)
	{
		if(value == null)
		{
			value = new Series();
		}
	}
}
