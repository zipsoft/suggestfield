package com.zipsoft.suggestfield.client;

import java.util.ArrayList;

import com.vaadin.shared.communication.ClientRpc;
import com.zipsoft.suggestfield.shared.SuggestFieldSuggestionImpl;

public interface SuggestFieldClientRpc extends ClientRpc {
	
	public void sendSuggestions(String query, ArrayList<SuggestFieldSuggestionImpl> suggestions);

}