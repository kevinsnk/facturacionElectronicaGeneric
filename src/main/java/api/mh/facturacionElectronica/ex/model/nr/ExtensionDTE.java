package api.mh.facturacionElectronica.ex.model.nr;

import java.util.Objects;

public class ExtensionDTE {

	public String nombEntrega;
	public String docuEntrega;
	public String nombRecibe;
	public String docuRecibe;
	public String observaciones;

	public String getNombEntrega() {
		return nombEntrega;
	}

	public void setNombEntrega(String nombEntrega) {
		this.nombEntrega = nombEntrega;
	}

	public String getDocuEntrega() {
		return docuEntrega;
	}

	public void setDocuEntrega(String docuEntrega) {
		this.docuEntrega = docuEntrega;
	}

	public String getNombRecibe() {
		return nombRecibe;
	}

	public void setNombRecibe(String nombRecibe) {
		this.nombRecibe = nombRecibe;
	}

	public String getDocuRecibe() {
		return docuRecibe;
	}

	public void setDocuRecibe(String docuRecibe) {
		this.docuRecibe = docuRecibe;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(docuEntrega, docuRecibe, nombEntrega, nombRecibe, observaciones);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExtensionDTE other = (ExtensionDTE) obj;
		return Objects.equals(docuEntrega, other.docuEntrega) && Objects.equals(docuRecibe, other.docuRecibe)
				&& Objects.equals(nombEntrega, other.nombEntrega) && Objects.equals(nombRecibe, other.nombRecibe)
				&& Objects.equals(observaciones, other.observaciones);
	}

	@Override
	public String toString() {
		return "ExtensionDTE [nombEntrega=" + nombEntrega + ", docuEntrega=" + docuEntrega + ", nombRecibe="
				+ nombRecibe + ", docuRecibe=" + docuRecibe + ", observaciones=" + observaciones + "]";
	}

}
