package api.mh.facturacionElectronica.ex.model.contingencia;

import java.util.Objects;

public class DetalleDTE {

	public int noItem;
	public String codigoGeneracion;
	public String tipoDoc;

	public int getNoItem() {
		return noItem;
	}

	public void setNoItem(int noItem) {
		this.noItem = noItem;
	}

	public String getCodigoGeneracion() {
		return codigoGeneracion;
	}

	public void setCodigoGeneracion(String codigoGeneracion) {
		this.codigoGeneracion = codigoGeneracion;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoGeneracion, noItem, tipoDoc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleDTE other = (DetalleDTE) obj;
		return Objects.equals(codigoGeneracion, other.codigoGeneracion) && noItem == other.noItem
				&& Objects.equals(tipoDoc, other.tipoDoc);
	}

	@Override
	public String toString() {
		return "DetalleDTE [noItem=" + noItem + ", codigoGeneracion=" + codigoGeneracion + ", tipoDoc=" + tipoDoc + "]";
	}

}
