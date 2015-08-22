package scheme_for_eclipse.editors;

import java.util.HashSet;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;

public class StrictWordRule implements IRule
{
	protected HashSet<String> fWords= new HashSet<String>();
	private StringBuffer fBuffer= new StringBuffer();
	protected IWordDetector fDetector;
	protected IToken fDefaultToken;

	
	public StrictWordRule(IWordDetector detector, IToken defaultToken, String[] words)
	{
		this.fDetector = detector;
		this.fDefaultToken = defaultToken;
		for(String word : words)
			fWords.add(word);
	}
	
	public StrictWordRule(IWordDetector detector, IToken defaultToken, Iterable<String> words)
	{
		this.fDetector = detector;
		this.fDefaultToken = defaultToken;
		for(String word : words)
			fWords.add(word);
	}
	
	public IToken evaluate(ICharacterScanner scanner)
	{
		int c = scanner.read();
		if(c != ICharacterScanner.EOF && fDetector.isWordStart((char)c))
		{
			fBuffer.setLength(0);
			do
			{
				fBuffer.append((char)c);
				c = scanner.read();
			}
			while(c != ICharacterScanner.EOF && fDetector.isWordPart((char)c));
			scanner.unread();
			
			if(fWords.contains(fBuffer.toString()))
				return fDefaultToken;
			else
			{
				unreadBuffer(scanner);
				return Token.UNDEFINED;
			}
		}
		
		scanner.unread();
		return Token.UNDEFINED;
	}

	protected void unreadBuffer(ICharacterScanner scanner)
	{
		for (int i= fBuffer.length() - 1; i >= 0; i--)
			scanner.unread();
	}
}
