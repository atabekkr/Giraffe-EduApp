import java.util.UUID

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.imax.giraffe.models"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    buildFeatures {
        buildConfig = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    // 🔥 Добавляем правильные assets
    sourceSets {
        getByName("main") {
            assets.srcDirs("src/main/assets", "$buildDir/generated/assets")
        }
    }
}

// 📌 Генерация UUID в assets (как в оригинальном Vosk)
tasks.register("genUUID") {
    val uuid = UUID.randomUUID().toString()
    val odir = file("$buildDir/generated/assets/model-en-us")
    val ofile = file("$odir/uuid")
    doLast {
        mkdir(odir)
        ofile.writeText(uuid)
    }
}

// 📌 Указываем, что `genUUID` должен выполниться перед `preBuild`
tasks.named("preBuild").configure {
    dependsOn("genUUID")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
