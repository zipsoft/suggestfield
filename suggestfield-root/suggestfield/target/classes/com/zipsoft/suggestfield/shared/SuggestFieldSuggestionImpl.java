package com.zipsoft.suggestfield.shared;

public class SuggestFieldSuggestionImpl implements SuggestFieldSuggestion {
	
	private static final long serialVersionUID = 8289901094641098926L;
	
	private String id;

	private String displayString;
	
	private String replacementString;
	
	public SuggestFieldSuggestionImpl() {		
	}
		
	public SuggestFieldSuggestionImpl(String id, String displayString, String replacementString) {
		super();
		this.id = id;
		this.displayString = displayString;
		this.replacementString = replacementString;		
	}

	public void setDisplayString(String displayString) {
		this.displayString = displayString;
	}

	public void setReplacementString(String replacementString) {
		this.replacementString = replacementString;
	}
	
	public String getDisplayString() {
		return displayString;
	}

	public String getReplacementString() {
		return replacementString;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		
}
