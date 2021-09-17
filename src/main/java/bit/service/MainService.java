package kr.or.bit.service;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class MainService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        System.out.println("MainService");
        response.setCharacterEncoding("UTF-8");

        String id = null;
        id = (String)session.getAttribute("userid");
        System.out.println(id);

        try {
            PrintWriter out = response.getWriter();
            if(id != null){
                //회원
                request.setAttribute("text",id+" 회원님 반갑습니다");
                if(id.equals("admin")) {
                    request.setAttribute("link", "<a href='Ex03_Memberlist.jsp'>회원관리</a>");
                }
            }else{
                //로그인 하지 않은 사용자
                //메인 페이지는 회원만 볼수 있어요 (강제 링크 추가)
                request.setAttribute("text","사이트 방문을 환영합니다 ^^ <br>회원가입 좀 하지 ...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/main.jsp");

        return forward;
    }
}
