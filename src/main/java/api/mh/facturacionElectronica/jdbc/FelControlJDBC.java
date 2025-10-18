package api.mh.facturacionElectronica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import api.mh.facturacionElectronica.ex.model.FelControl;
import api.mh.facturacionElectronica.util.SqlConn;

public class FelControlJDBC extends AbstractJDBC {

	@Override
	public void save(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(Object entity) throws SQLException {
		FelControl felControl = (FelControl) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		String query = "UPDATE [@FELCONTROL]\n"
				+ "SET U_FECHAPRO = SYSDATETIME(),\n"
				+ "U_CERTIFICADO = ?,\n"
				+ "U_ENVIADO = ?,\n"
				+ "U_E_CODGENE = ?,\n"
				+ "U_E_NUMCONT = ?,\n"
				+ "U_E_SELLRECEP = ?,\n"
				+ "U_ESTADO = ?,\n"
				+ "U_JSON = ?,\n"
				+ "U_FIRMA = ?, \n"
				+ "U_JSON_ENVIADO = ?,\n"
				+ "U_CARDCODE = ?, \n"
				+ "U_E_MODFACT = ?,\n"
				+ "U_E_TIPOTRANS = ?\n"
				+ "WHERE U_DOCENTRY = ?";
		
		try {
			
			ps = conn.prepareStatement(query);
			ps.setString(1, felControl.getuCertificado());
			ps.setString(2, felControl.getuEnviado());
			ps.setString(3, felControl.getuECodGene());
			ps.setString(4, felControl.getuENumCont());
			ps.setString(5, felControl.getuESellRecep());
			ps.setString(6, felControl.getuEstado());
			ps.setString(7, felControl.getuJson());
			ps.setString(8, felControl.getuFirma());
			ps.setString(9, felControl.getuJsonEnviado());
			ps.setString(10, felControl.getCardCode());
			ps.setString(11, felControl.getuEModFact());
			ps.setString(12, felControl.getuETipoTrans());
			ps.setString(13, felControl.getuDocEntry());

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

	public FelControl getFEL() {
		FelControl felControl = new FelControl();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT U_TIPO, U_NUMERO, U_FECHAIN, U_FECHAPRO, U_CERTIFICADO, "
				+ "U_ENVIADO, U_E_CODGENE, U_E_NUMCONT, U_E_SELLRECEP, U_DOCENTRY, "
				+ "U_ESTADO, U_JSON, U_FIRMA "
				+ "FROM [@FELCONTROL]";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				felControl.setuTipo(rs.getString("U_TIPO"));
				felControl.setuNumero(rs.getString("U_NUMERO"));
				felControl.setuFechaIn(rs.getDate("U_FECHAIN"));
				felControl.setuFechaPro(rs.getDate("U_FECHAPRO"));
				felControl.setuCertificado(rs.getString("U_CERTIFICADO"));
				felControl.setuEnviado(rs.getString("U_ENVIADO"));
				felControl.setuECodGene(rs.getString("U_E_CODGENE"));
				felControl.setuENumCont(rs.getString("U_E_NUMCONT"));
				felControl.setuESellRecep(rs.getString("U_E_SELLRECEP"));
				felControl.setuDocEntry(rs.getString("U_DOCENTRY"));
				felControl.setuEstado(rs.getString("U_ESTADO"));
				felControl.setuJson(rs.getString("U_JSON"));
				felControl.setuFirma(rs.getString("U_FIRMA"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return felControl;
	}
	
	public List<FelControl> findFelRecords() {
		List<FelControl> lista = new ArrayList<FelControl>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT U_TIPO, U_NUMERO, U_FECHAIN, U_FECHAPRO, U_CERTIFICADO, \r\n"
				+ "U_ENVIADO, U_E_CODGENE, U_E_NUMCONT, U_E_SELLRECEP, U_DOCENTRY, \r\n"
				+ "U_ESTADO, U_JSON, U_FIRMA, U_CARDCODE, U_TIPODOCANUL, U_CONTINGENCIA\r\n"
				+ "FROM [@FELCONTROL] \r\n"
				+ "WHERE U_ESTADO = 'I' \r\n"
				+ "AND CAST(U_FECHAIN AS DATE) = CAST(GETDATE() AS DATE)\r\n"
				+ "AND U_CONTINGENCIA IS NULL\r\n"
				+ "UNION ALL\r\n"
				+ "SELECT A.U_TIPO, A.U_NUMERO, A.U_FECHAIN, A.U_FECHAPRO, A.U_CERTIFICADO, \r\n"
				+ "A.U_ENVIADO, A.U_E_CODGENE, A.U_E_NUMCONT, A.U_E_SELLRECEP, A.U_DOCENTRY, \r\n"
				+ "A.U_ESTADO, A.U_JSON, A.U_FIRMA, A.U_CARDCODE, A.U_TIPODOCANUL, A.U_CONTINGENCIA\r\n"
				+ "FROM [@FELCONTROL] A\r\n"
				+ "INNER JOIN [@FE_CONTINGENCIA] B ON A.U_NUMCONTINGENCIA = B.Code\r\n"
				+ "WHERE A.U_FECHAIN BETWEEN B.U_fechaIni AND B.U_fechaFin\r\n"
				+ "AND A.U_ESTADO = 'I' \r\n"
				+ "AND A.U_CONTINGENCIA = 'I'\r\n"
				+ "AND CAST(A.U_FECHAIN AS DATE) <> CAST(GETDATE() AS DATE)\r\n"
				+ "AND B.U_ESTADO = 'A'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				FelControl felControl = new FelControl();
				felControl.setuTipo(rs.getString("U_TIPO"));
				felControl.setuNumero(rs.getString("U_NUMERO"));
				felControl.setuFechaIn(rs.getDate("U_FECHAIN"));
				felControl.setuFechaPro(rs.getDate("U_FECHAPRO"));
				felControl.setuCertificado(rs.getString("U_CERTIFICADO"));
				felControl.setuEnviado(rs.getString("U_ENVIADO"));
				felControl.setuECodGene(rs.getString("U_E_CODGENE"));
				felControl.setuENumCont(rs.getString("U_E_NUMCONT"));
				felControl.setuESellRecep(rs.getString("U_E_SELLRECEP"));
				felControl.setuDocEntry(rs.getString("U_DOCENTRY"));
				felControl.setuEstado(rs.getString("U_ESTADO"));
				felControl.setuJson(rs.getString("U_JSON"));
				felControl.setuFirma(rs.getString("U_FIRMA"));
				felControl.setCardCode(rs.getString("U_CARDCODE"));
				felControl.setuTipoDocAnul(rs.getString("U_TIPODOCANUL"));
				felControl.setuContingencia(rs.getString("U_CONTINGENCIA"));
				
				lista.add(felControl);
			}
		} catch (Exception e) {
			lista = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public FelControl getFELByDocEntry(String docentry) {
		FelControl felControl = new FelControl();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		ResultSet rs = null;
		String query = "SELECT U_TIPO, U_NUMERO, U_FECHAIN, U_FECHAPRO, U_CERTIFICADO, \n"
				+ "U_ENVIADO, U_E_CODGENE, U_E_NUMCONT, U_E_SELLRECEP, U_DOCENTRY, \n"
				+ "U_ESTADO, U_JSON, U_JSON_ENVIADO, U_FIRMA, U_CARDCODE, U_TIPODOCANUL, U_CONTINGENCIA\n"
				+ "FROM [@FELCONTROL] \n"
				+ "WHERE U_DOCENTRY = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, docentry);
			rs = ps.executeQuery();
			if (rs.next()) {
				felControl.setuTipo(rs.getString("U_TIPO"));
				felControl.setuNumero(rs.getString("U_NUMERO"));
				felControl.setuFechaIn(rs.getDate("U_FECHAIN"));
				felControl.setuFechaPro(rs.getDate("U_FECHAPRO"));
				felControl.setuCertificado(rs.getString("U_CERTIFICADO"));
				felControl.setuEnviado(rs.getString("U_ENVIADO"));
				felControl.setuECodGene(rs.getString("U_E_CODGENE"));
				felControl.setuENumCont(rs.getString("U_E_NUMCONT"));
				felControl.setuESellRecep(rs.getString("U_E_SELLRECEP"));
				felControl.setuDocEntry(rs.getString("U_DOCENTRY"));
				felControl.setuEstado(rs.getString("U_ESTADO"));
				felControl.setuJson(rs.getString("U_JSON"));
				felControl.setuFirma(rs.getString("U_FIRMA"));
				felControl.setCardCode(rs.getString("U_CARDCODE"));
				felControl.setuTipoDocAnul(rs.getString("U_TIPODOCANUL"));
				felControl.setuContingencia(rs.getString("U_CONTINGENCIA"));
				felControl.setuJsonEnviado(rs.getString("U_JSON_ENVIADO"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return felControl;
	}
	
	public void cambiarEstado(String estado, String docEntry) throws SQLException {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		String query = "UPDATE [@FELCONTROL]\n"
				+ "SET U_ESTADO = ?\n"
				+ "WHERE U_DOCENTRY = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, estado);
			ps.setString(2, docEntry);

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
	
	public List<FelControl> listDteVinculadosContingencia(int numContingencia) {
		List<FelControl> lista = new ArrayList<FelControl>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		ResultSet rs = null;
		String query = "SELECT ROW_NUMBER() OVER (Order by A.code) as NUM_ITEM, A.U_TIPO, A.U_E_CODGENE, A.U_DOCENTRY \r\n"
				+ "FROM [@FELCONTROL] A\r\n"
				+ "INNER JOIN [@FE_CONTINGENCIA] B ON A.U_NUMCONTINGENCIA = B.Code\r\n"
				+ "WHERE A.U_FECHAIN BETWEEN B.U_fechaIni AND B.U_fechaFin\r\n"
				+ "AND A.U_ESTADO = 'I' \r\n"
				+ "AND A.U_CONTINGENCIA = 'I'\r\n"
				+ "AND CAST(A.U_FECHAIN AS DATE) <> CAST(GETDATE() AS DATE)\r\n"
				+ "AND A.U_NUMCONTINGENCIA = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, numContingencia);
			rs = ps.executeQuery();
			while (rs.next()) {
				FelControl detalleDTE = new FelControl();
				detalleDTE.setNoItem(rs.getInt("NUM_ITEM"));
				detalleDTE.setuECodGene(rs.getString("U_E_CODGENE"));
				detalleDTE.setuTipo(rs.getString("U_TIPO"));
				detalleDTE.setuDocEntry(rs.getString("U_DOCENTRY"));
				
				lista.add(detalleDTE);
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
	
	public List<FelControl> findFelRecordsContingencia(int numContingencia) {
		List<FelControl> lista = new ArrayList<FelControl>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		ResultSet rs = null;
		String query = "SELECT A.U_TIPO, A.U_NUMERO, A.U_FECHAIN, A.U_FECHAPRO, A.U_CERTIFICADO, \r\n"
				+ "A.U_ENVIADO, A.U_E_CODGENE, A.U_E_NUMCONT, A.U_E_SELLRECEP, A.U_DOCENTRY, \r\n"
				+ "A.U_ESTADO, A.U_JSON, A.U_FIRMA, A.U_CARDCODE, A.U_TIPODOCANUL, A.U_CONTINGENCIA,\r\n"
				+ "A.U_NUMCONTINGENCIA\r\n"
				+ "FROM [@FELCONTROL] A\r\n"
				+ "INNER JOIN [@FE_CONTINGENCIA] B ON A.U_NUMCONTINGENCIA = B.Code\r\n"
				+ "WHERE A.U_FECHAIN BETWEEN B.U_fechaIni AND B.U_fechaFin\r\n"
				+ "AND A.U_ESTADO = 'I' \r\n"
				+ "AND A.U_CONTINGENCIA = 'I'\r\n"
				+ "AND CAST(A.U_FECHAIN AS DATE) <> CAST(GETDATE() AS DATE)\r\n"
				+ "AND A.U_NUMCONTINGENCIA = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, numContingencia);
			rs = ps.executeQuery();
			while (rs.next()) {
				FelControl felControl = new FelControl();
				felControl.setuTipo(rs.getString("U_TIPO"));
				felControl.setuNumero(rs.getString("U_NUMERO"));
				felControl.setuFechaIn(rs.getDate("U_FECHAIN"));
				felControl.setuFechaPro(rs.getDate("U_FECHAPRO"));
				felControl.setuCertificado(rs.getString("U_CERTIFICADO"));
				felControl.setuEnviado(rs.getString("U_ENVIADO"));
				felControl.setuECodGene(rs.getString("U_E_CODGENE"));
				felControl.setuENumCont(rs.getString("U_E_NUMCONT"));
				felControl.setuESellRecep(rs.getString("U_E_SELLRECEP"));
				felControl.setuDocEntry(rs.getString("U_DOCENTRY"));
				felControl.setuEstado(rs.getString("U_ESTADO"));
				felControl.setuJson(rs.getString("U_JSON"));
				felControl.setuFirma(rs.getString("U_FIRMA"));
				felControl.setCardCode(rs.getString("U_CARDCODE"));
				felControl.setuTipoDocAnul(rs.getString("U_TIPODOCANUL"));
				felControl.setuContingencia(rs.getString("U_CONTINGENCIA"));
				felControl.setuNumContingencia(rs.getString("U_NUMCONTINGENCIA"));
				
				lista.add(felControl);
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
	
	public void editConGeneracionContingencia(FelControl felControl) throws SQLException {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		String query = "UPDATE [@FELCONTROL]\n"
				+ "SET U_E_CODGENE = ?\n"
				+ "WHERE U_DOCENTRY = ?";
		
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, felControl.getuECodGene());
			ps.setString(2, felControl.getuDocEntry());

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
	
	public void editarEnvioEmail(Object entity) throws SQLException {
		FelControl felControl = (FelControl) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		String query = "UPDATE [@FELCONTROL]\n"
				+ "SET U_ENVIADO = ?\n"
				+ "WHERE U_DOCENTRY = ?";
		
		try {
			
			ps = conn.prepareStatement(query);
			ps.setString(1, felControl.getuEnviado());
			ps.setString(2, felControl.getuDocEntry());

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
}
