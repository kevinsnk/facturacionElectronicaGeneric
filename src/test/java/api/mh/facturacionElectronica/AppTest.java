package api.mh.facturacionElectronica;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import api.mh.facturacionElectronica.ex.model.DireccionDTE;
import api.mh.facturacionElectronica.ex.model.PagosDTE;
import api.mh.facturacionElectronica.ex.model.TributosDTE;
import api.mh.facturacionElectronica.ex.model.ccf.CuerpoDocumentoDTE;
import api.mh.facturacionElectronica.ex.model.ccf.DTE;
import api.mh.facturacionElectronica.ex.model.ccf.EmisorDTE;
import api.mh.facturacionElectronica.ex.model.IdentificacionDTE;
import api.mh.facturacionElectronica.ex.model.ccf.ReceptorDTE;
import api.mh.facturacionElectronica.ex.model.ccf.ResumenDTE;
import api.mh.facturacionElectronica.model.AutentificacionResponse;
import api.mh.facturacionElectronica.model.FirmardocumentoCCFRequest;
import api.mh.facturacionElectronica.model.FirmardocumentoResponse;
import api.mh.facturacionElectronica.model.RecepcionDTERequest;
import api.mh.facturacionElectronica.model.RecepcionDTEResponse;
import api.mh.facturacionElectronica.services.FacturaElectronicaService;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
//		AutentificacionResponse autentificacionResponse = getTokenSesion();
//		System.out.println("========================= REPONSE FIRMA =========================");
//		System.out.println(autentificacionResponse);
//		System.out.println("=================================================================");
		
		@SuppressWarnings("unused")
		FirmardocumentoResponse firmaResponse = generarFirmaDTE();
		System.out.println("========================= REPONSE FIRMA =========================");
		//System.out.println(firmaResponse);
		System.out.println("=================================================================");
