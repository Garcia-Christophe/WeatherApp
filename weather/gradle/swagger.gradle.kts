/**
 * Swagger.
 */
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("io.swagger.codegen.v3:swagger-codegen-generators:1.0.31")
    }
    project.the<SourceSetContainer>()["main"].java.srcDir("$buildDir/swagger/src/dto")
    tasks.named("compileJava") {
        dependsOn(":generateSwagger")
    }
}

apply(plugin = "java-library")

dependencies {
    // Swagger.
    "implementation"("io.swagger.core.v3:swagger-annotations:2.2.0")
    "implementation"("io.swagger.core.v3:swagger-jaxrs2:2.2.0")
}

task("generateSwagger") {
    val swaggerDir = "src/main/resources/swagger"
    val swaggerOutputDir = "$buildDir/swagger"
    inputs.dir(swaggerDir)
    outputs.dir(swaggerOutputDir)
    doLast {
        fileTree(File(swaggerDir)) {
            include(
                    "**/*.json",
                    "**/*.yaml",
                    "**/*.yml"
            )
        }.forEach { file: File ->
                var indexOf = file.parentFile.absolutePath.indexOf("swagger")
        val modelPackage = "dto." +
                file.nameWithoutExtension.replace("-", "")
        val config = io.swagger.codegen.v3.config.CodegenConfigurator()
        config.inputSpec = file.absolutePath
        config.outputDir = swaggerOutputDir
        // https://github.com/swagger-api/swagger-codegen/issues/8641
        config.lang = "jaxrs-jersey"
        config.modelPackage = modelPackage
        config.addAdditionalProperty("serializableModel", "true")
        config.addAdditionalProperty("performBeanValidation", "true")
        config.addAdditionalProperty("useBeanValidation", "true")
        config.addAdditionalProperty("dateLibrary", "java8")
        System.setProperty("models", "")
        System.setProperty("modelDocs", "false")
        val clientOptInput = config.toClientOptInput()
        println(clientOptInput.opts)
        var auth = emptyList<io.swagger.v3.parser.core.models.AuthorizationValue>()
        var parseOptions = io.swagger.v3.parser.core.models.ParseOptions()
        // options to fucking inline object
        parseOptions.setFlatten(true)
        // resolve file reference components
        parseOptions.setResolve(true)
        var openAPI = io.swagger.parser.OpenAPIParser()
                .readLocation(file.absolutePath, auth, parseOptions).openAPI
        clientOptInput.opts(io.swagger.codegen.v3.ClientOpts()).openAPI(openAPI)
        io.swagger.codegen.v3.DefaultGenerator().opts(clientOptInput).generate()
        }
        delete(fileTree(swaggerOutputDir).matching {
            include("**/InlineResponse*")
        })
    }
}
