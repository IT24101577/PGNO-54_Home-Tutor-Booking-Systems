<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yourteam.pgno54_hometutorbookingsystems.model.Tutor" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/partials/header.jsp" %>
  <% request.setAttribute("pageTitle", "Search Tutors"); %>
<div class="container mt-5 flex-grow-1">
  <h1 class="text-center mb-5 fw-bold"><i class="fas fa-search me-2"></i>Search Tutors</h1>
  <div class="form-container w-full max-w-md mx-auto">
    <form action="TutorServlet" method="get" class="p-4">
      <input type="hidden" name="action" value="search">
      <div class="mb-4">
        <label for="subject" class="form-label d-flex align-items-center">
          <i class="fas fa-book me-2"></i>Subject
        </label>
        <input type="text" class="form-control" id="subject" name="subject" placeholder="e.g., Mathematics">
      </div>
      <div class="mb-4">
        <label for="name" class="form-label d-flex align-items-center">
          <i class="fas fa-user me-2"></i>Name
        </label>
        <input type="text" class="form-control" id="name" name="name" placeholder="e.g., John Doe">
      </div>
      <div class="mb-4">
        <label for="minRating" class="form-label d-flex align-items-center">
          <i class="fas fa-star me-2"></i>Minimum Rating
        </label>
        <select class="form-control" id="minRating" name="minRating">
          <option value="">Any</option>
          <option value="1">1.0</option>
          <option value="2">2.0</option>
          <option value="3">3.0</option>
          <option value="4">4.0</option>
          <option value="5">5.0</option>
        </select>
      </div>
      <button type="submit" class="btn btn-primary w-100"><i class="fas fa-search me-2"></i>Search</button>
    </form>
    <% List<Tutor> tutors = (List<Tutor>) request.getAttribute("tutors"); %>
    <% if (tutors != null && !tutors.isEmpty()) { %>
    <div class="mt-4">
      <h3 class="fw-semibold"><i class="fas fa-user-graduate me-2"></i>Tutors Found (<%= tutors.size() %>)</h3>
      <% for (Tutor tutor : tutors) { %>
      <div class="p-4 bg-surface-dark-gray rounded-lg shadow-sm mb-3">
        <p class="mb-1"><strong>ID:</strong> <%= tutor.getId() %></p>
        <p class="mb-1"><strong>Name:</strong> <%= tutor.getName() %></p>
        <p class="mb-1"><strong>Subject:</strong> <%= tutor.getSubjectExpertise() %></p>
        <p class="mb-2"><strong>Rating:</strong> <%= tutor.getRating() %></p>
        <a href="TutorServlet?action=profile&id=<%= tutor.getId() %>" class="btn btn-secondary w-100"><i class="fas fa-eye me-2"></i>View Profile</a>
      </div>
      <% } %>
    </div>
    <% } else if (request.getParameter("subject") != null || request.getParameter("name") != null) { %>
    <p class="text-muted mt-3 fw-semibold"><i class="fas fa-exclamation-circle me-2"></i>No tutors found.</p>
    <% } %>
  </div>
</div>
<%@ include file="/WEB-INF/partials/footer.jsp" %>