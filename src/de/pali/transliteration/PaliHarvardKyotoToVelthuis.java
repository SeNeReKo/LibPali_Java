package de.pali.transliteration;

import java.util.HashMap;

import de.general.transliteration.ITransliterator;

public class PaliHarvardKyotoToVelthuis implements ITransliterator {

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
	public PaliHarvardKyotoToVelthuis()
	{
	}

	static {
		transliterationSchema = new HashMap<String, String>();
		transliterationSchema.put("A", "aa");
		transliterationSchema.put("I", "ii");
		transliterationSchema.put("U", "uu");
		transliterationSchema.put("L", ".l");
		transliterationSchema.put("M", ".m");
		transliterationSchema.put("H", ".h");
		transliterationSchema.put("G", "\"n");
		transliterationSchema.put("J", "~n");
		transliterationSchema.put("T", ".t");
		transliterationSchema.put("D", ".d");
		transliterationSchema.put("N", ".n");
		transliterationSchema.put("S", ".s");
	}
	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public String fromSchema()
	{
		return "VH";
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
