using iText.Pdfoptimizer;
using iText.Pdfoptimizer.Report.Builder;
using iText.Pdfoptimizer.Report.Message;
using iText.Pdfoptimizer.Report.Publisher;

using System.IO;

namespace PdfOptimizer_Examples
{
    class PdfOptimizer_Lossless_Example
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
}
