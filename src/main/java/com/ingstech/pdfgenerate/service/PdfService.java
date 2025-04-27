package com.ingstech.pdfgenerate.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.util.Map;

@Service
public class PdfService {

    private final TemplateEngine templateEngine;

    public PdfService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public ByteArrayOutputStream generatePdf(Map<String, Object> variables) throws Exception {
        Context context = new Context();
        context.setVariables(variables);

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
