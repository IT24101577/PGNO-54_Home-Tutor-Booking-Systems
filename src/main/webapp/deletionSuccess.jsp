<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/partials/header.jsp" %>
    <% request.setAttribute("pageTitle", "Deletion Successful"); %>
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
</style>
<div class="container mt-5 flex-grow-1">
    <div class="success-message shadow-sm">
        <h1 class="mb-4"><i class="fas fa-check-circle"></i> Deletion Successful</h1>
        <p>Your student account has been successfully deleted.</p>
    </div>
</div>
<%@ include file="/WEB-INF/partials/footer.jsp" %>