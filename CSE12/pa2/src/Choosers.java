
class LongWordChooser implements MyChooser<String> {

	@Override
	public boolean chooseElement(String s) {
		return s.length() > 5;
	}

} 

// Add your choosers here

class ContainsSpaceChoose implements MyChooser<String> {

	@Override
	public boolean chooseElement(String s) {
		if (s == null) {
			return false;
		}
		
		return s.contains(" ");
	}

} 

class ContainsNumberChoose implements MyChooser<String> {

	@Override
	public boolean chooseElement(String s) {
		if (s == null) {
			return false;
		}

		boolean flag = false;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				flag = true;
			}
		}

		return flag;
	}

}