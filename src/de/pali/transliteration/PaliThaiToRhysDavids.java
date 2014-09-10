package de.pali.transliteration;

import java.util.HashMap;

import de.general.transliteration.ITransliterator;
import de.unitrier.daalft.pali.general.Alphabet;
import de.unitrier.daalft.pali.tools.Patterner;

public class PaliThaiToRhysDavids implements ITransliterator {
	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	private static final HashMap<String, String> map;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public PaliThaiToRhysDavids()
	{
	}

	static {
		map = new HashMap<String, String>();
		map.put("อ","a");
		map.put("ิ","i");
		map.put("ุ","u");
		map.put("า","ā");
		map.put("ี","ī");
		map.put("ู","ū");
		map.put("เ","e");
		map.put("ั", "a");
		map.put("โ","o");
		map.put("ก","k");
		map.put("ข","kh");
		map.put("ค","g");
		map.put("ฆ","gh");
		map.put("ง","ṅ");
		map.put("จ","c");
		map.put("ฉ","ch");
		map.put("ช","j");
		map.put("ฌ","jh");
		map.put("ญ","ñ");
		map.put("ฏ","ṭ");
		map.put("ฐ","ṭh");
		map.put("ฑ","ḍ");
		map.put("ฒ","ḍh");
		map.put("ณ","ṇ");
		map.put("ต","t");
		map.put("ถ","th");
		map.put("ท","d");
		map.put("ฎ", "ṭ");
		map.put("ธ","dh");
		map.put("น","n");
		map.put("ป","p");
		map.put("ผ","ph");
		map.put("พ","b");
		map.put("ภ","bh");
		map.put("ม","m");
		map.put("ห","h");
		map.put("ย","y");
		map.put("ร","r");
		map.put("ฬ","ḷ");
		map.put("ล","l");
		map.put("ส","s");
		map.put("ว","v");
		map.put("ํ","ṃ");
		map.put("ฺ","#");
		map.put(" ", " ");
		map.put("ฯ", ".");
		map.put("๑", "1");
		map.put("๒", "2");
		map.put("๓", "3");
		map.put("๔", "4");
		map.put("๕", "5");
		map.put("๖", "6");
		map.put("๗", "7");
		map.put("๘", "8");
		map.put("๙", "9");
		map.put("๐", "0");

	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public String fromSchema() {
		return "TH";
	}

	@Override
	public String toSchema() {
		return "RD";
	}

	@Override
	public String transliterate(String text) {
		// place "e" and "o" behind following letter
		// behind the next two letters if virama is present
		text = text.replaceAll("([เโ])([^ฺ])", "$2$1").replaceAll("([เโ])(ฺ)(.)", "$2$3$1");
		// get single characters
		String[] sp = text.split("");
		// output 
		StringBuilder sb = new StringBuilder();
		// loop over chars, at index 0 is empty slot due to split
		for (int i = 1; i < sp.length; i++) {
			// get trans char
			String t = map.get(sp[i]);
			// if not in map
			if (t == null) {
				// append original char
				sb.append(sp[i]);
				continue;
			}
			// if consonant
			if (isConsonant(t)) {
				// append
				sb.append(t);
				// for niggahita, don't append inherent vowel
				if (t.equals("ṃ")) continue;
				// check that we have a "next" letter
				if (i+1 < sp.length) {
					// Inherent vowel appending block
					
					// get next letter transliterated
					String next = map.get(sp[i+1]);
					// if not in map
					if (next == null) {
						// append a
						sb.append("a"); continue;
					}
					// check for space
					boolean isSpace = next.trim().isEmpty();
					// append "a" before space
					if (isSpace) {
						sb.append("a");
					} else if (isVirama(next)) {
						// skip virama
						i++;
					} else if (isConsonant(next)) {
						// append "a" before another consonant
						sb.append("a");
					}
				} else { // if we are at the end of the word
					// and the current char is consonant
					// append inherent "a"
					sb.append("a");
				}
			} else { // else current char is vowel
				// if we have a "next" char
				if (i+1 < sp.length) {
					// get next char transliterated
					String next = map.get(sp[i+1]);
					// if next char is vowel
					if (!isConsonant(next)) {
						// if this char is "a"
						if (t.equals("a")) {
							// append only next vowel, skip "a"
							sb.append(next);
							i++;
						} 
						else // else this is not "a", append this (diphthong)
							sb.append(t);
					} else { // else next is consonant
						sb.append(t);
					}
				} else // else we don't have a "next" char 
					sb.append(t);
			}
		}
		return sb.toString();
	}

	private boolean isConsonant (String c) {
		if (c == null) return false;
		return c.matches(Patterner.patternOr(Alphabet.getConsonants()));
	}

	private boolean isVirama (String c) {
		if (c == null) return false;
		return c.matches("#");
	}
}
