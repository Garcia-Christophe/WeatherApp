plugins {
    java
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    jacoco
}

apply("gradle/swagger.gradle.kts")

group = "com.m2.tiila"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

jacoco {
    toolVersion = "0.8.9"
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2022.0.4"

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jersey")
    implementation("org.springframework:spring-web")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.4")
    implementation("com.h2database:h2:2.2.224")

    // Cache
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // Feign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("io.github.openfeign:feign-jackson")
    implementation("io.github.openfeign:feign-okhttp")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")


    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.github.openfeign:feign-mock")
    testImplementation("io.rest-assured:rest-assured:5.3.2")
    testImplementation("io.rest-assured:json-path:5.3.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}
tasks.check {
    finalizedBy(":jacocoTestReport")
}
tasks.jacocoTestReport {
    dependsOn("test")
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.outputLocation.set(file("${buildDir}/jacocoHtml"))
    }
    dependsOn(tasks.test)
}
tasks.test {
    reports.junitXml.required.set(true)
    reports.html.required.set(true)
    jvmArgs("-Denv=TEST")
    useJUnitPlatform()
    outputs.dir(file("${buildDir}/generated-snippets"))
    finalizedBy(":jacocoTestReport")
}

tasks.bootRun {
    val port: String by project
    jvmArgs("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=2${port}", "-Dserver.port=${port}")
}