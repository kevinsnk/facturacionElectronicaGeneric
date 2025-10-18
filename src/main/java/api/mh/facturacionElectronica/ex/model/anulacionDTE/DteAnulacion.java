package api.mh.facturacionElectronica.ex.model.anulacionDTE;

import java.util.Objects;

public class DteAnulacion {

	public IdentificacionAnulacion identificacion;
	public EmisorAnulacion emisor;
	public DocumentoAnulacion documento;
	public MotivoAnulacion motivo;

	public IdentificacionAnulacion getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(IdentificacionAnulacion identificacion) {
		this.identificacion = identificacion;
	}

	public EmisorAnulacion getEmisor() {
		return emisor;
	}

	public void setEmisor(EmisorAnulacion emisor) {
		this.emisor = emisor;
	}

	public DocumentoAnulacion getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoAnulacion documento) {
		this.documento = documento;
	}

	public MotivoAnulacion getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoAnulacion motivo) {
		this.motivo = motivo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(documento, emisor, identificacion, motivo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DteAnulacion other = (DteAnulacion) obj;
		return Objects.equals(documento, other.documento) && Objects.equals(emisor, other.emisor)
				&& Objects.equals(identificacion, other.identificacion) && Objects.equals(motivo, other.motivo);
	}

	@Override
	public String toString() {
		return "DteAnulacion [identificacion=" + identificacion + ", emisor=" + emisor + ", documento=" + documento
				+ ", motivo=" + motivo + "]";
	}

}
