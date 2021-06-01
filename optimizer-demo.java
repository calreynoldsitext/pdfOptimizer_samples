import com.itextpdf.licensekey.LicenseKey;

import com.itextpdf.pdfoptimizer.PdfOptimizer;
import com.itextpdf.pdfoptimizer.handlers.ColorSpaceConverter;
import com.itextpdf.pdfoptimizer.handlers.CompressionOptimizer;
import com.itextpdf.pdfoptimizer.handlers.FontDuplicationOptimizer;
import com.itextpdf.pdfoptimizer.handlers.FontSubsettingOptimizer;
import com.itextpdf.pdfoptimizer.handlers.ImageQualityOptimizer;
import com.itextpdf.pdfoptimizer.handlers.converters.ColorConversionMode;
import com.itextpdf.pdfoptimizer.handlers.converters.CsConverterProperties;
import com.itextpdf.pdfoptimizer.handlers.converters.RgbToCmykCsConverter;
import com.itextpdf.pdfoptimizer.handlers.imagequality.processors.BitmapCompressor;

import java.io.File;
import java.io.IOException;

public class PdfOptimizer_Demo_Lab {

   private static final String ORIG = "/uploads/optimize.pdf";
   private static final String OUTPUT_FOLDER = "/myfiles/output_optimized.pdf";

   public static float compression_level = 0.5f;
   public static float image_scalar_level = 0.5f;

    public static void main(String args[]) throws IOException {
        PdfOptimizer_Demo_Lab demo = new PdfOptimizer_Demo_Lab();
        demo.pdfOptimizerDemo();
    }

    public void pdfOptimizerDemo() throws IOException {

        PdfOptimizer optimizer = new PdfOptimizer();

        optimizer.addOptimizationHandler(new FontDuplicationOptimizer());
        
        optimizer.addOptimizationHandler(new FontSubsettingOptimizer());

        ImageQualityOptimizer tiff_optimizer = new ImageQualityOptimizer();
        tiff_optimizer.setTiffProcessor(new BitmapCompressor(image_scalar_level, compression_level));
        optimizer.addOptimizationHandler(new ImageQualityOptimizer());

        optimizer.addOptimizationHandler(new CompressionOptimizer());
        
        ColorSpaceConverter RGB_to_CMYK_Converter = new ColorSpaceConverter();
        CsConverterProperties csConversionProperties = new CsConverterProperties(ColorConversionMode.NORMAL);
        RGB_to_CMYK_Converter.setCsConverter(new RgbToCmykCsConverter(csConversionProperties));
        optimizer.addOptimizationHandler(RGB_to_CMYK_Converter);

        optimizer.optimize(
                new File(ORIG),
                new File(OUTPUT_FOLDER));
    }
}
