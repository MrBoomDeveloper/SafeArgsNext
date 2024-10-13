# Safe Args Next
A type-safe alternative to your boilerplate code.

## Usage examples

### Use arguments
```kotlin
class MyActivity : AppCompatActivity(), SafeArgsActivity<MyActivity.Args> {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
        safeArgs?.let { args: Args -> 
			Toast.makeText(this, "Name: ${args.name}, age: ${args.age}", Toast.DURATION_LONG).show()
        }
	}
    
	data class Args(val name: String, val age: Int)
}
```

### Put arguments into activity
```kotlin
startActivity(SafeArgsIntent(this, MyActivity::class, MyActivity.Args(
	name = "MrBoomDev",
	age = 17
)))
```

### Put arguments into fragment
```kotlin
supportFragmentManager.beginTransaction()
	.setReorderingAllowed(true)
	.add(id, MyFragment::class, safeArgs, "MyFragment")
	.commit()
```