import com.itextpdf.licensekey.LicenseKey;
import com.itextpdf.pdfoptimizer.PdfOptimizer;
import com.itextpdf.pdfoptimizer.handlers.CompressionOptimizer;
import com.itextpdf.pdfoptimizer.handlers.FontDuplicationOptimizer;
import com.itextpdf.pdfoptimizer.handlers.ImageQualityOptimizer;
import com.itextpdf.pdfoptimizer.handlers.imagequality.processors.JpegCompressor;

import java.io.File;
import java.io.IOException;

public class PdfOptimizer_Sample {

    public static String SRC = "input_to_be_optimized.pdf";
    public static String DEST = "output_optimized.pdf";

    public static void main(String args[]) throws IOException {
        PdfOptimizer_Sample test = new PdfOptimizer_Sample();
        test.pdfOptimizerTest();
    }

    public void pdfOptimizerTest() throws IOException {
        LicenseKey.loadLicenseFile("iText7_licensekey.xml");

        PdfOptimizer optimizer = new PdfOptimizer();

        optimizer.addOptimizationHandler(new FontDuplicationOptimizer());

        optimizer.addOptimizationHandler(new CompressionOptimizer());

        ImageQualityOptimizer jpeg_optimizer = new ImageQualityOptimizer();
        jpeg_optimizer.setJpegProcessor(new JpegCompressor(0.5f));
        optimizer.addOptimizationHandler(jpeg_optimizer);

        optimizer.optimize(
                new File(SRC),
                new File(DEST));
    }
}
