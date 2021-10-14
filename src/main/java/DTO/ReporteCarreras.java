package DTO;

public class ReporteCarreras {
	private int idCarrera;
	private String nombreCarrera;
	private Integer anio;
	private Long egresados;
	private Long inscriptos;

	public ReporteCarreras() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReporteCarreras(int idCarrera, String nombreCarrera, Integer anio, Long egresados, Long inscriptos) {
		super();
		this.idCarrera = idCarrera;
		this.nombreCarrera = nombreCarrera;
		this.anio = anio;
		this.egresados = egresados;
		this.inscriptos = inscriptos;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Long getEgresados() {
		return egresados;
	}

	public void setEgresados(Long egresados) {
		this.egresados = egresados;
	}

	public Long getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(Long inscriptos) {
		this.inscriptos = inscriptos;
	}

	@Override
	public String toString() {
		return "ReporteCarreras [idCarrera=" + idCarrera + ", nombreCarrera=" + nombreCarrera + ", anio=" + anio
				+ ", egresados=" + egresados + ", inscriptos=" + inscriptos + "]";
	}

}
