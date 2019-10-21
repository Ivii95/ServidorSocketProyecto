package conexiones;

import Modelos.Alquiler;
import Modelos.Pista;
import Modelos.Usuario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Time;

public class Consultas extends Conexion {

    public Statement sentencia;
    public Usuario usu = new Usuario();

    public Consultas() {

        super("proyectofinal");
        try {
            sentencia = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println("Error al crear la sentencia SQL");
        }
    }

    public Usuario getUsuario() {
        System.out.println(usu.getNacimiento());
        return usu;
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public boolean validarUsuario(String usuario, String pass) {

        boolean resul = false;

        String sql = "select * from usuarios where usuarios.nom_usu='" + usuario + "' and usuarios.pass='" + pass + "'";

        try {
            //sentencia.setString(1, usuario);
            //sentencia.setString(2, pass);
            ResultSet rs = sentencia.executeQuery(sql);

            while (rs.next()) {
                usu.setId(rs.getInt("id_usu"));
                usu.setUsuario(rs.getString("nom_usu"));
                usu.setContrasena(rs.getString("pass"));
                if (rs.getInt("admin") == 1) {
                    usu.setAdmin(true);
                }
                usu.setCorreoRecuperacion(rs.getString("correo"));
                usu.setNombre(rs.getString("nombre"));
                usu.setApellidos(rs.getString("apellidos"));
                usu.setTlf(rs.getInt("tlf"));
                usu.setSexo(rs.getString("sexo"));
                usu.setNacimiento(convertToLocalDateViaSqlDate(rs.getDate("fech_nac")));
                usu.setPais(rs.getString("pais"));
                usu.setComunidadAutonoma(rs.getString("comunidad_auto"));
                usu.setProvincia(rs.getString("provincia"));
                usu.setCiudad(rs.getString("ciudad"));
                usu.setDomicilio(rs.getString("domicilio"));
                resul = true;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta de loguear");
        } catch (Exception e) {
            System.out.println("Error en la consulta de loguear");
        }

        return resul;

    }

    public ArrayList<Usuario> ListarUsuarios() {
        String sql = "select * from usuarios";
        ArrayList<Usuario> Users = new ArrayList<>();
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setId(rs.getInt("id_usu"));
                usu.setUsuario(rs.getString("nom_usu"));
                usu.setContrasena(rs.getString("pass"));
                if (rs.getInt("admin") == 1) {
                    usu.setAdmin(true);
                }
                usu.setCorreoRecuperacion(rs.getString("correo"));
                usu.setNombre(rs.getString("nombre"));
                usu.setApellidos(rs.getString("apellidos"));
                usu.setTlf(rs.getInt("tlf"));
                usu.setSexo(rs.getString("sexo"));
                usu.setNacimiento(convertToLocalDateViaSqlDate(rs.getDate("fech_nac")));
                usu.setPais(rs.getString("pais"));
                usu.setComunidadAutonoma(rs.getString("comunidad_auto"));
                usu.setProvincia(rs.getString("provincia"));
                usu.setCiudad(rs.getString("ciudad"));
                usu.setDomicilio(rs.getString("domicilio"));
                Users.add(usu);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta para mostrar los usuarios");
        }
        return Users;
    }

    public ArrayList<Alquiler> ListarAlquileres() {
        String sql = "select * from alquiler";
        ArrayList<Alquiler> Users = new ArrayList<>();
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                Alquiler alq = new Alquiler();
                alq.setId(rs.getInt("id_horario"));
                alq.setP(this.BuscarPista(rs.getInt("id_pista")));
                alq.setUsu(this.BuscarUsuario(rs.getInt("id_usu")));
                alq.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                alq.setHoraFin(rs.getTime("hora_fin").toLocalTime());
                alq.setDia(convertToLocalDateViaSqlDate(rs.getDate("dia")));
                Users.add(alq);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta para mostrar los alquileres");
        }
        return Users;
    }

