/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cossystem.core.pojos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TMXIDSJPINAM
 */
@Entity
@Table(name = "tbl_PROSPECTO_ARCHIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPROSPECTOARCHIVO.findAll", query = "SELECT t FROM TblPROSPECTOARCHIVO t")})
public class TblPROSPECTOARCHIVO implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDARCHIVO")
    private Integer idarchivo;
    @Column(name = "IDEMPRESA")
    private Integer idempresa;
    @Column(name = "IDTIPOVINCULO")
    private Integer idtipovinculo;
    @Column(name = "Titulo")
    private String titulo;
    @Column(name = "VINCULO")
    private String vinculo;
    @Column(name = "IdEmpleado")
    private Integer idEmpleado;
    @JoinColumn(name = "IDPROSPECTO_ENC", referencedColumnName = "IDPROSPECTO_ENC")
    @ManyToOne(optional = false)
    private TblPROSPECTO idprospectoEnc;

    public TblPROSPECTOARCHIVO() {
    }

    public TblPROSPECTOARCHIVO(Integer idarchivo) {
        this.idarchivo = idarchivo;
    }

    public Integer getIdarchivo() {
        return idarchivo;
    }

    public void setIdarchivo(Integer idarchivo) {
        this.idarchivo = idarchivo;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Integer getIdtipovinculo() {
        return idtipovinculo;
    }

    public void setIdtipovinculo(Integer idtipovinculo) {
        this.idtipovinculo = idtipovinculo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @XmlTransient
    public TblPROSPECTO getIdprospectoEnc() {
        return idprospectoEnc;
    }

    public void setIdprospectoEnc(TblPROSPECTO idprospectoEnc) {
        this.idprospectoEnc = idprospectoEnc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idarchivo != null ? idarchivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPROSPECTOARCHIVO)) {
            return false;
        }
        TblPROSPECTOARCHIVO other = (TblPROSPECTOARCHIVO) object;
        if ((this.idarchivo == null && other.idarchivo != null) || (this.idarchivo != null && !this.idarchivo.equals(other.idarchivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cossystem.core.pojos.prospecto.TblPROSPECTOARCHIVO[ idarchivo=" + idarchivo + " ]";
    }

}
