plugins {
    id("org.jetbrains.kotlin.js") version "1.6.10"
    `maven-publish`

}

group = "org.leaflet-kt"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation(npm("leaflet" ,"1.7.1"))
}

kotlin {
    js {
        browser {
            webpackTask {
                cssSupport.enabled = true
            }

            runTask {
                cssSupport.enabled = true
            }

            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
    publishing{}


}

publishing {
    publications {




        create<MavenPublication>("mavenJava") {
            pom {
                name.set("Leaflet-Kt")
                description.set("A concise description of my library")
                //url.set("http://www.example.com/library")
                properties.set(mapOf(
                    "myProp" to "value",
                    "prop.with.dots" to "anotherValue"
                ))
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("etiennept")
                        //name.set("John Doe")
                        email.set("etiennept@gmail.com")
                    }
                }
                scm {
                    /*connection.set("scm:git:git://example.com/my-library.git")
                    developerConnection.set("scm:git:ssh://example.com/my-library.git")
                    url.set("http://example.com/my-library/")*/
                }
            }
        }
    }
}