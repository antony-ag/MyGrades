package br.com.fatecpg.mygrades;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Tony on 05/11/16.
 * Esta classe complementa o EditText e oculta o teclado quando o EditText não estiver em foco
 */

public class AutoHideKeyboardEditText extends EditText {
    public AutoHideKeyboardEditText(Context context) {
        super(context);
        init();
    }
    public AutoHideKeyboardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public AutoHideKeyboardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }
    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}