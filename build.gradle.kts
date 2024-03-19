plugins {
    java
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.data:spring-data-jpa:3.2.4")
    implementation("org.hibernate:hibernate-gradle-plugin:5.6.15.Final")

    // added MySQL connector
    implementation("mysql:mysql-connector-java:8.0.27")
    implementation("com.vladmihalcea:hibernate-types-52:2.14.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")
    //add dependencies as needed
}

tasks.withType<Test> {
    useJUnitPlatform()
}
