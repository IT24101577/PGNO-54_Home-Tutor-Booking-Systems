<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hometutor.component04.booking.model.Booking" %>
<%@ page import="com.hometutor.component04.booking.model.OnlineSession" %>
<%@ page import="com.hometutor.component04.booking.model.InPersonSession" %>
<html>
<head>
  <title>Booking History</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet" onload="console.log('Tailwind CSS loaded')" onerror="console.error('Tailwind CSS failed to load')">
  <style>
    .modal {
      display: none;
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.5);
      z-index: 50;
    }
    .modal-content {
      background-color: #fff;
      margin: 15% auto;
      padding: 20px;
      border-radius: 5px;
      width: 300px;
      text-align: center;
      color: #333;
      border-top: 8px solid #343a40;
      border-bottom: 8px solid #343a40;
    }
    .modal-content button {
      margin: 0 5px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    .modal-content .cancel-btn {
      background-color: #e2e8f0;
      color: #333;
      width: 80px;
      height: 40px;
    }
    .modal-content .ok-btn {
      background-color: #3182ce;
      color: #fff;
      width: 80px;
      height: 40px;
    }
  </style>
</head>
<body class="bg-[#6c757d] bg-gray-600 text-white min-h-screen flex flex-col" style="background-color: #6c757d;">
<header class="bg-[#343a40] bg-gray-800 p-4 fixed w-full top-0 z-10 shadow-md" style="background-color: #343a40;">
  <a href="${pageContext.request.contextPath}/index.jsp" class="text-2xl font-bold text-center block">Home Tutor</a>
</header>
<main class="flex-grow container mx-auto p-8">
  <div class="mt-16">
    <div class="flex justify-between items-center mb-6">
      <h1 class="text-3xl font-bold ml-0">Booking History</h1>
      <a href="${pageContext.request.contextPath}/component04/booking.jsp" class="inline-block bg-[#CDE6F5] text-white p-3 rounded-md hover:bg-[#B3D4E9]" style="background-color: #658da6;">Create New Booking</a>

    </div>
    <% if (request.getAttribute("error") != null) { %>
    <p class="text-red-500 text-center mb-4"><%= request.getAttribute("error") %></p>
    <% } %>
    <% List<Booking> bookings = (List<Booking>) request.getAttribute("bookings"); %>
    <%-- Debugging output to check bookings data --%>
    <%
      if (bookings == null) {
    %>
    <p class="text-yellow-500 text-center mb-4">Debug: Bookings list is null. Check if the servlet set the 'bookings' attribute.</p>
    <%
    } else if (bookings.isEmpty()) {
    %>
    <p class="text-yellow-500 text-center mb-4">Debug: Bookings list is empty. No bookings exist for this student.</p>
    <%
      }
    %>
    <% if (bookings == null || bookings.isEmpty()) { %>
    <p class="text-center text-gray-600">No bookings found.</p>
    <% } else { %>
    <div class="overflow-x-auto">
      <table class="w-full bg-white shadow-md rounded-lg">
        <thead>
        <tr class="bg-[#343a40] bg-gray-800 text-white" style="background-color: #343a40;">
          <th class="p-3 text-left">Booking ID</th>
          <th class="p-3 text-left">Tutor ID</th>
          <th class="p-3 text-left">Date & Time</th>
          <th class="p-3 text-left">Status</th>
          <th class="p-3 text-left">Session Type</th>
          <th class="p-3 text-left">Details</th>
          <th class="p-3 text-left">Actions</th>
        </tr>
        </thead>
        <tbody class="text-gray-800">
        <% for (Booking booking : bookings) { %>
        <tr class="border-b">
          <td class="p-3"><%= booking.getBookingId() %></td>
          <td class="p-3"><%= booking.getTutorId() %></td>
          <td class="p-3"><%= booking.getDateTime() %></td>
          <td class="p-3"><%= booking.getStatus() %></td>
          <td class="p-3"><%= booking.getBookingType() %></td>
          <td class="p-3">
            <% if (booking instanceof OnlineSession) { %>
            <a href="<%= ((OnlineSession) booking).getMeetingLink() %>" class="text-blue-500 hover:underline" target="_blank">Meeting Link</a>
            <% } else if (booking instanceof InPersonSession) { %>
            <%= ((InPersonSession) booking).getLocation() %>
            <% } %>
          </td>
          <td class="p-3 flex space-x-2">
            <button onclick="window.location.href='${pageContext.request.contextPath}/editBooking?bookingId=<%= booking.getBookingId() %>'" class="bg-blue-500 text-white w-16 h-8 rounded hover:bg-blue-600">Edit</button>
            <form id="deleteForm-<%= booking.getBookingId() %>" action="${pageContext.request.contextPath}/deleteBooking" method="post" class="inline">
              <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
              <input type="hidden" name="studentId" value="<%= booking.getStudentId() %>">
              <button type="button" onclick="showDeleteConfirmation('<%= booking.getBookingId() %>')" class="bg-red-500 text-white w-16 h-8 rounded hover:bg-red-600">Delete</button>
            </form>
          </td>
        </tr>
        <% } %>
        </tbody>
      </table>
    </div>
    <% } %>
  </div>
</main>
<footer class="bg-[#343a40] bg-gray-800 p-4 text-center" style="background-color: #343a40;">
  <p class="text-white">© 2025 Home Tutor</p>
</footer>

<!-- Delete Confirmation Modal -->
<div id="deleteModal" class="modal">
  <div class="modal-content">
    <p>Are you sure you want to delete this booking?</p>
    <button class="cancel-btn" onclick="hideDeleteConfirmation()">Cancel</button>
    <button class="ok-btn" onclick="confirmDelete()">OK</button>
  </div>
</div>

<script>
  let currentBookingId = null;

  function showDeleteConfirmation(bookingId) {
    currentBookingId = bookingId;
    document.getElementById('deleteModal').style.display = 'block';
  }

  function hideDeleteConfirmation() {
    document.getElementById('deleteModal').style.display = 'none';
    currentBookingId = null;
  }

  function confirmDelete() {
    if (currentBookingId) {
      document.getElementById('deleteForm-' + currentBookingId).submit();
    }
    hideDeleteConfirmation();
  }

  // Close modal if clicking outside
  window.onclick = function(event) {
    const modal = document.getElementById('deleteModal');
    if (event.target === modal) {
      hideDeleteConfirmation();
    }
  }
</script>
</body>
</html>