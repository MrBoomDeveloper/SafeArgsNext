package com.mrboomdev.safeargsnext

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mrboomdev.safeargsnext.owner.SafeArgsActivity

class MainExampleActivity : AppCompatActivity(), SafeArgsActivity<Args> {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		showSafeArgs()
		createUi()
	}

	private fun showSafeArgs() {
		safeArgs?.let {
			Toast.makeText(this, "Safe args were received!"
					+ " Name: ${it.name}"
					+ ", Age: ${it.age}", Toast.LENGTH_SHORT).show()
		} ?: run {
			Toast.makeText(this, "No args were passed to this activity!", Toast.LENGTH_SHORT).show()
		}
	}

	private fun createUi() {
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
					startActivity(SafeArgsIntent(context, MainExampleActivity::class, Args(
						nameEditText.text.toString(),
						ageEditText.text.let {
							if(it == null || it.isBlank()) return@let null
							return@let Integer.valueOf(it.toString())
						})))

					finish()
				}
			}

			Button(this).apply {
				text = "Start fragment with these args"
				linear.addView(this)

				setOnClickListener {
					startActivity(SafeArgsIntent(context, FragmentExampleActivity::class, FragmentExampleActivity.Args(
						nameEditText.text.toString(),
						ageEditText.text.let {
							if(it == null || it.isBlank()) return@let null
							return@let Integer.valueOf(it.toString())
						}, listOf("Earth", "USA", "Los-Angeles"), listOf(
							Movie().apply {
								name = "The Lego Movie"
							},

							Movie().apply {
								name = "A Minecraft Movie"
							}))))
				}
			}
		})
	}
}

data class Args(val name: String?, val age: Int?)