package api.mh.facturacionElectronica.ex.model.fse;

import java.util.ArrayList;
import java.util.List;

import api.mh.facturacionElectronica.ex.model.IdentificacionDTE;

public class DTE {

	public IdentificacionDTE identificacion;
	public EmisorDTE emisor;
	public SujetoExcluidoDTE sujetoExcluido;
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

	public SujetoExcluidoDTE getSujetoExcluido() {
		return sujetoExcluido;
	}

	public void setSujetoExcluido(SujetoExcluidoDTE sujetoExcluido) {
		this.sujetoExcluido = sujetoExcluido;
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
		return "DTE [identificacion=" + identificacion + ", emisor=" + emisor + ", sujetoExcluido=" + sujetoExcluido
				+ ", cuerpoDocumento=" + cuerpoDocumento + ", resumen=" + resumen + ", apendice=" + apendice + "]";
	}

}
