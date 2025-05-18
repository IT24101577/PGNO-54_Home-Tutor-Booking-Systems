<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/partials/header.jsp" %>
  <% request.setAttribute("pageTitle", "Admin Deletion Successful"); %>
<style>
  .success-message {
    background-color: #343a40;
    color: #f8f9fa;
    border: 1px solid #444;
    border-radius: 10px;
    padding: 30px;
    text-align: center;
    margin: 50px auto;
    max-width: 500px;
  }
  .success-message .btn-primary {
    background-color: #007bff;
    border-color: #007bff;
    color: #fff;
  }
  .success-message .btn-primary:hover {
    background-color: #0056b3;
    border-color: #0056b3;
  }
</style>
<div class="container mt-5 flex-grow-1">
  <div class="success-message shadow-sm">
    <h1 class="mb-4"><i class="fas fa-check-circle"></i> Deletion Successful</h1>
    <p>The student account has been successfully deleted by the admin.</p>
    <a href="adminDashboard.jsp" class="btn btn-primary mt-3"><i class="fas fa-home me-2"></i>Back to Admin Dashboard</a>
  </div>
</div>
<%@ include file="/WEB-INF/partials/footer.jsp" %>