package br.unijorge.baseconhecimento.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandlerCarateres {

	public static String[] REPLACES = { "a", "e", "i", "o", "u", "c" };

	public static Pattern[] PATTERNS = null;

	public static void mascaras() {
		PATTERNS = new Pattern[REPLACES.length];
		PATTERNS[0] = Pattern.compile("[âãáàä]", Pattern.CASE_INSENSITIVE);
		PATTERNS[1] = Pattern.compile("[éèêëẽ]", Pattern.CASE_INSENSITIVE);
		PATTERNS[2] = Pattern.compile("[íìîïĩ]", Pattern.CASE_INSENSITIVE);
		PATTERNS[3] = Pattern.compile("[óòôõö]", Pattern.CASE_INSENSITIVE);
		PATTERNS[4] = Pattern.compile("[úùûüũ]", Pattern.CASE_INSENSITIVE);
		PATTERNS[5] = Pattern.compile("[ç]", Pattern.CASE_INSENSITIVE);
	}

	public static String SubstituirCaracteres(String text) {
		if (PATTERNS == null) {
			mascaras();
		}

		String result = text;
		for (int i = 0; i < PATTERNS.length; i++) {
			Matcher matcher = PATTERNS[i].matcher(result);
			result = matcher.replaceAll(REPLACES[i]);
		}
		return result;
	}

}
