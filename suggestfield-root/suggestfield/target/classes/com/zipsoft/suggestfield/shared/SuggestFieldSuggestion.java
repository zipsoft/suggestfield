package com.zipsoft.suggestfield.shared;

import java.io.Serializable;

public interface SuggestFieldSuggestion extends Serializable {

	public String getId();
	
	public String getDisplayString();

	public String getReplacementString();
	
}
