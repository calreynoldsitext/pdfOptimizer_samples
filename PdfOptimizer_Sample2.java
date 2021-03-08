import com.itextpdf.licensekey.LicenseKey;
import com.itextpdf.pdfoptimizer.PdfOptimizer;
import com.itextpdf.pdfoptimizer.handlers.CompressionOptimizer;
import com.itextpdf.pdfoptimizer.handlers.FontDuplicationOptimizer;
import com.itextpdf.pdfoptimizer.handlers.ImageQualityOptimizer;
import com.itextpdf.pdfoptimizer.handlers.imagequality.processors.BitmapCompressor;

import java.io.File;
import java.io.IOException;

public class PdfOptimizer_Sample {

    public static String SRC = "input_to_be_optimized.pdf";
    public static String DEST = "output_optimized.pdf";

    public static void main(String args[]) throws IOException {
        PdfOptimizer_Sample2 test = new PdfOptimizer_Sample2();
        test.pdfOptimizerTest();
    }

    public void pdfOptimizerTest() throws IOException {
        LicenseKey.loadLicenseFile("iText7_licensekey.xml");

        PdfOptimizer optimizer = new PdfOptimizer();

        optimizer.addOptimizationHandler(new FontDuplicationOptimizer());

        optimizer.addOptimizationHandler(new CompressionOptimizer());

        ImageQualityOptimizer tiff_optimizer = new ImageQualityOptimizer();
        tiff_optimizer.setTiffProcessor(new BitmapCompressor(.5f, .5f));
        optimizer.addOptimizationHandler(new ImageQualityOptimizer());

        optimizer.optimize(
                new File(SRC),
                new File(DEST));
    }
}
