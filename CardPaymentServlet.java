package com.payment;

import org.bson.Document;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

public class CardPaymentServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appointmentId = request.getParameter("appointmentId");
        String amount = request.getParameter("amount");
        String status = request.getParameter("status");
        String bank = request.getParameter("bank");
        String methodType = request.getParameter("methodType");
        String methodBrand = request.getParameter("methodBrand");
        String holder = request.getParameter("holder");
        String cardNum = request.getParameter("cardNum");
        String expiry = request.getParameter("expiry");
        String cvv = request.getParameter("cvv");

        Document doc = new Document("appointmentId", appointmentId)
                .append("amount", amount)
                .append("status", status)
                .append("bank", bank)
                .append("methodType", methodType)
                .append("methodBrand", methodBrand)
                .append("holder", holder)
                .append("cardNum", cardNum)
                .append("expiry", expiry)
                .append("cvv", cvv);

        MongoDatabase db = DBUtil.getDatabase();
        MongoCollection<Document> collection = db.getCollection("cardPayments");
        collection.insertOne(doc);

        response.sendRedirect("success.html");
    }

}
