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
		
        public static void Main()
        {
            // Profile can be:
            //  HIGH_COMPRESSION, MID_COMPRESSION, LOW_COMPRESSION,
            //   LOSSLESS_COMPRESSION, CUSTOM
            PdfOptimizer optimizer = PdfOptimizerFactory.GetPdfOptimizerByProfile(PdfOptimizerProfile.LOSSLESS_COMPRESSION);

            FileReportPublisher publisher = new FileReportPublisher(new FileInfo("report.txt"));
            FileReportBuilder builder = new FileReportBuilder(SeverityLevel.INFO, publisher);
            optimizer.SetReportBuilder(builder);

            optimizer.SetReportBuilder(builder);
            optimizer.Optimize(
                    new FileStream(ORIG, FileMode.Open, FileAccess.Read, FileShare.Read),
                    new FileStream(OUTPUT_FILE, FileMode.Create));
        }
    }
