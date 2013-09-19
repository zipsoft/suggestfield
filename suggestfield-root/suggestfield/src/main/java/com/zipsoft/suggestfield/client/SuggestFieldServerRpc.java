package com.zipsoft.suggestfield.client;

import com.vaadin.shared.communication.ServerRpc;
import com.zipsoft.suggestfield.shared.SuggestFieldSuggestionImpl;

// ServerRpc is used to pass events from client to server
public interface SuggestFieldServerRpc extends ServerRpc {
	
	public void searchSuggestionsOnServer(String query, int limit);
	
	public void valueChanged(SuggestFieldSuggestionImpl value);
	

}
