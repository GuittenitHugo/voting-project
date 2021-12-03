package vue;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DocumentFilterDecimal extends DocumentFilter {

	@Override
    public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
		boolean hasDigitBefore = false;
		boolean hasComma = false;
        for (int n = string.length(); n > 0; n--) {
            char c = string.charAt(n - 1);
            if (Character.isDigit(c) || ((c == ',' || c == '.')&&!hasComma)) {
                super.replace(fb, i, i1, String.valueOf(c), as);
                if(Character.isDigit(c) && !hasDigitBefore) {
                	hasDigitBefore = true;
                }
                if(((c == ',' || c == '.')&&!hasComma)) {
                	hasComma = true;
                }
                
            }
        }
    }

    @Override
    public void remove(FilterBypass fb, int i, int i1) throws BadLocationException {
        super.remove(fb, i, i1);
    }

    @Override
    public void insertString(FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {
        super.insertString(fb, i, string, as);

    }
	
}
