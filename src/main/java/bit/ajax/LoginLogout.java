package kr.or.bit.ajax;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/LoginLogout")
public class LoginLogout extends HttpServlet {
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setCharacterEncoding("UTF-8");
        String id = (String) session.getAttribute("userid");
        PrintWriter out = response.getWriter();

        if(id != null){
            out.print("<b>[ " + id +" ]</b> 로그인 상태");
            out.print("<a href='Ex02_JDBC_Logout.jsp' id='loginlogout' class="+id+">[ 로그아웃 ]</a>");
        }else{
            out.print("<b>[로그인 하지 않으셨네요]</b>");
            out.print("<a href='Ex02_JDBC_Login.jsp' id='loginlogout'>[ 로그인 ]</a>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);

    }
}
