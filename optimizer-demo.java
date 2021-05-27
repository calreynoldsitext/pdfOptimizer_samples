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

import java.io.File;
import java.io.IOException;

public class PdfOptimizer_Demo_Lab {

   private static final String ORIG = "/uploads/optimize.pdf";
   private static final String OUTPUT_FOLDER = "/myfiles/";

    public static void main(String args[]) throws IOException {
        PdfOptimizer_Demo_Lab demo = new PdfOptimizer_Demo_Lab();
        demo.pdfOptimizerTest();
    }

    public void pdfOptimizerDemo() throws IOException {

        LicenseKey.loadLicenseFile("iText7_licensekey.xml");

        PdfOptimizer optimizer = new PdfOptimizer();

        optimizer.addOptimizationHandler(new FontDuplicationOptimizer());
        
        optimizer.addOptimizationHandler(new FontSubsettingOptimizer());

        ImageQualityOptimizer tiff_optimizer = new ImageQualityOptimizer();
        tiff_optimizer.setTiffProcessor(new BitmapCompressor(.5f, .5f));
        optimizer.addOptimizationHandler(new ImageQualityOptimizer());

        optimizer.addOptimizationHandler(new CompressionOptimizer());
        
        ColorSpaceConverter RGB_to_CMYK_Converter = new ColorSpaceConverter();
        CsConverterProperties csConversionProperties = new CsConverterProperties(ColorConversionMode.NORMAL);
        RGB_to_CMYK_Converter.setCsConverter(new RgbToCmykCsConverter(csConversionProperties));
        optimizer.addOptimizationHandler(RGB_to_CMYK_Converter);

        optimizer.optimize(
                new File(SRC),
                new File(DEST));
    }
}
