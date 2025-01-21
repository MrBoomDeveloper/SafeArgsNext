import com.vanniktech.maven.publish.SonatypeHost

plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.maven.publish)
}

group = "ru.mrboomdev.safeargsnext"
version = "1.0.0"

val owner = "github.com/MrBoomDeveloper"
val repository = "$owner/SafeArgsNext"

android {
	namespace = "com.mrboomdev.safeargsnext.library"
	compileSdk = 35

	defaultConfig {
		minSdk = 21
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	kotlinOptions {
		jvmTarget = "1.8"
	}
}

dependencies {
	implementation(libs.androidx.fragment.ktx)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}

mavenPublishing {
	coordinates(group.toString(), "safeargsnext", version.toString())

	pom {
		name = "SafeArgsNext"
		description = "Put and receive arguments with type-safety."
		url = "https://$repository"
		inceptionYear = "2025"

		licenses {
			license {
				name = "The Apache Licence, Version 2.0"
				url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
				description = url
			}
		}

		developers {
			developer {
				id = "mrboomdev"
				name = "MrBoomDev"
				url = "https://$owner"
			}
		}

		scm {
			url = "https://$repository"
			connection = "scm:git:git://$repository.git"
			developerConnection = "scm:git:ssh://git@$repository.git"
		}
	}

	publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, false)
	signAllPublications()
}