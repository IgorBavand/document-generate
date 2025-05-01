package com.ingstech.pdfgenerate.controller;

import com.ingstech.pdfgenerate.dto.BeautyServiceDto;
import com.ingstech.pdfgenerate.service.PdfService;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final PdfService pdfService;

    public DocumentController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf() {
        try {
            Map<String, Object> variables = new HashMap<>();

            ClassPathResource imgFile = new ClassPathResource("static/images/my-logo.jpg");
            InputStream inputStream = imgFile.getInputStream();
            byte[] imageBytes = IOUtils.toByteArray(inputStream);
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            variables.put("logoBase64", base64Image);

            variables.put("salonName", "Salão Beleza & Estilo");
            variables.put("salonAddress", "Av. das Flores, 456 - Rio de Janeiro, RJ");
            variables.put("contactInfo", "(21) 9876-5432");

            List<BeautyServiceDto> services = createMockedBeautyServices();
            variables.put("services", services);

            ByteArrayOutputStream pdfOutputStream = pdfService.generatePdf(variables);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=catalogo-servicos.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Erro ao gerar PDF: " + e.getMessage()).getBytes(java.nio.charset.StandardCharsets.UTF_8));
        }
    }

    private List<BeautyServiceDto> createMockedBeautyServices() {
        List<BeautyServiceDto> services = new ArrayList<>();

        services.add(new BeautyServiceDto("Corte de Cabelo", "Corte masculino ou feminino, incluindo lavagem", "50.00", "45 min"));
        services.add(new BeautyServiceDto("Penteado", "Penteado simples ou com tranças", "80.00", "60 min"));
        services.add(new BeautyServiceDto("Manicure", "Corte, lixamento e esmaltação das unhas", "25.00", "30 min"));
        services.add(new BeautyServiceDto("Massagem Relaxante", "Massagem corporal para relaxamento", "120.00", "90 min"));

        return services;
    }
}
