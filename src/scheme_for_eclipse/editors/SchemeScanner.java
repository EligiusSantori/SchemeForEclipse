package scheme_for_eclipse.editors;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

public class SchemeScanner extends RuleBasedScanner
{	
	private StyleManager style;
	private KeywordManager keywords;
	
	public SchemeScanner(StyleManager style, KeywordManager keywords)
	{
		super();
		
		this.style = style;
		this.keywords = keywords;
		
		doLoad();
	}
	
	public void doLoad()
	{
		// TODO #... #\... &... `... ,... #;... #b... #o... #d... #x... #i #I #e #E
		
		setRules(new IRule[]
		{
			new NumberRule(new Token(style.NUMBER)), // TODO rewrite
			new StartsRule(new Token(style.NUMBER), "#x"), // TODO strict
			new StartsRule(new Token(style.SYMBOL), "'"),
			new StartsRule(new Token(style.STRING), "#\\"), // TODO strict
			//new Rule(new Token(style.BRACKET_DEFAULT), "()[]{}"),
			new StrictWordRule(new LexemeDetector(), new Token(style.BOOLEAN), new String[] { "#t", "#f" }),
			new StrictWordRule(new LexemeDetector(), new Token(style.STANDARD_SYNTAX), keywords.getSyntax()),
			new StrictWordRule(new IdentifierDetector(), new Token(style.STANDARD_IDENTIFIER), keywords.getIdentifiers()),
			new SingleLineRule("\"", "\"", new Token(style.STRING), '\\'),
			new SingleLineRule(";", null, new Token(style.COMMENT)),
			new MultiLineRule("#|", "|#", new Token(style.COMMENT)),
		});
	}
}
