/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sar.controller;

import com.sar.model.EstadosPDF;
import com.sar.model.Evaluados;
import com.sar.model.Postulante;
import com.sar.model.ReporteUsuarios;
import com.sar.model.Requisicion;
import com.sar.model.UsuarioInge;
import com.sar.session.EvaluadosFacadeLocal;
import com.sar.session.PostulanteFacadeLocal;
import com.sar.session.RequisicionFacadeLocal;
import com.sar.session.UsuarioIngeFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author manuel
 */
@Named(value = "pdfcontroller")
@RequestScoped
public class PDfController implements Serializable {

    /**
     * Creates a new instance of PDfController
     */
    @EJB
    RequisicionFacadeLocal requisicionFacade;
    @EJB
    private EvaluadosFacadeLocal eFacade;
    @EJB
    private PostulanteFacadeLocal postulanteFacade;
    @EJB
    private UsuarioIngeFacadeLocal userFacade;

    List<EstadosPDF> lista = new ArrayList<>();
    List<ReporteUsuarios> listaReq = new ArrayList<>();
    JasperPrint jasper;
    private List<Postulante> aux = new ArrayList<Postulante>();
    private List<Evaluados> aux2 = new ArrayList<Evaluados>();
    ReporteUsuarios report = new ReporteUsuarios();
    private BigDecimal requisicion;
    private String idUsuario;
    private Date entrada;
    private Date salida;
    private int pres;
    private int eva;
    private int entrevistado;
    private int select;
    private int contra;

    public PDfController() {
        pres = 0;
        eva = 0;
        entrevistado = 0;
        select = 0;
        contra = 0;
    }

    public BigDecimal getRequisicion() {
        return requisicion;
    }

