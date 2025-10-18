package api.mh.facturacionElectronica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.mh.facturacionElectronica.ex.model.FeContingencia;
import api.mh.facturacionElectronica.util.SqlConn;

public class FeContingenciaJDBC extends AbstractJDBC{

	@Override
	public void save(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(Object entity) throws SQLException {
		FeContingencia felContingencia = (FeContingencia) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		String query = "UPDATE [@FE_CONTINGENCIA]\r\n"
				+ "SET U_E_SELLRECEEP = ?,\r\n"
				+ "U_JSONContingencia = ?,\r\n"
				+ "U_ESTADO = ?\r\n"
				+ "WHERE CODE = ?";
		
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, felContingencia.getuESelleRecepcion());
			ps.setString(2, felContingencia.getuJsonContingencia());
			ps.setString(3, felContingencia.getuEstado());
			ps.setInt(4, felContingencia.getCode());

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
	
	public List<FeContingencia> getContingenciasPendientes(){
		List<FeContingencia> lista = new ArrayList<FeContingencia>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		ResultSet rs = null;
		String query = "SELECT code, U_tipoContingencia, U_desContingencia, U_motivo, \r\n"
				+ "U_fechaIni, U_horaIni, U_fechaFin, U_horaFin, U_E_SELLRECEEP, U_JSONContingencia \r\n"
				+ "FROM [@FE_CONTINGENCIA]\r\n"
				+ "WHERE U_E_SELLRECEEP IS NULL\r\n"
				+ "AND (U_ESTADO NOT IN('E','A')\n"
				+ "OR U_ESTADO IS NULL)";
		try {
			ps = conn.prepareStatement(query);
			//ps.setInt(1, numContingencia);
			rs = ps.executeQuery();
			while (rs.next()) {
				FeContingencia feContingencia = new FeContingencia();
				feContingencia.setCode(rs.getInt("code"));
				feContingencia.setuContingencia(rs.getInt("U_tipoContingencia"));
				feContingencia.setuDesContingencia(rs.getString("U_desContingencia"));
				feContingencia.setuMotivo(rs.getString("U_motivo"));
				feContingencia.setuFechaIni(rs.getDate("U_fechaIni"));
				feContingencia.setuHoraIni(rs.getString("U_horaIni"));
				feContingencia.setuFechaFin(rs.getDate("U_fechaFin"));
				feContingencia.setuHoraFin(rs.getString("U_horaFin"));
				feContingencia.setuESelleRecepcion(rs.getString("U_E_SELLRECEEP"));
				feContingencia.setuJsonContingencia(rs.getString("U_JSONContingencia"));
				
				lista.add(feContingencia);
			}
		} catch (Exception e) {
			lista = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

}
