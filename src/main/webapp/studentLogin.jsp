<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/partials/header.jsp" %>
    <% request.setAttribute("pageTitle", "Student Login"); %>
<style>
    .login-form {
        background-color: #343a40;
        color: #f8f9fa;
        border: 1px solid #444;
        border-radius: 10px;
        padding: 30px;
    }
    .login-form label {
        color: #f8f9fa;
    }
    .login-form .form-control {
        background-color: #495057;
        color: #f8f9fa;
        border-color: #6c757d;
    }
    .login-form .form-control:focus {
        background-color: #495057;
        color: #f8f9fa;
        border-color: #007bff;
        box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
    }
    .login-form .btn-primary {
        background-color: #007bff;
        border-color: #007bff;
    }
    .login-form .btn-primary:hover {
        background-color: #0056b3;
        border-color: #0056b3;
    }
    .login-links {
        text-align: center;
        margin-top: 20px;
    }
    .login-links a {
        color: #007bff;
        text-decoration: none;
    }
    .login-links a:hover {
        text-decoration: underline;
    }
</style>
<div class="container mt-5 flex-grow-1">
    <h1 class="text-center mb-5 fw-bold text-light"><i class="fas fa-sign-in-alt me-2"></i>Login</h1>
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-4">
            <div class="login-form shadow-sm">
                <form action="StudentServlet" method="post">
                    <input type="hidden" name="action" value="login">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username" name="username" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <div class="d-grid mb-3">
                        <button type="submit" class="btn btn-primary"><i class="fas fa-sign-in-alt me-2"></i>Login</button>
                    </div>
                    <div class="login-links">
                        <p>Don't have an account? <a href="studentRegistration.jsp"><i class="fas fa-plus me-1"></i>Register</a></p>
                        <p>Admin? <a href="adminLogin.jsp"><i class="fas fa-user-shield me-1"></i>Admin Login</a></p>
                    </div>
                </form>
                <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger mt-3" role="alert">
                    <%= request.getAttribute("error") %>
                </div>
                <% } %>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/partials/footer.jsp" %>