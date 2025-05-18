<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yourteam.pgno54_hometutorbookingsystems.model.Student" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/partials/header.jsp" %>
  <% request.setAttribute("pageTitle", "Student Search"); %>
<style>
  .table-dark-custom {
    background-color: #343a40;
    color: #f8f9fa;
    border-color: #444;
  }
  .table-dark-custom th {
    background-color: #212529;
    color: #f8f9fa;
    border-color: #444;
  }
  .table-dark-custom td {
    background-color: #343a40;
    color: #f8f9fa;
    border-color: #444;
  }
  .table-dark-custom .btn-primary {
    background-color: #007bff;
    border-color: #007bff;
  }
  .table-dark-custom .btn-primary:hover {
    background-color: #0056b3;
    border-color: #0056b3;
  }
</style>
<div class="container mt-5 flex-grow-1">
  <h1 class="text-center mb-5 fw-bold"><i class="fas fa-user-graduate me-2"></i>Student Search</h1>
  <div class="mb-4">
    <form action="StudentSearchServlet" method="get" class="d-flex justify-content-center">
      <div class="input-group w-50">
        <input type="text" class="form-control" name="searchQuery" placeholder="Search by ID or Name" value="<%= request.getParameter("searchQuery") != null ? request.getParameter("searchQuery") : "" %>">
        <button type="submit" class="btn btn-primary"><i class="fas fa-search me-2"></i>Search</button>
      </div>
    </form>
  </div>
  <%
    List<Student> students = (List<Student>) request.getAttribute("students");
    if (students != null && !students.isEmpty()) {
  %>
  <div class="table-responsive">
    <table class="table table-bordered table-dark-custom">
      <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Username</th>
        <th>Preferred Subjects</th>
        <th>Contact Details</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <% for (Student student : students) { %>
      <tr>
        <td><%= student.getId() %></td>
        <td><%= student.getName() %></td>
        <td><%= student.getUsername() %></td>
        <td><%= String.join(", ", student.getPreferredSubjects()) %></td>
        <td><%= student.getContactDetails() %></td>
        <td>
          <a href="StudentServlet?action=profile&id=<%= student.getId() %>" class="btn btn-sm btn-primary"><i class="fas fa-edit me-1"></i>View/Edit</a>
        </td>
      </tr>
      <% } %>
      </tbody>
    </table>
  </div>
  <% } else { %>
  <div class="text-center mt-5">
    <p class="text-muted fw-semibold"><i class="fas fa-exclamation-circle me-2"></i>No students found.</p>
  </div>
  <% } %>
</div>
<%@ include file="/WEB-INF/partials/footer.jsp" %>