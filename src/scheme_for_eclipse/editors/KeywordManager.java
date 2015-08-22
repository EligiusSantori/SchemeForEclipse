package scheme_for_eclipse.editors;

import org.eclipse.jface.preference.IPreferenceStore;

import scheme_for_eclipse.preferences.PreferenceInitializer;

public class KeywordManager
{
	private IPreferenceStore store = null;
	
	private String[] syntax = null;
	private String[] identifiers = null;
	
	public KeywordManager(IPreferenceStore store)
	{
		this.store = store;
	}
	
	public String[] getSyntax()
	{
		if(syntax == null)
		{
			String config = store.getString(PreferenceInitializer.KEYWORDS_SYNTAX);
			if(config != null && config.length() > 0)
				syntax = config.split("\n");
			else
				syntax = new String[] { };
		}
		
		return syntax;
	}
	
	public String[] getIdentifiers()
	{
		if(identifiers == null)
		{
			String config = store.getString(PreferenceInitializer.KEYWORDS_IDENTIFIER);
			if(config != null && config.length() > 0)
				identifiers = config.split("\n");
			else
				identifiers = new String[] { };
		}
		
		return identifiers;
	}
}
