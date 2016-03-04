/*
 * Created on 4 Jan 2016 ( Time 13:12:42 )
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

import org.hibernate.annotations.Formula;

/**
 * Persistent class for entity stored in table "TAG_LPB"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="TAG_LPB", schema="USRGKDMFG" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="TagLpbEntity.countAll", query="SELECT COUNT(x) FROM TagLpbEntity x" )
} )
public class TagLpbEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="NO_REG", nullable=false, length=20)
    private String     noReg        ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Temporal(TemporalType.DATE)
    @Column(name="TGL_REG", nullable=false)
    private Date       tglReg       ;

    @Column(name="NO_LPB", nullable=false, length=21)
    private String     noLpb        ;

    @Column(name="KODE_BRG", nullable=false, length=20)
    private String     kodeBrg      ;

    @Column(name="KEL_LAP", length=5)
    private String     kelLap       ;

    @Column(name="NO_PP", length=20)
    private String     noPp         ;

    @Column(name="QTY")
    private BigDecimal qty          ;

    @Column(name="CREABY", length=10)
    private String     creaby       ;

    @Temporal(TemporalType.DATE)
    @Column(name="DTCREA")
    private Date       dtcrea       ;

    @Column(name="MODIBY", length=10)
    private String     modiby       ;

    @Temporal(TemporalType.DATE)
    @Column(name="DTMODI")
    private Date       dtmodi       ;

    @Column(name="KD_DOK", length=10)
    private String     kdDok        ;

    @Column(name="NO_FIFO", length=12)
    private String     noFifo       ;

    @Column(name="KD_LOKASI", length=200)
    private String     kdLokasi     ;

    @Column(name="KETERANGAN", length=500)
    private String     keterangan   ;

    @Column(name="KD_SITE", length=3)
    private String     kdSite       ;

    @Column(name="QTY_BPB")
    private BigDecimal qtyBpb       ;

    @Column(name="ST_OPEN", length=1)
    private String     stOpen       ;

    @Column(name="JML_PRINT")
    private BigDecimal jmlPrint     ;

    @Formula("(SELECT FNM_ITEM(KODE_BRG) from dual)")
    private String nmBrg;
    
    @Formula("(SELECT FNM_Model(KODE_BRG) from dual)")
    private String model;
    
    @Formula("(SELECT FGROUP(KODE_BRG) from dual)")
    private String kdGroup;
    
    @Formula("(SELECT FNM_GROUP(FGROUP(KODE_BRG)) from dual)")
    private String nmGroup;
    
    @Formula("(SELECT FWHS_MUTASI_TAG(TO_CHAR(TGL_REG, 'YYYY'), TO_CHAR(TGL_REG, 'MM'), KD_SITE, NO_REG, 'QTY_AKHIR') from dual)")
    private BigDecimal qtyAkhir;
    
    @Formula("(SELECT FQTY_SCAN_SUPPLY(NO_REG, KD_SITE) from dual)")
    private BigDecimal qtySupply;
    
    @Formula("(SELECT FQTY_SCAN_SUPPLYX(NO_REG, KD_SITE) from dual)")
    private BigDecimal qtySupplyx;
    
    @Formula("(SELECT FQTY_SCAN_RETURX(NO_REG, KD_SITE) from dual)")
    private BigDecimal qtyRetur;
    
    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public TagLpbEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setNoReg( String noReg ) {
        this.noReg = noReg ;
    }
    public String getNoReg() {
        return this.noReg;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : TGL_REG ( DATE ) 
    public void setTglReg( Date tglReg ) {
        this.tglReg = tglReg;
    }
    public Date getTglReg() {
        return this.tglReg;
    }

    //--- DATABASE MAPPING : NO_LPB ( VARCHAR2 ) 
    public void setNoLpb( String noLpb ) {
        this.noLpb = noLpb;
    }
    public String getNoLpb() {
        return this.noLpb;
    }

    //--- DATABASE MAPPING : KODE_BRG ( VARCHAR2 ) 
    public void setKodeBrg( String kodeBrg ) {
        this.kodeBrg = kodeBrg;
    }
    public String getKodeBrg() {
        return this.kodeBrg;
    }

    //--- DATABASE MAPPING : KEL_LAP ( VARCHAR2 ) 
    public void setKelLap( String kelLap ) {
        this.kelLap = kelLap;
    }
    public String getKelLap() {
        return this.kelLap;
    }

    //--- DATABASE MAPPING : NO_PP ( VARCHAR2 ) 
    public void setNoPp( String noPp ) {
        this.noPp = noPp;
    }
    public String getNoPp() {
        return this.noPp;
    }

    //--- DATABASE MAPPING : QTY ( NUMBER ) 
    public void setQty( BigDecimal qty ) {
        this.qty = qty;
    }
    public BigDecimal getQty() {
        return this.qty;
    }

    //--- DATABASE MAPPING : CREABY ( VARCHAR2 ) 
    public void setCreaby( String creaby ) {
        this.creaby = creaby;
    }
    public String getCreaby() {
        return this.creaby;
    }

    //--- DATABASE MAPPING : DTCREA ( DATE ) 
    public void setDtcrea( Date dtcrea ) {
        this.dtcrea = dtcrea;
    }
    public Date getDtcrea() {
        return this.dtcrea;
    }

    //--- DATABASE MAPPING : MODIBY ( VARCHAR2 ) 
    public void setModiby( String modiby ) {
        this.modiby = modiby;
    }
    public String getModiby() {
        return this.modiby;
    }

    //--- DATABASE MAPPING : DTMODI ( DATE ) 
    public void setDtmodi( Date dtmodi ) {
        this.dtmodi = dtmodi;
    }
    public Date getDtmodi() {
        return this.dtmodi;
    }

    //--- DATABASE MAPPING : KD_DOK ( VARCHAR2 ) 
    public void setKdDok( String kdDok ) {
        this.kdDok = kdDok;
    }
    public String getKdDok() {
        return this.kdDok;
    }

    //--- DATABASE MAPPING : NO_FIFO ( VARCHAR2 ) 
    public void setNoFifo( String noFifo ) {
        this.noFifo = noFifo;
    }
    public String getNoFifo() {
        return this.noFifo;
    }

    //--- DATABASE MAPPING : KD_LOKASI ( VARCHAR2 ) 
    public void setKdLokasi( String kdLokasi ) {
        this.kdLokasi = kdLokasi;
    }
    public String getKdLokasi() {
        return this.kdLokasi;
    }

    //--- DATABASE MAPPING : KETERANGAN ( VARCHAR2 ) 
    public void setKeterangan( String keterangan ) {
        this.keterangan = keterangan;
    }
    public String getKeterangan() {
        return this.keterangan;
    }

    //--- DATABASE MAPPING : KD_SITE ( VARCHAR2 ) 
    public void setKdSite( String kdSite ) {
        this.kdSite = kdSite;
    }
    public String getKdSite() {
        return this.kdSite;
    }

    //--- DATABASE MAPPING : QTY_BPB ( NUMBER ) 
    public void setQtyBpb( BigDecimal qtyBpb ) {
        this.qtyBpb = qtyBpb;
    }
    public BigDecimal getQtyBpb() {
        return this.qtyBpb;
    }

    //--- DATABASE MAPPING : ST_OPEN ( CHAR ) 
    public void setStOpen( String stOpen ) {
        this.stOpen = stOpen;
    }
    public String getStOpen() {
        return this.stOpen;
    }

    //--- DATABASE MAPPING : JML_PRINT ( NUMBER ) 
    public void setJmlPrint( BigDecimal jmlPrint ) {
        this.jmlPrint = jmlPrint;
    }
    public BigDecimal getJmlPrint() {
        return this.jmlPrint;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getKdGroup() {
		return kdGroup;
	}

	public void setKdGroup(String kdGroup) {
		this.kdGroup = kdGroup;
	}

	public String getNmGroup() {
		return nmGroup;
	}

	public void setNmGroup(String nmGroup) {
		this.nmGroup = nmGroup;
	}

	public BigDecimal getQtyAkhir() {
		return qtyAkhir;
	}

	public void setQtyAkhir(BigDecimal qtyAkhir) {
		this.qtyAkhir = qtyAkhir;
	}

	public String getNmBrg() {
		return nmBrg;
	}

	public void setNmBrg(String nmBrg) {
		this.nmBrg = nmBrg;
	}

	public BigDecimal getQtySupply() {
		return qtySupply;
	}

	public void setQtySupply(BigDecimal qtySupply) {
		this.qtySupply = qtySupply;
	}

	public BigDecimal getQtySupplyx() {
		return qtySupplyx;
	}

	public void setQtySupplyx(BigDecimal qtySupplyx) {
		this.qtySupplyx = qtySupplyx;
	}

	public BigDecimal getQtyRetur() {
		return qtyRetur;
	}

	public void setQtyRetur(BigDecimal qtyRetur) {
		this.qtyRetur = qtyRetur;
	}

	//----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(noReg);
        sb.append("]:"); 
        sb.append(tglReg);
        sb.append("|");
        sb.append(noLpb);
        sb.append("|");
        sb.append(kodeBrg);
        sb.append("|");
        sb.append(kelLap);
        sb.append("|");
        sb.append(noPp);
        sb.append("|");
        sb.append(qty);
        sb.append("|");
        sb.append(creaby);
        sb.append("|");
        sb.append(dtcrea);
        sb.append("|");
        sb.append(modiby);
        sb.append("|");
        sb.append(dtmodi);
        sb.append("|");
        sb.append(kdDok);
        sb.append("|");
        sb.append(noFifo);
        sb.append("|");
        sb.append(kdLokasi);
        sb.append("|");
        sb.append(keterangan);
        sb.append("|");
        sb.append(kdSite);
        sb.append("|");
        sb.append(qtyBpb);
        sb.append("|");
        sb.append(stOpen);
        sb.append("|");
        sb.append(jmlPrint);
        return sb.toString(); 
    } 

}
