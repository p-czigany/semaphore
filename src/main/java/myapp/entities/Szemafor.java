package myapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "szemafor", catalog = "workflow", schema = "example")
public class Szemafor implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "kod", nullable = false)
  private String kod;

  @Size(max = 80)
  @Column(name = "muvelet", length = 80)
  private String muvelet;

  @Column(name = "idopont")
  @Temporal(TemporalType.TIMESTAMP)
  private Date idopont;

  public Szemafor() {}

  public Szemafor(String kod) {
    this.kod = kod;
  }

  public String getKod() {
    return kod;
  }

  public void setKod(String kod) {
    this.kod = kod;
  }

  public String getMuvelet() {
    return muvelet;
  }

  public void setMuvelet(String muvelet) {
    this.muvelet = muvelet;
  }

  public Date getIdopont() {
    return idopont;
  }

  public void setIdopont(Date idopont) {
    this.idopont = idopont;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 79 * hash + Objects.hashCode(this.kod);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Szemafor other = (Szemafor) obj;
    return Objects.equals(this.kod, other.kod);
  }

  @Override
  public String toString() {
    return "Szemafor{" + "kod=" + kod + ", muvelet=" + muvelet + '}';
  }
}
