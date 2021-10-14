package DTO;

public class EstudianteAndCareer {
	private int nroEstudiante;
	private String nombreCarrera;

	public EstudianteAndCareer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EstudianteAndCareer(int nroEstudiante, String nombreCarrera) {
		super();
		this.nroEstudiante = nroEstudiante;
		this.nombreCarrera = nombreCarrera;
	}

	public int getNroEstudiante() {
		return nroEstudiante;
	}

	public void setNroEstudiante(int nroEstudiante) {
		this.nroEstudiante = nroEstudiante;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

}
