using iText.License;
using iText.Pdfoptimizer;
using iText.Pdfoptimizer.Handlers;
using iText.Pdfoptimizer.Handlers.Imagequality.Processors;
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

            optimizer.AddOptimizationHandler(new FontDuplicationOptimizer());
            optimizer.AddOptimizationHandler(new CompressionOptimizer());

            ImageQualityOptimizer jpeg_optimizer = new ImageQualityOptimizer();
            jpeg_optimizer.SetJpegProcessor(new JpegCompressor(.5f));
            optimizer.AddOptimizationHandler(jpeg_optimizer);

            optimizer.Optimize(
                    new FileInfo(SRC, FileMode.Open),
                    new FileInfo(DEST, FileMode.Create, FileAccess.Write));
        }
    }
}
