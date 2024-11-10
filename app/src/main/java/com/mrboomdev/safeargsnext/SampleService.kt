package com.mrboomdev.safeargsnext

import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.mrboomdev.safeargsnext.owner.SafeArgsService

class SampleService: SafeArgsService<SampleService.Args>() {
	class Args(val name: String)

	override fun onStartCommand(args: Args?, flags: Int, startId: Int): Int {
		Toast.makeText(this, args!!.name, Toast.LENGTH_SHORT).show()
		return super.onStartCommand(args, flags, startId)
	}

	override fun onBind(intent: Intent?): IBinder? {
		return null
	}
}