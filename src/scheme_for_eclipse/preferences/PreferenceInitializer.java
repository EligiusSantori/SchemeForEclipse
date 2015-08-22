package scheme_for_eclipse.preferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import scheme_for_eclipse.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer
{
	public static final String PLUGIN_ID = "scheme_for_eclipse";
	
	public static final String KEYWORDS_SYNTAX = "scheme_for_eclipse/keywords/syntax";
	public static final String KEYWORDS_IDENTIFIER = "scheme_for_eclipse/keywords/identifiers";
	
	public static final String STYLE_STANDARD_SYNTAX = "scheme_for_eclipse/style/standard_syntax";
	public static final String STYLE_STANDARD_IDENTIFIER = "scheme_for_eclipse/style/standard_identifier";
	public static final String STYLE_USER_SYNTAX = "scheme_for_eclipse/style/user_syntax";
	public static final String STYLE_USER_IDENTIFIER = "scheme_for_eclipse/style/user_identifier";
	public static final String STYLE_SYMBOL = "scheme_for_eclipse/style/symbol";
	public static final String STYLE_BOOLEAN = "scheme_for_eclipse/style/boolean";
	public static final String STYLE_NUMBER = "scheme_for_eclipse/style/number";
	public static final String STYLE_STRING = "scheme_for_eclipse/style/string";
	public static final String STYLE_COMMENT = "scheme_for_eclipse/style/comment";
	public static final String STYLE_BRACKET_DEFAULT = "scheme_for_eclipse/style/bracket_default";
	public static final String STYLE_BRACKET_CURRENT = "scheme_for_eclipse/style/bracket_current";
	
	public void initializeDefaultPreferences()
	{
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		store.setDefault(KEYWORDS_SYNTAX, String.join("\n", new String[] {
			"lambda", "begin", "set!", "do", "delay",
			"quote", "quasiquote", "unquote", "unquote-splicing",
			"syntax", "quasisyntax", "unsyntax", "unsyntax-splicing",
			"define", "let", "let*", "letrec", "letrec*", "let-values", "let*-values",
			"define-syntax", "let-syntax", "letrec-syntax", "syntax-rules",
			"if", "cond", "case", "else", "and", "or", "when", "unless",
		}));
		store.setDefault(KEYWORDS_IDENTIFIER, String.join("\n", new String[] {
			"+", "-", "*", "/", "=", "<", ">", "<=", ">=",
			"cons", "list", "car", "cdr", "pair?", "list?", "null?",
			"fold", "map", "filter", "for-each", "length", "append", "member", "remove", "reverse", "take", "drop",
			"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "last",
			"procedure?", "apply", "values", "compose",
			"boolean?", "not", "xor",
			"equal?", "eqv?", "eq?",
			"nan?", "zero?", "positive?", "negative?", "odd?", "even?", "min", "max",
			"set-car!", "set-cdr!",
			"display", "format",
			"error", "raise",
			"force",
		}));
		
		store.setDefault(STYLE_STANDARD_SYNTAX, "0x000000 bold");
		store.setDefault(STYLE_STANDARD_IDENTIFIER, "0x800055 bold");
		store.setDefault(STYLE_USER_SYNTAX, "0x000000");
		store.setDefault(STYLE_USER_IDENTIFIER, "0x800055");
		store.setDefault(STYLE_SYMBOL, "0x008000 bold");
		store.setDefault(STYLE_BOOLEAN, "0x0000ff");
		store.setDefault(STYLE_NUMBER, "0x0000ff bold");
		store.setDefault(STYLE_STRING, "0x008000");
		store.setDefault(STYLE_COMMENT, "0x808080 italic");
		store.setDefault(STYLE_BRACKET_DEFAULT, "0xff0000");
		store.setDefault(STYLE_BRACKET_CURRENT, "0xff0000 bold");
	}
	
	public static List<String> getKeys()
	{
		return Arrays.asList(new String[] {
				KEYWORDS_SYNTAX,
				KEYWORDS_IDENTIFIER,
				STYLE_STANDARD_SYNTAX,
				STYLE_STANDARD_IDENTIFIER,
				STYLE_USER_SYNTAX,
				STYLE_USER_IDENTIFIER,
				STYLE_SYMBOL,
				STYLE_BOOLEAN,
				STYLE_NUMBER,
				STYLE_STRING,
				STYLE_COMMENT,
				STYLE_BRACKET_DEFAULT,
				STYLE_BRACKET_CURRENT,
		});
	}
}
