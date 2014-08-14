package de.pali.transliteration;

import java.util.HashMap;

import de.general.transliteration.ITransliterator;

public class PaliVelthuisToHarvardKyoto implements ITransliterator {

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
	public PaliVelthuisToHarvardKyoto()
	{
	}

	static {
		transliterationSchema = new HashMap<String, String>();
		transliterationSchema.put("aa", "A");
		transliterationSchema.put("ii", "I");
		transliterationSchema.put("uu", "U");
		transliterationSchema.put("L", "L");
		transliterationSchema.put(".m", "M");
		transliterationSchema.put(".h", "H");
		transliterationSchema.put("\"n", "G");
		transliterationSchema.put("~n", "J");
		transliterationSchema.put(".t", "T");
		transliterationSchema.put(".d", "D");
		transliterationSchema.put(".n", "N");
		transliterationSchema.put(".s", "S");
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
		return "VH";
	}

	@Override
	public String transliterate(String text)
	{
		StringBuilder sb = new StringBuilder();
		String[] array = text.split("");
		for (int i = 1; i < array.length-1; i+=1) {
			String bigram = array[i] + array[i+1];
			String x = transliterationSchema.get(bigram);
			if (x != null) {
				sb.append(x);
				i++;
			} else {
				if (i+1 == array.length-1)
					sb.append(bigram);
				else
					sb.append(array[i]);
			}
		}
		return sb.toString();
	}	
}
