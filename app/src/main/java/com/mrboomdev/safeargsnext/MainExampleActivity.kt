package com.mrboomdev.safeargsnext

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mrboomdev.safeargsnext.library.SafeArgsActivity

class MainActivity : AppCompatActivity(), SafeArgsActivity<Args> {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		safeArgs?.let {
			Toast.makeText(this, "Safe args were received!"
				+ " Name: ${it.name}"
				+ ", Age: ${it.age}", Toast.LENGTH_SHORT).show()
		} ?: run {
			Toast.makeText(this, "No args were passed to this activity!", Toast.LENGTH_SHORT).show()
		}

		setContentView(LinearLayout(this).also { linear ->
			linear.orientation = LinearLayout.VERTICAL

			val nameEditText = EditText(this).apply {
				hint = "Name"
				imeOptions = EditorInfo.IME_FLAG_NO_FULLSCREEN
				linear.addView(this)
			}

			val ageEditText = EditText(this).apply {
				hint = "Age"
				imeOptions = EditorInfo.IME_FLAG_NO_FULLSCREEN
				inputType = EditorInfo.TYPE_CLASS_NUMBER or EditorInfo.TYPE_NUMBER_FLAG_SIGNED
				setSingleLine(true)
				linear.addView(this)
			}

			Button(this).apply {
				text = "Start activity with these args"
				linear.addView(this)

				setOnClickListener {
					startActivity(Intent(context, MainActivity::class.java).apply {
						putExtra("name", nameEditText.text)

						ageEditText.text.let setAge@{
							if(it == null || it.isBlank()) return@setAge
							putExtra("age", Integer.valueOf(it.toString()))
						}
					})

					finish()
				}
			}

			Button(this).apply {
				text = "Start fragment with these args"
				linear.addView(this)

				setOnClickListener {
					startActivity(Intent(context, FragmentExampleActivity::class.java).apply {
						putExtra("name", nameEditText.text)

						ageEditText.text.let setAge@{
							if(it == null || it.isBlank()) return@setAge
							putExtra("age", Integer.valueOf(it.toString()))
						}
					})
				}
			}
		})
	}
}

data class Args(val name: String?, val age: Int?)