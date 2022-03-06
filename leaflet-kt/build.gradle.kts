plugins {
    id("org.jetbrains.kotlin.js") version "1.6.10"
    `maven-publish`

}


group = "org.leaflet-kt"
version = "0.0.2"

repositories {
    mavenCentral()
    mavenLocal()
    maven ("https://maven.pkg.github.com/etiennept/Leaflet-kt")
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
publishing {
    repositories {
        publications {
            register<MavenPublication>("kotlin") {
                from(components["kotlin"])
                artifactId = "leaflet-kt"

               /* pom {
                    name.set("Leaflet-kt")
                    //description.set("A concise description of my library")
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
                            //id.set("j")
                            //name.set("John Doe")
                            email.set("etiennept@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/etiennept/Leaflet-ktt")
                        developerConnection.set("scm:git:ssh://github.com/etiennept/Leaflet-kt")
                        url.set("http://github.com/etiennept/Leaflet-kt")
                    }
                } */
            }
        }

        maven {
            // change to point to your repo, e.g. http://my.org/repo
            url = uri("https://maven.pkg.github.com/etiennept/Leaflet-kt")

            credentials {
                username = "etienenpt"
                password = "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"
            }

        }
    }
}







