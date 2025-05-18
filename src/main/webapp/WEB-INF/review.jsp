<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Submit Review</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Submit a Review</h1>
    <% if ("true".equals(request.getParameter("success"))) { %>
    <p class="text-green-500">Review submitted successfully!</p>
    <% } %>
    <form action="submitReview" method="post" class="space-y-4">
        <div>
            <label for="tutorName" class="block">Tutor Name:</label>
            <input type="text" id="tutorName" name="tutorName" required class="border p-2 w-full">
        </div>
        <div>
            <label for="studentName" class="block">Your Name:</label>
            <input type="text" id="studentName" name="studentName" required class="border p-2 w-full">
        </div>
        <div>
            <label for="rating" class="block">Rating (1-5):</label>
            <input type="number" id="rating" name="rating" min="1" max="5" required class="border p-2 w-full">
        </div>
        <div>
            <label for="comment" class="block">Comment:</label>
            <textarea id="comment" name="comment" required class="border p-2 w-full"></textarea>
        </div>
        <button type="submit" class="bg-blue-500 text-white p-2 rounded">Submit Review</button>
    </form>
    <a href="viewReviews" class="mt-4 inline-block text-blue-500">View All Reviews</a>
</div>
</body>
</html>