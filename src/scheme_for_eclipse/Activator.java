package scheme_for_eclipse;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

// TODO icon, double-click strategy, indexer & autocomplete

public final class Activator extends AbstractUIPlugin
{
	public static final String PLUGIN_ID = "scheme_for_eclipse";

	private static Activator plugin;
	
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		super.stop(context);
	}

	public static Activator getDefault()
	{
		return plugin;
	}
}
