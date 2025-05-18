<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/partials/header.jsp" %>
  <% request.setAttribute("pageTitle", "Admin Login"); %>
<div class="container mt-5 flex-grow-1">
  <h1 class="text-center mb-5 fw-bold"><i class="fas fa-user-shield me-2"></i>Admin Login</h1>
  <div class="form-container w-full max-w-md mx-auto">
    <form action="AdminServlet" method="post" class="p-4">
      <input type="hidden" name="action" value="adminLogin">
      <div class="mb-4">
        <label for="username" class="form-label d-flex align-items-center">
          <i class="fas fa-user me-2"></i>Username
        </label>
        <input type="text" class="form-control" id="username" name="username" required>
      </div>
      <div class="mb-4">
        <label for="password" class="form-label d-flex align-items-center">
          <i class="fas fa-lock me-2"></i>Password
        </label>
        <input type="password" class="form-control" id="password" name="password" required>
      </div>
      <button type="submit" class="btn btn-primary w-100"><i class="fas fa-sign-in-alt me-2"></i>Login</button>
    </form>
  </div>
  <% if (request.getAttribute("error") != null) { %>
  <div class="text-center mt-5">
    <p class="text-danger fw-semibold"><i class="fas fa-exclamation-circle me-2"></i><%= request.getAttribute("error") %></p>
  </div>
  <% } %>
</div>
<%@ include file="/WEB-INF/partials/footer.jsp" %>