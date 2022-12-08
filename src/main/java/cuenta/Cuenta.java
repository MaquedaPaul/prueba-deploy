package cuenta;

import lombok.Getter;
import lombok.Setter;
import spark.Request;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "cuenta")
@DiscriminatorColumn(name = "tipo_cuenta")
public abstract class Cuenta {
  @Id
  @Column(name = "CuentaId")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String usuario;

  private String password;

  public Cuenta(){
  }

  public Cuenta(String usuario, String password) {
    this.usuario = usuario;
    this.password = password;
  }

  public String getUsuario() {
    return usuario;
  }

  public String getPassword() {
    return password;
  }

  public abstract String home();

  public abstract void guardarEnSesion(Request request);

  public abstract boolean puedeAccederA(String path);

  public abstract Map<String, Object> datosDelHome(Request request);

  public abstract void limpiarSession(Request request);

  public abstract String getRecomendaciones();
}
