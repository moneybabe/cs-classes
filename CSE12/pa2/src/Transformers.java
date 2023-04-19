
class UpperCaseTransformer implements MyTransformer<String> {

	public String transformElement(String s) {
		return s.toUpperCase();
	}

}

// Add your transformers here

class CSE12Transformer implements MyTransformer<String> {

	@Override
	public String transformElement(String s) {
		if (s == null) {
			return s;
		}
		
		return "CSE12" + s;
	}
}

class LastLetterTransformer implements MyTransformer<String> {

	@Override
	public String transformElement(String s) {
		if (s == null) {
			return s;
		}
		
		if (s.length() == 0) {
			return s;
		}


		return s.substring(s.length() - 1);
	}
}