/*
 * Created on 18 Des 2015 ( Time 13:48:59 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.gkd.springwebgkd.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "WEBGKD_USERSEC"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="WEBGKD_USERSEC", schema="USRGKDMFG" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="WebgkdUsersecEntity.countAll", query="SELECT COUNT(x) FROM WebgkdUsersecEntity x" ),
  @NamedQuery(name = WebgkdUsersecEntity.FIND_BY_EMAIL, query = "select a from WebgkdUsersecEntity a where a.email = :email")
} )
public class WebgkdUsersecEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_BY_EMAIL = "WebgkdUsersecEntity.findByEmail";

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="EMAIL", nullable=false, length=40)
    private String     email        ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="PASSWORD", nullable=false, length=30)
    private String     password     ;

    @Column(name="USERID", nullable=false, length=10)
    private String     userid       ;

    @Column(name="NAMA", length=100)
    private String     nama         ;

    @Column(name="ST_ACTIVE", length=1)
    private String     stActive     ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="TGL_REGISTER")
    private Date       tglRegister  ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="TGL_INACTIVE")
    private Date       tglInactive  ;

    @Column(name="DESCRIPTION", length=200)
    private String     description  ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LAST_UPDATE", length=20)
    private Date     lastUpdate   ;

    @Column(name="ST_USER", length=1)
    private String     stUser       ;

    @Column(name="NPK", length=10)
    private String     npk  ;

    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public WebgkdUsersecEntity() {
		super();
    }
    
    public WebgkdUsersecEntity(String email, String password, String stUser, String npk) {
		this.email = email;
		this.password = password;
		this.stUser = stUser;
		this.lastUpdate = new Date();
		this.userid = email;
		this.stActive = "1";
		this.npk = npk;
	}
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setEmail( String email ) {
        this.email = email ;
    }
    public String getEmail() {
        return this.email;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : PASSWORD ( VARCHAR2 ) 
    public void setPassword( String password ) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    //--- DATABASE MAPPING : USERID ( VARCHAR2 ) 
    public void setUserid( String userid ) {
        this.userid = userid;
    }
    public String getUserid() {
        return this.userid;
    }

    //--- DATABASE MAPPING : NAMA ( VARCHAR2 ) 
    public void setNama( String nama ) {
        this.nama = nama;
    }
    public String getNama() {
        return this.nama;
    }

    //--- DATABASE MAPPING : ST_ACTIVE ( CHAR ) 
    public void setStActive( String stActive ) {
        this.stActive = stActive;
    }
    public String getStActive() {
        return this.stActive;
    }

    //--- DATABASE MAPPING : TGL_REGISTER ( DATE ) 
    public void setTglRegister( Date tglRegister ) {
        this.tglRegister = tglRegister;
    }
    public Date getTglRegister() {
        return this.tglRegister;
    }

    //--- DATABASE MAPPING : TGL_INACTIVE ( DATE ) 
    public void setTglInactive( Date tglInactive ) {
        this.tglInactive = tglInactive;
    }
    public Date getTglInactive() {
        return this.tglInactive;
    }

    //--- DATABASE MAPPING : DESCRIPTION ( VARCHAR2 ) 
    public void setDescription( String description ) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    //--- DATABASE MAPPING : LAST_UPDATE ( VARCHAR2 ) 
    public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

    //--- DATABASE MAPPING : ST_USER ( CHAR ) 
    public void setStUser( String stUser ) {
        this.stUser = stUser;
    }

	public String getStUser() {
        return this.stUser;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    public String getNpk() {
		return npk;
	}

	public void setNpk(String npk) {
		this.npk = npk;
	}

	//----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(email);
        sb.append("]:"); 
        sb.append(password);
        sb.append("|");
        sb.append(userid);
        sb.append("|");
        sb.append(nama);
        sb.append("|");
        sb.append(stActive);
        sb.append("|");
        sb.append(tglRegister);
        sb.append("|");
        sb.append(tglInactive);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(lastUpdate);
        sb.append("|");
        sb.append(stUser);
        return sb.toString(); 
    } 

}