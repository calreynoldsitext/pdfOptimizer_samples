import com.itextpdf.pdfoptimizer.PdfOptimizer;
import com.itextpdf.pdfoptimizer.PdfOptimizerFactory;
import com.itextpdf.pdfoptimizer.PdfOptimizerProfile;
import com.itextpdf.pdfoptimizer.report.builder.FileReportBuilder;
import com.itextpdf.pdfoptimizer.report.message.SeverityLevel;
import com.itextpdf.pdfoptimizer.report.publisher.FileReportPublisher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FontDuplicationOptimizationExample2 {

    private static final String ORIG = "/uploads/input.pdf";
    private static final String OUTPUT_FILE = "/myfiles/output_optimized.pdf";

    public static void main(String args[]) throws IOException {
        FontDuplicationOptimizationExample2 test = new FontDuplicationOptimizationExample2();
        test.pdfOptimizerTest();
    }

    public void pdfOptimizerTest() throws IOException {

        // Profile can be:
        //  HIGH_COMPRESSION, MID_COMPRESSION, LOW_COMPRESSION,
        //   LOSSLESS_COMPRESSION, CUSTOM
        PdfOptimizer optimizer = PdfOptimizerFactory.getPdfOptimizerByProfile(PdfOptimizerProfile.LOSSLESS_COMPRESSION);

        FileReportPublisher publisher = new FileReportPublisher(new File("report.txt"));
        FileReportBuilder builder = new FileReportBuilder(SeverityLevel.INFO, publisher);

        optimizer.setReportBuilder(builder);
        optimizer.optimize(new FileInputStream(ORIG), new FileOutputStream(OUTPUT_FILE));
    }
}
