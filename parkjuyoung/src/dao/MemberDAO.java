package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MemberDTO;

public class MemberDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public MemberDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource)envContext.lookup("jdbc/JSP-PDB");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(con != null) {
				con.close();
				con = null;
			}
		} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
		}
	}
	
	public void insert(MemberDTO dto) {
		String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, SYSDATE)";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void delete(String id) {
		String sql = "DELETE FROM MEMBER WHERE ID=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void update(MemberDTO dto) {
		String sql = "UPDATE MEMBER SET PWD=?, NAME=?, EMAIL=?, JOINDATE=? WHERE ID=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setDate(4, dto.getJoinDate());
			pstmt.setString(5, dto.getId());
			
			pstmt.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public MemberDTO getMember(String id) {
		String sql = "select * from member where id=?";
		MemberDTO dto = new MemberDTO();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setJoinDate(rs.getDate("joinDate"));
				dto.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dto;
	}
	
	public ArrayList<MemberDTO> list() {
		String sql = "select * from member";
		
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setJoinDate(rs.getDate("joinDate"));
				dto.setPwd(rs.getString("pwd"));
				dtos.add(dto);
			}
			rs.close();
			pstmt.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return dtos;
	}
}