plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "co.com.japl.model"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "CAROUSEL_HOME_DRIVE", project.findProperty("CAROUSEL_HOME_DRIVE")?.toString() ?:"false")
        buildConfigField("String", "CAROUSEL_HOME_JSON", project.findProperty("CAROUSEL_HOME_JSON")?.toString() ?:"false")
        buildConfigField("String", "CAROUSEL_HOME_LOCAL", project.findProperty("CAROUSEL_HOME_LOCAL")?.toString() ?:"false")

    }

    buildFeatures{
        viewBinding
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
         }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {

    implementation(project(":connect-gdrive"))
    implementation(project(":model"))

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("com.google.dagger:hilt-android:2.52")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}