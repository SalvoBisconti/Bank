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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTO_CORRENTE")
public class ContoCorrente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cc_seq")
	@SequenceGenerator(name = "cc_seq", sequenceName = "CC_SEQ", allocationSize = 1)
	@Column(name = "ID_CNT_CRNT")
	private long id_cnt_crnt;

	@Column(name = "NUM_CNT")
	private String num_cnt;

	@Column(name = "SALDO")
	private double saldo;

	@Column(name = "DELETED")
	private char deleted;

	@Column(name = "DATA_INS")
	private Date data_ins;

	@Column(name = "DATA_UPD")
	private Date data_upd;

	@ManyToOne
	@JoinColumn(name = "ID_CLNT", referencedColumnName = "ID_CLNT")
	private Cliente cliente;

	@OneToMany(mappedBy = "contoCorrente", cascade = CascadeType.ALL)
	private List<Transazione> transazioni = new ArrayList<>();

	public ContoCorrente() {
	}

	public ContoCorrente(long id_cnt_crnt, String num_cnt, double saldo, char deleted, Date data_ins, Date data_upd,
			Cliente cliente, List<Transazione> transazioni) {
		super();
		this.id_cnt_crnt = id_cnt_crnt;
		this.num_cnt = num_cnt;
		this.saldo = saldo;
		this.deleted = deleted;
		this.data_ins = data_ins;
		this.data_upd = data_upd;
		this.cliente = cliente;
		this.transazioni = transazioni;
	}

	public long getId_cnt_crnt() {
		return id_cnt_crnt;
	}

	public void setId_cnt_crnt(long id_cnt_crnt) {
		this.id_cnt_crnt = id_cnt_crnt;
	}

	public String getNum_cnt() {
		return num_cnt;
	}

	public void setNum_cnt(String num_cnt) {
		this.num_cnt = num_cnt;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Transazione> getTransazioni() {
		return transazioni;
	}

	public void setTransazioni(List<Transazione> transazioni) {
		this.transazioni = transazioni;
	}

	@Override
	public String toString() {
		return "ContoCorrente [id_cnt_crnt=" + id_cnt_crnt + ", num_cnt=" + num_cnt + ", saldo=" + saldo + ", deleted="
				+ deleted + ", data_ins=" + data_ins + ", data_upd=" + data_upd + ", cliente=" + cliente
				+ ", transazioni=" + transazioni + "]";
	}

}
