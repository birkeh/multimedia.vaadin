package at.windesign.application.views.Series;

import at.windesign.application.data.entity.Serie;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.server.StreamRegistration;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@CssImport("styles/views/series/series-edit-dialog.css")

//https://www.google.com/search?q=vaadin+dialog+styling&oq=vaadin+dialog+css&aqs=chrome.1.69i57j0i22i30.6430j0j7&sourceid=chrome&ie=UTF-8
//https://vaadin.com/docs/v17/themes/styling-components.html#overlays
//https://vaadin.com/components/vaadin-dialog/java-examples

public class SeriesEditDialog extends Dialog
{
	/*
	private Serie   m_serie;
	private Text    m_text         = new Text("");
	private int     m_screenWidth  = 0;
	private int     m_screenHeight = 0;

	private TextArea textArea = new TextArea();
*/

	private Div content = new Div();

	public SeriesEditDialog(int screenWidth, int screenHeight, Serie serie)
	{
		content.addClassName("my-style");
		content.setText("This component is styled using global styles");
		add(content);

// @formatter:off
		String styles = ".my-style { "
				+ "  color: red;"
				+ "  background-color: blue;"
//				+ "  background-image: url('images/logo.png');"
				+ " }";
// @formatter:on

		/*
		 * The code below register the style file dynamically. Normally you
		 * use @StyleSheet annotation for the component class. This way is
		 * chosen just to show the style file source code.
		 */
		StreamRegistration resource = UI.getCurrent().getSession()
				.getResourceRegistry()
				.registerResource(new StreamResource("styles.css", () ->
				{
					byte[] bytes = styles.getBytes(StandardCharsets.UTF_8);
					return new ByteArrayInputStream(bytes);
				}));
		UI.getCurrent().getPage().addStyleSheet(
				"base://" + resource.getResourceUri().toString());

		setWidth("400px");
		setHeight("150px");

//		button.addClickListener(event -> dialog.open());

/*
		setId("series-edit-dialog");

		m_screenWidth = screenWidth;
		m_screenHeight = screenHeight;
		m_serie = serie;

//		add(m_text);

		int w = m_screenWidth*9/10;
		int h = m_screenHeight*8/10;

		setWidth(w + "px");
		setHeight(h + "px");

		Notification.show(this.getElement().getStyle().toString());

		this.getElement().getStyle().set( "background-image" , "url('images/logo.png')" );

		textArea.setValue("blabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla \nblabla");
		add(textArea);

//		Image image = new Image("http://image.tmdb.org/t/p/original" + serie.getSeriesBackdrop(), "DummyImage");
//		add(image);

		m_text.setText(m_screenWidth + " x " + m_screenHeight);
//		m_text.setText(serie.getSeriesName() + " (" + serie.getSeriesID() + ")");
 */
	}
}
