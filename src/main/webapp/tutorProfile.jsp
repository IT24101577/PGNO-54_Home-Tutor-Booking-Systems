<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yourteam.pgno54_hometutorbookingsystems.model.Tutor" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/partials/header.jsp" %>
  <% request.setAttribute("pageTitle", "Tutor Profile"); %>
<div class="container mt-5 flex-grow-1">
  <h1 class="text-center mb-5 fw-bold"><i class="fas fa-user-graduate me-2"></i>Tutor Profile</h1>
  <% Tutor tutor = (Tutor) request.getAttribute("tutor"); %>
  <% if (tutor != null) { %>
  <div class="form-container w-full max-w-md mx-auto">
    <form action="TutorServlet" method="post" class="p-4">
      <input type="hidden" name="action" value="update">
      <input type="hidden" name="id" value="<%= tutor.getId() %>">
      <div class="mb-4">
        <label for="name" class="form-label d-flex align-items-center">
          <i class="fas fa-user me-2"></i>Name
        </label>
        <input type="text" class="form-control" id="name" name="name" value="<%= tutor.getName() %>" required>
      </div>
      <div class="mb-4">
        <label for="subject" class="form-label d-flex align-items-center">
          <i class="fas fa-book me-2"></i>Subject Expertise
        </label>
        <input type="text" class="form-control" id="subject" name="subject" value="<%= tutor.getSubjectExpertise() %>" required>
      </div>
      <div class="mb-4">
        <label for="rating" class="form-label d-flex align-items-center">
          <i class="fas fa-star me-2"></i>Rating
        </label>
        <input type="number" step="0.1" class="form-control" id="rating" name="rating" value="<%= tutor.getRating() %>" required>
      </div>
      <button type="submit" class="btn btn-primary w-100"><i class="fas fa-save me-2"></i>Update Tutor</button>
    </form>
    <form action="TutorServlet" method="post" class="p-4 pt-0">
      <input type="hidden" name="action" value="delete">
      <input type="hidden" name="id" value="<%= tutor.getId() %>">
      <button type="submit" class="btn btn-danger w-100"><i class="fas fa-trash-alt me-2"></i>Delete Tutor</button>
    </form>
  </div>
  <% } else { %>
  <div class="text-center mt-5">
    <p class="text-muted fw-semibold"><i class="fas fa-exclamation-circle me-2" style="color: var(--secondary-muted-red);"></i>Tutor not found.</p>
  </div>
  <% } %>
</div>
<%@ include file="/WEB-INF/partials/footer.jsp" %>