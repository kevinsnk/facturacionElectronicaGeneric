package api.mh.facturacionElectronica.ex.model.nr;

public class VentaTerceroDTE {

	public String nit;
	public String nombre;

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "VentaTerceroDTE [nit=" + nit + ", nombre=" + nombre + "]";
	}

}