    public ArrayList<Pista> ListarPistas() {
        String sql = "select * from pistas";
        ArrayList<Pista> Pistas = new ArrayList<>();
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                Pista p = new Pista();
                p.setId(rs.getInt("id_pista"));
                p.setTipo(rs.getString("tipo"));
                p.setNum(rs.getInt("num"));
                Pistas.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta para mostrar las pistas");
        }
        return Pistas;
    }

    public boolean insertarUsuario(Usuario usu) {
        boolean insertado = false;

        String sql = "insert into usuarios (nom_usu,pass,admin,correo,nombre,apellidos,tlf,sexo,fech_nac,pais,comunidad_auto,provincia,ciudad,domicilio)"
                + "values(?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, usu.getUsuario());
            sentencia.setString(2, usu.getContrasena());
            int admin = 0;
            if (usu.getAdmin()) {
                admin = 1;
            }
            sentencia.setInt(3, admin);
            sentencia.setString(4, usu.getCorreoRecuperacion());
            sentencia.setString(5, usu.getNombre());
            sentencia.setString(6, usu.getApellidos());
            sentencia.setInt(7, usu.getTlf());
            sentencia.setString(8, usu.getSexo());
            sentencia.setDate(9, Date.valueOf(usu.getNacimiento()));
            sentencia.setString(10, usu.getPais());
            sentencia.setString(11, usu.getComunidadAutonoma());
            sentencia.setString(12, usu.getProvincia());
            sentencia.setString(13, usu.getCiudad());
            sentencia.setString(14, usu.getDomicilio());
            int res = sentencia.executeUpdate();
            if (res > 0) {
                insertado = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertado;
    }

    public boolean insertarAlquiler(Alquiler alq) {
        boolean insertado = false;

        String sql = "insert into alquiler (id_horario,id_pista,id_usu,hora_inicio,hora_fin,dia)"
                + "values(?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, alq.getId());
            sentencia.setInt(2, alq.getP().getId());
            sentencia.setInt(3, alq.getUsu().getId());
            sentencia.setTime(4, Time.valueOf(alq.horaInicio));
            sentencia.setTime(5, Time.valueOf(alq.horaFin));
            sentencia.setDate(6, Date.valueOf(alq.getDia()));
            int res = sentencia.executeUpdate();
            if (res > 0) {
                insertado = true;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta para insertar alquiler");
        }

        return insertado;
    }

    public boolean BorrarUsuario(int id) {
        boolean borrado = false;
        String sql = "DELETE FROM `usuarios` WHERE `usuarios`.`id_usu` =" + id;
        try {
            Statement sentencia = conexion.createStatement();
            int rs = sentencia.executeUpdate(sql);
            if (rs == 1) {
                borrado = true;
            }
            sentencia.close();

        } catch (SQLException e) {
            System.out.println("Error en la consulta para borrar usuario");
        }
        return borrado;
    }

    public boolean BorrarAlquiler(int id) {
        boolean borrado = false;
        String sql = "DELETE FROM `alquiler` WHERE `alquiler`.`id_horario` =" + id;
        try {
            Statement sentencia = conexion.createStatement();
            int rs = sentencia.executeUpdate(sql);
            sentencia.close();
            if (rs == 1) {
                borrado = true;
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error en la consulta para borrar usuario");
        }
        return borrado;
    }

    public boolean ModificarUsuario(int id, Usuario usu) {
        boolean actualizado = false;
        String consulta = "UPDATE usuarios SET nom_usu = ? ,pass = ? ,admin = ? ,correo = ? ,nombre = ?,apellidos = ? ,tlf = ? ,"
                + "sexo = ? ,fech_nac = ? ,pais = ? ,comunidad_auto = ? ,provincia = ? ,ciudad = ? , domicilio = ? WHERE id_usu =" + id;
        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, usu.getUsuario());
            sentencia.setString(2, usu.getContrasena());
            int admin = 0;
            if (usu.getAdmin()) {
                admin = 1;
            }
            sentencia.setInt(3, admin);
            sentencia.setString(4, usu.getCorreoRecuperacion());
            sentencia.setString(5, usu.getNombre());
            sentencia.setString(6, usu.getApellidos());
            sentencia.setInt(7, usu.getTlf());
            sentencia.setString(8, usu.getSexo());

            sentencia.setDate(9, Date.valueOf(usu.getNacimiento()));
            sentencia.setString(10, usu.getPais());
            sentencia.setString(11, usu.getComunidadAutonoma());
            sentencia.setString(12, usu.getProvincia());
            sentencia.setString(13, usu.getCiudad());
            sentencia.setString(14, usu.getDomicilio());
            int res = sentencia.executeUpdate();
            if (res > 0) {
                actualizado = true;
            }
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actualizado;
    }

    public boolean ModificarAlquiler(int id, Alquiler alq) {
        boolean actualizado = false;
        String consulta = "UPDATE Usuario SET "
                + "id_pista = ?,"
                + "id_usu = ?,"
                + "hora_inicio = ?,"
                + "hora_fin = ?,"
                + "dia = ?"
                + " WHERE id_horarios = ?";
        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(id, alq.getP().getId());
            sentencia.setInt(2, alq.getUsu().getId());
            sentencia.setTime(3, Time.valueOf(alq.getHoraInicio()));
            sentencia.setTime(4, Time.valueOf(alq.getHoraFin()));
            sentencia.setDate(5, Date.valueOf(alq.getDia()));
            sentencia.setInt(15, id);
            if (sentencia.executeUpdate() > 0) {
                actualizado = true;
            }
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actualizado;
    }

    public Usuario BuscarUsuario(int id) {
        String sql = "select * from usuarios where usuarios.id_usu=" + id;
        Usuario usu = new Usuario();
        try {
            Statement sentencia = conexion.createStatement();

            ResultSet rs = sentencia.executeQuery(sql);
            while (rs.next()) {

                usu.setId(rs.getInt("id_usu"));
                usu.setUsuario(rs.getString("nom_usu"));
                usu.setContrasena(rs.getString("pass"));
                if (rs.getInt("admin") == 1) {
                    usu.setAdmin(true);
                } else {
                    usu.setAdmin(false);
                }
                usu.setCorreoRecuperacion(rs.getString("correo"));
                usu.setNombre(rs.getString("nombre"));
                usu.setApellidos(rs.getString("apellidos"));
                usu.setTlf(rs.getInt("tlf"));
                usu.setSexo(rs.getString("sexo"));
                usu.setNacimiento(rs.getDate("fech_nac").toLocalDate());
                usu.setPais(rs.getString("pais"));
                usu.setComunidadAutonoma(rs.getString("comunidad_auto"));
                usu.setProvincia(rs.getString("provincia"));
                usu.setCiudad(rs.getString("ciudad"));
                usu.setDomicilio(rs.getString("domicilio"));
            }
            sentencia.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta de buscar un usuario");
        }

        return usu;
    }

    public Alquiler BuscarAlquiler(int id) {
        String sql = "select * from alquiler where alquiler.id_horario=" + id;
        Alquiler alq = new Alquiler();
        try {
            Statement sentencia = conexion.createStatement();

            ResultSet rs = sentencia.executeQuery(sql);
            while (rs.next()) {
                alq.setId(rs.getInt("id_horario"));
                alq.setP(this.BuscarPista(rs.getInt("id_pista")));
                alq.setUsu(this.BuscarUsuario(rs.getInt("id_usu")));
                alq.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                alq.setHoraFin(rs.getTime("hora_fin").toLocalTime());
                alq.setDia(rs.getDate("dia").toLocalDate());
                /*int ocu = rs.getInt("ocupada");
                if (ocu == 0) {
                    alq.setOcupada(false);
                }
                if (ocu == 1) {
                    alq.setOcupada(true);
                }*/
            }
            sentencia.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta de buscar un alquiler");
        }
        return alq;
    }

    public Pista BuscarPista(int id) {
        String sql = "select * from pistas where pistas.id_pista=" + id;
        Pista pi = new Pista();
        try {
            Statement sentencia = conexion.createStatement();

            ResultSet rs = sentencia.executeQuery(sql);
            while (rs.next()) {
                pi.setId(rs.getInt("id_pista"));
                pi.setTipo(rs.getString("tipo"));
                pi.setNum(rs.getInt("num"));
                //pi.setPrecioHora(rs.getFloat("precio_hora"));
            }
            sentencia.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta de buscar una pista");
        }

        return pi;
    }

}// Fin de clase
