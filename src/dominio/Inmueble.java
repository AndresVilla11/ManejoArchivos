package dominio;

import java.util.*;

public class Inmueble {

	private String codigo;
	private String direccion;
	private String ciudad;
	private String foto;
	private String tipo;
	List<Propietario> propietarios = new ArrayList<Propietario>();
	private String fechaAdm;
	private String fechaCon;
	private String tipoAdm;
	private String valor;
	private String estado;

	public Inmueble() {

	}

	public Inmueble(String codigo, String direccion, String ciudad, String foto, String tipo,
			List<Propietario> propietarios, String fechaAdm, String fechaCon, String tipoAdm, String valor,
			String estado) {
		this.codigo = codigo;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.foto = foto;
		this.tipo = tipo;
		this.propietarios = propietarios;
		this.fechaAdm = fechaAdm;
		this.fechaCon = fechaCon;
		this.tipoAdm = tipoAdm;
		this.valor = valor;
		this.estado = estado;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Propietario> getPropietarios() {
		return this.propietarios;
	}

	public void setPropietarios(List<Propietario> propietarios) {
		this.propietarios = propietarios;
	}

	public String getFechaAdm() {
		return this.fechaAdm;
	}

	public void setFechaAdm(String fechaAdm) {
		this.fechaAdm = fechaAdm;
	}

	public String getFechaCon() {
		return this.fechaCon;
	}

	public void setFechaCon(String fechaCon) {
		this.fechaCon = fechaCon;
	}

	public String getTipoAdm() {
		return this.tipoAdm;
	}

	public void setTipoAdm(String tipoAdm) {
		this.tipoAdm = tipoAdm;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Inmueble [codigo= " + codigo + ", direccion= " + direccion + ", ciudad= " + ciudad + ", foto= " + foto
				+ ", tipo= " + tipo + ", propietarios= " + propietarios + ", fechaAdm= " + fechaAdm + ", fechaCon= "
				+ fechaCon + ", tipoAdm= " + tipoAdm + ", valor= " + valor + ", estado= " + estado + "]";
	}
}
