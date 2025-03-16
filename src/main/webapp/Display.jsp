<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Com.TransactionDAO"%>
<%@ page import="Com.TransactionDTO"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction Details</title>
</head>
<body>
    <h1>Welcome to Transaction Details Page</h1>

    <%
        int page1 = 1;
        int pagesize = 5;
        String pageParam = request.getParameter("page1");

        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page1 = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page1 = 1; // Reset to first page if invalid
            }
        }

        int totalrecords = TransactionDAO.getTotalRecords();
        int lastPage = (totalrecords > 0) ? (int) Math.ceil((double) totalrecords / pagesize) : 1;

        // Adjust start index based on database indexing (0-based for MySQL, 1-based for Oracle)
        int start = (page1 - 1) * pagesize; // Use (page1 - 1) * pagesize + 1 for 1-based index DBs

        // Adjust pagesize for last page
        int remainingRecords = totalrecords - ((lastPage - 1) * pagesize);
        if (page1 == lastPage && remainingRecords < pagesize) {
            pagesize = remainingRecords;
        }

        int prevPage = (page1 > 1) ? page1 - 1 : 1;
        int nextPage = (page1 < lastPage) ? page1 + 1 : lastPage;

        List<TransactionDTO> transactions = TransactionDAO.getAllRecords(start, pagesize);
    %>

    <% if (transactions.isEmpty()) { %>
        <p>No records found</p>
    <% } else { %>
        <table border="1">
            <tr>
                <th>Txn Id</th>
                <th>Date</th>
                <th>Source Id</th>
                <th>Target Id</th>
                <th>Source Type</th>
                <th>Dest Type</th>
                <th>Amount</th>
            </tr>
            <% for (TransactionDTO txn : transactions) { %>
                <tr>
                    <td><%= txn.getTxnId() %></td>
                    <td><%= txn.getTxnDateTime() %></td>
                    <td><%= txn.getSourceId() %></td>
                    <td><%= txn.getTargetId() %></td>
                    <td><%= txn.getSourceType() %></td>
                    <td><%= txn.getDestType() %></td>
                    <td><%= txn.getTxnAmount() %></td>
                </tr>
            <% } %>
        </table>
    <% } %>

    <div>
       

        <!-- Previous Page Button -->
        <a href="Display.jsp?page1=<%= prevPage %>"><<</a>

        <%-- Page Numbers --%>
        <% for (int i = 1; i <= lastPage; i++) { %>
            <a href="Display.jsp?page1=<%= i %>" 
               style="<%= (i == page1) ? "font-weight:bold; text-decoration:underline;" : "" %>">
                <%= i %>
            </a>
        <% } %>

        <!-- Next Page Button -->
        <a href="Display.jsp?page1=<%= nextPage %>">>></a>

      
    </div>

</body>
</html> 