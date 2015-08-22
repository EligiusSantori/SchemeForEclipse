package scheme_for_eclipse.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class SchemeConfiguration extends SourceViewerConfiguration
{
	private SchemeScanner scanner = null;
	
	public SchemeConfiguration(StyleManager style, KeywordManager keywords)
	{
		scanner = new SchemeScanner(style, keywords);
	}
	
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer)
	{
		PresentationReconciler pr = new PresentationReconciler();
		DefaultDamagerRepairer ddr = new DefaultDamagerRepairer(scanner);
		pr.setRepairer(ddr, IDocument.DEFAULT_CONTENT_TYPE);
		pr.setDamager(ddr, IDocument.DEFAULT_CONTENT_TYPE);
		return pr;
	}
	
	public SchemeScanner getScanner()
	{
		return scanner;
	}
	
	/*IContentAssistant getContentAssistant(ISourceViewer sourceViewer)
	{
		ContentAssistant ca = new ContentAssistant();
		IContentAssistProcessor cap = new CompletionProcessor();
		ca.setContentAssistProcessor(cap, IDocument.DEFAULT_CONTENT_TYPE);
		ca.setInformationControlCreator(getInformationControlCreator(sourceViewer));
		return ca;
	}
	
	public ITextHover getTextHover(ISourceViewer sv, String contentType) {
        return new TextHover();
     }*/
}