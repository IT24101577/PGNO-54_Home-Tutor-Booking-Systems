<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/partials/header.jsp" %>
    <% request.setAttribute("pageTitle", "Student Welcome"); %>
<style>
    .btn-primary {
        background-color: #007bff;
        border-color: #007bff;
        color: #fff;
        border-radius: 5px;
    }
    .btn-primary:hover {
        background-color: #0056b3;
        border-color: #0056b3;
    }
    .btn-find-tutor {
        padding: 15px 40px;
        font-size: 1.2rem;
        margin-top: 20px;
    }
    .btn-account {
        position: absolute;
        top: 20px;
        right: 20px;
        padding: 8px 20px;
        font-size: 0.9rem;
    }
    .container {
        position: relative;
    }
    .welcome-header {
        font-size: 3rem;
    }
</style>
<div class="container mt-5 flex-grow-1">
    <a href="StudentServlet?action=profile&id=<%= request.getAttribute("studentId") %>" class="btn btn-primary btn-account"><i class="fas fa-edit me-2"></i>Account</a>
    <h1 class="text-center mb-5 fw-bold text-light welcome-header"><i class="fas fa-user-graduate me-2"></i>Welcome to HomeTutor</h1>
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5 mb-4 text-center">
            <a href="findTutor.jsp" class="btn btn-primary btn-find-tutor"><i class="fas fa-search me-2"></i>Find a Tutor</a>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/partials/footer.jsp" %>