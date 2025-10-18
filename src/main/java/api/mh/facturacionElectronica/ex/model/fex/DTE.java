package api.mh.facturacionElectronica.ex.model.fex;

import java.util.ArrayList;
import java.util.List;

public class DTE {

	public IdentificacionDTE identificacion;
	public EmisorDTE emisor;
	public ReceptorDTE receptor;
	public ArrayList<OtrosDocumentosDTE> otrosDocumentos;
	public VentaTerceroDTE ventaTercero;
	public List<CuerpoDocumentoDTE> cuerpoDocumento;
	public ResumenDTE resumen;
	public ArrayList<ApendiceDTE> apendice;

	public IdentificacionDTE getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(IdentificacionDTE identificacion) {
		this.identificacion = identificacion;
	}

	public EmisorDTE getEmisor() {
		return emisor;
	}

	public void setEmisor(EmisorDTE emisor) {
		this.emisor = emisor;
	}

	public ReceptorDTE getReceptor() {
		return receptor;
	}

	public void setReceptor(ReceptorDTE receptor) {
		this.receptor = receptor;
	}

	public ArrayList<OtrosDocumentosDTE> getOtrosDocumentos() {
		return otrosDocumentos;
	}

	public void setOtrosDocumentos(ArrayList<OtrosDocumentosDTE> otrosDocumentos) {
		this.otrosDocumentos = otrosDocumentos;
	}

	public VentaTerceroDTE getVentaTercero() {
		return ventaTercero;
	}

	public void setVentaTercero(VentaTerceroDTE ventaTercero) {
		this.ventaTercero = ventaTercero;
	}

	public List<CuerpoDocumentoDTE> getCuerpoDocumento() {
		return cuerpoDocumento;
	}

	public void setCuerpoDocumento(List<CuerpoDocumentoDTE> cuerpoDocumento) {
		this.cuerpoDocumento = cuerpoDocumento;
	}

	public ResumenDTE getResumen() {
		return resumen;
	}

	public void setResumen(ResumenDTE resumen) {
		this.resumen = resumen;
	}

	public ArrayList<ApendiceDTE> getApendice() {
		return apendice;
	}

	public void setApendice(ArrayList<ApendiceDTE> apendice) {
		this.apendice = apendice;
	}

	@Override
	public String toString() {
		return "DTE [identificacion=" + identificacion + ", emisor=" + emisor + ", receptor=" + receptor
				+ ", otrosDocumentos=" + otrosDocumentos + ", ventaTercero=" + ventaTercero + ", cuerpoDocumento="
				+ cuerpoDocumento + ", resumen=" + resumen + ", apendice=" + apendice + "]";
	}

}
