package scheme_for_eclipse.preferences;

import java.util.Arrays;
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
	
	public void initializeDefaultPreferences() // $('tt').map(function(i, e) { return $(e).text(); }).get().join('\n');
	{
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		store.setDefault(KEYWORDS_SYNTAX, String.join("\n", new String[] {

		}));
		
		store.setDefault(KEYWORDS_IDENTIFIER, String.join("\n", new String[] {
				
			// http://www.r6rs.org/final/html/r6rs/r6rs-Z-H-21.html#node_chap_Temp_46
			"*", "+", "-", "...", "/", "<", "<=", "=", "=>", ">", ">=", "_", // .
			"abs", "acos", "and", "angle", "append", "apply", "asin", "assert", "assertion-violation", "atan",
			"begin", "begin0", "boolean=?", "boolean?",
			"caaaar", "caaadr", "caaar", "caadar", "caaddr", "caadr", "caar", "cadaar", "cadadr", "cadar", "caddar", "cadddr", "caddr", "cadr", "call-with-current-continuation", "call-with-values", "call/cc", "car", "case", "cdaaar", "cdaadr", "cdaar", "cdadar", "cdaddr", "cdadr", "cdar", "cddaar", "cddadr", "cddar", "cdddar", "cddddr", "cdddr", "cddr", "cdr",
			"ceiling", "char->integer", "char<=?", "char<?", "char=?", "char>=?", "char>?", "char?", "complex?", "cond", "condition?", "cons", "consi", "cos",
			"define", "define-syntax", "denominator", "div", "div-and-mod", "div0", "div0-and-mod0", "dot", "dw", "dynamic-wind",
			"else", "eq?", "equal?", "eqv?", "error", "even?", "exact", "exact-integer-sqrt", "exact?", "exp", "export", "expt",
			"finite?", "floor", "for-each",
			"gcd",
			"identifier-syntax", "if", "imag-part", "import", "inexact", "inexact?", "infinite?", "integer->char", "integer-valued?", "integer?",
			"lambda", "lcm", "length", "let", "let*", "let*-values", "let-syntax", "let-values", "letrec", "letrec*", "letrec-syntax", "library", "list", "list->string", "list->vector", "list-ref", "list-tail", "list?", "log",
			"magnitude", "make-polar", "make-rectangular", "make-string", "make-vector", "map", "max", "min", "mod", "mod0",
			"nan?", "negative?", "not", "null", "null?", "number->string", "number?", "numerator",
			"odd?", "or",
			"pair?", "positive?", "procedure?",
			"quasiquote", "quote",
			"raise", "raise-continuable", "rational-valued?", "rational?", "rationalize", "real-part", "real-valued?", "real?", "reverse", "rnrs", "round",
			"set!", "set-car!", "set-cdr!", "sin", "sqrt", "string", "string->list", "string->number", "string->symbol", "string-append", "string-copy", "string-for-each", "string-length", "string-ref", "string<=?", "string<?", "string=?", "string>=?", "string>?", "string?", "substring", "symbol->string", "symbol=?", "symbol?", "syntax-rules",
			"tan", "throw", "truncate",
			"unquote", "unquote-splicing",
			"values", "vector", "vector->list", "vector-fill!", "vector-for-each", "vector-length", "vector-map", "vector-ref", "vector-set!", "vector?",
			"with-exception-handler",
			"zero?",
				
			// http://www.r6rs.org/final/html/r6rs-lib/r6rs-lib-Z-H-21.html#node_chap_Temp_4
			/*"...", "=>", "_",*/
			"&assertion", "assertion-violation?", "assoc", "assp", "assq", "assv",
			"binary-port?", "bitwise-and", "bitwise-arithmetic-shift", "bitwise-arithmetic-shift-left", "bitwise-arithmetic-shift-right", "bitwise-bit-count", "bitwise-bit-field", "bitwise-bit-set?", "bitwise-copy-bit", "bitwise-copy-bit-field", "bitwise-first-bit-set", "bitwise-if", "bitwise-ior", "bitwise-length", "bitwise-not", "bitwise-reverse-bit-field", "bitwise-rotate-bit-field", "bitwise-xor", "bound-identifier=?", "buffer-mode", "buffer-mode?", "bytevector->sint-list", "bytevector->string", "bytevector->u8-list", "bytevector->uint-list", "bytevector-copy", "bytevector-copy!", "bytevector-fill!", "bytevector-ieee-double-native-ref", "bytevector-ieee-double-native-set!", "bytevector-ieee-double-ref", "bytevector-ieee-single-native-ref", "bytevector-ieee-single-native-set!", "bytevector-ieee-single-ref", "bytevector-length", "bytevector-s16-native-ref", "bytevector-s16-native-set!", "bytevector-s16-ref", "bytevector-s16-set!", "bytevector-s32-native-ref", "bytevector-s32-native-set!", "bytevector-s32-ref", "bytevector-s32-set!", "bytevector-s64-native-ref", "bytevector-s64-native-set!", "bytevector-s64-ref", "bytevector-s64-set!", "bytevector-s8-ref", "bytevector-s8-set!", "bytevector-sint-ref", "bytevector-sint-set!", "bytevector-u16-native-ref", "bytevector-u16-native-set!", "bytevector-u16-ref", "bytevector-u16-set!", "bytevector-u32-native-ref", "bytevector-u32-native-set!", "bytevector-u32-ref", "bytevector-u32-set!", "bytevector-u64-native-ref", "bytevector-u64-native-set!", "bytevector-u64-ref", "bytevector-u64-set!", "bytevector-u8-ref", "bytevector-u8-set!", "bytevector-uint-ref", "bytevector-uint-set!", "bytevector=?", "bytevector?",
			"call-with-bytevector-output-port", "call-with-input-file", "call-with-output-file", "call-with-port", "call-with-string-output-port", "case-lambda", "char-alphabetic?", "char-ci<=?", "char-ci<?", "char-ci=?", "char-ci>=?", "char-ci>?", "char-downcase", "char-foldcase", "char-general-category", "char-lower-case?", "char-numeric?", "char-title-case?", "char-titlecase", "char-upcase", "char-upper-case?", "char-whitespace?", "close-input-port", "close-output-port", "close-port", "command-line", "&condition", "condition", "condition-accessor", "condition-irritants", "condition-message", "condition-predicate", "condition-who", /*"condition?",*/ "cons*", "current-error-port", "current-input-port", "current-output-port",
			"datum->syntax", "define-condition-type", "define-enumeration", "define-record-type", "delay", "delete-file", "display", "do",
			/*"else",*/ "endianness", "enum-set->list", "enum-set-complement", "enum-set-constructor", "enum-set-difference", "enum-set-indexer", "enum-set-intersection", "enum-set-member?", "enum-set-projection", "enum-set-subset?", "enum-set-union", "enum-set-universe", "enum-set=?", "environment", "eof-object", "eof-object?", "eol-style", "equal-hash", "&error", "error-handling-mode", "error?", "eval", "exact->inexact", "exists", "exit",
			"fields", "file-exists?", "file-options", "filter", "find", "fixnum->flonum", "fixnum-width", "fixnum?", "fl*", "fl+", "fl-", "fl/", "fl<=?", "fl<?", "fl=?", "fl>=?", "fl>?", "flabs", "flacos", "flasin", "flatan", "flceiling", "flcos", "fldenominator", "fldiv", "fldiv-and-mod", "fldiv0", "fldiv0-and-mod0", "fleven?", "flexp", "flexpt", "flfinite?", "flfloor", "flinfinite?", "flinteger?", "fllog", "flmax", "flmin", "flmod", "flmod0", "flnan?", "flnegative?", "flnumerator", "flodd?", "flonum?", "flpositive?", "flround", "flsin", "flsqrt", "fltan", "fltruncate", "flush-output-port", "flzero?", "fold-left", "fold-right", "for-all", "force", "free-identifier=?", "fx*", "fx*/carry", "fx+", "fx+/carry", "fx-", "fx-/carry", "fx<=?", "fx<?", "fx=?", "fx>=?", "fx>?", "fxand", "fxarithmetic-shift", "fxarithmetic-shift-left", "fxarithmetic-shift-right", "fxbit-count", "fxbit-field", "fxbit-set?", "fxcopy-bit", "fxcopy-bit-field", "fxdiv", "fxdiv-and-mod", "fxdiv0", "fxdiv0-and-mod0", "fxeven?", "fxfirst-bit-set", "fxif", "fxior", "fxlength", "fxmax", "fxmin", "fxmod", "fxmod0", "fxnegative?", "fxnot", "fxodd?", "fxpositive?", "fxreverse-bit-field", "fxrotate-bit-field", "fxxor", "fxzero?",
			"generate-temporaries", "get-bytevector-all", "get-bytevector-n", "get-bytevector-n!", "get-bytevector-some", "get-char", "get-datum", "get-line", "get-string-all", "get-string-n", "get-string-n!", "get-u8", "greatest-fixnum", "guard",
			"hashtable-clear!", "hashtable-contains?", "hashtable-copy", "hashtable-delete!", "hashtable-entries", "hashtable-equivalence-function", "hashtable-hash-function", "hashtable-keys", "hashtable-mutable?", "hashtable-ref", "hashtable-set!", "hashtable-size", "hashtable-update!", "hashtable?",
			"&i/o", "&i/o-decoding", "i/o-decoding-error?", "&i/o-encoding", "i/o-encoding-error-char", "i/o-encoding-error?", "i/o-error-filename", "i/o-error-port", "i/o-error-position", "i/o-error?", "&i/o-file-already-exists", "i/o-file-already-exists-error?", "&i/o-file-does-not-exist", "i/o-file-does-not-exist-error?", "&i/o-file-is-read-only", "i/o-file-is-read-only-error?", "&i/o-file-protection", "i/o-file-protection-error?", "&i/o-filename", "i/o-filename-error?", "&i/o-invalid-position", "i/o-invalid-position-error?", "&i/o-port", "i/o-port-error?", "&i/o-read", "i/o-read-error?", "&i/o-write", "i/o-write-error?", "identifier?", "immutable", "&implementation-restriction", "implementation-restriction-violation?", "inexact->exact", "input-port?", "&irritants", "irritants-condition?",
			"latin-1-codec", "least-fixnum", "&lexical", "lexical-violation?", "list-sort", "lookahead-char", "lookahead-u8",
			"make-assertion-violation", "make-bytevector", "make-custom-binary-input-port", "make-custom-binary-input/output-port", "make-custom-binary-output-port", "make-custom-textual-input-port", "make-custom-textual-input/output-port", "make-custom-textual-output-port", "make-enumeration", "make-eq-hashtable", "make-eqv-hashtable", "make-error", "make-hashtable", "make-i/o-decoding-error", "make-i/o-encoding-error", "make-i/o-error", "make-i/o-file-already-exists-error", "make-i/o-file-does-not-exist-error", "make-i/o-file-is-read-only-error", "make-i/o-file-protection-error", "make-i/o-filename-error", "make-i/o-invalid-position-error", "make-i/o-port-error", "make-i/o-read-error", "make-i/o-write-error", "make-implementation-restriction-violation", "make-irritants-condition", "make-lexical-violation", "make-message-condition", "make-no-infinities-violation", "make-no-nans-violation", "make-non-continuable-violation", "make-record-constructor-descriptor", "make-record-type-descriptor", "make-serious-condition", "make-syntax-violation", "make-transcoder", "make-undefined-violation", "make-variable-transformer", "make-violation", "make-warning", "make-who-condition", "member", "memp", "memq", "memv", "&message", "message-condition?", "modulo", "mutable",
			"native-endianness", "native-eol-style", "native-transcoder", "newline", "&no-infinities", "no-infinities-violation?", "&no-nans", "no-nans-violation?", "&non-continuable", "non-continuable-violation?", "nongenerative", "null-environment",
			"opaque", "open-bytevector-input-port", "open-bytevector-output-port", "open-file-input-port", "open-file-input/output-port", "open-file-output-port", "open-input-file", "open-output-file", "open-string-input-port", "open-string-output-port", "output-port-buffer-mode", "output-port?",
			"parent", "parent-rtd", "partition", "peek-char", "port-eof?", "port-has-port-position?", "port-has-set-port-position!?", "port-position", "port-transcoder", "port?", "protocol", "put-bytevector", "put-char", "put-datum", "put-string", "put-u8",
			"quasisyntax", "quotient",
			/*"raise",*/ "raise-continuable", "read", "read-char", "real->flonum", "record-accessor", "record-constructor", "record-constructor-descriptor", "record-field-mutable?", "record-mutator", "record-predicate", "record-rtd", "record-type-descriptor", "record-type-descriptor?", "record-type-field-names", "record-type-generative?", "record-type-name", "record-type-opaque?", "record-type-parent", "record-type-sealed?", "record-type-uid", "record?", "remainder", "remove", "remp", "remq", "remv",
			"scheme-report-environment", "sealed", "&serious", "serious-condition?", /*"set-car!", "set-cdr!",*/ "set-port-position!", "simple-conditions", "sint-list->bytevector", "standard-error-port", "standard-input-port", "standard-output-port", "string->bytevector", "string->utf16", "string->utf32", "string->utf8", "string-ci-hash", "string-ci<=?", "string-ci<?", "string-ci=?", "string-ci>=?", "string-ci>?", "string-downcase", "string-fill!", "string-foldcase", "string-hash", "string-normalize-nfc", "string-normalize-nfd", "string-normalize-nfkc", "string-normalize-nfkd", "string-set!", "string-titlecase", "string-upcase", "symbol-hash", "&syntax", "syntax", "syntax->datum", "syntax-case", "syntax-violation", "syntax-violation-form", "syntax-violation-subform", "syntax-violation?",
			"textual-port?", "transcoded-port", "transcoder-codec", "transcoder-eol-style", "transcoder-error-handling-mode",
			"u8-list->bytevector", "uint-list->bytevector", "&undefined", "undefined-violation?", "unless", "unsyntax", "unsyntax-splicing", "utf-16-codec", "utf-8-codec", "utf16->string", "utf32->string", "utf8->string",
			"vector-sort", "vector-sort!", "&violation", "violation?",
			"&warning", "warning?", "when", "&who", "who-condition?", /*"with-exception-handler",*/ "with-input-from-file", "with-output-to-file", "with-syntax", "write", "write-char",
			
			/* TODO srfi
			 * "take", "drop", "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "last", "compose", "xor", "format",
			 * ...
			 */
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
