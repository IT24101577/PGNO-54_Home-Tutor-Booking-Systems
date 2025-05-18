package com.payment;

import org.bson.Document;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;


public class CashPaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String appointment = request.getParameter("appointment");
        String amount = request.getParameter("amount");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        Document doc = new Document("name", name)
                .append("appointment", appointment)
                .append("amount", amount)
                .append("date", date)
                .append("status", status);

        MongoDatabase db = DBUtil.getDatabase();
        MongoCollection<Document> collection = db.getCollection("cashPayments");
        collection.insertOne(doc);

        response.sendRedirect("success.html");
    }

}
