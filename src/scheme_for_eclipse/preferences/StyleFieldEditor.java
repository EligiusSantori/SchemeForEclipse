package scheme_for_eclipse.preferences;

import org.eclipse.jface.preference.ColorSelector;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class StyleFieldEditor extends FieldEditor
{
	class Style
	{
		public Style()
		{
			foreground = new RGB(0, 0, 0);
		}
		
		public Style(String s)
		{
			for(String p : s.trim().toLowerCase().split(" "))
			{
				if(p.matches("0x[0-9a-f]{6}"))
				{
					int rgb = Integer.decode(p);
					int r = (rgb >> 16) & 0xff;
					int g = (rgb >> 8) & 0xff;
					int b = rgb & 0xff;
					foreground = new RGB(r, g, b);
				}
				else if(p.equals("bold"))
					bold = true;
				else if(p.equals("italic"))
					italic = true;
			}
		}
		
		public Style(RGB f, boolean b, boolean i)
		{
			foreground = f;
			bold = b;
			italic = i;
		}
		
		public String toString()
		{
			String s = "";
			if(foreground != null)
			{
				int rgb = foreground.red << 16 | foreground.green << 8 | foreground.blue;
				s += String.format("0x%06x", rgb);
			}
			if(bold)
				s += " bold";
			if(italic)
				s += " italic"; 
			return s.trim();
		}
		
		public RGB foreground;
		public boolean bold;
		public boolean italic;
	}
	
	private Composite topBox;
	private ColorSelector colorSelector;
	private Button boldCheckbox;
	private Button italicCheckbox;
	
	public StyleFieldEditor(String name, String labelText, Composite parent)
	{
		super(name, labelText, parent);
	}

	protected void adjustForNumColumns(int numColumns)
	{
		((GridData)topBox.getLayoutData()).horizontalSpan = numColumns;
	}

	protected void doFillIntoGrid(Composite parent, int numColumns)
	{
		topBox = new Composite(parent, SWT.NULL);
		GridData topGrid = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
		topGrid.horizontalSpan = numColumns;
		topBox.setLayoutData(topGrid);
		topBox.setLayout(new GridLayout(4, false));
		
		Control control = getLabelControl(topBox);
		control.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_CENTER | GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));

		Button color = getChangeControl(topBox);
		color.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_CENTER));
		
		boldCheckbox = createCheckbox(topBox, "bold");
		boldCheckbox.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_CENTER));
		
		italicCheckbox = createCheckbox(topBox, "italic");
		italicCheckbox.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_CENTER));
	}

	protected void doLoad()
	{
		if(colorSelector != null && boldCheckbox != null && italicCheckbox != null)
		{
			Style s = new Style(getPreferenceStore().getString(getPreferenceName()));
			colorSelector.setColorValue(s.foreground);
			boldCheckbox.setSelection(s.bold);
			italicCheckbox.setSelection(s.italic);
		}
	}

	protected void doLoadDefault()
	{
		if(colorSelector != null && boldCheckbox != null && italicCheckbox != null)
		{		
			Style s = new Style(getPreferenceStore().getDefaultString(getPreferenceName()));
			colorSelector.setColorValue(s.foreground);
			boldCheckbox.setSelection(s.bold);
			italicCheckbox.setSelection(s.italic);
		}
	}

	protected void doStore()
	{
		if(colorSelector != null && boldCheckbox != null && italicCheckbox != null)
		{
			Style s = new Style(colorSelector.getColorValue(), boldCheckbox.getSelection(), italicCheckbox.getSelection());
			getPreferenceStore().setValue(getPreferenceName(), s.toString());
		}
	}

	public int getNumberOfControls()
	{
		return 5;
	}
	
	protected Point computeImageSize(Control window)
	{
		GC gc = new GC(window);
		Font f = JFaceResources.getFontRegistry().get(
				JFaceResources.DEFAULT_FONT);
		gc.setFont(f);
		int height = gc.getFontMetrics().getHeight();
		gc.dispose();
		Point p = new Point(height * 3 - 6, height);
		return p;
	}
	
	protected Button getChangeControl(Composite parent)
	{
		if (colorSelector == null)
		{
			colorSelector = new ColorSelector(parent);
			colorSelector.addListener(new IPropertyChangeListener()
			{
				public void propertyChange(PropertyChangeEvent event)
				{
					fireValueChanged(event.getProperty(), event.getOldValue(), event.getNewValue());
					setPresentsDefaultValue(false);
				}
			});
		}
		else
			checkParent(colorSelector.getButton(), parent);
		
		return colorSelector.getButton();
	}
	
	protected Button createCheckbox(Composite parent, String text)
	{
		Button bold = new Button(parent, SWT.CHECK | SWT.BOLD);
		bold.setText(text);
		return bold;
	}
	
	public void setEnabled(boolean enabled, Composite parent)
	{
		super.setEnabled(enabled, parent);
		colorSelector.setEnabled(enabled);
		boldCheckbox.setEnabled(enabled);
		italicCheckbox.setEnabled(enabled);
	}
}
