package com.zipsoft.suggestfield.demo;

import java.util.ArrayList;
import java.util.List;

import com.zipsoft.suggestfield.SuggestField;
import com.zipsoft.suggestfield.SuggestField.SuggestionLoader;
import com.zipsoft.suggestfield.SuggestField.SuggestionSelectedEvent;
import com.zipsoft.suggestfield.SuggestField.SuggestionSelectedListener;
import com.zipsoft.suggestfield.shared.SuggestFieldSuggestion;
import com.zipsoft.suggestfield.shared.SuggestFieldSuggestionImpl;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

//@Theme("demo")
@Title("SuggestField Add-on Demo")
public class DemoUI extends UI
{

	private static final long serialVersionUID = -829034832083785687L;

	@WebServlet(value = {"/*", "/VAADIN/*"}, asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "com.zipsoft.suggestfield.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {

		private static final long serialVersionUID = 2927130973603505840L;
    }

    @Override
    protected void init(VaadinRequest request) {

        // Initialize our new UI component
        final SuggestField component = new SuggestField();
        component.setImmediate(true);
        component.setInputPrompt("Odaberite bla bla");
        
        component.addSuggestionSelectedListener(new SuggestionSelectedListener() {
			
			@Override
			public void onSuggestionSelected(SuggestionSelectedEvent event) {
				System.out.println(event.getSuggestion());
				
			}
		});
        
        component.setSuggestionLoader(new SuggestionLoader() {
			
			@Override
			public List<SuggestFieldSuggestionImpl> loadSuggestions(String query, int limit) {
				List<SuggestFieldSuggestionImpl> suggestions = new ArrayList<SuggestFieldSuggestionImpl>();
				suggestions.add(new SuggestFieldSuggestionImpl("1", "Marko", "Marko"));
				suggestions.add(new SuggestFieldSuggestionImpl("2", "Zoran", "Zoran"));
				return suggestions;
			}

			@Override
			public SuggestFieldSuggestion convertToModel(
					SuggestFieldSuggestionImpl suggestion) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public SuggestFieldSuggestionImpl convertToSuggestion(
					SuggestFieldSuggestion model) {
				// TODO Auto-generated method stub
				return null;
			}
		});
        
        
        
        component.addBlurListener(new BlurListener() {
			
			@Override
			public void blur(BlurEvent event) {
				System.out.println("BLUR EVENT");
				
			}
		});
                              
        
        ComboBox cb = new ComboBox();
//        cb.setContainerDataSource(newDataSource);
//        cb.setConverter(converter);
        

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.addComponent(component);
        layout.setComponentAlignment(component, Alignment.MIDDLE_CENTER);
        setContent(layout);
        
        final Label lbl = new Label();
        lbl.setSizeFull();
        layout.addComponent(lbl);
        
        
        Button btn = new Button("test value");
        btn.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				lbl.setValue(component.getValue().toString());
				System.out.println(component.getValue());
			}
		});
        layout.addComponent(btn);

    }

}
