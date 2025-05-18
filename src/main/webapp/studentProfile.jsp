<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yourteam.pgno54_hometutorbookingsystems.model.Student" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/partials/header.jsp" %>
  <% request.setAttribute("pageTitle", "Student Profile"); %>
<style>
  .profile-form {
    background-color: #343a40;
    color: #f8f9fa;
    border: 1px solid #444;
    border-radius: 10px;
    padding: 30px;
  }
  .profile-form label {
    color: #f8f9fa;
  }
  .profile-form .form-control {
    background-color: #495057;
    color: #f8f9fa;
    border-color: #6c757d;
  }
  .profile-form .form-control:focus {
    background-color: #495057;
    color: #f8f9fa;
    border-color: #007bff;
    box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
  }
  .profile-form .btn-primary {
    background-color: #007bff;
    border-color: #007bff;
  }
  .profile-form .btn-primary:hover {
    background-color: #0056b3;
    border-color: #0056b3;
  }
  .profile-form .btn-danger {
    background-color: #dc3545;
    border-color: #dc3545;
  }
  .profile-form .btn-danger:hover {
    background-color: #b02a37;
    border-color: #b02a37;
  }
  .btn-logout {
    position: absolute;
    top: 20px;
    right: 20px;
    padding: 8px 20px;
    font-size: 0.9rem;
  }
  .container {
    position: relative;
  }
</style>
<div class="container mt-5 flex-grow-1">
  <a href="StudentServlet?action=login" class="btn btn-primary btn-logout"><i class="fas fa-sign-out-alt me-2"></i>Logout</a>
  <h1 class="text-center mb-5 fw-bold text-light"><i class="fas fa-user-graduate me-2"></i>Student Profile</h1>
  <%
    Student student = (Student) request.getAttribute("student");
    if (student != null) {
  %>
  <div class="row justify-content-center">
    <div class="col-md-8 col-lg-6">
      <div class="profile-form shadow-sm">
        <form action="StudentServlet" method="post">
          <input type="hidden" name="action" value="update">
          <input type="hidden" name="id" value="<%= student.getId() %>">
          <div class="mb-3">
            <label for="id" class="form-label">Student ID</label>
            <input type="text" class="form-control" id="id" value="<%= student.getId() %>" disabled>
          </div>
          <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" value="<%= student.getName() != null ? student.getName() : "" %>">
          </div>
          <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" name="username" value="<%= student.getUsername() %>">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" value="<%= student.getPassword() %>">
          </div>
          <div class="mb-3">
            <label for="preferredSubjects" class="form-label">Preferred Subjects (comma-separated)</label>
            <input type="text" class="form-control" id="preferredSubjects" name="preferredSubjects" value="<%= String.join(",", student.getPreferredSubjects()) %>">
          </div>
          <div class="mb-3">
            <label for="contactDetails" class="form-label">Contact Details</label>
            <input type="text" class="form-control" id="contactDetails" name="contactDetails" value="<%= student.getContactDetails() != null ? student.getContactDetails() : "" %>">
          </div>
          <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-primary"><i class="fas fa-save me-2"></i>Update Profile</button>
          </div>
        </form>
        <form action="StudentServlet" method="post" style="margin-top: 20px;">
          <input type="hidden" name="action" value="delete">
          <input type="hidden" name="id" value="<%= student.getId() %>">
          <input type="hidden" name="role" value="student">
          <button type="submit" class="btn btn-danger"><i class="fas fa-trash-alt me-2"></i>Delete Profile</button>
        </form>
        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger mt-3" role="alert">
          <%= request.getAttribute("error") %>
        </div>
        <% } %>
      </div>
    </div>
  </div>
  <% } else { %>
  <div class="text-center mt-5">
    <p class="text-muted fw-semibold"><i class="fas fa-exclamation-circle me-2"></i>Student not found.</p>
  </div>
  <% } %>
</div>
<%@ include file="/WEB-INF/partials/footer.jsp" %>