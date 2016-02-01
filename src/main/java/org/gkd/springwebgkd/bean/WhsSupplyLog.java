/*
 * Created on 23 Des 2015 ( Time 11:12:13 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.gkd.springwebgkd.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;

public class WhsSupplyLog implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private BigDecimal noId;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Size( max = 40 )
    private String kodeBarcode;

    @Size( max = 20 )
    private String kdBrg;


    private BigDecimal qty;


    private Date tglTrx;

    @Size( max = 20 )
    private String status;

    @Size( max = 20 )
    private String lokasi;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setNoId( BigDecimal noId ) {
        this.noId = noId ;
    }

    public BigDecimal getNoId() {
        return this.noId;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setKodeBarcode( String kodeBarcode ) {
        this.kodeBarcode = kodeBarcode;
    }
    public String getKodeBarcode() {
        return this.kodeBarcode;
    }

    public void setKdBrg( String kdBrg ) {
        this.kdBrg = kdBrg;
    }
    public String getKdBrg() {
        return this.kdBrg;
    }

    public void setQty( BigDecimal qty ) {
        this.qty = qty;
    }
    public BigDecimal getQty() {
        return this.qty;
    }

    public void setTglTrx( Date tglTrx ) {
        this.tglTrx = tglTrx;
    }
    public Date getTglTrx() {
        return this.tglTrx;
    }

    public void setStatus( String status ) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }

    public void setLokasi( String lokasi ) {
        this.lokasi = lokasi;
    }
    public String getLokasi() {
        return this.lokasi;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(noId);
        sb.append("|");
        sb.append(kodeBarcode);
        sb.append("|");
        sb.append(kdBrg);
        sb.append("|");
        sb.append(qty);
        sb.append("|");
        sb.append(tglTrx);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(lokasi);
        return sb.toString(); 
    } 


}