package com.mrboomdev.safeargsnext

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.mrboomdev.safeargsnext.library.SafeArgsFragment

class FragmentExampleActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(FragmentContainerView(this).apply {
			id = View.generateViewId()
			supportFragmentManager.beginTransaction()
				.setReorderingAllowed(true)
				.add(id, ExampleFragment::class.java, intent.extras, "MyFragment")
				.commit()
		})
	}

	class ExampleFragment: Fragment(), SafeArgsFragment<Args> {
		override fun onCreateView(
			inflater: LayoutInflater,
			container: ViewGroup?,
			savedInstanceState: Bundle?
		): View {
			return TextView(inflater.context).apply {
				val args = safeArgs

				if(args == null) {
					text = "No args were provided!"
					return@apply
				}

				text = "Name: ${args.name}, age: ${args.age}"
			}
		}
	}

	data class Args(val name: String?, val age: Int?)
}