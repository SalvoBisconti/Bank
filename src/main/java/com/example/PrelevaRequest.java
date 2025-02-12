//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v3.0.2 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2025.02.12 alle 05:48:24 PM CET 
//


package com.example;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idConto" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="importo" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idConto",
    "importo"
})
@XmlRootElement(name = "PrelevaRequest")
public class PrelevaRequest {

    protected long idConto;
    protected double importo;

    /**
     * Recupera il valore della proprietà idConto.
     * 
     */
    public long getIdConto() {
        return idConto;
    }

    /**
     * Imposta il valore della proprietà idConto.
     * 
     */
    public void setIdConto(long value) {
        this.idConto = value;
    }

    /**
     * Recupera il valore della proprietà importo.
     * 
     */
    public double getImporto() {
        return importo;
    }

    /**
     * Imposta il valore della proprietà importo.
     * 
     */
    public void setImporto(double value) {
        this.importo = value;
    }

}
