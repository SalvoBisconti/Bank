package com.advancia.stage.Banca.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
	@SequenceGenerator(name = "cliente_seq", sequenceName = "CLNT_SEQ", allocationSize = 1)
	@Column(name = "ID_CLNT")
	private long id_clnt;

	@Column(name = "NOME_CLNT")
	private String nome_clnt;

	@Column(name = "CGN_CLNT")
	private String cgn_clnt;

	@Column(name = "CF_CLNT")
	private String cf_clnt;

	@Column(name = "EMAIL_CLNT")
	private String email_clnt;

	@Column(name = "PASSWORD_CLNT")
	private String password_clnt;

	@Column(name = "DELETED")
	private char deleted;

	@Column(name = "DATA_INS")
	private Date data_ins;

	@Column(name = "DATA_UPD")
	private Date data_upd;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<ContoCorrente> listaCC = new ArrayList<>();

	public Cliente() {
	}

	public Cliente(long id_clnt, String nome_clnt, String cgn_clnt, String cf_clnt, String email_clnt,
			String password_clnt, char deleted, Date data_ins, Date data_upd, List<ContoCorrente> listaCC) {
		super();
		this.id_clnt = id_clnt;
		this.nome_clnt = nome_clnt;
		this.cgn_clnt = cgn_clnt;
		this.cf_clnt = cf_clnt;
		this.email_clnt = email_clnt;
		this.password_clnt = password_clnt;
		this.deleted = deleted;
		this.data_ins = data_ins;
		this.data_upd = data_upd;
		this.listaCC = listaCC;
	}

	public long getId_clnt() {
		return id_clnt;
	}

	public void setId_clnt(long id_clnt) {
		this.id_clnt = id_clnt;
	}

	public String getNome_clnt() {
		return nome_clnt;
	}

	public void setNome_clnt(String nome_clnt) {
		this.nome_clnt = nome_clnt;
	}

	public String getCgn_clnt() {
		return cgn_clnt;
	}

	public void setCgn_clnt(String cgn_clnt) {
		this.cgn_clnt = cgn_clnt;
	}

	public String getCf_clnt() {
		return cf_clnt;
	}

	public void setCf_clnt(String cf_clnt) {
		this.cf_clnt = cf_clnt;
	}

	public String getEmail_clnt() {
		return email_clnt;
	}

	public void setEmail_clnt(String email_clnt) {
		this.email_clnt = email_clnt;
	}

	public char getDeleted() {
		return deleted;
	}

	public void setDeleted(char deleted) {
		this.deleted = deleted;
	}

	public Date getData_ins() {
		return data_ins;
	}

	public void setData_ins(Date data_ins) {
		this.data_ins = data_ins;
	}

	public Date getData_upd() {
		return data_upd;
	}

	public void setData_upd(Date data_upd) {
		this.data_upd = data_upd;
	}

	public List<ContoCorrente> getListaCC() {
		return listaCC;
	}

	public void setListaCC(List<ContoCorrente> listaCC) {
		this.listaCC = listaCC;
	}

	public String getPassword_clnt() {
		return password_clnt;
	}

	public void setPassword_clnt(String password_clnt) {
		this.password_clnt = password_clnt;
	}

	@Override
	public String toString() {
		return "Cliente [id_clnt=" + id_clnt + ", nome_clnt=" + nome_clnt + ", cgn_clnt=" + cgn_clnt + ", cf_clnt="
				+ cf_clnt + ", email_clnt=" + email_clnt + ", password_clnt=" + password_clnt + ", deleted=" + deleted
				+ ", data_ins=" + data_ins + ", data_upd=" + data_upd + ", listaCC=" + listaCC + "]";
	}

}
