/*
 * Created on 4 Jan 2016 ( Time 13:49:29 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.gkd.springwebgkd.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.util.Date;

public class TagLpb implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@NotNull
	@Size(min = 1, max = 20)
	private String noReg;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@NotNull
	private Date tglReg;

	@NotNull
	@Size(min = 1, max = 21)
	private String noLpb;

	@NotNull
	@Size(min = 1, max = 20)
	private String kodeBrg;

	@Size(max = 5)
	private String kelLap;

	@Size(max = 20)
	private String noPp;

	private BigDecimal qty;

	@Size(max = 10)
	private String creaby;

	private Date dtcrea;

	@Size(max = 10)
	private String modiby;

	private Date dtmodi;

	@Size(max = 10)
	private String kdDok;

	@Size(max = 12)
	private String noFifo;

	@Size(max = 200)
	private String kdLokasi;

	@Size(max = 500)
	private String keterangan;

	@Size(max = 3)
	private String kdSite;

	private BigDecimal qtyBpb;

	@Size(max = 1)
	private String stOpen;

	private BigDecimal jmlPrint;

	@Size(max = 100)
	private String nmBrg;

	@Size(max = 100)
	private String model;

	@Size(max = 10)
	private String kdGroup;

	@Size(max = 100)
	private String nmGroup;

	private BigDecimal qtyAkhir;
	private BigDecimal qtySupply;
	private BigDecimal qtyRetur;
	private BigDecimal qtySupplyx;

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public void setNoReg(String noReg) {
		this.noReg = noReg;
	}

	public String getNoReg() {
		return this.noReg;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	public String getNmBrg() {
		return nmBrg;
	}

	public void setNmBrg(String nmBrg) {
		this.nmBrg = nmBrg;
	}

	public void setTglReg(Date tglReg) {
		this.tglReg = tglReg;
	}

	public Date getTglReg() {
		return this.tglReg;
	}

	public void setNoLpb(String noLpb) {
		this.noLpb = noLpb;
	}

	public String getNoLpb() {
		return this.noLpb;
	}

	public void setKodeBrg(String kodeBrg) {
		this.kodeBrg = kodeBrg;
	}

	public String getKodeBrg() {
		return this.kodeBrg;
	}

	public void setKelLap(String kelLap) {
		this.kelLap = kelLap;
	}

	public String getKelLap() {
		return this.kelLap;
	}

	public void setNoPp(String noPp) {
		this.noPp = noPp;
	}

	public String getNoPp() {
		return this.noPp;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getQty() {
		return this.qty;
	}

	public BigDecimal getQtySupply() {
		return qtySupply;
	}

	public void setQtySupply(BigDecimal qtySupply) {
		this.qtySupply = qtySupply;
	}

	public void setCreaby(String creaby) {
		this.creaby = creaby;
	}

	public String getCreaby() {
		return this.creaby;
	}

	public void setDtcrea(Date dtcrea) {
		this.dtcrea = dtcrea;
	}

	public Date getDtcrea() {
		return this.dtcrea;
	}

	public void setModiby(String modiby) {
		this.modiby = modiby;
	}

	public String getModiby() {
		return this.modiby;
	}

	public void setDtmodi(Date dtmodi) {
		this.dtmodi = dtmodi;
	}

	public Date getDtmodi() {
		return this.dtmodi;
	}

	public void setKdDok(String kdDok) {
		this.kdDok = kdDok;
	}

	public String getKdDok() {
		return this.kdDok;
	}

	public void setNoFifo(String noFifo) {
		this.noFifo = noFifo;
	}

	public String getNoFifo() {
		return this.noFifo;
	}

	public void setKdLokasi(String kdLokasi) {
		this.kdLokasi = kdLokasi;
	}

	public String getKdLokasi() {
		return this.kdLokasi;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKdSite(String kdSite) {
		this.kdSite = kdSite;
	}

	public String getKdSite() {
		return this.kdSite;
	}

	public void setQtyBpb(BigDecimal qtyBpb) {
		this.qtyBpb = qtyBpb;
	}

	public BigDecimal getQtyBpb() {
		return this.qtyBpb;
	}

	public void setStOpen(String stOpen) {
		this.stOpen = stOpen;
	}

	public String getStOpen() {
		return this.stOpen;
	}

	public void setJmlPrint(BigDecimal jmlPrint) {
		this.jmlPrint = jmlPrint;
	}

	public BigDecimal getJmlPrint() {
		return this.jmlPrint;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------

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

	public BigDecimal getQtyRetur() {
		return qtyRetur;
	}

	public void setQtyRetur(BigDecimal qtyRetur) {
		this.qtyRetur = qtyRetur;
	}

	public BigDecimal getQtySupplyx() {
		return qtySupplyx;
	}

	public void setQtySupplyx(BigDecimal qtySupplyx) {
		this.qtySupplyx = qtySupplyx;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(noReg);
		sb.append("|");
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
