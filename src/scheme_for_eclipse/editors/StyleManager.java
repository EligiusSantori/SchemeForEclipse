package scheme_for_eclipse.editors;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;

import scheme_for_eclipse.preferences.PreferenceInitializer;

public class StyleManager // TODO variable, procedure, mutator?
{
	public TextAttribute STANDARD_SYNTAX;
	public TextAttribute STANDARD_IDENTIFIER;
	public TextAttribute USER_SYNTAX;
	public TextAttribute USER_IDENTIFIER;
	public TextAttribute SYMBOL;
	public TextAttribute BOOLEAN;
	public TextAttribute NUMBER;
	public TextAttribute STRING;
	public TextAttribute COMMENT;
	public TextAttribute BRACKET_DEFAULT;
	public TextAttribute BRACKET_CURRENT;
	
	private IPreferenceStore store;
	private Device device;
	
	public StyleManager(Device device, IPreferenceStore store)
	{
		this.device = device;
		this.store = store;
		doLoad();
	}
	
	public void doLoad()
	{
		STANDARD_SYNTAX = parseStyle(store.getString(PreferenceInitializer.STYLE_STANDARD_SYNTAX));
		STANDARD_IDENTIFIER = parseStyle(store.getString(PreferenceInitializer.STYLE_STANDARD_IDENTIFIER));
		USER_SYNTAX = parseStyle(store.getString(PreferenceInitializer.STYLE_USER_SYNTAX));
		USER_IDENTIFIER = parseStyle(store.getString(PreferenceInitializer.STYLE_USER_IDENTIFIER));
		SYMBOL = parseStyle(store.getString(PreferenceInitializer.STYLE_SYMBOL));
		BOOLEAN = parseStyle(store.getString(PreferenceInitializer.STYLE_BOOLEAN));
		NUMBER = parseStyle(store.getString(PreferenceInitializer.STYLE_NUMBER));
		STRING = parseStyle(store.getString(PreferenceInitializer.STYLE_STRING));
		COMMENT = parseStyle(store.getString(PreferenceInitializer.STYLE_COMMENT));
		BRACKET_DEFAULT = parseStyle(store.getString(PreferenceInitializer.STYLE_BRACKET_DEFAULT));
		BRACKET_CURRENT = parseStyle(store.getString(PreferenceInitializer.STYLE_BRACKET_CURRENT));		
	}
	
	private TextAttribute parseStyle(String s)
	{	
		Color foreground = null;
		int style = SWT.NORMAL;
		for(String p : s.trim().toLowerCase().split(" "))
		{
			if(p.matches("0x[0-9a-f]{6}"))
			{
				int rgb = Integer.decode(p);
				int r = (rgb >> 16) & 0xff;
				int g = (rgb >> 8) & 0xff;
				int b = rgb & 0xff;
				foreground = new Color(device, r, g, b);
			}
			else if(p.compareTo("bold") == 0)
				style |= SWT.BOLD;
			else if(p.compareTo("italic") == 0)
				style |= SWT.ITALIC;
		}
		return new TextAttribute(foreground, null, style, null);
	}
}
