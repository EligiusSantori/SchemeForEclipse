package scheme_for_eclipse.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import scheme_for_eclipse.Activator;

public class SchemePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	public SchemePreferencePage()
	{
		super(GRID);
		setDescription("General settings for Scheme development:");
	}
	
	public void createFieldEditors()
	{
		Composite parent = getFieldEditorParent();
		
		TabFolder tabs = new TabFolder(parent, SWT.NULL);
		tabs.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		TabItem tabStyles = new TabItem(tabs, SWT.NULL);
		Composite styles = new Composite(tabs, SWT.NULL);
		tabStyles.setText("Styles");
		tabStyles.setControl(styles);
		
		TabItem tabSyntax = new TabItem(tabs, SWT.NULL);
		Composite syntax = new Composite(tabs, SWT.NULL);
		tabSyntax.setText("Syntax");
		tabSyntax.setControl(syntax);
		
		TabItem tabIdentifiers = new TabItem(tabs, SWT.NULL);
		Composite identifiers = new Composite(tabs, SWT.NULL);
		tabIdentifiers.setText("Identifiers");
		tabIdentifiers.setControl(identifiers);
		
		addField(new StyleFieldEditor(PreferenceInitializer.STYLE_STANDARD_SYNTAX, "Standard syntax style", styles));
		addField(new StyleFieldEditor(PreferenceInitializer.STYLE_STANDARD_IDENTIFIER, "Standard identifiers style", styles));
		//addField(new StyleFieldEditor(PreferenceInitializer.STYLE_USER_SYNTAX, "", styles));
		//addField(new StyleFieldEditor(PreferenceInitializer.STYLE_USER_IDENTIFIER, "", styles));
		addField(new StyleFieldEditor(PreferenceInitializer.STYLE_SYMBOL, "Symbols style", styles));
		addField(new StyleFieldEditor(PreferenceInitializer.STYLE_BOOLEAN, "Booleans style", styles));
		addField(new StyleFieldEditor(PreferenceInitializer.STYLE_NUMBER, "Numbers style", styles));
		addField(new StyleFieldEditor(PreferenceInitializer.STYLE_STRING, "Strings style", styles));
		addField(new StyleFieldEditor(PreferenceInitializer.STYLE_COMMENT, "Comment style", styles));
		//addField(new StyleFieldEditor(PreferenceInitializer.STYLE_BRACKET_DEFAULT, "Brackets style", styles));
		addField(new StyleFieldEditor(PreferenceInitializer.STYLE_BRACKET_CURRENT, "Highlighted brackets color", styles));

		addField(new UniqueStringListEditor(PreferenceInitializer.KEYWORDS_SYNTAX, "", syntax));
		addField(new UniqueStringListEditor(PreferenceInitializer.KEYWORDS_IDENTIFIER, "", identifiers));
	}

	public void init(IWorkbench workbench)
	{
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
}