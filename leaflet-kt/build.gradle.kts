plugins {
    id("org.jetbrains.kotlin.js") version "1.6.10"
    `maven-publish`
    signing

}


group = "org.leaflet-kt"
version = "0.0.1"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation(npm("leaflet" ,"1.7.1" ))
    testImplementation(kotlin("test-js"))
}

kotlin {
    js(BOTH) {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }


}
val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(kotlin.sourceSets.main.get().kotlin)
}






