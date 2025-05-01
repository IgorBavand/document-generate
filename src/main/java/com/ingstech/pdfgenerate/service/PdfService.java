package com.ingstech.pdfgenerate.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class PdfService {

    @Autowired
    private TemplateEngine templateEngine;

    public ByteArrayOutputStream generatePdf(Map<String, Object> variables) throws Exception {
        Context context = new Context();
        Map<String, Object> variablesCopy = new HashMap<>(variables);
        context.setVariables(variablesCopy);

        String htmlContent = templateEngine.process("template", context);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.toStream(outputStream);

        String baseUri = "classpath:/static/";

        builder.withHtmlContent(htmlContent, baseUri);
        builder.run();

        return outputStream;
    }
}