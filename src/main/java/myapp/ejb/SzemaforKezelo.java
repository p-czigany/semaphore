package myapp.ejb;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import myapp.entities.Szemafor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@LocalBean
@Stateless
public class SzemaforKezelo {

  @PersistenceContext(unitName = "PU")
  protected EntityManager em;

  private static final Logger logger = LoggerFactory.getLogger(SzemaforKezelo.class);
  private static final Map<String, Object> tulajdonsagok = new HashMap<>();

  static {
    tulajdonsagok.put("javax.persistence.lock.timeout", 2000);
  }

  public Szemafor elokeszitZarolas(String kod, String muvelet) {
    Szemafor szemafor = em.find(Szemafor.class, kod, LockModeType.PESSIMISTIC_WRITE, tulajdonsagok);
    logger.debug("SzkEz1: [{}]", szemafor);
    if (szemafor == null) {
      szemafor = new Szemafor(kod);
      logger.debug("SzkEz2: [{}]", szemafor);
      em.persist(szemafor);
      em.flush();
      em.lock(szemafor, LockModeType.PESSIMISTIC_WRITE);
    }
    Date aktualisIdopont = new Date();
    szemafor.setIdopont(aktualisIdopont);
    szemafor.setMuvelet(muvelet);
    logger.debug("SzkEz3: [{}]", szemafor);
    return szemafor;
  }

  public Szemafor zarol(String kod, String muvelet) {
    logger.debug("SzkZ0: '{}','{}'", kod, muvelet);
    elokeszitZarolas(kod, muvelet);
    Szemafor szemafor = elokeszitZarolas(kod, muvelet);
    logger.debug("SzkZ1: [{}]", szemafor);
    szemafor.setMuvelet("ellenőrzés");
    logger.debug("SzkZ2: [{}]", szemafor);
    return szemafor;
  }
}
