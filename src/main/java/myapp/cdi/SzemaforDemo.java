package myapp.cdi;

import jakarta.faces.event.ActionEvent;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import myapp.ejb.SzemaforKezelo;
import myapp.entities.Szemafor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@RequestScoped
public class SzemaforDemo {

  private static final Logger logger = LoggerFactory.getLogger(SzemaforDemo.class);

  private String uzenet;
  private final Date idopont = new Date();

  @Inject
  private SzemaforKezelo kezelo;
  @Inject
  private SzemaforDemoBean bean;

  private void vegrehajtZarolas(String kod, String muvelet) {
    Szemafor szemafor = kezelo.zarol(kod, muvelet);
    szemafor.setMuvelet("végrehajt");
    logger.debug("szD.vZ {}", szemafor);
  }

  private void ellenorizZarolas(String kod, String muvelet) {
    Szemafor szemafor = kezelo.elokeszitZarolas(kod, muvelet);
    logger.debug("szD.eZ {}", szemafor);
  }

  public void buttonClicked(ActionEvent event) {
    vegrehajtZarolas("H2", "próba");
    ellenorizZarolas("H2", "teszt");
    uzenet = String.format("szD.bC :: clientId = '%s'", event.getComponent().getClientId());
    bean.setUzenet(uzenet);
    logger.debug("szD.bC :: clientId = '{}'", event.getComponent().getClientId());
  }

  public String getUzenet() {
    return uzenet;
  }

  public Date getIdopont() {
    return idopont;
  }

  public SzemaforDemoBean getBean() {
    return bean;
  }
}
