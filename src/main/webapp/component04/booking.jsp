<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create Booking</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet" onload="console.log('Tailwind CSS loaded')" onerror="console.error('Tailwind CSS failed to load')">
  <script>
    function toggleSessionFields() {
      const sessionType = document.getElementById("sessionType").value;
      const meetingLinkDiv = document.getElementById("meetingLinkDiv");
      const locationDiv = document.getElementById("locationDiv");
      meetingLinkDiv.style.display = sessionType === "Online" ? "block" : "none";
      locationDiv.style.display = sessionType === "InPerson" ? "block" : "none";
    }

    function validateForm() {
      const studentId = document.getElementById("studentId").value;
      const tutorId = document.getElementById("tutorId").value;
      const dateTime = document.getElementById("dateTime").value;
      const sessionType = document.getElementById("sessionType").value;
      const meetingLink = document.getElementById("meetingLink").value;
      const location = document.getElementById("location").value;

      if (!studentId || !tutorId || !dateTime) {
        alert("Student ID, Tutor ID, and Date/Time are required.");
        return false;
      }

      const dateRegex = /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}$/;
      if (!dateRegex.test(dateTime)) {
        alert("Date and Time must be in format yyyy-MM-dd HH:mm (e.g., 2025-05-17 11:30).");
        return false;
      }

      if (sessionType === "Online" && (!meetingLink || meetingLink.trim() === "")) {
        alert("Meeting Link is required for Online sessions.");
        return false;
      }

      if (sessionType === "InPerson" && (!location || location.trim() === "")) {
        alert("Location is required for In-Person sessions.");
        return false;
      }

      return true;
    }
  </script>
</head>
<body class="bg-[#6c757d] bg-gray-600 text-white min-h-screen flex flex-col" style="background-color: #6c757d;">
<header class="bg-[#343a40] bg-gray-800 p-4 fixed w-full top-0 z-10 shadow-md" style="background-color: #343a40;">
  <a href="${pageContext.request.contextPath}/index.jsp" class="text-2xl font-bold text-center block">Home Tutor</a>
</header>
<main class="flex-grow container mx-auto p-8 flex items-center justify-center">
  <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md mx-auto mt-16 text-gray-800">
    <h1 class="text-2xl font-bold mb-6 text-center">Create a New Booking</h1>
    <form action="${pageContext.request.contextPath}/createBooking" method="post" onsubmit="return validateForm()">
      <div class="mb-4">
        <label for="studentId" class="block text-sm font-medium text-gray-700">Student ID</label>
        <input type="text" id="studentId" name="studentId" class="mt-1 p-2 w-full border rounded-md" value="student1" required>
      </div>
      <div class="mb-4">
        <label for="tutorId" class="block text-sm font-medium text-gray-700">Tutor ID</label>
        <input type="text" id="tutorId" name="tutorId" class="mt-1 p-2 w-full border rounded-md" value="tutor1" required>
      </div>
      <div class="mb-4">
        <label for="dateTime" class="block text-sm font-medium text-gray-700">Date and Time (yyyy-MM-dd HH:mm)</label>
        <input type="text" id="dateTime" name="dateTime" class="mt-1 p-2 w-full border rounded-md" value="2025-05-17 11:30" required>
      </div>
      <div class="mb-4">
        <label for="sessionType" class="block text-sm font-medium text-gray-700">Session Type</label>
        <select id="sessionType" name="sessionType" class="mt-1 p-2 w-full border rounded-md" onchange="toggleSessionFields()" required>
          <option value="Online">Online</option>
          <option value="InPerson">In-Person</option>
        </select>
      </div>
      <div id="meetingLinkDiv" class="mb-4">
        <label for="meetingLink" class="block text-sm font-medium text-gray-700">Meeting Link (for Online)</label>
        <input type="text" id="meetingLink" name="meetingLink" class="mt-1 p-2 w-full border rounded-md" value="https://zoom.us/j/123456">
      </div>
      <div id="locationDiv" class="mb-4 hidden">
        <label for="location" class="block text-sm font-medium text-gray-700">Location (for In-Person)</label>
        <input type="text" id="location" name="location" class="mt-1 p-2 w-full border rounded-md" value="Library Room 101">
      </div>
      <button type="submit" class="w-full bg-[#CDE6F5] text-white p-3 rounded-md hover:bg-[#B3D4E9]" style="background-color: #658da6;">Create Booking</button>
    </form>
    <% if (request.getAttribute("error") != null) { %>
    <p class="mt-4 text-red-500 text-center"><%= request.getAttribute("error") %></p>
    <% } %>
  </div>
</main>
<footer class="bg-[#343a40] bg-gray-800 p-4 text-center" style="background-color: #343a40;">
  <p class="text-white">© 2025 Home Tutor</p>
</footer>
<script>
  // Initialize session fields visibility on page load
  toggleSessionFields();
</script>
</body>
</html>