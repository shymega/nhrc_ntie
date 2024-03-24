package uk.nhrc.apps.ntie.views.list;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Hello from Ti.to!")
@Route(value = "hello")
public class HelloTitoView extends HorizontalLayout {
    public HelloTitoView() {
        super();
    }
}
