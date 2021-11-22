plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.allopen") version "1.5.31"
    id("java-library")
    id("org.openapi.generator") version "5.3.0"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    // https://mvnrepository.com/artifact/org.openapitools/openapi-generator-gradle-plugin
    implementation("org.openapitools:openapi-generator-gradle-plugin:5.3.0")
}

openApiGenerate {
    skipValidateSpec.set(true)
    verbose.set(false)
    generatorName.set("spring")
    inputSpec.set("$projectDir/docs/openapi/api.yml")
    outputDir.set("$projectDir/build")
    invokerPackage.set("com.kbalazsworks.stackjudge_aws_sdk")
    apiPackage.set("com.kbalazsworks.stackjudge_aws_sdk.api")
    modelPackage.set("com.kbalazsworks.stackjudge_aws_sdk.model")
}

group = "com.kbalazsworks"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.javaParameters = true
}

sourceSets {
    main {
        java.srcDir("$projectDir/build/src/main/java")
    }
}
