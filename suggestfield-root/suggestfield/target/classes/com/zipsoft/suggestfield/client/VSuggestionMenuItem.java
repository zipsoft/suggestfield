package com.zipsoft.suggestfield.client;

import com.google.gwt.aria.client.Roles;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.safehtml.client.HasSafeHtml;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.UIObject;
import com.zipsoft.suggestfield.shared.SuggestFieldSuggestionImpl;

public class VSuggestionMenuItem extends UIObject implements HasHTML, HasEnabled,
		HasSafeHtml {

	private static final String DEPENDENT_STYLENAME_SELECTED_ITEM = "selected";
	private static final String DEPENDENT_STYLENAME_DISABLED_ITEM = "disabled";
	private static final String STYLENAME_DEFAULT = "z-suggestion-item";

	private ScheduledCommand command;
	private VSuggestionMenu parentMenu;
	private boolean enabled = true;
	private final SuggestFieldSuggestionImpl suggestion;

	public VSuggestionMenuItem(SuggestFieldSuggestionImpl suggestion, boolean asHtml) {
		this.suggestion = suggestion;
		setElement(DOM.createTD());
		setSelectionStyle(false);

		if (asHtml) {
			setHTML(suggestion.getDisplayString());
		} else {
			setText(suggestion.getDisplayString());
		}
		setStyleName(STYLENAME_DEFAULT);

		DOM.setElementAttribute(getElement(), "id", DOM.createUniqueId());
		DOM.setStyleAttribute(getElement(), "whiteSpace", "nowrap");
		// Add a11y role "menuitem"
		Roles.getMenuitemRole().set(getElement());								
	}

	@Override
	public String getText() {		
		return DOM.getInnerText(getElement());
	}

	@Override
	public void setText(String text) {
		DOM.setInnerText(getElement(), text);

	}

	@Override
	public void setHTML(SafeHtml html) {
		setHTML(html.asString());
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		if (enabled) {
			removeStyleDependentName(DEPENDENT_STYLENAME_DISABLED_ITEM);
		} else {
			addStyleDependentName(DEPENDENT_STYLENAME_DISABLED_ITEM);
		}
		this.enabled = enabled;

	}

	@Override
	public String getHTML() {
		return DOM.getInnerHTML(getElement());
	}

	@Override
	public void setHTML(String html) {
		DOM.setInnerHTML(getElement(), html);

	}

	protected void setSelectionStyle(boolean selected) {
		if (selected) {
			addStyleDependentName(DEPENDENT_STYLENAME_SELECTED_ITEM);
		} else {
			removeStyleDependentName(DEPENDENT_STYLENAME_SELECTED_ITEM);
		}
	}

	public VSuggestionMenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(VSuggestionMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public ScheduledCommand getCommand() {
		return command;
	}

	public void setCommand(ScheduledCommand command) {
		this.command = command;
	}

	public SuggestFieldSuggestionImpl getSuggestion() {
		return suggestion;
	}

}
