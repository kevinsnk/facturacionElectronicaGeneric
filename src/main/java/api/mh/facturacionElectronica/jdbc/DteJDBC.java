package api.mh.facturacionElectronica.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import api.mh.facturacionElectronica.ex.model.DatosEmisor;
import api.mh.facturacionElectronica.ex.model.DatosReceptor;
import api.mh.facturacionElectronica.ex.model.DetalleDTE;
import api.mh.facturacionElectronica.ex.model.DireccionDTE;
import api.mh.facturacionElectronica.ex.model.EncabezadoDTE;
import api.mh.facturacionElectronica.ex.model.FelControl;
import api.mh.facturacionElectronica.ex.model.TributosDTE;
import api.mh.facturacionElectronica.ex.model.DocumentoRelacionado;
import api.mh.facturacionElectronica.util.SqlConn;

public class DteJDBC extends AbstractJDBC{

	@Override
	public void save(Object entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(Object entity) {

	}

	@Override
	public void delete(Object entity) {
		// TODO Auto-generated method stub
		
	}

	public void listAll() {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		try {
			st = conn.createStatement();
	        rs = st.executeQuery("SELECT 'hello' as PRUEBA");
	        while(rs.next()) {
	        	System.out.println(rs.getString("PRUEBA"));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
	        try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        System.out.println("Connection Closed....");
		}
	}
	
	public DatosEmisor getEmisorDTE() {
		DatosEmisor emisorDTE = new DatosEmisor();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT REPLACE(FE.U_NIT, '-','') AS U_NIT, REPLACE(FE.U_NRC, '-','') AS U_NRC, FE.U_NOMBRE, \n"
				+ "FE.U_ACTECON, FAE.U_DESC_ACTEC, FE.U_TIPOESTABLECIMIENTO, FE.U_DEPARTAMENTO,\n"
				+ "FE.U_MUNICIPIO, FE.U_DIRECCION, FE.U_TELEFONO, FE.U_CORREO\n"
				+ "FROM [@FE_EMISOR] FE, [@FE_ACT_ECON] FAE\n"
				+ "WHERE FE.U_ACTECON = FAE.U_COD_AEC";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				DireccionDTE direccionDTE = new DireccionDTE();
				emisorDTE.setNit(rs.getString("U_NIT"));
				emisorDTE.setNrc(rs.getString("U_NRC"));
				emisorDTE.setNombre(rs.getString("U_NOMBRE"));
				emisorDTE.setCodActividad(rs.getString("U_ACTECON"));
				emisorDTE.setDescActividad(rs.getString("U_DESC_ACTEC"));
				emisorDTE.setTipoEstablecimiento(rs.getString("U_TIPOESTABLECIMIENTO"));
				direccionDTE.setDepartamento(rs.getString("U_DEPARTAMENTO"));
				direccionDTE.setMunicipio(rs.getString("U_MUNICIPIO"));
				direccionDTE.setComplemento(rs.getString("U_DIRECCION"));
				emisorDTE.setDireccion(direccionDTE);
				emisorDTE.setTelefono(rs.getString("U_TELEFONO"));
				emisorDTE.setCorreo(rs.getString("U_CORREO"));;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emisorDTE;
	}
	
	public DatosReceptor getReceptorDTE(String codReceptor) {
		DatosReceptor receptorDTE = new DatosReceptor();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT REPLACE(O.U_NIT, '-', '') AS U_NIT, REPLACE(O.U_NRC, '-', '') AS U_NRC,\r\n"
				+ "O.CardName, FAE.u_cod_aec, FAE.U_DESC_ACTEC,\r\n"
				+ "RIGHT('00'+ISNULL(O.U_FE_DEPARTAMENTO,''),2) AS U_FE_DEPARTAMENTO, RIGHT('00'+ISNULL(O.U_FE_MUNICIPIO,''),2) AS U_FE_MUNICIPIO,\r\n"
				+ "O.U_FE_PAIS AS U_FE_PAIS, FP.Name AS NOMBRE_PAIS,\r\n"
				+ "O.ADDRESS, O.U_FE_CORREO, O.U_tipoPersona,\r\n"
				+ "REPLACE(U_numdocumento, '-', '') as NUM_DOCUMENTO, u_documento as TIPO_DOCU ,REPLACE(phone1, '-', '') as TELEFONO \r\n"
				+ "FROM OCRD O \r\n"
				+ "LEFT JOIN [@FE_ACT_ECON] FAE ON  O.U_FE_ACT_ECON = FAE.code\r\n"
				+ "LEFT JOIN [@fe_pais] FP ON FP.Code = O.U_FE_PAIS\r\n"
				+ "WHERE CardCode = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, codReceptor);
			rs = ps.executeQuery();
			if (rs.next()) {
				DireccionDTE direccionDTE = new DireccionDTE();
				receptorDTE.setNit(rs.getString("U_NIT"));
				receptorDTE.setNrc(rs.getString("U_NRC"));
				receptorDTE.setNombre(rs.getString("CardName"));
				receptorDTE.setCodActividad(rs.getString("u_cod_aec"));
				receptorDTE.setDescActividad(rs.getString("U_DESC_ACTEC"));
				direccionDTE.setDepartamento(rs.getString("U_FE_DEPARTAMENTO"));
				direccionDTE.setMunicipio(rs.getString("U_FE_MUNICIPIO"));
				direccionDTE.setComplemento(rs.getString("ADDRESS"));
				receptorDTE.setDireccion(direccionDTE);
				receptorDTE.setTelefono(rs.getString("TELEFONO"));
				receptorDTE.setCorreo(rs.getString("U_FE_CORREO"));
				receptorDTE.setTipoDocumento(rs.getString("TIPO_DOCU"));
				receptorDTE.setNumDocumento(rs.getString("NUM_DOCUMENTO"));
				receptorDTE.setCodPais(rs.getString("U_FE_PAIS"));
				receptorDTE.setNombrePais(rs.getString("NOMBRE_PAIS"));
				receptorDTE.setTipoPersona(rs.getInt("U_tipoPersona"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return receptorDTE;
	}
	
	public EncabezadoDTE getEncabezadoDTE(String numDocumento) {
		EncabezadoDTE encabezadoDTE = new EncabezadoDTE();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT T0.DocNum as numeroDocumento, T0.CardCode as codCliente,\r\n"
				+ "format(T0.DocDate,'yyyy-MM-dd') as fechaFacturacion, DATEPART(HOUR, T0.DocDate) as horaFacturacion,\r\n"
				+ "0.00 totalNoSuj,     0.00 totalExenta,    \r\n"
				+ "CAST((T0.docTotal-T0.VatSum)+T0.wtSum AS NUMERIC(19,2)) totalGravada,     \r\n"
				+ "CAST((T0.docTotal-T0.VatSum)+T0.wtSum AS NUMERIC(19,2)) subTotalVentas,     \r\n"
				+ "0.00 descuNoSuj,     \r\n"
				+ "0.00 descuExenta,    \r\n"
				+ "CAST(T0.DiscSum AS NUMERIC(19,2)) descuGravada,    \r\n"
				+ "CAST(T0.DiscPrcnt AS NUMERIC(19,2)) porcentajeDescuento,    \r\n"
				+ "CAST(T0.DiscSum AS NUMERIC(19,2)) totalDescu,     \r\n"
				+ "CASE WHEN T0.U_FacSerie='EXP' THEN '' ELSE '20' END tributo,    \r\n"
				+ "CAST((T0.docTotal-T0.VatSum)+T0.wtSum AS NUMERIC(19,2)) subTotal,   \r\n"
				+ "CAST(T0.wtSum AS NUMERIC(19,2)) ivaRete1,    \r\n"
				+ "0.00 reteRenta,    \r\n"
				+ "CAST(T0.docTotal+T0.wtSum AS NUMERIC(19,2)) montoTotalOperacion,   \r\n"
				+ "0.00 totalNoGravado,    \r\n"
				+ "CAST(T0.docTotal AS NUMERIC(19,2)) totalPagar,     \r\n"
				+ "dbo.[FN_CANTIDADALETRAS](T0.docTotal) totalLetras,    \r\n"
				+ "0.00 saldoFavor,    \r\n"
				+ "CASE WHEN (SELECT K0.PymntGroup FROM OCTG K0 WHERE K0.groupNum=T0.groupNum) LIKE '%CRED%' THEN 2 ELSE 1 END condicionOperacion,     \r\n"
				+ "CAST(ISNULL(T0.VatSum,0.00) AS NUMERIC(19,2)) iva,\r\n"
				+ "CAST(T0.DiscSum AS NUMERIC(19,2)) descuento,\r\n"
				+ "T0.U_flete,     \r\n"
				+ "T0.U_Seguro,     \r\n"
				+ "RIGHT(T0.U_E_INCOTERMS,2) codINCOTERMS,     \r\n"
				+ "ISNULL((SELECT K0.Name FROM [@FE_INCOTERMS] K0 WHERE K0.Code=T0.U_E_INCOTERMS),'') descINCOTERMS, \r\n"
				+ "CASE WHEN T0.DocType = 'I' THEN 1 WHEN T0.DocType = 'S' THEN 2 END tipoItem \r\n"
				+ "FROM OINV T0 WHERE T0.docentry= ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, numDocumento);
			rs = ps.executeQuery();
			if (rs.next()) {
				encabezadoDTE.setNumeroControl(rs.getString("numeroDocumento"));
				encabezadoDTE.setFecEmi(rs.getString("fechaFacturacion"));
				encabezadoDTE.setHorEmi(rs.getString("horaFacturacion"));
				encabezadoDTE.setCodReceptor(rs.getString("codCliente"));
				encabezadoDTE.setTotalNoSuj(rs.getBigDecimal("totalNoSuj"));
				encabezadoDTE.setTotalExenta(rs.getBigDecimal("totalExenta"));
				encabezadoDTE.setTotalGravada(rs.getBigDecimal("totalGravada"));
				encabezadoDTE.setSubTotalVentas(rs.getBigDecimal("subTotalVentas"));
				encabezadoDTE.setDescuNoSuj(rs.getBigDecimal("descuNoSuj"));
				encabezadoDTE.setDescuExenta(rs.getBigDecimal("descuExenta"));
				encabezadoDTE.setDescuGravada(rs.getBigDecimal("descuGravada"));
				encabezadoDTE.setPorcentajeDescuento(rs.getBigDecimal("porcentajeDescuento"));
				encabezadoDTE.setTotalDescu(rs.getBigDecimal("totalDescu"));
				encabezadoDTE.setSubTotal(rs.getBigDecimal("subTotal"));
				//encabezadoDTE.setIvaPerci1(rs.getBigDecimal("iva"));
				encabezadoDTE.setIva(rs.getBigDecimal("iva"));
				encabezadoDTE.setIvaPerci1(BigDecimal.ZERO);
				encabezadoDTE.setIvaRete1(rs.getBigDecimal("ivaRete1"));
				encabezadoDTE.setReteRenta(rs.getBigDecimal("reteRenta"));
				encabezadoDTE.setMontoTotalOperacion(rs.getBigDecimal("montoTotalOperacion"));
				encabezadoDTE.setTotalNoGravado(rs.getBigDecimal("totalNoGravado"));
				encabezadoDTE.setTotalPagar(rs.getBigDecimal("totalPagar"));
				encabezadoDTE.setTotalLetras(rs.getString("totalLetras"));
				encabezadoDTE.setSaldoFavor(rs.getBigDecimal("saldoFavor"));
				encabezadoDTE.setCondicionOperacion(rs.getBigDecimal("condicionOperacion"));
				encabezadoDTE.setDescuento(rs.getBigDecimal("descuento"));
				encabezadoDTE.setFlete(rs.getBigDecimal("U_flete"));
				encabezadoDTE.setSeguro(rs.getBigDecimal("U_Seguro"));
				encabezadoDTE.setCodIncoterms(rs.getString("codINCOTERMS"));
				encabezadoDTE.setDescIncoterms(rs.getString("descINCOTERMS"));
				encabezadoDTE.setTipoItem(rs.getInt("tipoItem"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return encabezadoDTE;
	}
	
	public ArrayList<DetalleDTE> getDetalleDTE(String numDocumento) {
		ArrayList<DetalleDTE> listaDocumentos = new ArrayList<DetalleDTE>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT  CAST(ROW_NUMBER() OVER(ORDER BY T0.lineNum ASC) AS INT) numItem,    \n"
				+ "CASE WHEN T1.DocType = 'I' THEN 1 WHEN T1.DocType = 'S' THEN 2 END tipoItem,      \n"
				+ "CASE WHEN T0.Quantity=0 THEN 1 ELSE T0.Quantity END cantidad,     \n"
				+ " ISNULL(T0.ItemCode,'') codigo,  \n"
				+ "ISNULL((SELECT CAST(K0.U_FE_UMEDIDA AS INT) FROM OITM K0 WHERE K0.ItemCode=T0.ItemCode),59) unidadMedida,  \n"
				+ "T0.Dscription description,    \n"
				+ "CAST(T0.Price AS NUMERIC(19, 6)) precioUni,    \n"
				+ "0.00 montoDescu,    \n"
				+ "CASE WHEN T0.TaxCode = 'IVAEXE' THEN CAST(T0.lineTotal AS NUMERIC(19, 2)) ELSE 0.00 END ventaExenta,\n"
				+ "CASE WHEN T0.TaxCode = 'IVACRF' OR T0.TaxCode = 'IVACOF' OR T0.TaxCode = 'IVAEXP' THEN CAST(T0.lineTotal AS NUMERIC(19, 2)) ELSE 0.00 END ventaGravada,\n"
				+ "CASE WHEN T0.TaxCode = 'NSJ' THEN CAST(T0.lineTotal AS NUMERIC(19, 2)) ELSE 0.00 END ventaNoSuj, \n"
				+ "CAST(ISNULL(T0.VatSum,0.00) AS NUMERIC(19,2)) iva,\n"
				+ "T2.U_Numdocumento, T1.U_NumUnic, T0.TaxCode\n"
				+ "FROM INV1 T0 JOIN OINV T1 ON T0.docEntry = T1.docEntry \n"
				+ "INNER JOIN OCRD T2 ON T1.CARDCODE = T2.CARDCODE\n"
				+ "WHERE T1.DocEntry = ? ORDER BY T0.LineNum ASC";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, numDocumento);
			rs = ps.executeQuery();
			while (rs.next()) {
				DetalleDTE cuerpoDocumentoDTE = new DetalleDTE();
				
				//Se quita las comillas dobles a las descripciones
				String descripcion = rs.getString("description");
				descripcion = descripcion.replace("\"","");
				
				cuerpoDocumentoDTE.setDescripcion(descripcion);
				cuerpoDocumentoDTE.setNumItem(rs.getInt("numItem"));
				cuerpoDocumentoDTE.setTipoItem(rs.getInt("tipoItem"));
				cuerpoDocumentoDTE.setUniMedida(rs.getInt("unidadMedida"));
				cuerpoDocumentoDTE.setCantidad(rs.getInt("cantidad"));
				cuerpoDocumentoDTE.setMontoDescu(rs.getBigDecimal("montoDescu"));
				cuerpoDocumentoDTE.setVentaNoSuj(rs.getBigDecimal("ventaNoSuj"));
				cuerpoDocumentoDTE.setVentaExenta(rs.getBigDecimal("ventaExenta"));
				cuerpoDocumentoDTE.setVentaGravada(rs.getBigDecimal("ventaGravada"));
				cuerpoDocumentoDTE.setNumeroDocumento(rs.getString("U_Numdocumento"));
//				cuerpoDocumentoDTE.setNoGravado(rs.getBigDecimal(""));
//				cuerpoDocumentoDTE.setPsv(rs.getBigDecimal(""));
				cuerpoDocumentoDTE.setNoGravado(BigDecimal.ZERO);
				cuerpoDocumentoDTE.setPsv(BigDecimal.ZERO);
				cuerpoDocumentoDTE.setPrecioUni(rs.getBigDecimal("precioUni"));
				if(!rs.getString("codigo").equals("")) {
					cuerpoDocumentoDTE.setCodigo(rs.getString("codigo"));
				}
				cuerpoDocumentoDTE.setIvaItem(rs.getBigDecimal("iva"));
				
				ArrayList<String> listaTributos = new ArrayList<String>();
				listaTributos.add("20");
				cuerpoDocumentoDTE.setTributos(listaTributos);
				cuerpoDocumentoDTE.setTaxCode(rs.getString("TaxCode"));
				
				listaDocumentos.add(cuerpoDocumentoDTE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaDocumentos;
	}
	
	public ArrayList<DocumentoRelacionado> getDocumentosRelacionados(String numDocumento) {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Date fecha = null;
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat formatearFecha = new SimpleDateFormat(pattern);
		ArrayList<DocumentoRelacionado> listaDocumentos = new ArrayList<DocumentoRelacionado>();
		String query = "SELECT  TOP 1   CASE WHEN T0.U_FacSerie IS NULL THEN '' \n"
				+ "ELSE(SELECT RIGHT('00'+ISNULL(K0.U_ID_HACIENDA,''),2) FROM [@TIPOSDOCUMENTO] K0 \n"
				+ "WHERE K0.code = (SELECT J0.U_FacSerie FROM OINV J0 WHERE J0.DocNum = T1.BaseRef AND DocSubType!='DN')) END tipoDoc, \n"
				+ "ISNULL((SELECT U_E_CODGENE FROM OINV K0 WHERE K0.DocNum = T1.BaseRef), (SELECT K0.U_num_doc FROM OINV K0 WHERE K0.DocNum = T1.BaseRef)) docNum,   \n"
				+ "ISNULL(CAST((SELECT CAST(CAST(K0.U_E_FECHORA AS VARCHAR) AS DATE) FROM OINV K0 WHERE K0.DocNum = T1.BaseRef) AS VARCHAR),\n"
				+ "CAST((SELECT CAST(CAST(K0.docDate AS VARCHAR) AS DATE) FROM OINV K0 WHERE K0.DocNum = T1.BaseRef) AS VARCHAR)) fechaEmision,   \n"
				+ "CASE WHEN (SELECT U_E_CODGENE FROM OINV K0 WHERE K0.DocNum = T1.BaseRef) IS NULL THEN 1 ELSE 2 END tipoGeneracion \n"
				+ "FROM ORIN T0 JOIN RIN1 T1 ON T0.DocEntry = T1.DocEntry WHERE T0.docEntry = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, numDocumento);
			rs = ps.executeQuery();
			while (rs.next()) {
				DocumentoRelacionado documentoRelacionado = new DocumentoRelacionado();
				documentoRelacionado.setTipoDocumento(rs.getString("tipoDoc")); 
				documentoRelacionado.setNumeroDocumento(rs.getString("docNum"));
				fecha = rs.getDate("fechaEmision");
				documentoRelacionado.setFechaEmision(formatearFecha.format(fecha));
				documentoRelacionado.setTipoGeneracion(rs.getInt("tipoGeneracion"));
				
				listaDocumentos.add(documentoRelacionado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaDocumentos;
	}
	
	public EncabezadoDTE getEncabezadoNC(String numDocumento) {
		EncabezadoDTE encabezadoDTE = new EncabezadoDTE();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT T0.DocNum as numeroDocumento, T0.CardCode as codCliente,\r\n"
				+ "format(T0.DocDate,'yyyy-MM-dd') as fechaFacturacion, DATEPART(HOUR, T0.DocDate) as horaFacturacion,\r\n"
				+ "0.00 totalNoSuj,     0.00 totalExenta,    \r\n"
				+ "CAST((T0.docTotal-T0.VatSum)+T0.wtSum AS NUMERIC(19,2)) totalGravada,     \r\n"
				+ "CAST((T0.docTotal-T0.VatSum)+T0.wtSum AS NUMERIC(19,2)) subTotalVentas,     \r\n"
				+ "0.00 descuNoSuj,     \r\n"
				+ "0.00 descuExenta,    \r\n"
				+ "CAST(T0.DiscSum AS NUMERIC(19,2)) descuGravada,    \r\n"
				+ "CAST(T0.DiscPrcnt AS NUMERIC(19,2)) porcentajeDescuento,    \r\n"
				+ "CAST(T0.DiscSum AS NUMERIC(19,2)) totalDescu,     \r\n"
				+ "CASE WHEN T0.U_FacSerie='EXP' THEN '' ELSE '20' END tributo,    \r\n"
				+ "CAST((T0.docTotal-T0.VatSum)+T0.wtSum AS NUMERIC(19,2)) subTotal,   \r\n"
				+ "CAST(T0.wtSum AS NUMERIC(19,2)) ivaRete1,    \r\n"
				+ "0.00 reteRenta,    \r\n"
				+ "CAST(T0.docTotal AS NUMERIC(19,2)) montoTotalOperacion,   \r\n"
				+ "0.00 totalNoGravado,    \r\n"
				+ "CAST(T0.docTotal AS NUMERIC(19,2)) totalPagar,     \r\n"
				+ "dbo.[FN_CANTIDADALETRAS](T0.docTotal) totalLetras,    \r\n"
				+ "0.00 saldoFavor,    \r\n"
				+ "CASE WHEN (SELECT K0.PymntGroup FROM OCTG K0 WHERE K0.groupNum=T0.groupNum) LIKE '%CRED%' THEN 2 ELSE 1 END condicionOperacion,     \r\n"
				+ "CAST(ISNULL(T0.VatSum,0.00) AS NUMERIC(19,2)) iva,\r\n"
				+ "CAST(T0.DiscSum AS NUMERIC(19,2)) descuento,\r\n"
				+ "T0.U_flete,     \r\n"
				+ "T0.U_Seguro,     \r\n"
				+ "RIGHT(T0.U_E_INCOTERMS,2) codINCOTERMS,     \r\n"
				+ "ISNULL((SELECT K0.Name FROM [@FE_INCOTERMS] K0 WHERE K0.Code=T0.U_E_INCOTERMS),'') descINCOTERMS \r\n"
				+ "FROM ORIN T0 WHERE T0.docentry= ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, numDocumento);
			rs = ps.executeQuery();
			if (rs.next()) {
				encabezadoDTE.setNumeroControl(rs.getString("numeroDocumento"));
				encabezadoDTE.setFecEmi(rs.getString("fechaFacturacion"));
				encabezadoDTE.setHorEmi(rs.getString("horaFacturacion"));
				encabezadoDTE.setCodReceptor(rs.getString("codCliente"));
				encabezadoDTE.setTotalNoSuj(rs.getBigDecimal("totalNoSuj"));
				encabezadoDTE.setTotalExenta(rs.getBigDecimal("totalExenta"));
				encabezadoDTE.setTotalGravada(rs.getBigDecimal("totalGravada"));
				encabezadoDTE.setSubTotalVentas(rs.getBigDecimal("subTotalVentas"));
				encabezadoDTE.setDescuNoSuj(rs.getBigDecimal("descuNoSuj"));
				encabezadoDTE.setDescuExenta(rs.getBigDecimal("descuExenta"));
				encabezadoDTE.setDescuGravada(rs.getBigDecimal("descuGravada"));
				encabezadoDTE.setPorcentajeDescuento(rs.getBigDecimal("porcentajeDescuento"));
				encabezadoDTE.setTotalDescu(rs.getBigDecimal("totalDescu"));
				encabezadoDTE.setSubTotal(rs.getBigDecimal("subTotal"));
				//encabezadoDTE.setIvaPerci1(rs.getBigDecimal("iva"));
				encabezadoDTE.setIva(rs.getBigDecimal("iva"));
				encabezadoDTE.setIvaPerci1(BigDecimal.ZERO);
				encabezadoDTE.setIvaRete1(rs.getBigDecimal("ivaRete1"));
				encabezadoDTE.setReteRenta(rs.getBigDecimal("reteRenta"));
				encabezadoDTE.setMontoTotalOperacion(rs.getBigDecimal("montoTotalOperacion"));
				encabezadoDTE.setTotalNoGravado(rs.getBigDecimal("totalNoGravado"));
				encabezadoDTE.setTotalPagar(rs.getBigDecimal("totalPagar"));
				encabezadoDTE.setTotalLetras(rs.getString("totalLetras"));
				encabezadoDTE.setSaldoFavor(rs.getBigDecimal("saldoFavor"));
				encabezadoDTE.setCondicionOperacion(rs.getBigDecimal("condicionOperacion"));
				encabezadoDTE.setDescuento(rs.getBigDecimal("descuento"));
				encabezadoDTE.setFlete(rs.getBigDecimal("U_flete"));
				encabezadoDTE.setSeguro(rs.getBigDecimal("U_Seguro"));
				encabezadoDTE.setCodIncoterms(rs.getString("codINCOTERMS"));
				encabezadoDTE.setDescIncoterms(rs.getString("descINCOTERMS"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return encabezadoDTE;
	}
	
	public ArrayList<DetalleDTE> getDetalleNC(String numDocumento) {
		ArrayList<DetalleDTE> listaDocumentos = new ArrayList<DetalleDTE>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT  CAST(ROW_NUMBER() OVER(ORDER BY T0.lineNum ASC) AS INT) numItem,    \n"
				+ "    CASE WHEN T1.DocType = 'I' THEN 1 WHEN T1.DocType = 'S' THEN 2 END tipoItem,      \n"
				+ "    CASE WHEN T0.Quantity=0 THEN 1 ELSE T0.Quantity END cantidad,     \n"
				+ "     ISNULL(T0.ItemCode,'') codigo,  \n"
				+ "      ISNULL((SELECT CAST(K0.U_FE_UMEDIDA AS INT) FROM OITM K0 WHERE K0.ItemCode=T0.ItemCode),59) unidadMedida,    \n"
				+ "      T0.Dscription description,    \n"
				+ "      CAST(T0.Price AS NUMERIC(19, 6)) precioUni,    \n"
				+ "      0.00 montoDescu,    \n"
				+ "      0.00 ventaNoSuj,    \n"
				+ "      0.00 ventaExenta,   \n"
				+ "		 CAST(ISNULL(T0.VatSum,0.00) AS NUMERIC(19,2)) iva,\r\n"
				+ "      CAST(T0.lineTotal AS NUMERIC(19, 2)) ventaGravada     \n"
				+ "      FROM RIN1 T0 JOIN ORIN T1 ON T0.docEntry = T1.docEntry \n"
				+ "      WHERE T1.DocEntry = ? ORDER BY T0.LineNum ASC";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, numDocumento);
			rs = ps.executeQuery();
			while (rs.next()) {
				DetalleDTE cuerpoDocumentoDTE = new DetalleDTE();
				
				//Se quita las comillas dobles a las descripciones
				String descripcion = rs.getString("description");
				descripcion = descripcion.replace("\"","");
				
				cuerpoDocumentoDTE.setDescripcion(descripcion);
				cuerpoDocumentoDTE.setNumItem(rs.getInt("numItem"));
				cuerpoDocumentoDTE.setTipoItem(rs.getInt("tipoItem"));
				cuerpoDocumentoDTE.setUniMedida(rs.getInt("unidadMedida"));
				cuerpoDocumentoDTE.setCantidad(rs.getInt("cantidad"));
				cuerpoDocumentoDTE.setMontoDescu(rs.getBigDecimal("montoDescu"));
				cuerpoDocumentoDTE.setVentaNoSuj(rs.getBigDecimal("ventaNoSuj"));
				cuerpoDocumentoDTE.setVentaExenta(rs.getBigDecimal("ventaExenta"));
				cuerpoDocumentoDTE.setVentaGravada(rs.getBigDecimal("ventaGravada"));
//				cuerpoDocumentoDTE.setNoGravado(rs.getBigDecimal(""));
//				cuerpoDocumentoDTE.setPsv(rs.getBigDecimal(""));
				cuerpoDocumentoDTE.setNoGravado(BigDecimal.ZERO);
				cuerpoDocumentoDTE.setPsv(BigDecimal.ZERO);
				cuerpoDocumentoDTE.setPrecioUni(rs.getBigDecimal("precioUni"));
				if(!rs.getString("codigo").equals("")) {
					cuerpoDocumentoDTE.setCodigo(rs.getString("codigo"));
				}
				cuerpoDocumentoDTE.setIvaItem(rs.getBigDecimal("iva"));
				
				ArrayList<String> listaTributos = new ArrayList<String>();
				listaTributos.add("20");
				cuerpoDocumentoDTE.setTributos(listaTributos);
				
				listaDocumentos.add(cuerpoDocumentoDTE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaDocumentos;
	}
	
	public EncabezadoDTE getEncabezadoNR(String numDocumento) {
		EncabezadoDTE encabezadoDTE = new EncabezadoDTE();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT T0.DocNum as numeroDocumento, T0.CardCode as codCliente,\r\n"
				+ "format(T0.DocDate,'yyyy-MM-dd') as fechaFacturacion, DATEPART(HOUR, T0.DocDate) as horaFacturacion,\r\n"
				+ "0.00 totalNoSuj,     0.00 totalExenta,    \r\n"
				+ "CAST((T0.docTotal-T0.VatSum)+T0.wtSum AS NUMERIC(19,2)) totalGravada,     \r\n"
				+ "CAST((T0.docTotal-T0.VatSum)+T0.wtSum AS NUMERIC(19,2)) subTotalVentas,     \r\n"
				+ "0.00 descuNoSuj,     \r\n"
				+ "0.00 descuExenta,    \r\n"
				+ "CAST(T0.DiscSum AS NUMERIC(19,2)) descuGravada,    \r\n"
				+ "CAST(T0.DiscPrcnt AS NUMERIC(19,2)) porcentajeDescuento,    \r\n"
				+ "CAST(T0.DiscSum AS NUMERIC(19,2)) totalDescu,     \r\n"
				+ "CASE WHEN T0.U_FacSerie='EXP' THEN 'C3' ELSE '20' END tributo,    \r\n"
				+ "CAST((T0.docTotal-T0.VatSum)+T0.wtSum AS NUMERIC(19,2)) subTotal,   \r\n"
				+ "CAST(T0.wtSum AS NUMERIC(19,2)) ivaRete1,    \r\n"
				+ "0.00 reteRenta,    \r\n"
				+ "CAST(T0.docTotal AS NUMERIC(19,2)) montoTotalOperacion,   \r\n"
				+ "0.00 totalNoGravado,    \r\n"
				+ "CAST(T0.docTotal AS NUMERIC(19,2)) totalPagar,     \r\n"
				+ "dbo.[FN_CANTIDADALETRAS](T0.docTotal) totalLetras,    \r\n"
				+ "0.00 saldoFavor,    \r\n"
				+ "CASE WHEN (SELECT K0.PymntGroup FROM OCTG K0 WHERE K0.groupNum=T0.groupNum) LIKE '%CRED%' THEN 2 ELSE 1 END condicionOperacion,     \r\n"
				+ "CAST(ISNULL(T0.VatSum,0.00) AS NUMERIC(19,2)) iva,\r\n"
				+ "CAST(T0.DiscSum AS NUMERIC(19,2)) descuento,\r\n"
				+ "T0.U_flete,     \r\n"
				+ "T0.U_Seguro,     \r\n"
				+ "RIGHT(T0.U_E_INCOTERMS,2) codINCOTERMS,     \r\n"
				+ "ISNULL((SELECT K0.Name FROM [@FE_INCOTERMS] K0 WHERE K0.Code=T0.U_E_INCOTERMS),'') descINCOTERMS \r\n"
				+ "FROM ODLN T0 WHERE T0.docentry=?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, numDocumento);
			rs = ps.executeQuery();
			if (rs.next()) {
				encabezadoDTE.setNumeroControl(rs.getString("numeroDocumento"));
				encabezadoDTE.setFecEmi(rs.getString("fechaFacturacion"));
				encabezadoDTE.setHorEmi(rs.getString("horaFacturacion"));
				encabezadoDTE.setCodReceptor(rs.getString("codCliente"));
				encabezadoDTE.setTotalNoSuj(rs.getBigDecimal("totalNoSuj"));
				encabezadoDTE.setTotalExenta(rs.getBigDecimal("totalExenta"));
				encabezadoDTE.setTotalGravada(rs.getBigDecimal("totalGravada"));
				encabezadoDTE.setSubTotalVentas(rs.getBigDecimal("subTotalVentas"));
				encabezadoDTE.setDescuNoSuj(rs.getBigDecimal("descuNoSuj"));
				encabezadoDTE.setDescuExenta(rs.getBigDecimal("descuExenta"));
				encabezadoDTE.setDescuGravada(rs.getBigDecimal("descuGravada"));
				encabezadoDTE.setPorcentajeDescuento(rs.getBigDecimal("porcentajeDescuento"));
				encabezadoDTE.setTotalDescu(rs.getBigDecimal("totalDescu"));
				encabezadoDTE.setSubTotal(rs.getBigDecimal("subTotal"));
				//encabezadoDTE.setIvaPerci1(rs.getBigDecimal("iva"));
				encabezadoDTE.setIva(rs.getBigDecimal("iva"));
				encabezadoDTE.setIvaPerci1(BigDecimal.ZERO);
				encabezadoDTE.setIvaRete1(rs.getBigDecimal("ivaRete1"));
				encabezadoDTE.setReteRenta(rs.getBigDecimal("reteRenta"));
				encabezadoDTE.setMontoTotalOperacion(rs.getBigDecimal("montoTotalOperacion"));
				encabezadoDTE.setTotalNoGravado(rs.getBigDecimal("totalNoGravado"));
				encabezadoDTE.setTotalPagar(rs.getBigDecimal("totalPagar"));
				encabezadoDTE.setTotalLetras(rs.getString("totalLetras"));
				encabezadoDTE.setSaldoFavor(rs.getBigDecimal("saldoFavor"));
				encabezadoDTE.setCondicionOperacion(rs.getBigDecimal("condicionOperacion"));
				encabezadoDTE.setDescuento(rs.getBigDecimal("descuento"));
				encabezadoDTE.setFlete(rs.getBigDecimal("U_flete"));
				encabezadoDTE.setSeguro(rs.getBigDecimal("U_Seguro"));
				encabezadoDTE.setCodIncoterms(rs.getString("codINCOTERMS"));
				encabezadoDTE.setDescIncoterms(rs.getString("descINCOTERMS"));
				
				TributosDTE tributosDTE = new TributosDTE();
				ArrayList<TributosDTE> listaTributos = new ArrayList<TributosDTE>();
				tributosDTE.setCodigo(rs.getString("tributo"));
				if(tributosDTE.getCodigo().equals("20")) {
					tributosDTE.setDescripcion("Impuesto al Valor Agregado 13%");
					tributosDTE.setValor(encabezadoDTE.getIva());
				}else {
					tributosDTE.setDescripcion("Impuesto al Valor Agregado (exportaciones) 0%");
					tributosDTE.setValor(BigDecimal.ZERO);
				}
				encabezadoDTE.setTributos(listaTributos);
				
				listaTributos.add(tributosDTE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return encabezadoDTE;
	}
	
	public ArrayList<DetalleDTE> getDetalleNR(String numDocumento) {
		ArrayList<DetalleDTE> listaDocumentos = new ArrayList<DetalleDTE>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT  CAST(ROW_NUMBER() OVER(ORDER BY T0.lineNum ASC) AS INT) numItem,    \n"
				+ "    CASE WHEN T1.DocType = 'I' THEN 1 WHEN T1.DocType = 'S' THEN 2 END tipoItem,      \n"
				+ "    CASE WHEN T0.Quantity=0 THEN 1 ELSE T0.Quantity END cantidad,     \n"
				+ "     ISNULL(T0.ItemCode,'') codigo,  \n"
				+ "      ISNULL((SELECT CAST(K0.U_FE_UMEDIDA AS INT) FROM OITM K0 WHERE K0.ItemCode=T0.ItemCode),59) unidadMedida,    \n"
				+ "      T0.Dscription description,    \n"
				+ "      CAST(T0.Price AS NUMERIC(19, 6)) precioUni,    \n"
				+ "      0.00 montoDescu,    \n"
				+ "      0.00 ventaNoSuj,    \n"
				+ "      0.00 ventaExenta,   \n"
				+ "		 CAST(ISNULL(T0.VatSum,0.00) AS NUMERIC(19,2)) iva,\r\n"
				+ "      CAST(T0.lineTotal AS NUMERIC(19, 2)) ventaGravada     \n"
				+ "      FROM DLN1 T0 JOIN ODLN T1 ON T0.docEntry = T1.docEntry \n"
				+ "      WHERE T1.DocEntry = ? ORDER BY T0.LineNum ASC";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, numDocumento);
			rs = ps.executeQuery();
			while (rs.next()) {
				DetalleDTE cuerpoDocumentoDTE = new DetalleDTE();
				
				//Se quita las comillas dobles a las descripciones
				String descripcion = rs.getString("description");
				descripcion = descripcion.replace("\"","");
				
				cuerpoDocumentoDTE.setDescripcion(descripcion);
				cuerpoDocumentoDTE.setNumItem(rs.getInt("numItem"));
				cuerpoDocumentoDTE.setTipoItem(rs.getInt("tipoItem"));
				cuerpoDocumentoDTE.setUniMedida(rs.getInt("unidadMedida"));
				cuerpoDocumentoDTE.setCantidad(rs.getInt("cantidad"));
				cuerpoDocumentoDTE.setMontoDescu(rs.getBigDecimal("montoDescu"));
				cuerpoDocumentoDTE.setVentaNoSuj(rs.getBigDecimal("ventaNoSuj"));
				cuerpoDocumentoDTE.setVentaExenta(rs.getBigDecimal("ventaExenta"));
				cuerpoDocumentoDTE.setVentaGravada(rs.getBigDecimal("ventaGravada"));
//				cuerpoDocumentoDTE.setNoGravado(rs.getBigDecimal(""));
//				cuerpoDocumentoDTE.setPsv(rs.getBigDecimal(""));
				cuerpoDocumentoDTE.setNoGravado(BigDecimal.ZERO);
				cuerpoDocumentoDTE.setPsv(BigDecimal.ZERO);
				cuerpoDocumentoDTE.setPrecioUni(rs.getBigDecimal("precioUni"));
				if(!rs.getString("codigo").equals("")) {
					cuerpoDocumentoDTE.setCodigo(rs.getString("codigo"));
				}
				cuerpoDocumentoDTE.setIvaItem(rs.getBigDecimal("iva"));
				
				ArrayList<String> listaTributos = new ArrayList<String>();
				listaTributos.add("20");
				cuerpoDocumentoDTE.setTributos(listaTributos);
				
				listaDocumentos.add(cuerpoDocumentoDTE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaDocumentos;
	}
	
	public EncabezadoDTE getEncabezadoFSE(String numDocumento) {
		EncabezadoDTE encabezadoDTE = new EncabezadoDTE();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT T0.DocNum as numeroDocumento, T0.CardCode as codCliente,\r\n"
				+ "format(T0.DocDate,'yyyy-MM-dd') as fechaFacturacion, DATEPART(HOUR, T0.DocDate) as horaFacturacion,\r\n"
				+ "0.00 totalNoSuj,     0.00 totalExenta,    \r\n"
				+ "CAST((T0.docTotal-T0.VatSum)+T0.wtSum AS NUMERIC(19,2)) totalGravada,     \r\n"
				+ "CAST((T0.docTotal-T0.VatSum)+T0.wtSum AS NUMERIC(19,2)) subTotalVentas,     \r\n"
				+ "0.00 descuNoSuj,     \r\n"
				+ "0.00 descuExenta,    \r\n"
				+ "CAST(T0.DiscSum AS NUMERIC(19,2)) descuGravada,    \r\n"
				+ "CAST(T0.DiscPrcnt AS NUMERIC(19,2)) porcentajeDescuento,    \r\n"
				+ "CAST(T0.DiscSum AS NUMERIC(19,2)) totalDescu,     \r\n"
				+ "CASE WHEN T0.U_FacSerie='EXP' THEN '' ELSE '20' END tributo,    \r\n"
				+ "CAST((T0.docTotal-T0.VatSum)+T0.wtSum AS NUMERIC(19,2)) subTotal,   \r\n"
				+ "0.00 ivaRete1,    \r\n"
				+ "CAST(T0.wtSum AS NUMERIC(19,2)) reteRenta,    \r\n"
				+ "CAST(T0.docTotal+T0.wtSum AS NUMERIC(19,2)) montoTotalOperacion,   \r\n"
				+ "0.00 totalNoGravado,    \r\n"
				+ "CAST(T0.docTotal AS NUMERIC(19,2)) totalPagar,     \r\n"
				+ "dbo.[FN_CANTIDADALETRAS](T0.docTotal) totalLetras,    \r\n"
				+ "0.00 saldoFavor,    \r\n"
				+ "CASE WHEN (SELECT K0.PymntGroup FROM OCTG K0 WHERE K0.groupNum=T0.groupNum) LIKE '%CRED%' THEN 2 ELSE 1 END condicionOperacion,     \r\n"
				+ "CAST(ISNULL(T0.VatSum,0.00) AS NUMERIC(19,2)) iva,\r\n"
				+ "CAST(T0.DiscSum AS NUMERIC(19,2)) descuento,\r\n"
				+ "T0.U_flete,     \r\n"
				+ "T0.U_Seguro,     \r\n"
				+ "RIGHT(T0.U_E_INCOTERMS,2) codINCOTERMS,     \r\n"
				+ "ISNULL((SELECT K0.Name FROM [@FE_INCOTERMS] K0 WHERE K0.Code=T0.U_E_INCOTERMS),'') descINCOTERMS \r\n"
				+ "FROM OPCH T0 WHERE T0.docentry= ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, numDocumento);
			rs = ps.executeQuery();
			if (rs.next()) {
				encabezadoDTE.setNumeroControl(rs.getString("numeroDocumento"));
				encabezadoDTE.setFecEmi(rs.getString("fechaFacturacion"));
				encabezadoDTE.setHorEmi(rs.getString("horaFacturacion"));
				encabezadoDTE.setCodReceptor(rs.getString("codCliente"));
				encabezadoDTE.setTotalNoSuj(rs.getBigDecimal("totalNoSuj"));
				encabezadoDTE.setTotalExenta(rs.getBigDecimal("totalExenta"));
				encabezadoDTE.setTotalGravada(rs.getBigDecimal("totalGravada"));
				encabezadoDTE.setSubTotalVentas(rs.getBigDecimal("subTotalVentas"));
				encabezadoDTE.setDescuNoSuj(rs.getBigDecimal("descuNoSuj"));
				encabezadoDTE.setDescuExenta(rs.getBigDecimal("descuExenta"));
				encabezadoDTE.setDescuGravada(rs.getBigDecimal("descuGravada"));
				encabezadoDTE.setPorcentajeDescuento(rs.getBigDecimal("porcentajeDescuento"));
				encabezadoDTE.setTotalDescu(rs.getBigDecimal("totalDescu"));
				encabezadoDTE.setSubTotal(rs.getBigDecimal("subTotal"));
				//encabezadoDTE.setIvaPerci1(rs.getBigDecimal("iva"));
				encabezadoDTE.setIva(rs.getBigDecimal("iva"));
				encabezadoDTE.setIvaPerci1(BigDecimal.ZERO);
				encabezadoDTE.setIvaRete1(rs.getBigDecimal("ivaRete1"));
				encabezadoDTE.setReteRenta(rs.getBigDecimal("reteRenta"));
				encabezadoDTE.setMontoTotalOperacion(rs.getBigDecimal("montoTotalOperacion"));
				encabezadoDTE.setTotalNoGravado(rs.getBigDecimal("totalNoGravado"));
				encabezadoDTE.setTotalPagar(rs.getBigDecimal("totalPagar"));
				encabezadoDTE.setTotalLetras(rs.getString("totalLetras"));
				encabezadoDTE.setSaldoFavor(rs.getBigDecimal("saldoFavor"));
				encabezadoDTE.setCondicionOperacion(rs.getBigDecimal("condicionOperacion"));
				encabezadoDTE.setDescuento(rs.getBigDecimal("descuento"));
				encabezadoDTE.setFlete(rs.getBigDecimal("U_flete"));
				encabezadoDTE.setSeguro(rs.getBigDecimal("U_Seguro"));
				encabezadoDTE.setCodIncoterms(rs.getString("codINCOTERMS"));
				encabezadoDTE.setDescIncoterms(rs.getString("descINCOTERMS"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return encabezadoDTE;
	}
	
	public ArrayList<DetalleDTE> getDetalleFSE(String numDocumento) {
		ArrayList<DetalleDTE> listaDocumentos = new ArrayList<DetalleDTE>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT  CAST(ROW_NUMBER() OVER(ORDER BY T0.lineNum ASC) AS INT) numItem,    \n"
				+ "    CASE WHEN T1.DocType = 'I' THEN 1 WHEN T1.DocType = 'S' THEN 2 END tipoItem,      \n"
				+ "    CASE WHEN T0.Quantity=0 THEN 1 ELSE T0.Quantity END cantidad,     \n"
				+ "     ISNULL(T0.ItemCode,'') codigo,  \n"
				+ "      ISNULL((SELECT CAST(K0.U_FE_UMEDIDA AS INT) FROM OITM K0 WHERE K0.ItemCode=T0.ItemCode),59) unidadMedida,    \n"
				+ "      T0.Dscription description,    \n"
				+ "      CAST(T0.Price AS NUMERIC(19, 6)) precioUni,    \n"
				+ "      0.00 montoDescu,    \n"
				+ "      0.00 ventaNoSuj,    \n"
				+ "      0.00 ventaExenta,   \n"
				+ "		 CAST(ISNULL(T0.VatSum,0.00) AS NUMERIC(19,2)) iva,\r\n"
				+ "      CAST(T0.lineTotal AS NUMERIC(19, 2)) ventaGravada     \n"
				+ "      FROM PCH1 T0 JOIN OPCH T1 ON T0.docEntry = T1.docEntry \n"
				+ "      WHERE T1.DocEntry = ? ORDER BY T0.LineNum ASC";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, numDocumento);
			rs = ps.executeQuery();
			while (rs.next()) {
				DetalleDTE cuerpoDocumentoDTE = new DetalleDTE();
				
				//Se quita las comillas dobles a las descripciones
				String descripcion = rs.getString("description");
				descripcion = descripcion.replace("\"","");
				
				cuerpoDocumentoDTE.setDescripcion(descripcion);
				cuerpoDocumentoDTE.setNumItem(rs.getInt("numItem"));
				cuerpoDocumentoDTE.setTipoItem(rs.getInt("tipoItem"));
				cuerpoDocumentoDTE.setUniMedida(rs.getInt("unidadMedida"));
				cuerpoDocumentoDTE.setCantidad(rs.getInt("cantidad"));
				cuerpoDocumentoDTE.setMontoDescu(rs.getBigDecimal("montoDescu"));
				cuerpoDocumentoDTE.setVentaNoSuj(rs.getBigDecimal("ventaNoSuj"));
				cuerpoDocumentoDTE.setVentaExenta(rs.getBigDecimal("ventaExenta"));
				cuerpoDocumentoDTE.setVentaGravada(rs.getBigDecimal("ventaGravada"));
//				cuerpoDocumentoDTE.setNoGravado(rs.getBigDecimal(""));
//				cuerpoDocumentoDTE.setPsv(rs.getBigDecimal(""));
				cuerpoDocumentoDTE.setNoGravado(BigDecimal.ZERO);
				cuerpoDocumentoDTE.setPsv(BigDecimal.ZERO);
				cuerpoDocumentoDTE.setPrecioUni(rs.getBigDecimal("precioUni"));
				if(!rs.getString("codigo").equals("")) {
					cuerpoDocumentoDTE.setCodigo(rs.getString("codigo"));
				}
				cuerpoDocumentoDTE.setIvaItem(rs.getBigDecimal("iva"));
				
				ArrayList<String> listaTributos = new ArrayList<String>();
				listaTributos.add("20");
				cuerpoDocumentoDTE.setTributos(listaTributos);
				
				listaDocumentos.add(cuerpoDocumentoDTE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaDocumentos;
	}
	
	public void editOINV(FelControl felControl) {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		String query = "UPDATE OINV SET \n"
				+ "U_E_CODGENE = ?,\n"
				+ "U_E_NUMCONT = ?,\n"
				+ "U_E_SELLRECEP = ?,\n"
				+ "U_JSON_DTE = ?,\n"
				+ "U_FIRMA_DTE = ?,\n"
				+ "U_E_FECHORA = ?,\n"
				+ "U_E_MODFACT = ?,\n"
				+ "U_E_TIPOTRANS = ?\n"
				+ "WHERE DOCENTRY = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, felControl.getuECodGene());
			ps.setString(2, felControl.getuENumCont());
			ps.setString(3, felControl.getuESellRecep());
			ps.setString(4, felControl.getuJsonEnviado());
			ps.setString(5, felControl.getuFirma());
			String pattern = "yyyy-MM-dd hh:mm:ss a";
			SimpleDateFormat formatearFecha = new SimpleDateFormat(pattern);
			ps.setString(6, (felControl.getuEstado() == "A") ? formatearFecha.format(felControl.getuFechaPro()) : null);
			ps.setString(7, felControl.getuEModFact());
			ps.setString(8, felControl.getuETipoTrans());
			ps.setString(9, felControl.getuDocEntry());

			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void editORIN(FelControl felControl) {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		String query = "UPDATE ORIN SET \n"
				+ "U_E_CODGENE = ?,\n"
				+ "U_E_NUMCONT = ?,\n"
				+ "U_E_SELLRECEP = ?,\n"
				+ "U_JSON_DTE = ?,\n"
				+ "U_FIRMA_DTE = ?,\n"
				+ "U_E_FECHORA = ?,\n"
				+ "U_E_MODFACT = ?,\n"
				+ "U_E_TIPOTRANS = ?\n"
				+ "WHERE DOCENTRY = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, felControl.getuECodGene());
			ps.setString(2, felControl.getuENumCont());
			ps.setString(3, felControl.getuESellRecep());
			ps.setString(4, felControl.getuJsonEnviado());
			ps.setString(5, felControl.getuFirma());
			String pattern = "yyyy-MM-dd hh:mm:ss a";
			SimpleDateFormat formatearFecha = new SimpleDateFormat(pattern);
			ps.setString(6, (felControl.getuEstado() == "A") ? formatearFecha.format(felControl.getuFechaPro()) : null);
			ps.setString(7, felControl.getuEModFact());
			ps.setString(8, felControl.getuETipoTrans());
			ps.setString(9, felControl.getuDocEntry());

			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void editODLN(FelControl felControl) {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		String query = "UPDATE ODLN SET \n"
				+ "U_E_CODGENE = ?,\n"
				+ "U_E_NUMCONT = ?,\n"
				+ "U_E_SELLRECEP = ?,\n"
				+ "U_JSON_DTE = ?,\n"
				+ "U_FIRMA_DTE = ?,\n"
				+ "U_E_FECHORA = ?,\n"
				+ "U_E_MODFACT = ?,\n"
				+ "U_E_TIPOTRANS = ?\n"
				+ "WHERE DOCENTRY = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, felControl.getuECodGene());
			ps.setString(2, felControl.getuENumCont());
			ps.setString(3, felControl.getuESellRecep());
			ps.setString(4, felControl.getuJsonEnviado());
			ps.setString(5, felControl.getuFirma());
			String pattern = "yyyy-MM-dd hh:mm:ss a";
			SimpleDateFormat formatearFecha = new SimpleDateFormat(pattern);
			ps.setString(6, (felControl.getuEstado() == "A") ? formatearFecha.format(felControl.getuFechaPro()) : null);
			ps.setString(7, felControl.getuEModFact());
			ps.setString(8, felControl.getuETipoTrans());
			ps.setString(9, felControl.getuDocEntry());

			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void editOPCH(FelControl felControl) {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps;
		String query = "UPDATE OPCH SET \n"
				+ "U_E_CODGENE = ?,\n"
				+ "U_E_NUMCONT = ?,\n"
				+ "U_E_SELLRECEP = ?,\n"
				+ "U_JSON_DTE = ?,\n"
				+ "U_FIRMA_DTE = ?,\n"
				+ "U_E_FECHORA = ?,\n"
				+ "U_E_MODFACT = ?,\n"
				+ "U_E_TIPOTRANS = ?\n"
				+ "WHERE DOCENTRY = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, felControl.getuECodGene());
			ps.setString(2, felControl.getuENumCont());
			ps.setString(3, felControl.getuESellRecep());
			ps.setString(4, felControl.getuJsonEnviado());
			ps.setString(5, felControl.getuFirma());
			String pattern = "yyyy-MM-dd hh:mm:ss a";
			SimpleDateFormat formatearFecha = new SimpleDateFormat(pattern);
			ps.setString(6, (felControl.getuEstado() == "A") ? formatearFecha.format(felControl.getuFechaPro()) : null);
			ps.setString(7, felControl.getuEModFact());
			ps.setString(8, felControl.getuETipoTrans());
			ps.setString(9, felControl.getuDocEntry());

			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public EncabezadoDTE getMotivoCancelacionOINV(String numDocumento) {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		EncabezadoDTE encabezadoDTE = new EncabezadoDTE();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT T0.U_E_MOTIVO_ANUL, \n"
				+ "		T0.U_NOMBRE_RES_ANUL,\n"
				+ "		T0.U_NOMBRE_RES_SOLI,\n"
				+ "		T0.U_DOCU_RESP,\n"
				+ "		T0.U_DOCU_SOLI,\n"
				+ "		T0.U_TIPO_DOC_RESPO,\n"
				+ "		T0.U_TIPO_DOC_SOLI,\n"
				+ "		T0.U_TIPO_ANUL,\n"
				+ "		CAST(ISNULL(T0.VatSum,0.00) AS NUMERIC(19,2)) iva,\n"
				+ "		U_E_CODGENE, \n"
				+ "		U_E_NUMCONT, \n"
				+ "		U_E_SELLRECEP, \n"
				+ "		U_E_FECHORA \n"
				+ "		FROM OINV T0 \n"
				+ "		WHERE T0.DOCENTRY = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, numDocumento);
			rs = ps.executeQuery();
			if (rs.next()) {
				encabezadoDTE.setMotivoAnulacion(rs.getString("U_E_MOTIVO_ANUL"));
				encabezadoDTE.setNombreResponsable(rs.getString("U_NOMBRE_RES_ANUL"));
				encabezadoDTE.setNombreSolicita(rs.getString("U_NOMBRE_RES_SOLI"));
				encabezadoDTE.setNumDocResponsable(rs.getString("U_DOCU_RESP"));
				encabezadoDTE.setNumDocSolicita(rs.getString("U_DOCU_SOLI"));
				encabezadoDTE.setTipDocResponsable(rs.getString("U_TIPO_DOC_RESPO"));
				encabezadoDTE.setTipDocSolicita(rs.getString("U_TIPO_DOC_SOLI"));
				encabezadoDTE.setTipoAnulacion(rs.getInt("U_TIPO_ANUL"));
				encabezadoDTE.setIva(rs.getBigDecimal("iva"));
				encabezadoDTE.setuECodGene(rs.getString("U_E_CODGENE"));
				encabezadoDTE.setuENumCont(rs.getString("U_E_NUMCONT"));
				encabezadoDTE.setuESellRecep(rs.getString("U_E_SELLRECEP"));
				encabezadoDTE.setuEFechaHora(rs.getDate("U_E_FECHORA"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return encabezadoDTE;
	}
	
	public EncabezadoDTE getMotivoCancelacionORIN(String numDocumento) {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		EncabezadoDTE encabezadoDTE = new EncabezadoDTE();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT T0.U_E_MOTIVO_ANUL, \n"
				+ "		T0.U_NOMBRE_RES_ANUL,\n"
				+ "		T0.U_NOMBRE_RES_SOLI,\n"
				+ "		T0.U_DOCU_RESP,\n"
				+ "		T0.U_DOCU_SOLI,\n"
				+ "		T0.U_TIPO_DOC_RESPO,\n"
				+ "		T0.U_TIPO_DOC_SOLI,\n"
				+ "		T0.U_TIPO_ANUL,\n"
				+ "		CAST(ISNULL(T0.VatSum,0.00) AS NUMERIC(19,2)) iva,\n"
				+ "		U_E_CODGENE, \n"
				+ "		U_E_NUMCONT, \n"
				+ "		U_E_SELLRECEP, \n"
				+ "		U_E_FECHORA \n"
				+ "		FROM ORIN T0 \n"
				+ "		WHERE T0.DOCENTRY = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, numDocumento);
			rs = ps.executeQuery();
			if (rs.next()) {
				encabezadoDTE.setMotivoAnulacion(rs.getString("U_E_MOTIVO_ANUL"));
				encabezadoDTE.setNombreResponsable(rs.getString("U_NOMBRE_RES_ANUL"));
				encabezadoDTE.setNombreSolicita(rs.getString("U_NOMBRE_RES_SOLI"));
				encabezadoDTE.setNumDocResponsable(rs.getString("U_DOCU_RESP"));
				encabezadoDTE.setNumDocSolicita(rs.getString("U_DOCU_SOLI"));
				encabezadoDTE.setTipDocResponsable(rs.getString("U_TIPO_DOC_RESPO"));
				encabezadoDTE.setTipDocSolicita(rs.getString("U_TIPO_DOC_SOLI"));
				encabezadoDTE.setTipoAnulacion(rs.getInt("U_TIPO_ANUL"));
				encabezadoDTE.setIva(rs.getBigDecimal("iva"));
				encabezadoDTE.setuECodGene(rs.getString("U_E_CODGENE"));
				encabezadoDTE.setuENumCont(rs.getString("U_E_NUMCONT"));
				encabezadoDTE.setuESellRecep(rs.getString("U_E_SELLRECEP"));
				encabezadoDTE.setuEFechaHora(rs.getDate("U_E_FECHORA"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return encabezadoDTE;
	}
	
	public EncabezadoDTE getMotivoCancelacionODLN(String numDocumento) {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		EncabezadoDTE encabezadoDTE = new EncabezadoDTE();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT T0.U_E_MOTIVO_ANUL, \n"
				+ "		T0.U_NOMBRE_RES_ANUL,\n"
				+ "		T0.U_NOMBRE_RES_SOLI,\n"
				+ "		T0.U_DOCU_RESP,\n"
				+ "		T0.U_DOCU_SOLI,\n"
				+ "		T0.U_TIPO_DOC_RESPO,\n"
				+ "		T0.U_TIPO_DOC_SOLI,\n"
				+ "		T0.U_TIPO_ANUL,\n"
				+ "		CAST(ISNULL(T0.VatSum,0.00) AS NUMERIC(19,2)) iva,\n"
				+ "		U_E_CODGENE, \n"
				+ "		U_E_NUMCONT, \n"
				+ "		U_E_SELLRECEP, \n"
				+ "		U_E_FECHORA \n"
				+ "		FROM ODLN T0 \n"
				+ "		WHERE T0.DOCENTRY = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, numDocumento);
			rs = ps.executeQuery();
			if (rs.next()) {
				encabezadoDTE.setMotivoAnulacion(rs.getString("U_E_MOTIVO_ANUL"));
				encabezadoDTE.setNombreResponsable(rs.getString("U_NOMBRE_RES_ANUL"));
				encabezadoDTE.setNombreSolicita(rs.getString("U_NOMBRE_RES_SOLI"));
				encabezadoDTE.setNumDocResponsable(rs.getString("U_DOCU_RESP"));
				encabezadoDTE.setNumDocSolicita(rs.getString("U_DOCU_SOLI"));
				encabezadoDTE.setTipDocResponsable(rs.getString("U_TIPO_DOC_RESPO"));
				encabezadoDTE.setTipDocSolicita(rs.getString("U_TIPO_DOC_SOLI"));
				encabezadoDTE.setTipoAnulacion(rs.getInt("U_TIPO_ANUL"));
				encabezadoDTE.setIva(rs.getBigDecimal("iva"));
				encabezadoDTE.setuECodGene(rs.getString("U_E_CODGENE"));
				encabezadoDTE.setuENumCont(rs.getString("U_E_NUMCONT"));
				encabezadoDTE.setuESellRecep(rs.getString("U_E_SELLRECEP"));
				encabezadoDTE.setuEFechaHora(rs.getDate("U_E_FECHORA"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return encabezadoDTE;
	}
	
	public EncabezadoDTE getMotivoCancelacionOPCH(String numDocumento) {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		EncabezadoDTE encabezadoDTE = new EncabezadoDTE();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT T0.U_E_MOTIVO_ANUL, \n"
				+ "		T0.U_NOMBRE_RES_ANUL,\n"
				+ "		T0.U_NOMBRE_RES_SOLI,\n"
				+ "		T0.U_DOCU_RESP,\n"
				+ "		T0.U_DOCU_SOLI,\n"
				+ "		T0.U_TIPO_DOC_RESPO,\n"
				+ "		T0.U_TIPO_DOC_SOLI,\n"
				+ "		T0.U_TIPO_ANUL,\n"
				+ "		CAST(ISNULL(T0.VatSum,0.00) AS NUMERIC(19,2)) iva,\n"
				+ "		U_E_CODGENE, \n"
				+ "		U_E_NUMCONT, \n"
				+ "		U_E_SELLRECEP, \n"
				+ "		U_E_FECHORA \n"
				+ "		FROM OPCH T0 \n"
				+ "		WHERE T0.DOCENTRY = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, numDocumento);
			rs = ps.executeQuery();
			if (rs.next()) {
				encabezadoDTE.setMotivoAnulacion(rs.getString("U_E_MOTIVO_ANUL"));
				encabezadoDTE.setNombreResponsable(rs.getString("U_NOMBRE_RES_ANUL"));
				encabezadoDTE.setNombreSolicita(rs.getString("U_NOMBRE_RES_SOLI"));
				encabezadoDTE.setNumDocResponsable(rs.getString("U_DOCU_RESP"));
				encabezadoDTE.setNumDocSolicita(rs.getString("U_DOCU_SOLI"));
				encabezadoDTE.setTipDocResponsable(rs.getString("U_TIPO_DOC_RESPO"));
				encabezadoDTE.setTipDocSolicita(rs.getString("U_TIPO_DOC_SOLI"));
				encabezadoDTE.setTipoAnulacion(rs.getInt("U_TIPO_ANUL"));
				encabezadoDTE.setIva(rs.getBigDecimal("iva"));
				encabezadoDTE.setuECodGene(rs.getString("U_E_CODGENE"));
				encabezadoDTE.setuENumCont(rs.getString("U_E_NUMCONT"));
				encabezadoDTE.setuESellRecep(rs.getString("U_E_SELLRECEP"));
				encabezadoDTE.setuEFechaHora(rs.getDate("U_E_FECHORA"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return encabezadoDTE;
	}
	
	public String getTipoDoc(String numDocumento) {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String tipoDoc = "";
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "SELECT CASE A.U_tipo_doc\n"
				+ "WHEN 'CRF' THEN 'CCF'\n"
				+ "WHEN 'COF' THEN 'FAC'\n"
				+ "WHEN 'EXP' THEN 'FAE'\n"
				+ "ELSE '' END AS TIPO_DOC\n"
				+ "FROM OINV A WHERE A.DOCENTRY \n"
				+ "IN (SELECT TOP 1 T1.BaseEntry \n"
				+ "FROM OINV T0 \n"
				+ "INNER JOIN INV1 T1 ON T0.DocEntry = T1.DocEntry\n"
				+ "WHERE T0.DOCENTRY = ?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, numDocumento);
			rs = ps.executeQuery();
			if (rs.next()) {
				tipoDoc = rs.getString("TIPO_DOC");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tipoDoc;
	}
	
	public DatosReceptor getDatosReceptorFC(String docentry, DatosReceptor datosReceptor) {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		String query = "select CASE WHEN T0.U_NOMBRE IS NULL THEN T0.CARDNAME ELSE T0.U_NOMBRE END CardName, \r\n"
				+ "CASE WHEN T0.U_E_FELCORREO IS NULL THEN T4.U_FE_CORREO ELSE T0.U_E_FELCORREO END U_FE_CORREO\r\n"
				+ "from oinv t0 \r\n"
				+ "INNER JOIN ocrd t4 on t4.CardCode = t0.CardCode\r\n"
				+ "where  t0.docEntry = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, docentry);
			rs = ps.executeQuery();
			if (rs.next()) {
				datosReceptor.setNombre(rs.getString("CardName"));
				datosReceptor.setCorreo(rs.getString("U_FE_CORREO"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return datosReceptor;
	}
}
