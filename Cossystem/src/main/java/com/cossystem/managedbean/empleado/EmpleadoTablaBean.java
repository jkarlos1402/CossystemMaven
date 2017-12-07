package com.cossystem.managedbean.empleado;

import com.cossystem.core.exception.CossException;
import com.cossystem.core.exception.DAOException;
import com.cossystem.core.exception.DataBaseException;
import com.cossystem.managedbean.PrincipalBean;
import com.cossystem.managedbean.GenericTablaBean;
import com.cossystem.util.Propiedades;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class EmpleadoTablaBean extends GenericTablaBean implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{principalBean}")
    private PrincipalBean principalBean;
    
    @ManagedProperty(value = "#{filtroEmpleadoBean}")
    FiltroEmpleadoBean filtroEmpleadoBean;
    
    @ManagedProperty(value = "#{frmEmpleadoBean}")
    FrmEmpleadoBean frmEmpleadoBean;

    /**
     * Creates a new instance of AlertasBean
     */
    public EmpleadoTablaBean() {
        super();
    }
    
    @Override
    public void init() {
        super.init();
        refreshElementoCatalogo();
    }
    
    public PrincipalBean getPrincipalBean() {
        return principalBean;
    }
    
    public void setPrincipalBean(PrincipalBean principalBean) {
        this.principalBean = principalBean;
    }
    
    public FiltroEmpleadoBean getFiltroEmpleadoBean() {
        return filtroEmpleadoBean;
    }
    
    public void setFiltroEmpleadoBean(FiltroEmpleadoBean filtroEmpleadoBean) {
        this.filtroEmpleadoBean = filtroEmpleadoBean;
    }
    
    public FrmEmpleadoBean getFrmEmpleadoBean() {
        return frmEmpleadoBean;
    }
    
    public void setFrmEmpleadoBean(FrmEmpleadoBean frmEmpleadoBean) {
        this.frmEmpleadoBean = frmEmpleadoBean;
    }
    
    public void todosLosRegistros() {
        super.todosLosRegistros(frmEmpleadoBean, filtroEmpleadoBean);
    }

    public void handleFileExcel(FileUploadEvent event) {
        super.handleFileExcel(event, frmEmpleadoBean, filtroEmpleadoBean);
    }
    
    public StreamedContent getFileExcel() {
        return super.getFileExcel(filtroEmpleadoBean);
    }
    
    public String generaExcelElemento() throws DAOException, DataBaseException, CossException, IOException {
        return super.generaExcelElemento(filtroEmpleadoBean);
    }
    
    public void eliminaElemento(String nombreTabla) {
        FacesMessage message;
        Properties properties;
        try {
            properties = Propiedades.obtienePropiedades();
            campoIdElemento.setAccessible(true);
            Integer idEliminado = campoIdElemento.getInt(elementoSeleccionado);
            super.eliminaElemento(nombreTabla, frmEmpleadoBean, filtroEmpleadoBean);
            File dirFotos = new File(properties.getProperty("fotosPath") + File.separator + idEliminado);
            if (dirFotos.isDirectory()) {
                dirFotos.delete();
            }
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El elemento ha sido eliminado");
        } catch (IOException | IllegalArgumentException | IllegalAccessException ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void cerroDialogElemento(String nombreFormulario) {
        super.cerroDialogElemento(nombreFormulario, frmEmpleadoBean);
    }
    
    public void refreshElementoCatalogo() {
        super.refreshElementoCatalogo(frmEmpleadoBean, filtroEmpleadoBean, true);
    }
    
    public void nuevoElemento() {
        super.nuevoElemento(frmEmpleadoBean);
    }
    
    public void openDialogFrmElemento(String nombreDialog, String tipoOperacion) {
        super.openDialogFrmElemento(nombreDialog, tipoOperacion, frmEmpleadoBean, filtroEmpleadoBean);
    }
}