    public void setRequisicion(BigDecimal requisicion) {
        this.requisicion = requisicion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    //jar maven
    public List<Postulante> preseleccionados() {
        aux = new ArrayList<Postulante>();
        for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("PRESELECCIONADO")) {
                aux.add(i);
            }
        }
        return aux;
    }

    public List<Postulante> evaluados() {
        aux = new ArrayList<Postulante>();
        for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("EVALUADO")) {
                aux.add(i);
            }
        }
        return aux;
    }

    public List<Postulante> entrevistados() {
        aux = new ArrayList<Postulante>();
        for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("ENTREVISTADO")) {
                aux.add(i);
            }
        }
        return aux;
    }

    public List<Postulante> seleccionados() {
        aux = new ArrayList<Postulante>();
        aux = new ArrayList();
        for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("SELECCIONADO")) {
                aux.add(i);
            }
        }
        return aux;
    }

    public List<Postulante> listContratados() {

        aux = new ArrayList<Postulante>();
        for (Postulante i : postulanteFacade.findAll()) {
            if (i.getEstado().getDetalle().equals("CONTRATADO")) {
                aux.add(i);
            }
        }
        return aux;
    }
    //agregar descartados

    public void loadListFilter() {
        if (requisicion.equals(BigDecimal.ZERO)) {
            loadList();
        } else {
            for (Postulante pos : postulanteFacade.findAll()) {
                if (pos.getRequisicion().getNumrequisicion().equals(requisicion)) {
                    switch (pos.getEstado().getDetalle()) {
                        case "PRESELECCIONADO":
                            pres++;
                            break;
                        case "EVALUADO":
                            eva++;
                            break;
                        case "ENTREVISTADO":
                            entrevistado++;
                            break;
                        case "SELECCIONADO":
                            select++;
                            break;
                        case "CONTRATADO":
                            contra++;
                            break;

                    }
                }
            }
            lista.add(new EstadosPDF("Preseleccionados", pres));
            lista.add(new EstadosPDF("Evaluados", this.eva));
            lista.add(new EstadosPDF("Entrevistados", this.entrevistado));
            lista.add(new EstadosPDF("Seleccionados", this.select));
            lista.add(new EstadosPDF("Contratados", contra));
        }
        try {
            this.doPDF();
        } catch (JRException ex) {
            Logger.getLogger(PDfController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDfController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadList() {
        lista.add(new EstadosPDF("Preseleccionados", this.preseleccionados().size()));
        lista.add(new EstadosPDF("Evaluados", this.evaluados().size()));
        lista.add(new EstadosPDF("Entrevistados", this.entrevistados().size()));
        lista.add(new EstadosPDF("Seleccionado", this.seleccionados().size()));
        lista.add(new EstadosPDF("Contratados", this.listContratados().size()));
    }

    public void init() throws JRException {
        //    loadList();
        if (lista == null) {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "AVISO", "DATOS SOLICITADOS NO CORRESPONDEN"));
        
        } else {
            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(lista);
            String relativePath = FacesContext.getCurrentInstance().getExternalContext().
                    getRealPath("estados.jasper");
            jasper = JasperFillManager.fillReport(relativePath, new HashMap(), source);
            lista = new ArrayList();
        }
    }

    public void doPDF()
            throws JRException, IOException {
        init();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().
                getExternalContext().getResponse();
        //response.addHeader("Content-disposition", "attachment; filename=Estados.pdf");
        response.addHeader("Content-disposition", "attachment; filename=" + nombreEstados());
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasper, stream);
        FacesContext.getCurrentInstance().responseComplete();
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "PDF CARGADO EXITOSAMENTE"));
        
    }

    public String nombreEstados() {
        String filename = "";
        if (requisicion.equals(BigDecimal.ZERO)) {
            filename = "Todos.pdf";
        } else {
            for (Requisicion req : requisicionFacade.findAll()) {
                if (requisicion.equals(req.getNumrequisicion())) {
                    filename = req.toString() + ".pdf";
                }
            }

        }
        return filename;
    }

    public String nombreRequisicion() {
        String filename = "";
        if (this.idUsuario.equals("Todos")) {
            filename = "Todos.pdf";
        } else {
            for (UsuarioInge us : userFacade.findAll()) {
                if (us.getCedula().equals(idUsuario)) {
                    filename = idUsuario + "-" + us.getNombre() + ".pdf";
                }
            }

        }
        return filename;
    }

    //segundo reporte
    public void loadAllRequisiciones() {
        Date fecha = null;
        for (UsuarioInge user : userFacade.findAll()) {
            for (Requisicion req : this.requisicionFacade.findAll()) {
                if (user.getCedula().equals(req.getUsuario().getCedula()) && req.getFechaEntrega() != null) {
                    if (req.getFechaInicio().after(entrada) && req.getFechaEntrega().before(salida)) {
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        String fechas = format.format(req.getFechaInicio()) + " / " + format.format(req.getFechaEntrega());
                        listaReq.add(new ReporteUsuarios(req.getNumrequisicion(), req.getPuesto(),
                                fechas, req.getUsuario().getNombre()));
                        break;
                    }
                }
            }
        }

    }

    public void loadRequisicionFilter() {
        if (this.idUsuario.equals("Todos")) {
            this.loadAllRequisiciones();
        } else {
            for (Requisicion r : requisicionFacade.findAll()) {
                if (r.getUsuario().getCedula().equals(idUsuario) && r.getFechaEntrega() != null) {
                    if (r.getFechaEntrega().before(salida) && r.getFechaInicio().after(entrada)) {
                        //  req.getFechaInicio().after(entrada) && req.getFechaEntrega().before(salida)
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        // String fechas = format.format(entrada)+" / "+format.format(salida);
                        String fechas = format.format(r.getFechaInicio()) + " / " + format.format(r.getFechaEntrega());

                        listaReq.add(new ReporteUsuarios(r.getNumrequisicion(), r.getPuesto(),
                                fechas, r.getUsuario().getNombre()));
                        break;
                    }
                }
            }
        }
        try {
            this.doPDFRequisicion();
        } catch (JRException ex) {
            Logger.getLogger(PDfController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDfController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initReport2() throws JRException {
        // this.loadAllRequisiciones();
        if (listaReq == null) {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "AVISO", "DATOS SOLICITADOS NO CORRESPONDEN"));
        
        } else {
            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(listaReq);
            String relativePath = FacesContext.getCurrentInstance().getExternalContext().
                    getRealPath("requisicion.jasper");
            jasper = JasperFillManager.fillReport(relativePath, new HashMap(), source);
            listaReq = new ArrayList();
        }
    }

    public void doPDFRequisicion()
            throws JRException, IOException {
        initReport2();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().
                getExternalContext().getResponse();
        //response.addHeader("Content-disposition", "attachment; filename=Estados.pdf");
        response.addHeader("Content-disposition", "attachment; filename=" + nombreRequisicion());
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasper, stream);
        FacesContext.getCurrentInstance().responseComplete();
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "PDF CARGADO EXITOSAMENTE"));
        
    }

}
