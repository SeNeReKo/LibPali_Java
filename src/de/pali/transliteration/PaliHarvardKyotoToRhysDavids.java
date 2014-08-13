package de.pali.transliteration;


import java.util.*;

import de.general.transliteration.ITransliterator;


/**
 *
 * @author knauth
 */
public class PaliHarvardKyotoToRhysDavids implements ITransliterator
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
	public PaliHarvardKyotoToRhysDavids()
	{
	}

	static {
		transliterationSchema = new HashMap<String, String>();
		transliterationSchema.put("A", "ā");
		transliterationSchema.put("I", "ī");
		transliterationSchema.put("U", "ū");
		transliterationSchema.put("L", "ḷ");
		transliterationSchema.put("M", "ṃ");
		transliterationSchema.put("H", "ḥ");
		transliterationSchema.put("G", "ṅ");
		transliterationSchema.put("J", "ñ");
		transliterationSchema.put("T", "ṭ");
		transliterationSchema.put("D", "ḍ");
		transliterationSchema.put("N", "ṇ");
		transliterationSchema.put("S", "ṣ");
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public String fromSchema()
	{
		return "HKC";
	}

	@Override
	public String toSchema()
	{
		return "RD";
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
