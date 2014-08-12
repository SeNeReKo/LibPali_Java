package de.pali.transliteration;


import java.util.*;

import de.general.transliteration.ITransliterator;


/**
 *
 * @author knauth
 */
public class PaliLatinToHarvardKyoto implements ITransliterator
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	private static final HashMap<String, String> transliterationSchema;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public PaliLatinToHarvardKyoto()
	{
	}

	static {
		transliterationSchema = new HashMap<String, String>();
		transliterationSchema.put("ā", "A");
		transliterationSchema.put("ī", "I");
		transliterationSchema.put("ū", "U");
		transliterationSchema.put("ḷ", "L");
		transliterationSchema.put("ṃ", "M");
		transliterationSchema.put("ḥ", "H");
		transliterationSchema.put("ṅ", "G");
		transliterationSchema.put("ñ", "J");
		transliterationSchema.put("ṭ", "T");
		transliterationSchema.put("ḍ", "D");
		transliterationSchema.put("ṇ", "N");
		transliterationSchema.put("ṣ", "S");
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public String fromSchema()
	{
		return "RL";
	}

	@Override
	public String toSchema()
	{
		return "HKC";
	}

	@Override
	public String transliterate(String text)
	{
		StringBuilder sb = new StringBuilder();
		for (char c : text.toCharArray()) {
			String x = transliterationSchema.get("" + c);
			if (x != null) {
				sb.append(x);
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

}
