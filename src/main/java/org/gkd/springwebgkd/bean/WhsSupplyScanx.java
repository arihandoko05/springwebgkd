/*
 * Created on 4 Mar 2016 ( Time 09:42:50 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.gkd.springwebgkd.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;

public class WhsSupplyScanx implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private BigDecimal kdTrx;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @NotNull
    @Size( min = 1, max = 30 )
    private String noBarcode;

    @NotNull
    @Size( min = 1, max = 4 )
    private String tahun;

    @NotNull
    @Size( min = 1, max = 2 )
    private String bulan;


    private Date tanggalTrx;


    private BigDecimal qtyBpb;

    @Size( max = 50 )
    private String locScan;

    @Size( max = 3 )
    private String stApprove;

    @Size( max = 20 )
    private String kdLine;

    @Size( max = 20 )
    private String noDps;

    @Size( max = 6 )
    private String picProd;

    @Size( max = 200 )
    private String keterangan;


    private Date createdate;

    @Size( max = 10 )
    private String createby;


    private Date modidate;

    @Size( max = 10 )
    private String modiby;

    @Size( max = 20 )
    private String kdMesin;

    @Size( max = 3 )
    private String kdGudang;

    @Size( max = 21 )
    private String noBpb;


    private BigDecimal qtyRetur;


    private BigDecimal qtySupply;



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
    public void setNoBarcode( String noBarcode ) {
        this.noBarcode = noBarcode;
    }
    public String getNoBarcode() {
        return this.noBarcode;
    }

    public void setTahun( String tahun ) {
        this.tahun = tahun;
    }
    public String getTahun() {
        return this.tahun;
    }

    public void setBulan( String bulan ) {
        this.bulan = bulan;
    }
    public String getBulan() {
        return this.bulan;
    }

    public void setTanggalTrx( Date tanggalTrx ) {
        this.tanggalTrx = tanggalTrx;
    }
    public Date getTanggalTrx() {
        return this.tanggalTrx;
    }

    public void setQtyBpb( BigDecimal qtyBpb ) {
        this.qtyBpb = qtyBpb;
    }
    public BigDecimal getQtyBpb() {
        return this.qtyBpb;
    }

    public void setLocScan( String locScan ) {
        this.locScan = locScan;
    }
    public String getLocScan() {
        return this.locScan;
    }

    public void setStApprove( String stApprove ) {
        this.stApprove = stApprove;
    }
    public String getStApprove() {
        return this.stApprove;
    }

    public void setKdLine( String kdLine ) {
        this.kdLine = kdLine;
    }
    public String getKdLine() {
        return this.kdLine;
    }

    public void setNoDps( String noDps ) {
        this.noDps = noDps;
    }
    public String getNoDps() {
        return this.noDps;
    }

    public void setPicProd( String picProd ) {
        this.picProd = picProd;
    }
    public String getPicProd() {
        return this.picProd;
    }

    public void setKeterangan( String keterangan ) {
        this.keterangan = keterangan;
    }
    public String getKeterangan() {
        return this.keterangan;
    }

    public void setCreatedate( Date createdate ) {
        this.createdate = createdate;
    }
    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreateby( String createby ) {
        this.createby = createby;
    }
    public String getCreateby() {
        return this.createby;
    }

    public void setModidate( Date modidate ) {
        this.modidate = modidate;
    }
    public Date getModidate() {
        return this.modidate;
    }

    public void setModiby( String modiby ) {
        this.modiby = modiby;
    }
    public String getModiby() {
        return this.modiby;
    }

    public void setKdMesin( String kdMesin ) {
        this.kdMesin = kdMesin;
    }
    public String getKdMesin() {
        return this.kdMesin;
    }

    public void setKdGudang( String kdGudang ) {
        this.kdGudang = kdGudang;
    }
    public String getKdGudang() {
        return this.kdGudang;
    }

    public void setNoBpb( String noBpb ) {
        this.noBpb = noBpb;
    }
    public String getNoBpb() {
        return this.noBpb;
    }

    public void setQtyRetur( BigDecimal qtyRetur ) {
        this.qtyRetur = qtyRetur;
    }
    public BigDecimal getQtyRetur() {
        return this.qtyRetur;
    }

    public void setQtySupply( BigDecimal qtySupply ) {
        this.qtySupply = qtySupply;
    }
    public BigDecimal getQtySupply() {
        return this.qtySupply;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(noBarcode);
        sb.append("|");
        sb.append(tahun);
        sb.append("|");
        sb.append(bulan);
        sb.append("|");
        sb.append(tanggalTrx);
        sb.append("|");
        sb.append(qtyBpb);
        sb.append("|");
        sb.append(locScan);
        sb.append("|");
        sb.append(stApprove);
        sb.append("|");
        sb.append(kdLine);
        sb.append("|");
        sb.append(noDps);
        sb.append("|");
        sb.append(picProd);
        sb.append("|");
        sb.append(keterangan);
        sb.append("|");
        sb.append(createdate);
        sb.append("|");
        sb.append(createby);
        sb.append("|");
        sb.append(modidate);
        sb.append("|");
        sb.append(modiby);
        sb.append("|");
        sb.append(kdTrx);
        sb.append("|");
        sb.append(kdMesin);
        sb.append("|");
        sb.append(kdGudang);
        sb.append("|");
        sb.append(noBpb);
        sb.append("|");
        sb.append(qtyRetur);
        sb.append("|");
        sb.append(qtySupply);
        return sb.toString(); 
    } 


}
