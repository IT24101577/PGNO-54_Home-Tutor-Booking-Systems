<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yourteam.hometutorsearchbookingsystem.model.Tutor" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/partials/header.jsp" %>
  <% request.setAttribute("pageTitle", "Tutor Search & Sort"); %>
<div class="container mt-5 flex-grow-1">
  <h1 class="text-center mb-5 fw-bold"><i class="fas fa-search me-2"></i>Tutor Search & Sort</h1>
  <div class="form-container w-full max-w-md mx-auto mb-4">
    <form action="TutorSearchSortServlet" method="get" class="p-4">
      <div class="mb-4">
        <label for="subject" class="form-label d-flex align-items-center">
          <i class="fas fa-book me-2"></i>Subject
        </label>
        <input type="text" class="form-control" id="subject" name="subject">
      </div>
      <div class="mb-4">
        <label for="name" class="form-label d-flex align-items-center">
          <i class="fas fa-user me-2"></i>Name
        </label>
        <input type="text" class="form-control" id="name" name="name">
      </div>
      <div class="mb-4">
        <label for="minRating" class="form-label d-flex align-items-center">
          <i class="fas fa-star me-2"></i>Minimum Rating
        </label>
        <input type="number" step="0.1" class="form-control" id="minRating" name="minRating" value="0.0">
      </div>
      <div class="mb-4">
        <label for="sortCriterion" class="form-label d-flex align-items-center">
          <i class="fas fa-sort me-2"></i>Sort By
        </label>
        <select class="form-control" id="sortCriterion" name="sortCriterion">
          <option value="rating">Rating</option>
          <option value="experience">Experience</option>
        </select>
      </div>
      <button type="submit" class="btn btn-primary w-100"><i class="fas fa-search me-2"></i>Search & Sort</button>
    </form>
  </div>
  <% List<Tutor> tutors = (List<Tutor>) request.getAttribute("tutors"); %>
  <% if (tutors != null && !tutors.isEmpty()) { %>
  <div class="table-responsive">
    <table class="table table-dark table-striped">
      <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Subject</th>
        <th>Rating</th>
      </tr>
      </thead>
      <tbody>
      <% for (Tutor tutor : tutors) { %>
      <tr>
        <td><%= tutor.getId() %></td>
        <td><%= tutor.getName() %></td>
        <td><%= tutor.getSubjectExpertise() %></td>
        <td><%= tutor.getRating() %></td>
      </tr>
      <% } %>
      </tbody>
    </table>
  </div>
  <% } else { %>
  <div class="text-center mt-5">
    <p class="text-muted fw-semibold"><i class="fas fa-info-circle me-2"></i>No tutors found.</p>
  </div>
  <% } %>
</div>
<%@ include file="/WEB-INF/partials/footer.jsp" %>