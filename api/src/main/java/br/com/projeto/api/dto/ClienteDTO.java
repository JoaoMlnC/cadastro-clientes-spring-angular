package br.com.projeto.api.dto;

public class ClienteDTO {
    private Long id;
    private String cnpj;
    private String razaoSocial;
    private String status;
    private String username;
    private String password; 
    private String confirmPassword; 

  
    public ClienteDTO() {}


	public ClienteDTO(Long id, String cnpj, String razaoSocial, String status, String username, String password,
			String confirmPassword) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.status = status;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getRazaoSocial() {
		return razaoSocial;
	}


	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

    
}
