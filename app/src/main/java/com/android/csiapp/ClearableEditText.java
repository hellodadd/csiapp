package com.android.csiapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Currency;
import java.util.Locale;

/**
 * Created by user on 2016/9/26.
 */
public class ClearableEditText extends RelativeLayout
{
    private LayoutInflater inflater = null;
    private EditText edit_text;
    private Button btn_clear;

    public ClearableEditText(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initViews();
    }

    public ClearableEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initViews();

    }

    public ClearableEditText(Context context)
    {
        super(context);
        initViews();
    }

    void initViews()
    {
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.clearable_edit_text, this, true);
        edit_text = (EditText) findViewById(R.id.clearable_edit);
        btn_clear = (Button) findViewById(R.id.clearable_button_clear);
        btn_clear.setVisibility(RelativeLayout.INVISIBLE);
        clearText();
        showHideClearButton();
    }

    void clearText()
    {
        btn_clear.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                edit_text.setText("");
            }
        });
    }

    void showHideClearButton()
    {
        edit_text.addTextChangedListener(new TextWatcher()
        {

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (s.length() > 0)
                    btn_clear.setVisibility(RelativeLayout.VISIBLE);
                else
                    btn_clear.setVisibility(RelativeLayout.INVISIBLE);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            public void afterTextChanged(Editable s)
            {

            }
        });
    }

    public String getText()
    {
        Editable text = edit_text.getText();
        return text.toString();
    }

    public void setText(String text)
    {
        edit_text.setText(text);
    }
}
