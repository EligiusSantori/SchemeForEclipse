package scheme_for_eclipse.editors;

public abstract class SchemeSyntax
{
	/*private boolean isNumber(String s)
	{
		return false;
	}
	
	private boolean isIdentifier(String s)
	{
		return false;
	}*/
	
	public static boolean isPrintable(char c)
	{
		return (c >= 33 && c <= 126) || Character.isLetterOrDigit(c); // TODO
	}
	
	public static boolean isDelimiter(char c)
	{
		return c == '|' || c == '`' || c == '\'' || c == '"' || c == ';' || isBracket(c) || isWhitespace(c) || isEOL(c);
	}
	
	public static boolean isBracket(char c)
	{
		return c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}';
	}
	
	public static boolean isWhitespace(char c)
	{
		return c == ' ' || c == '\t';
	}
	
	public static boolean isEOL(char c)
	{
		return c == '\r' || c == '\n';
	}
}
