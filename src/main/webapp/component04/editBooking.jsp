<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.hometutor.component04.booking.model.Booking" %>
<%@ page import="com.hometutor.component04.booking.model.OnlineSession" %>
<%@ page import="com.hometutor.component04.booking.model.InPersonSession" %>
<html>
<head>
  <title>Edit Booking</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet" onload="console.log('Tailwind CSS loaded')" onerror="console.error('Tailwind CSS failed to load')">
</head>
<body class="bg-[#6c757d] bg-gray-600 text-white min-h-screen flex flex-col" style="background-color: #6c757d;">
<header class="bg-[#343a40] bg-gray-800 p-4 fixed w-full top-0 z-10 shadow-md" style="background-color: #343a40;">
  <a href="${pageContext.request.contextPath}/index.jsp" class="text-2xl font-bold text-center block">Home Tutor</a>
</header>
<main class="flex-grow container mx-auto p-8 flex items-center justify-center">
  <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md mx-auto mt-16 text-gray-800">
    <h1 class="text-2xl font-bold mb-6 text-center">Edit Booking</h1>
    <% Booking booking = (Booking) request.getAttribute("booking"); %>
    <% if (booking == null) { %>
    <p class="text-red-500 text-center">No booking found.</p>
    <% } else { %>
    <form action="${pageContext.request.contextPath}/updateBooking" method="post" class="space-y-4">
      <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
      <input type="hidden" name="studentId" value="<%= booking.getStudentId() %>">
      <div>
        <label for="tutorId" class="block text-sm font-medium text-gray-700">Tutor ID</label>
        <input type="text" id="tutorId" name="tutorId" value="<%= booking.getTutorId() %>" required
               class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
      </div>
      <div>
        <label for="dateTime" class="block text-sm font-medium text-gray-700">Date and Time (yyyy-MM-dd HH:mm)</label>
        <input type="text" id="dateTime" name="dateTime" value="<%= booking.getDateTime().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) %>" required
               class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
      </div>
      <div>
        <label for="status" class="block text-sm font-medium text-gray-700">Status</label>
        <select id="status" name="status" required class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
          <option value="Confirmed" <%= "Confirmed".equals(booking.getStatus()) ? "selected" : "" %>>Confirmed</option>
          <option value="Pending" <%= "Pending".equals(booking.getStatus()) ? "selected" : "" %>>Pending</option>
          <option value="Cancelled" <%= "Cancelled".equals(booking.getStatus()) ? "selected" : "" %>>Cancelled</option>
        </select>
      </div>
      <div>
        <label for="sessionType" class="block text-sm font-medium text-gray-700">Session Type</label>
        <select id="sessionType" name="sessionType" required class="mt-1 block w-full p-2 border border-gray-300 rounded-md"
                onchange="toggleSessionFields()">
          <option value="Online" <%= booking.getBookingType().equals("Online") ? "selected" : "" %>>Online</option>
          <option value="InPerson" <%= booking.getBookingType().equals("InPerson") ? "selected" : "" %>>In-Person</option>
        </select>
      </div>
      <div id="meetingLinkField" class="<%= booking.getBookingType().equals("Online") ? "" : "hidden" %>">
        <label for="meetingLink" class="block text-sm font-medium text-gray-700">Meeting Link</label>
        <input type="url" id="meetingLink" name="meetingLink" value="<%= booking instanceof OnlineSession ? ((OnlineSession) booking).getMeetingLink() : "" %>"
               class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
      </div>
      <div id="locationField" class="<%= booking.getBookingType().equals("InPerson") ? "" : "hidden" %>">
        <label for="location" class="block text-sm font-medium text-gray-700">Location</label>
        <input type="text" id="location" name="location" value="<%= booking instanceof InPersonSession ? ((InPersonSession) booking).getLocation() : "" %>"
               class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
      </div>
      <button type="submit" class="w-full bg-blue-500 text-white p-2 rounded-md hover:bg-blue-600">Update Booking</button>
    </form>
    <script>
      function toggleSessionFields() {
        const sessionType = document.getElementById('sessionType').value;
        document.getElementById('meetingLinkField').classList.toggle('hidden', sessionType !== 'Online');
        document.getElementById('locationField').classList.toggle('hidden', sessionType !== 'InPerson');
      }
    </script>
    <% } %>
    <div class="mt-4 text-center">
      <a href="${pageContext.request.contextPath}/viewBookings?studentId=<%= booking != null ? booking.getStudentId() : "student1" %>" class="inline-block bg-gray-500 text-white p-2 rounded-md hover:bg-gray-600">Back to Booking History</a>
    </div>
  </div>
</main>
<footer class="bg-[#343a40] bg-gray-800 p-4 text-center" style="background-color: #343a40;">
  <p class="text-white">© 2025 Home Tutor</p>
</footer>
</body>
</html>