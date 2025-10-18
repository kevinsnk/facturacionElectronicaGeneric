package api.mh.facturacionElectronica.ex.model.fex;

import java.util.Objects;

public class OtrosDocumentosDTE {

	public int codDocAsociado;
	public String descDocumento;
	public String detalleDocumento;
	public String placaTrans;
	public String modoTransp;
	public String numConductor;
	public String nombreConductor;

	public int getCodDocAsociado() {
		return codDocAsociado;
	}

	public void setCodDocAsociado(int codDocAsociado) {
		this.codDocAsociado = codDocAsociado;
	}

	public String getDescDocumento() {
		return descDocumento;
	}

	public void setDescDocumento(String descDocumento) {
		this.descDocumento = descDocumento;
	}

	public String getDetalleDocumento() {
		return detalleDocumento;
	}

	public void setDetalleDocumento(String detalleDocumento) {
		this.detalleDocumento = detalleDocumento;
	}

	public String getPlacaTrans() {
		return placaTrans;
	}

	public void setPlacaTrans(String placaTrans) {
		this.placaTrans = placaTrans;
	}

	public String getModoTransp() {
		return modoTransp;
	}

	public void setModoTransp(String modoTransp) {
		this.modoTransp = modoTransp;
	}

	public String getNumConductor() {
		return numConductor;
	}

	public void setNumConductor(String numConductor) {
		this.numConductor = numConductor;
	}

	public String getNombreConductor() {
		return nombreConductor;
	}

	public void setNombreConductor(String nombreConductor) {
		this.nombreConductor = nombreConductor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codDocAsociado, descDocumento, detalleDocumento, modoTransp, nombreConductor, numConductor,
				placaTrans);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OtrosDocumentosDTE other = (OtrosDocumentosDTE) obj;
		return codDocAsociado == other.codDocAsociado && Objects.equals(descDocumento, other.descDocumento)
				&& Objects.equals(detalleDocumento, other.detalleDocumento)
				&& Objects.equals(modoTransp, other.modoTransp)
				&& Objects.equals(nombreConductor, other.nombreConductor)
				&& Objects.equals(numConductor, other.numConductor) && Objects.equals(placaTrans, other.placaTrans);
	}

	@Override
	public String toString() {
		return "OtrosDocumentosDTE [codDocAsociado=" + codDocAsociado + ", descDocumento=" + descDocumento
				+ ", detalleDocumento=" + detalleDocumento + ", placaTrans=" + placaTrans + ", modoTransp=" + modoTransp
				+ ", numConductor=" + numConductor + ", nombreConductor=" + nombreConductor + "]";
	}

}
