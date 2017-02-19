package com.orangelittle.u17.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.orangelittle.u17.util.StringUtil;


/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class MyEditText extends RelativeLayout {
    private EditText editText;
    private View divider;
    private ImageView del;
    private TextView tvWarn;
    private int maxLenght;

    private String textContent, hintContent, warnContent;
    private boolean isPassWord, isNum;

    public MyEditText(Context context) {
        super(context);
        initView(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyEditText);
        textContent = array.getString(R.styleable.MyEditText_contenttext);
        hintContent = array.getString(R.styleable.MyEditText_text_hint);
        warnContent = array.getString(R.styleable.MyEditText_warnText);
        isPassWord = array.getBoolean(R.styleable.MyEditText_isPassWord, false);
        isNum = array.getBoolean(R.styleable.MyEditText_isNum, false);
        maxLenght = array.getInteger(R.styleable.MyEditText_maxLenght, 30);

        if (hintContent == null || hintContent == "") {
            hintContent = "请填写内容";
        }
        array.recycle();
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_editview, this, true);
        editText = (EditText) view.findViewById(R.id.myedit_content);
        divider = view.findViewById(R.id.myedit_divider);
        del = (ImageView) view.findViewById(R.id.myedit_iv_del);
        tvWarn = (TextView) view.findViewById(R.id.myedit_warn);
        editText.setHint(hintContent);
        editText.setTextSize(16);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLenght)});

        tvWarn.setText(warnContent);
        tvWarn.setTextSize(12);
        tvWarn.setTextColor(getResources().getColor(R.color.pink));

        if (isPassWord) {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else if (isNum) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
        }

        del.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    divider.setBackgroundColor(getResources().getColor(R.color.black));
                } else {
                    divider.setBackgroundColor(getResources().getColor(R.color.divider_gray));
                }
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    tvWarn.setVisibility(VISIBLE);
                    del.setVisibility(VISIBLE);
                } else {
                    tvWarn.setVisibility(INVISIBLE);
                    del.setVisibility(INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setHint(String text) {
        tvWarn.setText(text);
    }

    public String getMyText() {
        return editText.getText().toString().trim();
    }

    public void setTextChangeListener(TextWatcher watcher) {
        editText.addTextChangedListener(watcher);
    }

    public void setMText(String content) {
        if (StringUtil.isNull(content))
            editText.setText("");
        else
            editText.setText(content);
    }

}
