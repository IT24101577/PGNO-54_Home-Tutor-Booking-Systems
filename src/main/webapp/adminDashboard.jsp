<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/partials/header.jsp" %>
  <% request.setAttribute("pageTitle", "Admin Dashboard"); %>
<style>
  .dark-card {
    background-color: #343a40;
    color: #f8f9fa;
    border: 1px solid #444;
    border-radius: 10px;
    padding: 30px;
    min-height: 250px;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
  .dark-card .card-title {
    color: #f8f9fa;
    font-size: 1.5rem;
  }
  .dark-card .card-text {
    color: #ced4da;
    font-size: 1rem;
  }
  .dark-card .btn-primary {
    background-color: #007bff;
    border-color: #007bff;
    color: #fff;
    padding: 10px 25px;
    border-radius: 5px;
    font-size: 1rem;
    margin-top: auto;
  }
  .dark-card .btn-primary:hover {
    background-color: #0056b3;
    border-color: #0056b3;
  }
  .dark-card .icon {
    font-size: 3rem;
    color: #6c757d;
    margin-bottom: 20px;
  }
</style>
<div class="container mt-5 flex-grow-1">
  <h1 class="text-center mb-5 fw-bold text-light"><i class="fas fa-user-shield me-2"></i>Admin Dashboard</h1>
  <div class="row justify-content-center">
    <div class="col-md-6 col-lg-5 mb-4">
      <div class="dark-card shadow-sm">
        <div class="text-center">
          <i class="fas fa-chalkboard-teacher icon"></i>
          <h5 class="card-title">Manage Tutors</h5>
          <a href="TutorServlet" class="btn btn-primary"><i class="fas fa-arrow-right me-2"></i>Go to Tutor Management</a>
        </div>
      </div>
    </div>
    <div class="col-md-6 col-lg-5 mb-4">
      <div class="dark-card shadow-sm">
        <div class="text-center">
          <i class="fas fa-user-graduate icon"></i>
          <h5 class="card-title">Manage Students</h5>
          <a href="StudentSearchServlet" class="btn btn-primary"><i class="fas fa-arrow-right me-2"></i>Go to Student Management</a>
        </div>
      </div>
    </div>
  </div>
</div>
<%@ include file="/WEB-INF/partials/footer.jsp" %>