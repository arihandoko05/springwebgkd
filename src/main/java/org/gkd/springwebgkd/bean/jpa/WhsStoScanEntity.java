/*
 * Created on 19 Jan 2016 ( Time 16:36:26 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.gkd.springwebgkd.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "WHS_STO_SCAN"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="WHS_STO_SCAN", schema="USRGKDMFG" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="WhsStoScanEntity.countAll", query="SELECT COUNT(x) FROM WhsStoScanEntity x" )
} )
public class WhsStoScanEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="KD_TRX", nullable=false)
    private BigDecimal kdTrx        ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="NO_BARCODE", nullable=false, length=30)
    private String     noBarcode    ;

    @Column(name="TAHUN", nullable=false, length=4)
    private String     tahun        ;

    @Column(name="BULAN", nullable=false, length=2)
    private String     bulan        ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="TGL_TRX")
    private Date       tglTrx       ;

    @Column(name="QTY_SCAN")
    private BigDecimal qtyScan      ;

    @Column(name="KD_GUDANG", length=4)
    private String     kdGudang     ;

    @Column(name="ST_TARIK", length=1)
    private String     stTarik      ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATEDATE")
    private Date       createdate   ;

    @Column(name="CREATEBY", length=40)
    private String     createby     ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="MODIDATE")
    private Date       modidate     ;

    @Column(name="MODIBY", length=40)
    private String     modiby       ;

    @Column(name="KETERANGAN", length=100)
    private String     keterangan   ;

    @Column(name="ST_DEL", length=1)
    private String     stDel      ;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public WhsStoScanEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setKdTrx( BigDecimal kdTrx ) {
        this.kdTrx = kdTrx ;
    }
    public BigDecimal getKdTrx() {
        return this.kdTrx;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : NO_BARCODE ( VARCHAR2 ) 
    public void setNoBarcode( String noBarcode ) {
        this.noBarcode = noBarcode;
    }
    public String getNoBarcode() {
        return this.noBarcode;
    }

    //--- DATABASE MAPPING : TAHUN ( VARCHAR2 ) 
    public void setTahun( String tahun ) {
        this.tahun = tahun;
    }
    public String getTahun() {
        return this.tahun;
    }

    //--- DATABASE MAPPING : BULAN ( VARCHAR2 ) 
    public void setBulan( String bulan ) {
        this.bulan = bulan;
    }
    public String getBulan() {
        return this.bulan;
    }

    //--- DATABASE MAPPING : TGL_TRX ( DATE ) 
    public void setTglTrx( Date tglTrx ) {
        this.tglTrx = tglTrx;
    }
    public Date getTglTrx() {
        return this.tglTrx;
    }

    //--- DATABASE MAPPING : QTY_SCAN ( NUMBER ) 
    public void setQtyScan( BigDecimal qtyScan ) {
        this.qtyScan = qtyScan;
    }
    public BigDecimal getQtyScan() {
        return this.qtyScan;
    }

    //--- DATABASE MAPPING : KD_GUDANG ( VARCHAR2 ) 
    public void setKdGudang( String kdGudang ) {
        this.kdGudang = kdGudang;
    }
    public String getKdGudang() {
        return this.kdGudang;
    }

    //--- DATABASE MAPPING : ST_TARIK ( CHAR ) 
    public void setStTarik( String stTarik ) {
        this.stTarik = stTarik;
    }
    public String getStTarik() {
        return this.stTarik;
    }

    //--- DATABASE MAPPING : CREATEDATE ( DATE ) 
    public void setCreatedate( Date createdate ) {
        this.createdate = createdate;
    }
    public Date getCreatedate() {
        return this.createdate;
    }

    //--- DATABASE MAPPING : CREATEBY ( VARCHAR2 ) 
    public void setCreateby( String createby ) {
        this.createby = createby;
    }
    public String getCreateby() {
        return this.createby;
    }

    //--- DATABASE MAPPING : MODIDATE ( DATE ) 
    public void setModidate( Date modidate ) {
        this.modidate = modidate;
    }
    public Date getModidate() {
        return this.modidate;
    }

    //--- DATABASE MAPPING : MODIBY ( VARCHAR2 ) 
    public void setModiby( String modiby ) {
        this.modiby = modiby;
    }
    public String getModiby() {
        return this.modiby;
    }

    //--- DATABASE MAPPING : KETERANGAN ( VARCHAR2 ) 
    public void setKeterangan( String keterangan ) {
        this.keterangan = keterangan;
    }
    public String getKeterangan() {
        return this.keterangan;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    public String getStDel() {
		return stDel;
	}

	public void setStDel(String stDel) {
		this.stDel = stDel;
	}

	//----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(kdTrx);
        sb.append("]:"); 
        sb.append(noBarcode);
        sb.append("|");
        sb.append(tahun);
        sb.append("|");
        sb.append(bulan);
        sb.append("|");
        sb.append(tglTrx);
        sb.append("|");
        sb.append(qtyScan);
        sb.append("|");
        sb.append(kdGudang);
        sb.append("|");
        sb.append(stTarik);
        sb.append("|");
        sb.append(createdate);
        sb.append("|");
        sb.append(createby);
        sb.append("|");
        sb.append(modidate);
        sb.append("|");
        sb.append(modiby);
        sb.append("|");
        sb.append(keterangan);
        return sb.toString(); 
    } 

}
