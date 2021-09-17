package kr.or.bit.dao;

import kr.or.bit.dto.KoreaMemberDto;
import kr.or.bit.utils.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class KoreaMemberDao {
    public int insertKoreanMember(KoreaMemberDto koreaMemberDto){
        Connection conn=null;
        int resultRow=0;
        PreparedStatement pstmt = null;

        try{
            conn = ConnectionHelper.getConnection("oracle");

            String sql="insert into koreamember(id,pwd,name,age,gender,email,ip) values(?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, koreaMemberDto.getId());
            pstmt.setString(2, koreaMemberDto.getPwd());
            pstmt.setString(3, koreaMemberDto.getName());
            pstmt.setInt(4,koreaMemberDto.getAge());
            pstmt.setString(5, koreaMemberDto.getGender());
            pstmt.setString(6, koreaMemberDto.getEmail());
            pstmt.setString(7, koreaMemberDto.getIp());

            resultRow = pstmt.executeUpdate();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return resultRow;
    }

    // edit view == detail
    public KoreaMemberDto detailMember(String id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        KoreaMemberDto kdto = null;


        try{
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select id,pwd,name,age,gender,email from koreamember where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            while(rs.next()){
                kdto = new KoreaMemberDto();
                kdto.setId(rs.getString("id"));
                kdto.setPwd(rs.getString("pwd"));
                kdto.setName(rs.getString("name"));
                kdto.setAge(rs.getInt("age"));
                kdto.setGender(rs.getNString("gender"));
                kdto.setEmail(rs.getString("email"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return kdto;
    }

    // edit
    public int editMember(KoreaMemberDto kdto){
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultrow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "update koreamember set name=? , age=? , email=? , gender=? where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kdto.getName());
            pstmt.setInt(2,kdto.getAge());
            pstmt.setString(3,kdto.getEmail());
            pstmt.setString(4,kdto.getGender());
            pstmt.setString(5,kdto.getId());
            resultrow=pstmt.executeUpdate();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return resultrow;
    }
}
