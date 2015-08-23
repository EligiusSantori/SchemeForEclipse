package scheme_for_eclipse.preferences;

import java.util.Arrays;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

public class UniqueStringListEditor extends FieldEditor
{
	private Text textField;
	
	private List listField;

	private Composite headBox;

	private Button addButton;

	private Button removeButton;

	private SelectionListener selectionListener;

	/**
	 * Creates a list field editor.
	 *
	 * @param name the name of the preference this field editor works on
	 * @param labelText the label text of the field editor
	 * @param parent the parent of the field editor's control
	 */
	protected UniqueStringListEditor(String name, String labelText, Composite parent)
	{
		init(name, labelText);
		createControl(parent);
	}

	/**
	 * Notifies that the Add button has been pressed.
	 */
	private void addPressed()
	{
		setPresentsDefaultValue(false);
		String input = textField.getText().trim();
		textField.setText("");

		if(input.length() > 0)
		{
			String[] l = new String[listField.getItemCount() + 1];
			l[l.length - 1] = input;
			for(int i = 0; i < l.length - 1; i++)
			{
				l[i] = listField.getItem(i);
				if(l[i].compareTo(input) == 0)
					return;
			}
				
			Arrays.sort(l);
			listField.setItems(l);
			removeButtonUpdate();
		}
	}

	@Override
	protected void adjustForNumColumns(int numColumns)
	{
		((GridData)headBox.getLayoutData()).horizontalSpan = numColumns;
		((GridData)listField.getLayoutData()).horizontalSpan = numColumns;
	}

	/**
	 * Combines the given list of items into a single string.
	 * This method is the converse of <code>parseString</code>.
	 * <p>
	 * Subclasses must implement this method.
	 * </p>
	 *
	 * @param items the list of items
	 * @return the combined string
	 * @see #parseString
	 */
	protected String createList(String[] items)
	{
		return String.join("\n", items) ;
	}

	/**
	 * Helper method to create a push button.
	 *
	 * @param parent the parent control
	 * @param key the resource name used to supply the button's label text
	 * @return Button
	 */
	private Button createPushButton(Composite parent, String text)
	{
		Button button = new Button(parent, SWT.PUSH);
		button.setText(text);
		button.setFont(parent.getFont());
		GridData data = new GridData();
		int widthHint = convertHorizontalDLUsToPixels(button, IDialogConstants.BUTTON_WIDTH);
		data.widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
		button.setLayoutData(data);
		button.addSelectionListener(getSelectionListener());
		return button;
	}

	/**
	 * Creates a selection listener.
	 */
	public void createSelectionListener()
	{
		selectionListener = new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				Widget widget = event.widget;
				if (widget == addButton)
					addPressed();
				else if (widget == removeButton)
					removeButtonPressed();
				else if (widget == listField)
					removeButtonUpdate();
			}
		};
	}

	protected void doFillIntoGrid(Composite parent, int numColumns)
	{	
		GridData gd;

		if(getLabelText() != null && getLabelText().length() > 0)
		{
			Control label = getLabelControl(parent);
			gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
			gd.horizontalSpan = numColumns;
			label.setLayoutData(gd);
		}
		
		Control head = getHeadControl(parent);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = numColumns;
		gd.grabExcessHorizontalSpace = true;
		head.setLayoutData(gd);
		
		Control list = getListControl(parent);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = SWT.NORMAL;
		gd.horizontalSpan = numColumns;
		gd.grabExcessHorizontalSpace = true;
		list.setLayoutData(gd);
		
		//this.addButtonUpdate();
		this.removeButtonUpdate();
	}

	protected void doLoad()
	{
		if (listField != null)
		{
			String s = getPreferenceStore().getString(getPreferenceName());
			String[] array = parseString(s);
			for (int i = 0; i < array.length; i++)
				listField.add(array[i]);
		}
	}

	protected void doLoadDefault()
	{
		if (listField != null)
		{
			listField.removeAll();
			String s = getPreferenceStore().getDefaultString(getPreferenceName());
			String[] array = parseString(s);
			for(int i = 0; i < array.length; i++)
				listField.add(array[i]);
		}
	}

	protected void doStore()
	{
		String s = createList(listField.getItems());
		if (s != null)
			getPreferenceStore().setValue(getPreferenceName(), s);
	}

	protected Control getHeadControl(Composite parent)
	{
		if(headBox == null)
		{
			headBox = new Composite(parent, SWT.NULL);
			headBox.setFont(parent.getFont());
			headBox.setVisible(true);
			GridLayout layout = new GridLayout();
			layout.marginWidth = 0;
			layout.numColumns = 3;
			headBox.setLayout(layout);
			
            textField = new Text(headBox, SWT.SINGLE | SWT.BORDER);
            textField.setFont(headBox.getFont());        
            textField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
			
			addButton = createPushButton(headBox, "Add");
			removeButton = createPushButton(headBox, "Remove");
			
			headBox.addDisposeListener(new DisposeListener()
			{
				public void widgetDisposed(DisposeEvent event)
				{
					textField = null;
					addButton = null;
					removeButton = null;
					headBox = null;
				}
			});
		}
		else
			checkParent(headBox, parent);

		return headBox;
	}

	protected List getListControl(Composite parent)
	{
		if(listField == null)
		{
			listField = new List(parent, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);
			listField.setFont(parent.getFont());
			
			listField.addSelectionListener(getSelectionListener());
			listField.addDisposeListener(new DisposeListener()
			{
				public void widgetDisposed(DisposeEvent event)
				{
					listField = null;
				}
			});
		}
		else
			checkParent(listField, parent);
		
		return listField;
	}

	protected String getNewInputObject()
	{
		return "123456";
	}

	public int getNumberOfControls()
	{
		return 2;
	}

	private SelectionListener getSelectionListener()
	{
		if (selectionListener == null)
			createSelectionListener();
		return selectionListener;
	}

	protected Shell getShell()
	{
		if (addButton == null)
			return null;
		
		return addButton.getShell();
	}

	protected String[] parseString(String stringList)
	{
		if(stringList != null && stringList.length() > 0)
		{
			String[] items = stringList.split("\n");
			Arrays.sort(items);
			return items;
		}
		else
			return new String[] { };
	}

	/**
	 * Notifies that the Remove button has been pressed.
	 */
	private void removeButtonPressed()
	{
		setPresentsDefaultValue(false);
		int index = listField.getSelectionIndex();
		if (index >= 0) {
			listField.remove(index);
			listField.select(index >= listField.getItemCount() ? index - 1 : index);
			removeButtonUpdate();
		}
	}

	protected void addButtonUpdate()
	{
		addButton.setEnabled(textField.getText().trim().length() > 0);
	}
	
	protected void removeButtonUpdate()
	{
		removeButton.setEnabled(listField.getSelectionIndex() >= 0);
	}

	public void setFocus()
	{
		if(textField != null)
			textField.setFocus();
	}

	public void setEnabled(boolean enabled, Composite parent)
	{
		super.setEnabled(enabled, parent);
		textField.setEnabled(enabled);
		addButton.setEnabled(enabled);
		removeButton.setEnabled(enabled);
		listField.setEnabled(enabled);
	}

	protected Button getAddButton()
	{
		return addButton;
	}

	protected Button getRemoveButton()
	{
		return removeButton;
	}

	protected List getList()
	{
		return listField;
	}
}
