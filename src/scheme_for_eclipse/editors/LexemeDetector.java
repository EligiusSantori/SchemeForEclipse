package scheme_for_eclipse.editors;

import org.eclipse.jface.text.rules.IWordDetector;

public class LexemeDetector implements IWordDetector
{
	public boolean isWordStart(char c)
	{
		return SchemeSyntax.isPrintable(c) && !SchemeSyntax.isDelimiter(c);
	}

	public boolean isWordPart(char c)
	{
		return SchemeSyntax.isPrintable(c) && !SchemeSyntax.isDelimiter(c);
	}
}
