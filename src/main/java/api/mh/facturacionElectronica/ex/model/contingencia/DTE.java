package api.mh.facturacionElectronica.ex.model.contingencia;

import java.util.List;
import java.util.Objects;

public class DTE {

	public IdentificacionDTE identificacion;
	public EmisorDTE emisor;
	public List<DetalleDTE> detalleDTE;
	public Motivo motivo;

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

	public List<DetalleDTE> getDetalleDTE() {
		return detalleDTE;
	}

	public void setDetalleDTE(List<DetalleDTE> detalleDTE) {
		this.detalleDTE = detalleDTE;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(detalleDTE, emisor, identificacion, motivo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTE other = (DTE) obj;
		return Objects.equals(detalleDTE, other.detalleDTE) && Objects.equals(emisor, other.emisor)
				&& Objects.equals(identificacion, other.identificacion) && Objects.equals(motivo, other.motivo);
	}

	@Override
	public String toString() {
		return "DTE [identificacion=" + identificacion + ", emisor=" + emisor + ", detalleDTE=" + detalleDTE
				+ ", motivo=" + motivo + "]";
	}

}
