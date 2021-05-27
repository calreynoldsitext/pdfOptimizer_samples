using iText.License;
using iText.Pdfoptimizer;
using iText.Pdfoptimizer.Handlers;
using iText.Pdfoptimizer.Handlers.Converters;
using iText.Pdfoptimizer.Handlers.Imagequality.Processors;
using iText.Pdfoptimizer.Report.Builder;
using iText.Pdfoptimizer.Report.Message;
using iText.Pdfoptimizer.Report.Publisher;
using System.IO;

namespace itext_dev.Optimizer_Demo
{
    class PdfOptimizer_Sample
    {
        public static void Main()
        {
            PdfOptimizer_Sample test = new PdfOptimizer_Sample();
            test.PdfOptimizerTest();
        }

        public void PdfOptimizerTest()
        {

            LicenseKey.LoadLicenseFile("iText7_licensekey.xml");

            PdfOptimizer optimizer = new PdfOptimizer();

            // Removes duplicate instances of font files embedded within documents.
            optimizer.AddOptimizationHandler(new FontDuplicationOptimizer());
            
            // Removes all character data per font which is not actively utilized in the input document.
            optimizer.AddOptimizationHandler(new FontSubsettingOptimizer());

            /* Scales down and compresses Image objects. In this case, we scale and compress a
                 Tiff image by 50% */
            ImageQualityOptimizer tiff_optimizer = new ImageQualityOptimizer();
            tiff_optimizer.SetTiffProcessor(new BitmapCompressor(.5f, .5f));
            optimizer.AddOptimizationHandler(new ImageQualityOptimizer());

            // Here, we add a general compression strategy on our entire document based on Deflater logic.
            optimizer.AddOptimizationHandler(new CompressionOptimizer());

            /* Adds a handler to our optimization process to convert our input document's colorspace
                  from RGB, to CMYK.                                                              */
            ColorSpaceConverter RGB_to_CMYK_Converter = new ColorSpaceConverter();
            CsConverterProperties csConversionProperties = new CsConverterProperties(ColorConversionMode.NORMAL);
            RGB_to_CMYK_Converter.SetCsConverter(new RgbToCmykCsConverter(csConversionProperties));
            optimizer.AddOptimizationHandler(RGB_to_CMYK_Converter);

            // Document is optimized according to defined handlers and written out to file.
            optimizer.Optimize(
                    new FileInfo(SRC, FileMode.Open),
                    new FileInfo(DEST, FileMode.Create, FileAccess.Write));
        }
    }
}
