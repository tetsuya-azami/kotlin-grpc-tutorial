import com.google.protobuf.gradle.id
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
	id("com.google.protobuf") version "0.9.4"
	id("idea")
}

group = "com.example.demo"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation(kotlin("kotlin-reflect"))
	implementation(kotlin("kotlin-stdlib-jdk8"))
	implementation("io.grpc:grpc-stub:1.61.1")
	implementation("io.grpc:grpc-protobuf:1.61.1")
	implementation("io.github.lognet:grpc-spring-boot-starter:4.4.5")
	implementation("javax.annotation:javax.annotation-api:1.3.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

configurations {
	all {
		resolutionStrategy.eachDependency {
			if (requested.group == "org.springframework.boot") {
				useVersion("3.2.2")
			}
		}
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:3.7.0"
	}
	plugins{
		id("grpc"){
			artifact = "io.grpc:protoc-gen-grpc-java:1.61.1"
		}
	}
	generateProtoTasks{
		ofSourceSet("main").forEach{
			it.plugins{
				id("grpc")
			}
		}
	}
}
