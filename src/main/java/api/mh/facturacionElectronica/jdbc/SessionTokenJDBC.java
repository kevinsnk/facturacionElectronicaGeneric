package api.mh.facturacionElectronica.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import api.mh.facturacionElectronica.ex.model.SessionToken;
import api.mh.facturacionElectronica.util.SqlConn;

public class SessionTokenJDBC extends AbstractJDBC {

	public SessionToken getTokenActivoTest() {
		SessionToken token = new SessionToken();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(
					"SELECT 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwNjE0MjkxMjAwMTAxNyIsImF1dGhvcml0aWVzIjpbIlVTRVIiLCJVU0VSX0FQSSIsIlVzdWFyaW8iXSwiaWF0IjoxNzE2MDcxMjcxLCJleHAiOjE3MTYxNTc2NzF9.bIL48C4gFGVWAWF62kyhCdF_hLvXFxsqCvtHuXrk_GvsrSXI6Q67uW2Y4nV9Fsa2OCAllh5zIpR2SrR4chAffw'"
							+ " as TOKEN");
			while (rs.next()) {
				token.setToken(rs.getString("TOKEN"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return token;
	}
	
	public SessionToken getTokenActivo() {
		SessionToken token = null;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT U_USUARIO, U_TOKEN, U_FECHA_CREA, U_FECHA_EXPIRACION, U_ESTADO "
				+ "FROM [@FE_TOKEN] "
				+ "WHERE U_ESTADO = 'A'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			if (rs.next()) {
				token = new SessionToken();
				token.setUsuario(rs.getString("U_USUARIO"));
				token.setToken(rs.getString("U_TOKEN"));
				token.setFechaCreacion(rs.getDate("U_FECHA_CREA"));
				token.setFechaExpiracion(rs.getDate("U_FECHA_EXPIRACION"));
				token.setEstado(rs.getString("U_ESTADO"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return token;
	}

	@Override
	public void save(Object entity) throws SQLException {
		SessionToken sessionToken = (SessionToken) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps = null;
		String query = "INSERT INTO [@FE_TOKEN] (code, name, U_USUARIO, U_TOKEN, U_FECHA_CREA, U_FECHA_EXPIRACION, U_ESTADO) " 
		+ "VALUES ((Select  Right('00000000' + IsNull(Convert(Varchar,Max(Convert(Int,T0.Code) + 1)),1),8) From [@FE_TOKEN] T0), "
		+ "(Select  Right('00000000' + IsNull(Convert(Varchar,Max(Convert(Int,T0.Code) + 1)),1),8) From [@FE_TOKEN] T0), "
		+ "?, ?, GETDATE(), (GETDATE() + ?), ?)";
		try {
			Properties properties = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream path = loader.getResourceAsStream("resources/config/config.properties");
			properties.load(path);
			
			int tokenTimeout = 2;
			String ambiente = properties.getProperty("AMBIENTE");
			if(ambiente.equals("00")) {
				tokenTimeout = Integer.valueOf(properties.getProperty("TOKEN_TIMEOUT_DESA"));
			}else {
				tokenTimeout = Integer.valueOf(properties.getProperty("TOKEN_TIMEOUT_PROD"));
			}
			
			ps = conn.prepareStatement(query);
			ps.setString(1, sessionToken.getUsuario());
			ps.setString(2, sessionToken.getToken());
			ps.setInt(3, tokenTimeout);
			ps.setString(4, "A");

			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void edit(Object entity) throws SQLException {
		SessionToken sessionToken = (SessionToken) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "UPDATE [@FE_TOKEN] "
				+ "SET U_ESTADO = 'V' "
				+ "WHERE cast(U_TOKEN as nvarchar(max)) = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, sessionToken.getToken());


			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Object entity) throws SQLException {
		// TODO Auto-generated method stub

	}

}
