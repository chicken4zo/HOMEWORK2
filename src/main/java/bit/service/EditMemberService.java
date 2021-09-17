package kr.or.bit.service;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.dto.KoreaMemberDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Member;

public class EditMemberService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");

        ActionForward forward = new ActionForward();

        if(name == null){
            KoreaMemberDao dao = new KoreaMemberDao();
            KoreaMemberDto kdto = dao.detailMember(id);

            request.setAttribute("detailmember", kdto);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/Ex03_MemberEdit.jsp");
        } else {
            KoreaMemberDto kdto = new KoreaMemberDto();
            kdto.setName(name);
            kdto.setAge(age);
            kdto.setEmail(email);
            kdto.setGender(gender);
            kdto.setId(id);

            KoreaMemberDao dao = new KoreaMemberDao();
            int result = dao.editMember(kdto);

            String msg = "";
            String url="";

            if(result>0){
                msg="수정성공";
                url="MemberList.do";
            }else {
                msg="수정실패";
                url="MemberList.do";
            }

            request.setAttribute("board_msg",msg);
            request.setAttribute("board_url",url);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/view/redirect.jsp");

        }
        return forward;
    }
}
