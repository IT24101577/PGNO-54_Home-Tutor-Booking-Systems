<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.hometutor.component04.booking.model.Booking" %>
<%@ page import="com.hometutor.component04.booking.model.OnlineSession" %>
<%@ page import="com.hometutor.component04.booking.model.InPersonSession" %>
<html>
<head>
    <title>Booking Confirmation</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet" onload="console.log('Tailwind CSS loaded')" onerror="console.error('Tailwind CSS failed to load')">
</head>
<body class="bg-[#6c757d] bg-gray-600 text-white min-h-screen flex flex-col" style="background-color: #6c757d;">
<header class="bg-[#343a40] bg-gray-800 p-4 fixed w-full top-0 z-10 shadow-md" style="background-color: #343a40;">
    <a href="${pageContext.request.contextPath}/index.jsp" class="text-2xl font-bold text-center block">Home Tutor</a>
</header>
<main class="flex-grow container mx-auto p-8 flex items-center justify-center">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md mx-auto mt-16 text-gray-800">
        <h1 class="text-2xl font-bold mb-6 text-center">Booking Confirmed</h1>
        <div class="mb-4">
            <% Booking booking = (Booking) request.getAttribute("booking"); %>
            <p><strong>Booking ID:</strong> <%= booking.getBookingId() %></p>
            <p><strong>Student ID:</strong> <%= booking.getStudentId() %></p>
            <p><strong>Tutor ID:</strong> <%= booking.getTutorId() %></p>
            <p><strong>Date and Time:</strong> <%= booking.getDateTime() %></p>
            <p><strong>Session Type:</strong> <%= booking.getBookingType() %></p>
            <% if (booking instanceof OnlineSession) { %>
            <p><strong>Meeting Link:</strong> <%= ((OnlineSession) booking).getMeetingLink() %></p>
            <% } else if (booking instanceof InPersonSession) { %>
            <p><strong>Location:</strong> <%= ((InPersonSession) booking).getLocation() %></p>
            <% } %>
        </div>
        <div class="mt-4 space-y-2">
            <a href="${pageContext.request.contextPath}/component04/booking.jsp" class="block w-full bg-[#CDE6F5] text-white p-2 rounded-md text-center hover:bg-[#B3D4E9]" style="background-color: #658da6;">Create Another Booking</a>
            <a href="${pageContext.request.contextPath}/viewBookings?studentId=student1" class="block w-full bg-gray-500 text-white p-2 rounded-md text-center hover:bg-gray-600">View Booking History</a>
        </div>
    </div>
</main>
<footer class="bg-[#343a40] bg-gray-800 p-4 text-center" style="background-color: #343a40;">
    <p class="text-white">© 2025 Home Tutor</p>
</footer>
</body>
</html>