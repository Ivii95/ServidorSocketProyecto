package conexiones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.*;






public class Consultas extends Conexion {

	public Statement sentencia;
	public Usuario usu=new Usuario();

	public Consultas() {
		
		super("proyectofinal");
		try {
			sentencia=conexion.createStatement();
		} catch (SQLException e) {
			System.out.println("Error al crear la sentencia SQL");
		}
	}
	public Usuario getUsuario() {
		System.out.println(usu.getNombre());
		return usu;
	}	
	public boolean validarUsuario(String usuario, String pass) {
		
		boolean resul = false;
		
		String sql = "select * from usuarios where usuarios.nom_usu='"+usuario+"' and usuarios.pass='"+pass+"'";
		
		try {
			System.out.println("intentando loguear: "+usuario+"|"+pass);

			//sentencia.setString(1, usuario);
			//sentencia.setString(2, pass);
			
			ResultSet rs = sentencia.executeQuery(sql);
			
			while (rs.next()) {
				usu.setId(rs.getInt("id_usu"));
                usu.setUsuario(rs.getString("nom_usu"));
                usu.setContrasena(rs.getString("pass"));
                usu.setAdmin(rs.getInt("admin"));
                usu.setCorreoRecuperacion(rs.getString("correo"));
                usu.setNombre(rs.getString("nombre"));
                usu.setApellidos(rs.getString("apellidos"));
                usu.setTlf(rs.getInt("tlf"));
                usu.setSexo(rs.getString("sexo"));
                usu.setNacimiento(rs.getString("fech_nac"));
                usu.setPais(rs.getString("pais"));
                usu.setComunidadAutonoma(rs.getString("comunidad_auto"));
                usu.setProvincia(rs.getString("provincia"));
                usu.setCiudad(rs.getString("ciudad"));
                usu.setDomicilio(rs.getString("domicilio"));
				resul=true;
			}
			
		} catch (SQLException e) {
			System.out.println("Error en la consulta de loguear");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return resul;
		
	}
	
	public ArrayList<Usuario> ListarUsuarios() {
		String sql = "select * from usuarios";
        ArrayList<Usuario> Users = new ArrayList<Usuario>();
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);			
			ResultSet rs = sentencia.executeQuery();
			
			while (rs.next()) {
				Usuario usu = new Usuario();
				usu.setId(rs.getInt("id_usu"));
                                usu.setUsuario(rs.getString("nom_usu"));
                                usu.setContrasena(rs.getString("pass"));
                                usu.setAdmin(rs.getInt("admin"));
                                usu.setCorreoRecuperacion(rs.getString("correo"));
                                usu.setNombre(rs.getString("nombre"));
                                usu.setApellidos(rs.getString("apellidos"));
                                usu.setTlf(rs.getInt("tlf"));
                                usu.setSexo(rs.getString("sexo"));
                                usu.setNacimiento(rs.getString("fech_nac"));
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
		String sql = "select * from 'alquiler'";
        ArrayList<Alquiler> Users = new ArrayList<Alquiler>();
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);			
			ResultSet rs = sentencia.executeQuery();
			
			while (rs.next()) {
				Alquiler alq = new Alquiler();
					alq.setId(rs.getInt("id_horario"));
					alq.setP(this.BuscarPista(rs.getInt("id_pista")));
					alq.setUsu(this.BuscarUsuario(rs.getInt("id_usu")));
					alq.setHoraInicio(rs.getString("hora_inicio"));
					alq.setHoraFin(rs.getString("hora_fin"));
					alq.setDia(rs.getString("dia"));
						int ocu=rs.getInt("ocupada");
						if(ocu==0) 
							alq.setOcupada(false);
						if(ocu==1)
							alq.setOcupada(true);
				Users.add(alq);

			}
			
		} catch (SQLException e) {
			System.out.println("Error en la consulta para mostrar los alquileres");
		}
            
