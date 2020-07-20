import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("kotlin-android")
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "29.0.3"

    defaultConfig {
        applicationId = "com.knvovk.gamehub"

        minSdkVersion(21)
        targetSdkVersion(30)

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8

        coreLibraryDesugaringEnabled = true
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    // Java 8 library desugaring in D8 and R8
    val desugarVersion = "1.0.9"
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:$desugarVersion")

    // Kotlin
    val kotlinVersion = "1.3.72"
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    // AppCompat & Constraint
    val appCompatVersion = "1.1.0"
    val constraintVersion = "1.1.3"
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintVersion")

    // RecyclerView
    val recyclerVersion = "1.1.0"
    val recyclerSelectionVersion = "1.1.0-rc01"
    implementation("androidx.recyclerview:recyclerview:$recyclerVersion")
    implementation("androidx.recyclerview:recyclerview-selection:$recyclerSelectionVersion")

    // Material components
    val materialVersion = "1.1.0"
    implementation("com.google.android.material:material:$materialVersion")

    // CardView
    val cardViewVersion = "1.0.0"
    implementation("androidx.cardview:cardview:$cardViewVersion")

    // Android KTX
    val coreKtxVersion = "1.3.0"
    val fragmentKtxVersion = "1.2.5"
    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("androidx.fragment:fragment-ktx:$fragmentKtxVersion")

    // LiveData & ViewModel
    val lifecycleVersion = "2.2.0"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")

    // Navigation
    val navVersion = "2.3.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    // Paging
    val pagingVersion = "2.1.2"
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")

    // Retrofit
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion")

    // OkHttp
    val okHttpVersion = "4.8.0"
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")

    // RxJava
    val rxJavaVersion = "3.0.0"
    implementation("io.reactivex.rxjava3:rxjava:$rxJavaVersion")
    implementation("io.reactivex.rxjava3:rxandroid:$rxJavaVersion")
    implementation("io.reactivex.rxjava3:rxkotlin:$rxJavaVersion")

    // Tests
    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}