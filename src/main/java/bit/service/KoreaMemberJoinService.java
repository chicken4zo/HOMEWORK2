package kr.or.bit.service;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.dto.KoreaMemberDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KoreaMemberJoinService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String ip = request.getRemoteAddr();

        KoreaMemberDao dao = new KoreaMemberDao();
        KoreaMemberDto dto = new KoreaMemberDto();

        dto.setId(id);
        dto.setPwd(pwd);
        dto.setName(name);
        dto.setAge(age);
        dto.setGender(gender);
        dto.setEmail(email);
        dto.setIp(ip);

        int result = dao.insertKoreanMember(dto);

        String msg = "";
        String url = "";
        if (result > 0) {
            msg = "등록성공";
            url = "KoreaMemeberJoinCheck.do";
        } else {
            msg = "등록실패";
            url = "";
        }

        request.setAttribute("board_msg", msg);
        request.setAttribute("board_url", url);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/redirect.jsp");

        return forward;
    }
}
