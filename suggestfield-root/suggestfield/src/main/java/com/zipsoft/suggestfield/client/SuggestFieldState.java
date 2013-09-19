package com.zipsoft.suggestfield.client;

import com.vaadin.shared.ui.TabIndexState;
import com.zipsoft.suggestfield.shared.SuggestFieldSuggestionImpl;

public class SuggestFieldState extends TabIndexState {

	private static final long serialVersionUID = 2443417008322022109L;

	{
		primaryStyleName = "z-suggestfield";
	}
	public int limit = 20;	      
	
	public String inputPrompt = "";
	
	public boolean isDisplayStringHTML = false;
	
	public SuggestFieldSuggestionImpl suggestion = null;
}