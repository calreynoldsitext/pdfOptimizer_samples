import com.itextpdf.licensekey.LicenseKey;
import com.itextpdf.pdfoptimizer.PdfOptimizer;
import com.itextpdf.pdfoptimizer.handlers.ColorSpaceConverter;
import com.itextpdf.pdfoptimizer.handlers.CompressionOptimizer;
import com.itextpdf.pdfoptimizer.handlers.FontDuplicationOptimizer;
import com.itextpdf.pdfoptimizer.handlers.ImageQualityOptimizer;
import com.itextpdf.pdfoptimizer.handlers.converters.ColorConversionMode;
import com.itextpdf.pdfoptimizer.handlers.converters.CsConverterProperties;
import com.itextpdf.pdfoptimizer.handlers.converters.RgbToCmykCsConverter;
import com.itextpdf.pdfoptimizer.handlers.imagequality.processors.BitmapCompressor;
import com.itextpdf.pdfoptimizer.report.builder.FileReportBuilder;
import com.itextpdf.pdfoptimizer.report.message.SeverityLevel;
import com.itextpdf.pdfoptimizer.report.publisher.FileReportPublisher;

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

        // First, we must load our license file in order to utilize pdfOptimizer.
        LicenseKey.loadLicenseFile("iText7_licensekey.xml");

        // As the namesake class of the PdfOptimizer add-on, this is where we configure our optimization.
        PdfOptimizer optimizer = new PdfOptimizer();

        /* Here we instantiate a FileReportBuilder which we can use as a log of the efficacy of
            our optimization.  Security level configuration possible.                          */

        FileReportPublisher publisher = new FileReportPublisher(new File("report.txt"));
        FileReportBuilder builder = new FileReportBuilder(SeverityLevel.INFO, publisher);
        optimizer.setReportBuilder(builder);

        /* From here on, we configure how PdfOptimizer will optimize our documents.
            Each handler represents a different optimization strategy                           */

        // Removes duplicate instances of font files embedded within documents.
        optimizer.addOptimizationHandler(new FontDuplicationOptimizer());
        
        // Removes all character data per font which is not actively utilized in the input document.
        optimizer.addOptimizationHandler(new FontSubsettingOptimizer());

        /* Scales down and compresses Image objects. In this case, we scale and compress a
             Tiff image by 50% */
        ImageQualityOptimizer tiff_optimizer = new ImageQualityOptimizer();
        tiff_optimizer.setTiffProcessor(new BitmapCompressor(.5f, .5f));
        optimizer.addOptimizationHandler(new ImageQualityOptimizer());

        // Here, we add a general compression strategy on our entire document based on Deflater logic.
        optimizer.addOptimizationHandler(new CompressionOptimizer());

        /* Adds a handler to our optimization process to convert our input document's colorspace
              from RGB, to CMYK.                                                              */
        ColorSpaceConverter RGB_to_CMYK_Converter = new ColorSpaceConverter();
        CsConverterProperties csConversionProperties = new CsConverterProperties(ColorConversionMode.NORMAL);
        RGB_to_CMYK_Converter.setCsConverter(new RgbToCmykCsConverter(csConversionProperties));
        optimizer.addOptimizationHandler(RGB_to_CMYK_Converter);

        // Document is optimized according to defined handlers and written out to file.
        optimizer.optimize(
                new File(SRC),
                new File(DEST));
    }
}
