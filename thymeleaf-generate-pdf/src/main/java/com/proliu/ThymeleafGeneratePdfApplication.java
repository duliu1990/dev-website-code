package com.proliu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;

@SpringBootApplication
public class ThymeleafGeneratePdfApplication implements CommandLineRunner {

    @Autowired
    private TemplateEngine templateEngine;

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafGeneratePdfApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Context context = new Context();

        context.setVariable("name", "Java");

        String html = this.templateEngine.process("demo", context);

        String outputFolder = "demo.pdf";

        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();

        renderer.setDocumentFromString(html);
        renderer.layout();

        renderer.createPDF(outputStream);

        outputStream.close();
    }
}
