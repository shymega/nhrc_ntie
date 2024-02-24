package uk.nhrc.apps.ntie.views.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Value;
import uk.nhrc.apps.ntie.data.TitoTicket;
import uk.nhrc.apps.ntie.ext.tito.TitoApiClientFactory;

import java.util.Arrays;

@PageTitle("Hello from Ti.to!")
@Route(value = "hello")
public class HelloTitoView extends HorizontalLayout {
    @Value("${tito.secret}")
    private String titoSecret;

    public HelloTitoView() {
	super();
    }
}
