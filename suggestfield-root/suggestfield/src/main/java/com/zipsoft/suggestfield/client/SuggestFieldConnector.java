package com.zipsoft.suggestfield.client;

import java.util.ArrayList;

import com.zipsoft.suggestfield.SuggestField;
import com.zipsoft.suggestfield.client.VSuggestFieldWidget.SuggestionsLoaderClient;
import com.zipsoft.suggestfield.shared.SuggestFieldSuggestionImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.EventHelper;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.communication.FieldRpc.FocusAndBlurServerRpc;
import com.vaadin.shared.ui.Connect;

@Connect(SuggestField.class)
public class SuggestFieldConnector extends AbstractComponentConnector implements FocusHandler, BlurHandler,
	ValueChangeHandler<SuggestFieldSuggestionImpl> {

	private static final long serialVersionUID = 322786496901560008L;
	
	private HandlerRegistration focusHandlerRegistration;
    private HandlerRegistration blurHandlerRegistration;

	SuggestFieldServerRpc rpc = RpcProxy.create(SuggestFieldServerRpc.class, this);

	public SuggestFieldConnector() {

		// To receive RPC events from server, we register ClientRpc
		// implementation
		registerRpc(SuggestFieldClientRpc.class, new SuggestFieldClientRpc() {

			private static final long serialVersionUID = -2298419570848552379L;
			
			/**
			 * Saljem rezultate pretrage sa servera na klijentsku stranu
			 */
			@Override
			public void sendSuggestions(String query, ArrayList<SuggestFieldSuggestionImpl> suggestions) {
				getWidget().getSuggestions().clear();
				getWidget().getSuggestions().addAll(suggestions);
				getWidget().showSuggestionsFromServer();
			}
		});

		getWidget().setSuggestionLoaderClient(new SuggestionsLoaderClient() {
			
			@Override
			public void doLoadSuggestions(String query, int limit) {
				rpc.searchSuggestionsOnServer(query, limit);								
			}
		});		
		
	}
	
	@Override
	protected void init() {
		super.init();	
		getWidget().addValueChangeHandler(this);
	}

	// We must implement createWidget() to create correct type of widget
	@Override
	protected Widget createWidget() {
		return GWT.create(VSuggestFieldWidget.class);
	}

	// We must implement getWidget() to cast to correct type
	@Override
	public VSuggestFieldWidget getWidget() {
		return (VSuggestFieldWidget) super.getWidget();
	}

	// We must implement getState() to cast to correct type
	@Override
	public SuggestFieldState getState() {
		return (SuggestFieldState) super.getState();
	}

	// Whenever the state changes in the server-side, this method is called
	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		getWidget().setLimit(getState().limit);	
		getWidget().inputPrompt = getState().inputPrompt;
		getWidget().setDisplayStringHTML(getState().isDisplayStringHTML);
									                             
        if (getState().readOnly || !getState().enabled) {
            getWidget().setEnabled(false);
        } else {
        	getWidget().setEnabled(true);
        }        
        getWidget().setValue(getState().suggestion);
        
        focusHandlerRegistration = EventHelper.updateFocusHandler(this, focusHandlerRegistration);
        blurHandlerRegistration = EventHelper.updateBlurHandler(this, blurHandlerRegistration);
	}
		

	@Override
	public void onBlur(BlurEvent event) {
		getRpcProxy(FocusAndBlurServerRpc.class).blur();
		
	}

	@Override
	public void onFocus(FocusEvent event) {
		getRpcProxy(FocusAndBlurServerRpc.class).focus();
		
	}
	
	@Override
	public void flush() {
		// TODO Auto-generated method stub		
//		super.flush();
		getState().suggestion = getWidget().getValue();
	}

	@Override
	public void onValueChange(ValueChangeEvent<SuggestFieldSuggestionImpl> event) {
//		getState().suggestion = getWidget().getValue();
//		rpc.valueChanged(getWidget().getValue());
		rpc.valueChanged(event.getValue());
		
	}
	
	


}
