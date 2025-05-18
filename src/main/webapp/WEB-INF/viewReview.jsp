<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
  <title>View Reviews</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
  <h1 class="text-2xl font-bold mb-4">Tutor Reviews</h1>
  <% if ("true".equals(request.getParameter("updated"))) { %>
  <p class="text-green-500">Review updated successfully!</p>
  <% } %>
  <% if ("true".equals(request.getParameter("deleted"))) { %>
  <p class="text-green-500">Review deleted successfully!</p>
  <% } %>
  <c:forEach var="review" items="${reviews}">
    <div class="border p-4 mb-4">
      <p><strong>ID:</strong> ${review.reviewId}</p>
      <p><strong>Tutor:</strong> ${review.tutorName}</p>
      <p><strong>Student:</strong> ${review.studentName}</p>
      <p><strong>Rating:</strong> ${review.rating}</p>
      <p><strong>Comment:</strong> ${review.comment}</p>
      <p><strong>Posted:</strong> ${review.createdAt}</p>
      <p><strong>Type:</strong> ${review.displayReview()}</p>
      <form action="submitReview" method="get" class="mt-2">
        <input type="hidden" name="reviewId" value="${review.reviewId}">
        <input type="number" name="rating" min="1" max="5" placeholder="New Rating" class="border p-1">
        <input type="text" name="comment" placeholder="New Comment" class="border p-1">
        <button type="submit" name="action" value="update" class="bg-yellow-500 text-white p-1 rounded">Update</button>
        <button type="submit" name="action" value="delete" class="bg-red-500 text-white p-1 rounded">Delete</button>
      </form>
    </div>
  </c:forEach>
  <a href="review.jsp" class="text-blue-500">Submit a New Review</a>
</div>
</body>
</html>