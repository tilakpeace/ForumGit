<%

    if (session.getAttribute("sessionProfile") == null) {

        String redirect = request.getContextPath() + "/login/view";
        response.sendRedirect(redirect);
        return;
    }
%>




