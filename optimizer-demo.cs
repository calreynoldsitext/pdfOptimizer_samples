using iText.License;

using iText.Pdfoptimizer;
using iText.Pdfoptimizer.Handlers;
using iText.Pdfoptimizer.Handlers.Converters;
using iText.Pdfoptimizer.Handlers.Imagequality.Processors;

using System.IO;

namespace PdfOptimizer_Demo_Lab
{
    class PdfOptimizer_Demo
    {
        public static string ORIG = "/uploads/input.pdf";
        public static string OUTPUT_FILE = "/myfiles/output.pdf";

        public static float compression_level = 0.5f;
        public static float image_scalar_level = 0.5f;

        public static void Main()
        {
            PdfOptimizer_Demo test = new PdfOptimizer_Demo();
            test.PdfOptimizerDemo();
        }

        public void PdfOptimizerDemo()
        {

            PdfOptimizer optimizer = new PdfOptimizer();

            optimizer.AddOptimizationHandler(new FontDuplicationOptimizer());

            optimizer.AddOptimizationHandler(new FontSubsettingOptimizer());

            ImageQualityOptimizer tiff_optimizer = new ImageQualityOptimizer();
            tiff_optimizer.SetTiffProcessor(new BitmapCompressor(image_scalar_level, compression_level));
            optimizer.AddOptimizationHandler(new ImageQualityOptimizer());

            optimizer.AddOptimizationHandler(new CompressionOptimizer());

            ColorSpaceConverter RGB_to_CMYK_Converter = new ColorSpaceConverter();
            CsConverterProperties csConversionProperties = new CsConverterProperties(ColorConversionMode.NORMAL);
            RGB_to_CMYK_Converter.SetCsConverter(new RgbToCmykCsConverter(csConversionProperties));
            optimizer.AddOptimizationHandler(RGB_to_CMYK_Converter);

            optimizer.Optimize(
                    new FileInfo(ORIG, FileMode.Open),
                    new FileInfo(OUTPUT_FILE, FileMode.Create, FileAccess.Write));
        }
    }
}
