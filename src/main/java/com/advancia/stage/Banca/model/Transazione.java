package com.advancia.stage.Banca.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="TRANSAZIONE")
public class Transazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tnsz_seq")
	@SequenceGenerator(name="tnsz_seq", sequenceName="TRSN_SEQ", allocationSize = 1 )
	@Column(name="ID_TNSZ")
	private long id_tnsz;

	@Column(name="TIPO_OPZ")
	private String tipo_opz;
	
	@Column(name="IMPORTO")
	private double importo;
	
	@Column(name="DATA_OPZ")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_opz;
	
	@Column(name="STATO_TRANSAZIONE")
	private String stato_transazione;
	
	@ManyToOne
	@JoinColumn(name="ID_CNT_CRNT", referencedColumnName = "ID_CNT_CRNT")
	private ContoCorrente contoCorrente;
	
	
	public Transazione() {}


	public Transazione(long id_tnsz, String tipo_opz, double importo, Date data_opz, String stato_transazione,
			ContoCorrente contoCorrente) {
		super();
		this.id_tnsz = id_tnsz;
		this.tipo_opz = tipo_opz;
		this.importo = importo;
		this.data_opz = data_opz;
		this.stato_transazione = stato_transazione;
		this.contoCorrente = contoCorrente;
	}
	
	public Transazione(String tipo_opz, double importo, Date data_opz, String stato_transazione,
			ContoCorrente contoCorrente) {
		super();
		this.tipo_opz = tipo_opz;
		this.importo = importo;
		this.data_opz = data_opz;
		this.stato_transazione = stato_transazione;
		this.contoCorrente = contoCorrente;
	}


	public long getId_tnsz() {
		return id_tnsz;
	}


	public void setId_tnsz(long id_tnsz) {
		this.id_tnsz = id_tnsz;
	}


	public String getTipo_opz() {
		return tipo_opz;
	}


	public void setTipo_opz(String tipo_opz) {
		this.tipo_opz = tipo_opz;
	}


	public double getImporto() {
		return importo;
	}


	public void setImporto(double importo) {
		this.importo = importo;
	}


	public Date getData_opz() {
		return data_opz;
	}


	public void setData_opz(Date data_opz) {
		this.data_opz = data_opz;
	}


	public String getStato_transazione() {
		return stato_transazione;
	}


	public void setStato_transazione(String stato_transazione) {
		this.stato_transazione = stato_transazione;
	}


	public ContoCorrente getContoCorrente() {
		return contoCorrente;
	}


	public void setContoCorrente(ContoCorrente contoCorrente) {
		this.contoCorrente = contoCorrente;
	}


	@Override
	public String toString() {
		return "Transazione [id_tnsz=" + id_tnsz + ", tipo_opz=" + tipo_opz + ", importo=" + importo + ", data_opz="
				+ data_opz + ", stato_transazione=" + stato_transazione + ", contoCorrente=" + contoCorrente + "]";
	};
	
	
}
