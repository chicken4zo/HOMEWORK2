package kr.or.bit.controller;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.EditMemberService;
import kr.or.bit.service.KoreaMemberJoinService;
import kr.or.bit.service.MainService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.do")
public class KoreaMemberController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public KoreaMemberController() {
        super();
    }

    public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url_Command = requestURI.substring(contextPath.length());

        Action action = null;
        ActionForward forward = null;

        if(url_Command.equals("/KoreaMemberJoin.do")){
            action = new KoreaMemberJoinService();
            forward = action.execute(request, response);
            System.out.println("KoreaMemberJoin Start");
        }else if(url_Command.equals("/EditMember.do")){
            action = new EditMemberService();
            forward = action.execute(request,response);
        } else if(url_Command.equals("/Main.do")) {
            action = new MainService();
            forward = action.execute(request, response);
        }

        if (forward != null) {
            if (forward.isRedirect()) { //true
                response.sendRedirect(forward.getPath());
            } else { //false (모든 자원 ) 사용
                //UI
                //UI + 로직
                //forward url 주소 변환 없어 View 내용을 받을 수 있다
                RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
                dis.forward(request, response);
            }
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
