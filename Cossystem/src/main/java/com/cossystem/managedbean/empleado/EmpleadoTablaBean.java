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
//    @PostConstruct
//    public void init() {
//        usuarioSesion = principalBean.getUsuarioSesion();
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        Map<String, String> parametros = externalContext.getRequestParameterMap();
//        for (String param : parametros.keySet()) {
//            System.out.println("parametro: " + param + " - " + parametros.get(param));
//        }
//        if (parametros.containsKey("idMenu")) {
//            idMenu = Integer.parseInt((String) parametros.get("idMenu"));
//        }
//        List<TblAccesoPantallas> menu = principalBean.getMenuCompleto();
//        TblAccesoPantallas menuTemp = new TblAccesoPantallas();
//        menuTemp.setIdMenu(idMenu);
//        System.out.println("idMenu: " + idMenu);
//        menuOrigen = obtieneMenu(menuTemp, menu);
//        System.out.println("menu origen: " + menuOrigen);
//        configuracionPantalla = Configuracion.obtieneConfiguracion(menuOrigen.getIdTablaVist());
//        if (menuOrigen.getIdTablaVist().equals(menuOrigen.getIdTableTran())) {
//            configuracionPantallaTransaccional = configuracionPantalla;
//        } else {
//            configuracionPantallaTransaccional = Configuracion.obtieneConfiguracion(menuOrigen.getIdTableTran());
//        }
//        if (configuracionPantalla != null && !configuracionPantalla.isEmpty()) {
//            String[] nombreTablaArray = configuracionPantalla.get(0).getNTabla().split("_");
//            String nombreClase = "";
//            for (String nombreTablaParcial : nombreTablaArray) {
//                nombreClase += nombreTablaParcial.replaceFirst((nombreTablaParcial.charAt(0) + ""), (nombreTablaParcial.charAt(0) + "").toUpperCase());
//            }
//            Field[] camposElemento;
//            if (!"".equals(nombreClase)) {
//                try {
//                    claseElemento = Class.forName("com.cossystem.core.pojos." + nombreClase);
//                    camposElemento = claseElemento.getDeclaredFields();
//                    for (Field campoElemento : camposElemento) {
//                        if (campoElemento.isAnnotationPresent(Id.class)) {
//                            campoIdElemento = campoElemento;
//                            break;
//                        }
//                    }
//                } catch (ClassNotFoundException ex) {
//                    claseElemento = null;
//                }
//            }
//            nombreTablaArray = configuracionPantallaTransaccional.get(0).getNTabla().split("_");
//            nombreClase = "";
//            for (String nombreTablaParcial : nombreTablaArray) {
//                nombreClase += nombreTablaParcial.replaceFirst((nombreTablaParcial.charAt(0) + ""), (nombreTablaParcial.charAt(0) + "").toUpperCase());
//            }
//            if (!"".equals(nombreClase)) {
//                try {
//                    claseElementoTransaccional = Class.forName("com.cossystem.core.pojos." + nombreClase);
//                    camposElemento = claseElementoTransaccional.getDeclaredFields();
//                    for (Field campoElemento : camposElemento) {
//                        if (campoElemento.isAnnotationPresent(Id.class)) {
//                            campoIdElementoTransaccional = campoElemento;
//                            break;
//                        }
//                    }
//                } catch (ClassNotFoundException ex) {
//                    claseElementoTransaccional = null;
//                }
//            }
//
//            System.out.println("claseElemento ");
//            System.out.println(claseElemento != null ? claseElemento.getSimpleName() : claseElemento);
//        }
//        columnas = new ArrayList<>();
//        if (configuracionPantalla != null) {
//            for (TblAccesoPantallasCampos config : configuracionPantalla) {
//                if (config.getVisibleGrid()) {
//                    Field[] camposClase = claseElemento.getDeclaredFields();
//                    for (Field campoClase : camposClase) {
//                        if ((campoClase.isAnnotationPresent(Column.class) && ((Column) campoClase.getAnnotation(Column.class)).name().equals(config.getNColumna())) || (campoClase.isAnnotationPresent(JoinColumn.class) && ((JoinColumn) campoClase.getAnnotation(JoinColumn.class)).name().equals(config.getNColumna()))) {
//                            columnas.add(new Columnas(config.getDescripcion(), campoClase.getName()));
//                            break;
//                        }
//                    }
//
//                }
//            }
//        }
//        refreshElementoCatalogo();
//    }