		return Users;
	}
	
	public boolean insertarUsuario(Usuario usu) {
		boolean insertado=false;
		
		String sql = "insert into usuarios (nom_usu,pass,admin,correo,nombre,apellidos,tlf,sexo,fech_nac,pais,comunidad_auto,provincia,ciudad,domicilio)"
                        + "values(?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)";
		
		try {
			
			PreparedStatement sentencia = conexion.prepareStatement(sql);
						sentencia.setString(1, usu.getUsuario());
						sentencia.setString(2, usu.getContrasena());
						sentencia.setInt(3, usu.getAdmin());
						sentencia.setString(4, usu.getCorreoRecuperacion());
						sentencia.setString(5, usu.getNombre());
						sentencia.setString(6, usu.getApellidos());
						sentencia.setInt(7, usu.getTlf());
						sentencia.setString(8, usu.getSexo());
                        sentencia.setString(9, usu.getNacimiento());
                        sentencia.setString(10,usu.getPais());
                        sentencia.setString(11, usu.getComunidadAutonoma());
                        sentencia.setString(12,usu.getProvincia());
                        sentencia.setString(13, usu.getCiudad());
                        sentencia.setString(14, usu.getDomicilio());
		int res = sentencia.executeUpdate();
		
		if (res>0) {
			insertado=true;
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return insertado;
	}
	
	public boolean insertarAlquiler(Alquiler alq) {
		boolean insertado=false;
		
		String sql = "insert into alquiler (id_horario,id_pista,id_usu,hora_inicio,hora_fin,dia,ocupada)"
                        + "values(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			PreparedStatement sentencia = conexion.prepareStatement(sql);
						sentencia.setInt(1, alq.getId());
						sentencia.setInt(2, alq.getP().getId());
						sentencia.setInt(3, alq.getUsu().getId());
						sentencia.setString(4, alq.horaInicio);
						sentencia.setString(5, alq.horaFin);
						sentencia.setString(6, alq.getDia());
						if(alq.isOcupada())sentencia.setInt(7,1);
						if(!alq.isOcupada())sentencia.setInt(7,0);
		int res = sentencia.executeUpdate();
		
		if (res>0) {
			insertado=true;
		}
			
		} catch (SQLException e) {
			System.out.println("Error en la consulta para insertar alquiler");
		} 
		
		return insertado;
	}
	
	public boolean BorrarUsuario(int id) {
		boolean borrado=false;
		String sql = "DELETE FROM `usuarios` WHERE `usuarios`.`id_usu` ="+id;
        try {
			Statement sentencia = conexion.createStatement();		
                        int rs = sentencia.executeUpdate(sql);
                        if(rs==1) {
                        	borrado=true;
                        }
                        sentencia.close();
                        
            } catch (SQLException e) {
            	System.out.println("Error en la consulta para borrar usuario");
            }
        return borrado;
	}
	
	public boolean BorrarAlquiler(int id) {
		boolean borrado=false;
		String sql = "DELETE FROM `alquiler` WHERE `alquiler`.`id_horario` ="+id;
        try {
			Statement sentencia = conexion.createStatement();		
                        int rs = sentencia.executeUpdate(sql);
                        sentencia.close();
                        if(rs==1) {
                        	borrado=true;
                        }
                        return true;
            } catch (SQLException e) {
            	System.out.println("Error en la consulta para borrar usuario");
            }
        return borrado;
	}
	
	public boolean ModificarUsuario(int id, Usuario usu) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean ModificarAlquiler(int id, Alquiler alq) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Usuario BuscarUsuario(int id) {
		 String sql = "select * from usuarios where usuarios.id_usu="+id;
	        Usuario usu = new Usuario();
	        try {
				Statement sentencia = conexion.createStatement();
	                        
				ResultSet rs = sentencia.executeQuery(sql);
	                        while(rs.next()){
	                            
									usu.setId(rs.getInt("id_usu"));
	                                usu.setUsuario(rs.getString("nom_usu"));
	                                usu.setContrasena(rs.getString("pass"));
	                                usu.setAdmin(rs.getInt("admin"));
	                                usu.setCorreoRecuperacion(rs.getString("correo"));
	                                usu.setNombre(rs.getString("nombre"));
	                                usu.setApellidos(rs.getString("apellidos"));
	                                usu.setTlf(rs.getInt("tlf"));
	                                usu.setSexo(rs.getString("sexo"));
	                                usu.setNacimiento(rs.getString("fech_nac"));
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
		String sql = "select * from alquiler where alquiler.id_horario="+id;
		Alquiler alq = new Alquiler();
        try {
			Statement sentencia = conexion.createStatement();
                        
			ResultSet rs = sentencia.executeQuery(sql);
                        while(rs.next()){
        					alq.setId(rs.getInt("id_horario"));
        					alq.setP(this.BuscarPista(rs.getInt("id_pista")));
        					alq.setUsu(this.BuscarUsuario(rs.getInt("id_usu")));
        					alq.setHoraInicio(rs.getString("hora_inicio"));
        					alq.setHoraFin(rs.getString("hora_fin"));
        					alq.setDia(rs.getString("dia"));
        						int ocu=rs.getInt("ocupada");
        						if(ocu==0) 
        							alq.setOcupada(false);
        						if(ocu==1)
        							alq.setOcupada(true);
                        }
                        sentencia.close();
            } catch (SQLException e) {
            	System.out.println("Error en la consulta de buscar un alquiler");
            }
        
        return alq;
	}

	public Pista BuscarPista(int id) {
		String sql = "select * from pistas where pistas.id_pista="+id;
		Pista pi = new Pista();
        try {
			Statement sentencia = conexion.createStatement();
                        
			ResultSet rs = sentencia.executeQuery(sql);
                        while(rs.next()){
        					pi.setId(rs.getInt("id_pista"));
        					pi.setTipo(rs.getString("tipo"));
        					pi.setNum(rs.getInt("num"));
        					pi.setPrecioHora(rs.getFloat("precio_hora"));
                        }
                        sentencia.close();
            } catch (SQLException e) {
            	System.out.println("Error en la consulta de buscar una pista");
            }
        
        return pi;
	}


}// Fin de clase
