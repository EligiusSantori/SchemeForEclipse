package scheme_for_eclipse.editors;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class StartsRule implements IRule
{
	private IToken token;
	private char[] starts;
	
	public StartsRule(IToken token, String starts)
	{
		this.token = token;
		this.starts = starts.toCharArray();
	}
	
	public IToken evaluate(ICharacterScanner scanner)
	{
		int i = 0, c;
		
		while(true)
		{
			i++;
			c = scanner.read();
			
			if(c == ICharacterScanner.EOF)
				break;
			
			if(i <= starts.length)
			{
				if((char)c != starts[i - 1])
					break;
			}
			else
			{
				if(SchemeSyntax.isDelimiter((char)c))
					break;
			}
		}
		
		scanner.unread();
		
		if(i - 1 > starts.length)
			return token;
		else
		{
			for(int j = 0; j < i - 1; j++)
				scanner.unread();
			return Token.UNDEFINED;
		}
	}
}
