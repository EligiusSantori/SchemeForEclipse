package scheme_for_eclipse.editors;

import java.util.HashSet;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.PaintManager;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.MatchingCharacterPainter;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.editors.text.TextEditor;

import scheme_for_eclipse.Activator;
import scheme_for_eclipse.preferences.PreferenceInitializer;

public class SchemeEditor extends TextEditor
{
	// TODO icon, double-click strategy, indexer & autocomplete
	
	private StyleManager style;
	private KeywordManager keywords;

	private SchemeConfiguration configuration;
	
	private PaintManager paintManager;
	private MatchingCharacterPainter bracketPainter;
	
	public SchemeEditor()
	{
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		style = new StyleManager(Display.getCurrent(), store);
		keywords = new KeywordManager(store);
		
		configuration = new SchemeConfiguration(style, keywords);
		setSourceViewerConfiguration(configuration);
		
		HashSet<String> properties = new HashSet<String>(PreferenceInitializer.getKeys());
		store.addPropertyChangeListener(new IPropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent event)
			{
				if(properties.contains(event.getProperty()))
				{
					style.doLoad();
					keywords.reset();
					
					SchemeScanner scanner = configuration.getScanner();
					if(scanner != null)
						scanner.doLoad();
						
					bracketPainter.setColor(style.BRACKET_CURRENT.getForeground());
					
					ISourceViewer sourceViewer = getSourceViewer();
					if(sourceViewer instanceof SourceViewer)
						((SourceViewer)sourceViewer).invalidateTextPresentation();
				}
			}
		});
	}

	protected void createActions()
	{
		super.createActions();
		
		paintManager = new PaintManager(getSourceViewer());
		
		char[] brackets = new char[] { '(', ')', '[', ']', '{', '}' }; // TODO preferences (checkboxes, general tab)
		ICharacterPairMatcher pairMatcher = new DefaultCharacterPairMatcher(brackets);
		bracketPainter = new MatchingCharacterPainter(getSourceViewer(), pairMatcher);
		bracketPainter.setHighlightCharacterAtCaretLocation(true);
		bracketPainter.setHighlightEnclosingPeerCharacters(true);
		bracketPainter.setColor(style.BRACKET_CURRENT.getForeground());
		paintManager.addPainter(bracketPainter);
	}

	/*private void stopBracketHighlighting() {
	if (fBracketPainter != null) {
	fPaintManager.removePainter(fBracketPainter);
	fBracketPainter.deactivate(true);
	fBracketPainter.dispose();
	fBracketPainter = null;
	}*/
	
	/*protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support)
	{
		super.configureSourceViewerDecorationSupport(support);
		
		support.setCharacterPairMatcher(new DefaultCharacterPairMatcher(new char[] {'(', ')', '[', ']', '{', '}'}));
		//MatchingCharacterPainter p = new MatchingCharacterPainter(sourceViewer, matcher) 
		support.setMatchingCharacterPainterPreferenceKeys(EDITOR_MATCHING_BRACKETS,EDITOR_MATCHING_BRACKETS_COLOR);
	 
		//Enable bracket highlighting in the preference store
		//IPreferenceStore store = getPreferenceStore();
		//store.setDefault(EDITOR_MATCHING_BRACKETS, true);
		//store.setDefault(EDITOR_MATCHING_BRACKETS_COLOR, "128,128,128");
	}*/
}

