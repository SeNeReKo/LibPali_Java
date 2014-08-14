package de.pali.transliteration;

import java.util.HashMap;

import de.general.transliteration.ITransliterator;

public class PaliDevanagariToHarvardKyoto implements ITransliterator {
	private static final HashMap<String, String> map;
	static {
		map = new HashMap<String, String>();
		map.put("अ","a");
		map.put("आ","A");
		map.put("इ","i");
		map.put("ई","I");
		map.put("उ","u");
		map.put("ऊ","U");
		map.put("ऋ","R");
		map.put("ॠ","RR");
		map.put("ऌ","lR");
		map.put("ॡ","lRR");
		map.put("ए","e");
		map.put("ऐ","ai");
		map.put("ओ","o");
		map.put("औ","au");
		map.put("ा","A");
		map.put("ि","i");
		map.put("ी","I");
		map.put("ु","u");
		map.put("ू","U");
		map.put("ृ","R");
		map.put("ॄ","RR");
		map.put("ॢ","lR");
		map.put("ॣ","lRR");
		map.put("े","e");
		map.put("ै","ai");
		map.put("ो","o");
		map.put("ौ","au");
		map.put("्","");
		map.put("ं","M");
		map.put("ः","H");
		map.put("ँ","");
		map.put("ॅ","");
		map.put("क","k");
		map.put("ख","kh");
		map.put("ग","g");
		map.put("घ","gh");
		map.put("ङ","G");
		map.put("च","c");
		map.put("छ","ch");
		map.put("ज","j");
		map.put("झ","jh");
		map.put("ञ","J");
		map.put("ट","T");
		map.put("ठ","Th");
		map.put("ड","D");
		map.put("ढ","Dh");
		map.put("ण","N");
		map.put("त","t");
		map.put("थ","th");
		map.put("द","d");
		map.put("ध","dh");
		map.put("न","n");
		map.put("प","p");
		map.put("फ","ph");
		map.put("ब","b");
		map.put("भ","bh");
		map.put("म","m");
		map.put("य","y");
		map.put("र","r");
		map.put("ल","l");
		map.put("व","v");
		map.put("श","z");
		map.put("ष","S");
		map.put("स","s");
		map.put("ह","h");
		map.put("ळ","L");
		map.put("क्ष","kS");
		map.put("ज्ञ","jJ");
		map.put("ॐ","OM");
		map.put("ऽ","'");
		map.put("।","|");
		map.put("॥","||");
		map.put("॒","");
		map.put("॑","");
		map.put("०","0");
		map.put("१","1");
		map.put("२","2");
		map.put("३","3");
		map.put("४","4");
		map.put("५","5");
		map.put("६","6");
		map.put("७","7");
		map.put("८","8");
		map.put("९","9");
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public String fromSchema()
	{
		return "DEV";
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
		String[] a = text.split("");
		
		for (int i = 1; i < a.length; i++) {
			String c = a[i];
			String x = map.get(c);
			if (x != null) {
				sb.append(x);
				if ((i+1 < a.length) && isConsonant(c) && isConsonant(a[i+1])) {
					sb.append("a");
				}
			} else {
				sb.append(c);
			}
		}
		if (isConsonant(a[a.length-1])) {
			sb.append("a");
		}
		return sb.toString();
	}	
	
	private boolean isConsonant(String devLetter) {
		return map.get(devLetter).matches("[^iueoaIUEOA]+");
	}
}
