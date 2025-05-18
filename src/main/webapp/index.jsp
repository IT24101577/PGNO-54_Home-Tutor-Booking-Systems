<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>HomeTutor Application</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet" onload="console.log('Tailwind CSS loaded')" onerror="console.error('Tailwind CSS failed to load')">
</head>
<body class="bg-[#6c757d] bg-gray-600 text-white min-h-screen flex flex-col" style="background-color: #6c757d;">
<header class="bg-[#343a40] bg-gray-800 p-4 fixed w-full top-0 z-10 shadow-md" style="background-color: #343a40;">
  <h1 class="text-2xl font-bold text-center">Home Tutor</h1>
</header>
<main class="flex-grow container mx-auto p-8 flex items-center justify-center">
  <div class="mt-16 flex items-center w-full">
    <img src="${pageContext.request.contextPath}/images/online-tutor.png" alt="Online Tutor" class="w-2/5 mr-8" style="min-width: 500px;">
    <div class="text-center">
      <h1 class="text-3xl font-bold mb-6">Welcome to Home Tutor – Your Gateway to Personalized Online Learning</h1>
      <div class="space-y-4">
        <a href="${pageContext.request.contextPath}/component04/booking.jsp" class="inline-block bg-[#CDE6F5] text-black p-3 rounded-md hover:bg-[#B3D4E9]" style="background-color: #88b4cf;">Create New Booking</a>
        <a href="${pageContext.request.contextPath}/viewBookings?studentId=student1" class="inline-block bg-[#CDE6F5] text-black p-3 rounded-md hover:bg-[#B3D4E9]" style="background-color: #CDE6F5;">View Booking History</a>
      </div>
    </div>
  </div>
</main>
<footer class="bg-[#343a40] bg-gray-800 p-4 text-center" style="background-color: #343a40;">
  <p class="text-white">© 2025 Home Tutor</p>
</footer>
</body>
</html>