//    private TblAccesoPantallas obtieneMenu(TblAccesoPantallas menuTemp, List<TblAccesoPantallas> menuTodo) {
//        TblAccesoPantallas menuObtenido = null;
//        for (TblAccesoPantallas tblAccesoPantallas : menuTodo) {
//            if (tblAccesoPantallas.equals(menuTemp)) {
//                menuObtenido = tblAccesoPantallas;
//                break;
//            }
//            if (tblAccesoPantallas.getMenusHijo() != null) {
//                menuObtenido = obtieneMenu(menuTemp, tblAccesoPantallas.getMenusHijo());
//                if (menuObtenido != null) {
//                    break;
//                }
//            }
//        }
//        return menuObtenido;
//    }
//
//    public void openDialogFrmElemento(final String nombreDialog, final String tipoOperacion) {
//        FacesMessage message = null;
//        switch (tipoOperacion) {
//            case "nuevo":
//                nuevoElemento();
//                RequestContext.getCurrentInstance().execute("PF('" + nombreDialog + "').show()");
//                break;
//            case "editar":
//                if (elementoSeleccionado != null) {
//                    empleadoFrmBean.setIdElemento(idElementoSeleccionado);
//                    empleadoFrmBean.setPermissionToWrite(true);
//                    empleadoFrmBean.init();
//                    empleadoFrmBean.setNombreAccionFrm("Cat\u00E1logo de Empleados - Editar registro");
//                    RequestContext.getCurrentInstance().execute("PF('" + nombreDialog + "').show()");
//                } else {
//                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenci\u00f3n", "Ning\u00FAn elemento seleccionado.");
//                }
//                if (message != null) {
//                    FacesContext.getCurrentInstance().addMessage(null, message);
//                }
//                break;
//            case "ver":
//                if (elementoSeleccionado != null) {
//                    empleadoFrmBean.setIdElemento(idElementoSeleccionado);
//                    empleadoFrmBean.setPermissionToWrite(false);
//                    empleadoFrmBean.init();
//                    empleadoFrmBean.setNombreAccionFrm("Cat\u00E1logo de Empleados - Ver registro");
//                    RequestContext.getCurrentInstance().execute("PF('" + nombreDialog + "').show()");
//                } else {
//                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenci\u00f3n", "Ning\u00FAn elemento seleccionado.");
//                }
//                if (message != null) {
//                    FacesContext.getCurrentInstance().addMessage(null, message);
//                }
//                break;
//            case "imprimir":
//                empleadoFrmBean.setNombreAccionFrm("Cat\u00E1logo de Empleados - Reporte");
//                //RequestContext.getCurrentInstance().execute("PF('" + nombreDialog + "').show()");
//                break;
//            case "cargaExcel":
//                empleadoFrmBean.setNombreAccionFrm("Cat\u00E1logo de Empleados - Carga de excel");
//                RequestContext.getCurrentInstance().execute("PF('" + nombreDialog + "').show()");
//                break;
//            case "consulta":
//                //empleadoFrmBean.setNombreAccionFrm("Cat\u00E1logo de Empleados - Consulta");
//                filtroEntidad.setIdClaseEntidad(menuOrigen.getIdTablaVist());
//                filtroEntidad.setClaseEntidad(claseElemento);
//                RequestContext.getCurrentInstance().execute("PF('" + nombreDialog + "').show()");
//                break;
//        }
//    }
//
//    public void nuevoElemento() {
//        elementoSeleccionado = null;
//        idElementoSeleccionado = null;
//        empleadoFrmBean.setIdElemento(null);
//        empleadoFrmBean.setPermissionToWrite(true);
//        empleadoFrmBean.init();
//        empleadoFrmBean.setNombreAccionFrm("Cat\u00E1logo de Empleados - Agregar registro");
//    }
//
//    public void refreshElementoCatalogo() {
//        FacesMessage message = null;
//        GenericDAO genericDAO = null;
//        Field[] camposClaseElemento = claseElemento.getDeclaredFields();
//        try {
//            Class claseDAO = null;
//            try {
//                claseDAO = Class.forName("com.cossystem.core.dao." + claseElemento.getSimpleName() + "DAO");
//            } catch (ClassNotFoundException ex) {
//                claseDAO = null;
//            }
//            if (claseDAO != null) {
//                genericDAO = (GenericDAO) claseDAO.newInstance();
//            } else {
//                genericDAO = new GenericDAO();
//            }
//            if (filtros == null) {
//                filtros = new ArrayList<>();
//            }
//            Filtro filtroTemp;
//            if (usuarioSesion.getIdEmpresa() != null) {
//                for (Field campo : camposClaseElemento) {
//                    if (campo.getType().getSimpleName().equals(usuarioSesion.getIdEmpresa().getClass().getSimpleName())) {
//                        filtroTemp = Filtro.generaFiltro(campo, usuarioSesion.getIdEmpresa(), null, claseElemento);
//                        filtroTemp.setVisible(false);
//                        filtros.add(filtroTemp);
//                        break;
//                    }
//                }
//            }
//            if (menuOrigen.getFiltro() != null) {
//                System.out.println("si tiene filtros");
//                String[] filtrosDefault = menuOrigen.getFiltro().split(",");
//                String[] componentesFiltro;
//                String nombreCampo;
//                System.out.println("tiene: " + filtrosDefault.length + " filtros");
//                for (String filtroDefault : filtrosDefault) {
//                    componentesFiltro = filtroDefault.split("=");
//                    if (componentesFiltro.length == 2) {
//                        System.out.println("si entro a filtro");
//                        System.out.println(componentesFiltro[0] + "- " + componentesFiltro[1]);
//                        for (Field campo : camposClaseElemento) {
//                            if (campo.isAnnotationPresent(Column.class)) {
//                                nombreCampo = ((Column) campo.getAnnotation(Column.class)).name();
//                            } else if (campo.isAnnotationPresent(JoinColumn.class)) {
//                                nombreCampo = ((JoinColumn) campo.getAnnotation(JoinColumn.class)).name();
//                            } else {
//                                nombreCampo = null;
//                            }
//                            System.out.println("nombre campo: " + nombreCampo);
//                            if (nombreCampo != null && componentesFiltro[0].trim().equalsIgnoreCase(nombreCampo)) {
//                                System.out.println("se econtro campo: " + nombreCampo);
//                                TblAccesoPantallasCampos confCampo = null;
//                                for (TblAccesoPantallasCampos conf : configuracionPantalla) {
//                                    if (conf.getNColumna().equalsIgnoreCase(nombreCampo)) {
//                                        confCampo = conf;
//                                        break;
//                                    }
//                                }
//                                filtroTemp = Filtro.generaFiltro(campo, componentesFiltro[1], campo.getType().getSimpleName().equals(Date.class.getSimpleName()) ? confCampo != null ? confCampo.getFormatoCampo() : null : null, claseElemento);
//                                filtroTemp.setVisible(false);
//                                filtros.add(filtroTemp);
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//            if (filtroEntidad != null && (filtroEntidad.getFiltros() == null || filtroEntidad.getFiltros() != null && filtroEntidad.getFiltros().isEmpty())) {
//                filtroEntidad.setFiltros(filtros);
//            }
//            elementoCatalogo = genericDAO.findByComponents(claseElemento, filtroEntidad != null ? filtroEntidad.getFiltros() : filtros);
//        } catch (DAOException | DataBaseException | InstantiationException | IllegalAccessException | ParseException ex) {
//            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
//        } finally {
//            if (genericDAO != null) {
//                genericDAO.closeDAO();
//            }
//        }
//        if (message != null) {
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
//    }
//
//    public void selectElemento() {
//        FacesMessage message = null;
//        Field[] campos = claseElemento.getClass().getDeclaredFields();
//        Method[] metodos = claseElemento.getClass().getMethods();
//        for (Field campo : campos) {
//            try {
//                if (campo.isAnnotationPresent(Id.class)) {
//                    for (Method metodo : metodos) {
//                        if (metodo.getName().equalsIgnoreCase("get" + campo.getName())) {
//                            idElementoSeleccionado = (Integer) metodo.invoke(elementoSeleccionado);
//                            break;
//                        }
//                    }
//                }
//            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException ex) {
//                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
//            }
//        }
//        if (message != null) {
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
//    }
//
//    public void cerroDialogElemento(String nombreFormulario) {
//        RequestContext.getCurrentInstance().reset(nombreFormulario);
//        empleadoFrmBean.cerrarConexion();
//    }
//
//    public void eliminaElemento(final String nombreTabla) {
//        FacesMessage message;
//        GenericDAO genericDAO = null;
//        Properties properties;
//        if (elementoSeleccionado != null) {
//            try {
//                properties = Propiedades.obtienePropiedades();
//                Class claseDAO = Configuracion.buscarClase("com.cossystem.core.dao", claseElementoTransaccional.getSimpleName() + "DAO");
//                if (claseDAO != null) {
//                    genericDAO = (GenericDAO) claseDAO.newInstance();
//                } else {
//                    genericDAO = new GenericDAO();
//                }
//                campoIdElemento.setAccessible(true);
//                Integer idEliminado = campoIdElemento.getInt(elementoSeleccionado);
//                genericDAO.deleteById(claseElementoTransaccional, idEliminado);
//                File dirFotos = new File(properties.getProperty("fotosPath") + File.separator + idEliminado);
//                if (dirFotos.isDirectory()) {
//                    dirFotos.delete();
//                }
//                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El elemento ha sido eliminado");
//            } catch (DAOException | DataBaseException | IOException | InstantiationException | IllegalAccessException ex) {
//                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
//            } finally {
//                if (genericDAO != null) {
//                    genericDAO.closeDAO();
//                }
//                refreshElementoCatalogo();
//                RequestContext.getCurrentInstance().execute("PF('" + nombreTabla + "').filter()");
//            }
//        } else {
//            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenci\u00F3n", "Ning\u00FAn elemento seleccionado");
//        }
//        FacesContext.getCurrentInstance().addMessage(null, message);
//    }
//
//    public String generaExcelElemento() throws DAOException, DataBaseException, CossException {
//        FacesContext context = FacesContext.getCurrentInstance();
//        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
//        String contextPathResources = servletContext.getRealPath("");
//        return contextPathResources + File.separator + "tempExcel" + File.separator + ManagerXLSX.generaArchivoExcel(claseElementoTransaccional, filtroEntidad != null ? filtroEntidad.getFiltros() : filtros, contextPathResources + File.separator + "tempExcel");
//    }
//
//    public Integer getIdElementoSeleccionado() {
//        return idElementoSeleccionado;
//    }
//
//    public void setIdElementoSeleccionado(Integer idElementoSeleccionado) {
//        this.idElementoSeleccionado = idElementoSeleccionado;
//    }
//
//    public Object getElementoSeleccionado() {
//        return elementoSeleccionado;
//    }
//
//    public void setElementoSeleccionado(Object elementoSeleccionado) {
//        this.elementoSeleccionado = elementoSeleccionado;
//    }
//
//    public List<?> getElementoCatalogo() {
//        return elementoCatalogo;
//    }
//
//    public CatUsuarios getUsuarioSesion() {
//        return usuarioSesion;
//    }
//
//    public PrincipalBean getPrincipalBean() {
//        return principalBean;
//    }
//
//    public void setPrincipalBean(PrincipalBean principalBean) {
//        this.principalBean = principalBean;
//    }
//
//    public EmpleadoFrmBean getEmpleadoFrmBean() {
//        return empleadoFrmBean;
//    }
//
//    public void setEmpleadoFrmBean(EmpleadoFrmBean empleadoFrmBean) {
//        this.empleadoFrmBean = empleadoFrmBean;
//    }
//
//    public StreamedContent getFileExcel() {
//        FacesMessage message = null;
//        String nombreArchivoExcel;
//        InputStream stream;
//        try {
//            nombreArchivoExcel = generaExcelElemento();
//            stream = new FileInputStream(nombreArchivoExcel);
//            Calendar fechaHoy = Calendar.getInstance();
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
//            nombreArchivo = nombreArchivoExcel;
//            fileExcel = new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "catalogoEmpleados" + sdf.format(fechaHoy.getTime()) + ".xlsx");
//        } catch (IOException | DAOException | DataBaseException | CossException ex) {
//            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al descargar archivo", ex.getMessage());
//        }
//        if (message != null) {
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
//        return fileExcel;
//    }
//
//    public void setFileExcel(StreamedContent fileExcel) {
//        this.fileExcel = fileExcel;
//    }
//
//    public void borraTempExcel() {
//        File file = new File(nombreArchivo);
//        if (file.isFile()) {
//            file.delete();
//        }
//    }
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
