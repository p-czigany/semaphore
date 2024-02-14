package myapp.cdi;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SzemaforDemoBean implements Serializable {

  private String uzenet;

  public String getUzenet() {
    return uzenet;
  }

  public void setUzenet(String uzenet) {
    this.uzenet = uzenet;
  }
}
