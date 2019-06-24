/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;


import entidade.Instituto;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import rn.InstitutoRN;

/**
 *
 * @author Fabio
 */
@ManagedBean
@RequestScoped
public class InstitutoBean {

    private List<Instituto> institutos;
    private Instituto instituto = new Instituto();
    private final InstitutoRN RACA_RN = new InstitutoRN();

    /**
     * Creates a new instance of InstitutoBean
     */
    public InstitutoBean() {
    }

    @PostConstruct
    public void init() {
        institutos = RACA_RN.listar();
    }

    public List<Instituto> getInstitutos() {
        return institutos;
    }

    public void setInstitutos(List<Instituto> institutos) {
        this.institutos = institutos;
    }

    public Instituto getInstituto() {
        return instituto;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    public void excluir() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        if (RACA_RN.excluir(instituto)) {
            FacesMessage fm = new FacesMessage("Sucesso", "Instituto excluída");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            currentInstance.addMessage(null, fm);
            institutos = RACA_RN.listar();
        } else {
            FacesMessage fm = new FacesMessage("Erro", "Não foi possível excluir a instituto");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            currentInstance.addMessage(null, fm);
        }
    }

    public void salvar() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        if (RACA_RN.salvar(instituto)) {
            FacesMessage fm = new FacesMessage("Sucesso", "Instituto salvo");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            currentInstance.addMessage(null, fm);
        } else {
            FacesMessage fm = new FacesMessage("Erro", "Não foi possível salvar o instituto");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            currentInstance.addMessage(null, fm);
        }
    }

}
