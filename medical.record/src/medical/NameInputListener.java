package medical;

import android.text.InputType;
import android.text.method.NumberKeyListener;
import android.text.Editable;
import android.view.View;


public class NameInputListener extends NumberKeyListener {

	public int getInputType() {
		// TODO Auto-generated method stub
		return InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

	}
	
	@Override
    public void clearMetaKeyState(View view, Editable content, int states)
    {

    }


	@Override
	protected char[] getAcceptedChars() {
		// TODO Auto-generated method stub
		return new char [] { 
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'e', 's', 't', 
                'u', 'v', 'w', 'x', 'y', 'z', 
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'E', 'S', 'T', 
                'U', 'V', 'W', 'X', 'Y', 'Z','.'};

	}

}
