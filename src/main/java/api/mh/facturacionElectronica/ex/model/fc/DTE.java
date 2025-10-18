package api.mh.facturacionElectronica.ex.model.fc;

import java.util.List;

import api.mh.facturacionElectronica.ex.model.DocumentoRelacionado;
import api.mh.facturacionElectronica.ex.model.IdentificacionDTE;

public class DTE {

	public IdentificacionDTE identificacion;
	public DocumentoRelacionado documentoRelacionado;
	public EmisorDTE emisor;
	public ReceptorDTE receptor;
	public OtrosDocumentosDTE otrosDocumentos;
	public VentaTerceroDTE ventaTercero;
	public List<CuerpoDocumentoDTE> cuerpoDocumento;
	public ResumenDTE resumen;
	public ExtensionDTE extension;
	public ApendiceDTE apendice;

	public IdentificacionDTE getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(IdentificacionDTE identificacion) {
		this.identificacion = identificacion;
	}

	public DocumentoRelacionado getDocumentoRelacionado() {
		return documentoRelacionado;
	}

	public void setDocumentoRelacionado(DocumentoRelacionado documentoRelacionado) {
		this.documentoRelacionado = documentoRelacionado;
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

	public OtrosDocumentosDTE getOtrosDocumentos() {
		return otrosDocumentos;
	}

	public void setOtrosDocumentos(OtrosDocumentosDTE otrosDocumentos) {
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

	public ExtensionDTE getExtension() {
		return extension;
	}

	public void setExtension(ExtensionDTE extension) {
		this.extension = extension;
	}

	public ApendiceDTE getApendice() {
		return apendice;
	}

	public void setApendice(ApendiceDTE apendice) {
		this.apendice = apendice;
	}

	@Override
	public String toString() {
		return "DTE [identificacion=" + identificacion + ", documentoRelacionado=" + documentoRelacionado + ", emisor="
				+ emisor + ", receptor=" + receptor + ", otrosDocumentos=" + otrosDocumentos + ", ventaTercero="
				+ ventaTercero + ", cuerpoDocumento=" + cuerpoDocumento + ", resumen=" + resumen + ", extension="
				+ extension + ", apendice=" + apendice + "]";
	}

}
