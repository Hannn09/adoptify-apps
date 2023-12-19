package com.example.adoptify.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.view.View
import androidx.core.content.ContextCompat
import com.example.adoptify.R
import com.google.android.material.textfield.TextInputEditText

class CustomEditText : TextInputEditText {

    private val view: View? = null
    private var isError: Boolean = false
    private var errorBackground: Drawable? = null
    private var defaultBackground: Drawable? = null


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleArr: Int) : super(
        context,
        attrs,
        defStyleArr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        background = if (isError) {
            errorBackground
        } else {
            defaultBackground
        }
    }

    private fun init() {
        errorBackground = ContextCompat.getDrawable(context, R.drawable.background_text_error)
        defaultBackground = ContextCompat.getDrawable(context, R.drawable.background_text_login)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                when (view?.id) {
                    R.id.emailEditText -> {
                        if (!Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                            error = context.getString(R.string.error_email)
                            isError = true
                        } else {
                            isError = false
                        }
                    }

                    R.id.passwordEditText -> {
                        isError = if (s.toString().length < 8) {
                            setError(context.getString(R.string.error_password), null)
                            true
                        } else {
                            false
                        }
                    }

                    else -> {}
                }
            }

            override fun afterTextChanged(p0: Editable?) = Unit

        })
    }

}