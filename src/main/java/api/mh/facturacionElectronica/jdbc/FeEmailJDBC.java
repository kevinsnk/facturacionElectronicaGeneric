package api.mh.facturacionElectronica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import api.mh.facturacionElectronica.ex.model.FeEmail;
import api.mh.facturacionElectronica.util.SqlConn;

public class FeEmailJDBC extends AbstractJDBC{

	@Override
	public void save(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public FeEmail getParameter(String parameter) {
		FeEmail feEmail = new FeEmail();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		ResultSet rs = null;
		String query = "SELECT U_PARAMETRO, U_DESCRIPCION, U_VALOR\n"
				+ "FROM [@FE_EMAIL]\n"
				+ "WHERE U_PARAMETRO = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, parameter);
			rs = ps.executeQuery();
			if (rs.next()) {
				feEmail.setuParametro(rs.getString("U_PARAMETRO"));
				feEmail.setuDescripcion(rs.getString("U_DESCRIPCION"));
				feEmail.setuValor(rs.getString("U_VALOR"));
			}
		} catch (Exception e) {
			feEmail = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return feEmail;
	}

}