//		if (firmaResponse.getStatus().equals("OK")) {
//			RecepcionDTEResponse recepcionResponse = generarRecepcionDTE(firmaResponse, "");
//			System.out.println("========================= REPONSE RECEPCION =========================");
//			System.out.println(recepcionResponse);
//			System.out.println("=====================================================================");
//		}
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testApp() {
		assertTrue(true);
	}
	
	@SuppressWarnings("unused")
	private AutentificacionResponse getTokenSesion() {
		AutentificacionResponse autentificacionResponse = new AutentificacionResponse();
		FacturaElectronicaService facturaElectronicaService = new FacturaElectronicaService();
		
		autentificacionResponse = facturaElectronicaService.getTokenUsuarioService();
		
		return autentificacionResponse;
	}

	private FirmardocumentoResponse generarFirmaDTE() {
		FacturaElectronicaService facturaElectronicaService = new FacturaElectronicaService();
		FirmardocumentoCCFRequest dte = new FirmardocumentoCCFRequest();
		FirmardocumentoResponse response = new FirmardocumentoResponse();
		dte = generarValoresDTE();
		Gson gson = new GsonBuilder().serializeNulls().create();
		String json = gson.toJson(dte);
		System.out.println("========================= PETICION =========================");
		System.out.println(json);
		System.out.println("============================================================");
		response = facturaElectronicaService.certificarDTE(json);

		return response;
	}

	@SuppressWarnings("unused")
	private RecepcionDTEResponse generarRecepcionDTE(FirmardocumentoResponse firmardocumentoResponse, String token) {
		FacturaElectronicaService facturaElectronicaService = new FacturaElectronicaService();
		RecepcionDTERequest recepcionDTERequest = new RecepcionDTERequest();
		RecepcionDTEResponse response = new RecepcionDTEResponse();
		recepcionDTERequest = generarValoresRecepcionDTE(firmardocumentoResponse);
		response = facturaElectronicaService.recepcionDTE(recepcionDTERequest, token);

		return response;
	}

	private RecepcionDTERequest generarValoresRecepcionDTE(FirmardocumentoResponse firmardocumentoResponse) {
		RecepcionDTERequest recepcionDTERequest = new RecepcionDTERequest();
		try {
			//RecepcionDTERequest request = new RecepcionDTERequest();
		} catch (Exception e) {
			System.out.println("Error en generarValoresRecepcionDTE()");
			e.printStackTrace();
			recepcionDTERequest = null;
		}
		return recepcionDTERequest;
	}

	private FirmardocumentoCCFRequest generarValoresDTE() {
		FirmardocumentoCCFRequest dteRequest = new FirmardocumentoCCFRequest();
		try {
			dteRequest.setNit("06142912001017");
			dteRequest.setActivo(true);
			dteRequest.setPasswordPri("Dismadte2024");

			DTE dte = new DTE();
			// IdentificacionDTE
			// *****************************************************************************
			IdentificacionDTE identificacionDTE = new IdentificacionDTE();
			identificacionDTE.setVersion(3);
			identificacionDTE.setAmbiente("00");
			identificacionDTE.setTipoDte("03");
			identificacionDTE.setNumeroControl("DTE-03-00000000-000000000000012");
			identificacionDTE.setCodigoGeneracion("341CA743-70F1-4CFE-88BC-7E4CE72E70CB");
			identificacionDTE.setTipoModelo(1);
			identificacionDTE.setTipoOperacion(1);
			identificacionDTE.setTipoContingencia(null);
			identificacionDTE.setFecEmi("2024-05-16");
			identificacionDTE.setHorEmi("10:19:44");
			identificacionDTE.setTipoMoneda("USD");

			// *****************************************************************************
			// EmisorDTE
			// *****************************************************************************
			EmisorDTE emisorDTE = new EmisorDTE();
			emisorDTE.setNit("06142912001017");
			emisorDTE.setNrc("1282534");
			emisorDTE.setNombre("SALVADOR CABALITO");
			emisorDTE.setCodActividad("46900");
			emisorDTE.setDescActividad("Venta al por mayor de otros productos");
			emisorDTE.setTipoEstablecimiento("02");
			DireccionDTE direccionEmisor = new DireccionDTE();
			direccionEmisor.setDepartamento("06");
			direccionEmisor.setMunicipio("14");
			direccionEmisor.setComplemento("AV. ALVARADO DIAG. CENTROAMERICA, #4,");
			emisorDTE.setDireccion(direccionEmisor);
			emisorDTE.setTelefono("78963333");
			emisorDTE.setCorreo("salvador@ggg.com");

			// *****************************************************************************
			// ReceptorDTE
			// *****************************************************************************
			ReceptorDTE receptorDTE = new ReceptorDTE();
			receptorDTE.setNit("06142007941030");
			//receptorDTE.setTipoDocumento("02");
			//receptorDTE.setNumDocumento("22222222222211");
			receptorDTE.setNrc("797251");
			receptorDTE.setNombre("SALVADOR CABAL");
			receptorDTE.setCodActividad("68200");
			receptorDTE.setDescActividad(
					"Actividades Inmobiliarias Realizadas a Cambio de una Retribución o por Contrata");
			DireccionDTE direccionReceptor = new DireccionDTE();
			direccionReceptor.setDepartamento("06");
			direccionReceptor.setMunicipio("14");
			direccionReceptor.setComplemento("Dirección de Prueba 1 N 1234");
			receptorDTE.setDireccion(direccionReceptor);
			receptorDTE.setTelefono("22444444");
			receptorDTE.setCorreo("salvadorcabal@prueba.com");

			// *****************************************************************************
			// CuerpoDocumentoDTE
			// *****************************************************************************
			CuerpoDocumentoDTE cuerpoDocumentoDTE = new CuerpoDocumentoDTE();
			ArrayList<CuerpoDocumentoDTE> listaDocumentos = new ArrayList<CuerpoDocumentoDTE>();

			cuerpoDocumentoDTE.setDescripcion("PRODUCTO 1");
			cuerpoDocumentoDTE.setNumItem(1);
			cuerpoDocumentoDTE.setTipoItem(1);
			cuerpoDocumentoDTE.setUniMedida(59);
			cuerpoDocumentoDTE.setCantidad(1);
			cuerpoDocumentoDTE.setMontoDescu(BigDecimal.ZERO);
			cuerpoDocumentoDTE.setVentaNoSuj(BigDecimal.ZERO);
			cuerpoDocumentoDTE.setVentaExenta(BigDecimal.ZERO);
			cuerpoDocumentoDTE.setVentaGravada(new BigDecimal(2000));
			cuerpoDocumentoDTE.setNoGravado(BigDecimal.ZERO);
			cuerpoDocumentoDTE.setPsv(BigDecimal.ZERO);
			cuerpoDocumentoDTE.setPrecioUni(new BigDecimal(2000));
			
			ArrayList<String> lista = new ArrayList<>(Arrays.asList("20"));
			cuerpoDocumentoDTE.setTributos(lista);
			
			listaDocumentos.add(cuerpoDocumentoDTE);
			

			// *****************************************************************************
			// RESUMEN
			// *****************************************************************************
			ResumenDTE resumenDTE = new ResumenDTE();
			
			resumenDTE.setTotalNoSuj(BigDecimal.ZERO);
			resumenDTE.setTotalExenta(BigDecimal.ZERO);
			resumenDTE.setTotalGravada(new BigDecimal(2000));
			resumenDTE.setSubTotalVentas(new BigDecimal(2000));
			resumenDTE.setDescuNoSuj(BigDecimal.ZERO);
			resumenDTE.setDescuExenta(BigDecimal.ZERO);
			resumenDTE.setDescuGravada(BigDecimal.ZERO);
			resumenDTE.setPorcentajeDescuento(BigDecimal.ZERO);
			resumenDTE.setTotalDescu(BigDecimal.ZERO);
			resumenDTE.setSubTotal(new BigDecimal(2000));
			resumenDTE.setIvaPerci1(BigDecimal.ZERO);
			resumenDTE.setIvaRete1(BigDecimal.ZERO);
			resumenDTE.setReteRenta(BigDecimal.ZERO);
			resumenDTE.setMontoTotalOperacion(new BigDecimal(2260));
			resumenDTE.setTotalNoGravado(BigDecimal.ZERO);
			resumenDTE.setTotalPagar(new BigDecimal(2260));
			resumenDTE.setTotalLetras("DOS MIL DOSCIENTOS SESENTA DÓLARES");
			resumenDTE.setSaldoFavor(BigDecimal.ZERO);
			resumenDTE.setCondicionOperacion(BigDecimal.ONE);
			
			TributosDTE tributosDTE = new TributosDTE();
			ArrayList<TributosDTE> listaTributos = new ArrayList<TributosDTE>();
			
			tributosDTE.setCodigo("20");
			tributosDTE.setDescripcion("Impuesto al Valor Agregado 13%");
			tributosDTE.setValor(new BigDecimal(260));
			listaTributos.add(tributosDTE);
			
			PagosDTE pagosDTE = new PagosDTE();
			ArrayList<PagosDTE> listaPagos = new ArrayList<PagosDTE>();
			
			pagosDTE.setCodigo("01");
			pagosDTE.setMontoPago(new BigDecimal(2260));
			pagosDTE.setPlazo(null);
			pagosDTE.setReferencia("");
			listaPagos.add(pagosDTE);
			
			resumenDTE.setTributos(listaTributos);
			resumenDTE.setPagos(listaPagos);
		

			// *****************************************************************************

			dte.setIdentificacion(identificacionDTE);
			dte.setDocumentoRelacionado(null);
			dte.setEmisor(emisorDTE);
			dte.setReceptor(receptorDTE);
			dte.setOtrosDocumentos(null);
			dte.setVentaTercero(null);
			dte.setCuerpoDocumento(listaDocumentos);
			dte.setResumen(resumenDTE);
			dte.setExtension(null);// x
			dte.setApendice(null);

			dteRequest.setDteJson(dte);
		} catch (Exception e) {
			System.out.println("Error en generarValoresDTE()");
			e.printStackTrace();
			dteRequest = null;
		}

		return dteRequest;
	}

}
