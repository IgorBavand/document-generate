# PDF Generation Project

This project is a Spring Boot application for generating PDFs from HTML templates using Thymeleaf and OpenHTMLtoPDF.

## Dependencies

1. **Spring Boot Starter Web** (`spring-boot-starter-web`):
    - Provides the necessary components for building web applications, including RESTful APIs with Spring MVC.

2. **Spring Boot Starter Test** (`spring-boot-starter-test`):
    - A testing starter that includes various utilities for testing Spring Boot applications.
    - This dependency is only used in the test environment.

3. **OpenHTMLtoPDF** (`openhtmltopdf-pdfbox`):
    - A library for converting HTML to PDF using PDFBox.
    - Version `1.0.10` is used for generating PDFs from HTML content.

4. **Spring Boot Starter Thymeleaf** (`spring-boot-starter-thymeleaf`):
    - Provides integration with the Thymeleaf template engine to render dynamic HTML templates.

## Plugins

1. **Maven Compiler Plugin** (`maven-compiler-plugin`):
    - Configures the compilation of Java code, including annotation processors.

2. **Spring Boot Maven Plugin** (`spring-boot-maven-plugin`):
    - Assists in building and running Spring Boot applications from the command line.

This setup enables the generation of PDFs from dynamic HTML templates and integration with Thymeleaf for rendering. It also includes tools for testing and compiling the application.
