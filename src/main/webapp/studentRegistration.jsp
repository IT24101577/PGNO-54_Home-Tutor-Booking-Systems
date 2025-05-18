<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/partials/header.jsp" %>
    <% request.setAttribute("pageTitle", "Register Student"); %>
<div class="container mt-5 flex-grow-1">
    <h1 class="text-center mb-5 fw-bold"><i class="fas fa-user-plus me-2"></i>Register a Student</h1>
    <div class="form-container w-full max-w-md mx-auto">
        <form action="StudentServlet" method="post" class="p-4">
            <input type="hidden" name="action" value="add">
            <div class="mb-4">
                <label for="id" class="form-label d-flex align-items-center">
                    <i class="fas fa-id-badge me-2"></i>Student ID
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
            <div class="mb-4">
                <label for="preferredSubjects" class="form-label d-flex align-items-center">
                    <i class="fas fa-book me-2"></i>Preferred Subjects (comma-separated)
                </label>
                <input type="text" class="form-control" id="preferredSubjects" name="preferredSubjects" required>
            </div>
            <div class="mb-4">
                <label for="contactDetails" class="form-label d-flex align-items-center">
                    <i class="fas fa-envelope me-2"></i>Contact Details
                </label>
                <input type="text" class="form-control" id="contactDetails" name="contactDetails" required>
            </div>
            <button type="submit" class="btn btn-primary w-100"><i class="fas fa-plus-circle me-2"></i>Add Student</button>
        </form>
    </div>
    <% if (request.getAttribute("error") != null) { %>
    <div class="text-center mt-5">
        <p class="text-danger fw-semibold"><i class="fas fa-exclamation-circle me-2"></i><%= request.getAttribute("error") %></p>
    </div>
    <% } %>
</div>
<%@ include file="/WEB-INF/partials/footer.jsp" %>