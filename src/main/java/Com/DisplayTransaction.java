package hema;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DisplayTransactions")
public class DisplayTransactions extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        int page = 1;
        int pageSize = 10;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int start = (page - 1) * pageSize + 1;
        int total = pageSize;

        try {
            int totalRecords = TransactionDAO.getTotalRecords();
            int lastPage = (int) Math.ceil((double) totalRecords / pageSize);
            List<TransactionDTO> transactions = TransactionDAO.getAllRecords(start, total);

            // Displaying the transaction table
            if (transactions.isEmpty()) {
                pw.println("<p>No records found.</p>");
            } else {
                pw.print("<h2>Mini Statement</h2>");
                pw.print("<table border='1'>");
                pw.println("<tr><th>Txn ID</th><th>Date</th><th>Source ID</th><th>Target ID</th><th>Source Type</th><th>Dest Type</th><th>Amount</th></tr>");
                for (TransactionDTO txn : transactions) {
                    pw.println("<tr>");
                    pw.println("<td>" + txn.getTxnId() + "</td>");
                    pw.println("<td>" + txn.getTxnDateTime() + "</td>");
                    pw.println("<td>" + txn.getSourceId() + "</td>");
                    pw.println("<td>" + txn.getTargetId() + "</td>");
                    pw.println("<td>" + txn.getSourceType() + "</td>");
                    pw.println("<td>" + txn.getDestType() + "</td>");
                    pw.println("<td>" + txn.getTxnAmount() + "</td>");
                    pw.println("</tr>");
                }
                pw.println("</table>");
            }

            // Pagination Links (Removed from output in print view)
            pw.print("<div id='pagination'>");
            pw.print("<a href='DisplayTransactions?page=1'>First</a> ");
            pw.print("<a href='DisplayTransactions?page=1'>1</a> ");
            pw.print("<a href='DisplayTransactions?page=2'>2</a> ");
            pw.print("<a href='DisplayTransactions?page=3'>3</a> ");
            pw.print("<a href='DisplayTransactions?page=4'>4</a> ");
            pw.print("<a href='DisplayTransactions?page=5'>5</a> ");
            if (page < lastPage) {
                pw.print("<a href='DisplayTransactions?page=" + lastPage + "'> Last </a>");
            }
            pw.print("</div>");

            // Add the "Download as PDF" button that triggers the browser print dialog
            pw.println("<br><br><button id='downloadPdfBtn' onclick='window.print();'>Download as PDF</button>");

            // CSS to hide pagination links and button in print view
            pw.println("<style>");
            pw.println("@media print {");
            pw.println("#pagination, #downloadPdfBtn { display: none; }"); // Hide pagination and button during printing
            pw.println("body { font-family: Arial, sans-serif; }");
            pw.println("table { width: 100%; border-collapse: collapse; }");
            pw.println("table, th, td { border: 1px solid black; }");
            pw.println("th, td { padding: 10px; text-align: left; }");
            pw.println("}"); // End of print media query
            pw.println("</style>");

        } catch (Exception e) {
            pw.println("<p>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }
}
