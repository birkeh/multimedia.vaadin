package at.windesign.application.views.movies;

import at.windesign.application.data.entity.Movie;
import at.windesign.application.data.service.MovieService;
import at.windesign.application.views.main.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "movies", layout = MainView.class)
@PageTitle("Movies")
@CssImport("styles/views/movies/movies-view.css")
public class MoviesView extends Div implements AfterNavigationObserver
{
	@Autowired
	private MovieService movieService;

	private Grid<Movie> movies;

	private TextField     movieTitle = new TextField();
	private TextField     originalTitle  = new TextField();

	private TextArea	textArea = new TextArea();

	public MoviesView()
	{
		setId("movies-view");

		this.getElement().getStyle().set( "background-image" , "url('images/logo.png')" );

		textArea.setValue("blabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla");
		add(textArea);
/*
		movies = new Grid<>();
		movies.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		movies.setHeightFull();
		movies.addColumn(Movie::getMovieTitle).setHeader("Movie Title");
		movies.addColumn(Movie::getOriginalTitle).setHeader("Original Title");
		movies.addColumn(Movie::getReleaseDate).setHeader("Release Date");
		movies.addColumn(Movie::getResolution).setHeader("Resolution");

		//when a row is selected or deselected, populate form
		movies.asSingleSelect().addValueChangeListener(event -> populateForm(event.getValue()));

		movies.addItemDoubleClickListener(
				event ->
				{
					Notification.show("Clicked Item: " + event.getItem());
				});

		add(movies);
*/
	}


	@Override
	public void afterNavigation(AfterNavigationEvent event)
	{
//		movies.setItems(movieService.findAll());
	}

	private void populateForm(Movie value)
	{
/*
		if(value == null)
		{
			value = new Movie();
		}
*/
	}
}
