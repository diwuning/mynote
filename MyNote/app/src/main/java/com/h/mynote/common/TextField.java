package com.h.mynote.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.h.mynote.R;

/**
 * 带删除的输入框
 * @author cicue
 *
 */
public class TextField extends EditText {
	Drawable drawable = null;
	Context context;
	public TextField(Context context) {
		super(context);
		init(context);
	}

	public TextField(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttributeSet(context, attrs);
		init(context);
	}

	public TextField(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initAttributeSet(context, attrs);
		init(context);
	}


	/**
	 * 配置属性初始化
	 * 
	 * @param context
	 * @param attrs
	 */
	private void initAttributeSet(Context context, AttributeSet attrs) {
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextField);
		isDelete = typedArray.getBoolean(R.styleable.TextField_isDelete, true);
		typedArray.recycle();
	}

	/**
	 * 初始化
	 * 
	 * @param context
	 */
	@SuppressWarnings("deprecation")
	private void init(Context context) {
		this.context = context;
		this.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean isFocus) {
				if (isFocus) {
					setVisibleDelete(getText().toString().length() > 0);
				} else {
					setVisibleDelete(false);
				}
			}
		});
		
		this.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable editable) {
				setVisibleDelete(getText().toString().length() > 0);
			}
		});
	}
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
        case MotionEvent.ACTION_UP:
            boolean isClean =(event.getX() > (getWidth() - getTotalPaddingRight()))&&
                             (event.getX() < (getWidth() - getPaddingRight()));
            if (isClean) {
                setText("");
            }
            break;
 
        default:
            break;
        }
		return super.onTouchEvent(event);
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	private boolean isDelete = true;

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	private void setVisibleDelete(boolean isShow) {
		if (isShow && isDelete) {
			setCompoundDrawablesWithIntrinsicBounds(null, null, zoomDrawable(), null);
		}else{
			setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		}
	}
	
	@SuppressWarnings("deprecation")
	private Drawable zoomDrawable() {
		drawable = context.getResources().getDrawable(R.drawable.view_textfield_delete);
		return drawable;
	}

}
