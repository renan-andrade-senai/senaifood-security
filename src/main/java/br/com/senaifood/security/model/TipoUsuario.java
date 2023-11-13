package br.com.senaifood.security.model;

public enum TipoUsuario {
	
	CLIENTE("C", "CLIENTE"),
	RESTAURANTE("R", "RESTAURANTE"),
	ENTREGADOR("E", "ENTREGADOR");
	
	private final String tipoUsuario;
	private final String dsTipo;
	
	private TipoUsuario(String tipo, String dsTipo) {
		this.tipoUsuario = tipo;
		this.dsTipo = dsTipo;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public String getDsTipo() {
		return dsTipo;
	}
	
	public static TipoUsuario fromTipo(String tipo) {
		return switch (tipo) {
			case "C": 
				yield CLIENTE;
			case "R":
				yield RESTAURANTE;
			case "E":
				yield ENTREGADOR;
			default:
				throw new IllegalArgumentException("Unexpected value: " + tipo);
		};
	}
	
	public static String fromDesc(String dsTipo) {
		return switch (dsTipo) {
			case "CLIENTE": 
				yield CLIENTE.tipoUsuario;
			case "RESTAURANTE":
				yield RESTAURANTE.tipoUsuario;
			case "ENTREGADOR":
				yield ENTREGADOR.tipoUsuario;
			default:
				throw new IllegalArgumentException("Unexpected value: " + dsTipo);
		};
	}
	
}
