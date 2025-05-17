<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/partials/header.jsp" %>
  <% request.setAttribute("pageTitle", "Register Tutor"); %>
<div class="container mt-5 flex-grow-1">
  <h1 class="text-center mb-5 fw-bold"><i class="fas fa-user-plus me-2"></i>Register a New Tutor</h1>
  <div class="form-container w-full max-w-md mx-auto">
    <form action="TutorServlet" method="post" class="p-4">
      <input type="hidden" name="action" value="add">
      <div class="mb-4">
        <label for="id" class="form-label d-flex align-items-center">
          <i class="fas fa-id-badge me-2"></i>Tutor ID
        </label>
        <input type="text" class="form-control" id="id" name="id" required>
      </div>
      <div class="mb-4">
        <label for="name" class="form-label d-flex align-items-center">
          <i class="fas fa-user me-2"></i>Name
        </label>
        <input type="text" class="form-control" id="name" name="name" required>
      </div>
      <div class="mb-4">
        <label for="subject" class="form-label d-flex align-items-center">
          <i class="fas fa-book me-2"></i>Subject Expertise
        </label>
        <input type="text" class="form-control" id="subject" name="subject" required>
      </div>
      <div class="mb-4">
        <label for="rating" class="form-label d-flex align-items-center">
          <i class="fas fa-star me-2"></i>Rating
        </label>
        <input type="number" step="0.1" class="form-control" id="rating" name="rating" required>
      </div>
      <button type="submit" class="btn btn-primary w-100"><i class="fas fa-plus me-2"></i>Add Tutor</button>
    </form>
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
    <div class="alert alert-danger mt-3" role="alert">
      <i class="fas fa-exclamation-triangle me-2"></i><%= error %>
    </div>
    <% } %>
  </div>
</div>
<%@ include file="/WEB-INF/partials/footer.jsp" %>