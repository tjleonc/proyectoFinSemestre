/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import sql.Conexion;
import proyectofinsemestre.Libros;
import java.sql.*;
import java.util.ArrayList;
import view.listadoLibros;
import javax.swing.JOptionPane;

public class Registro {
    
    
    public static boolean agregarLibro(Libros dto){
        try{
            Connection conexion = Conexion.getConexion();
            String query = "INSERT INTO book(id,titulo, autor, año_de_publicacion, numero_paginas, gen_literario) VALUES (?,?,?,?,?,?)";
            PreparedStatement insertar = conexion.prepareStatement(query);
            insertar.setInt(1, dto.getId());
            insertar.setString(2, dto.getTitulo());
            insertar.setString(3, dto.getAutor());
            insertar.setInt(4, dto.getAño_de_publicacion());
            insertar.setInt(5, dto.getNumero_paginas());
            insertar.setString(6, dto.getGen_literario());
            insertar.execute();
            insertar.close();
            conexion.close();
            return true;
        }catch(SQLException s){
            System.out.println("Error SQL al agregar Libro"+s.getMessage());
            return false;
        }catch(Exception e){
            System.out.println("Error al agregar Libro"+e.getMessage());
            return false;
        }
    }
    
        public static ArrayList<Libros> mostrarTodos() {
        ArrayList<Libros> listaLibros = new ArrayList<Libros>();
        try{
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM book";
            PreparedStatement listarTodos = conexion.prepareStatement(query);
            ResultSet rs = listarTodos.executeQuery();
            while(rs.next()){
                Libros dto = new Libros();
                dto.setId(rs.getInt("ID"));
                dto.setAutor(rs.getString("Autor"));
                dto.setTitulo(rs.getString("Titulo"));
                dto.setAño_de_publicacion(rs.getInt("Año_de_publicacion"));
                dto.setNumero_paginas(rs.getInt("numero_paginas"));
                dto.setGen_literario(rs.getString("gen_literario"));
                listaLibros.add(dto);
            }
        }catch(SQLException s){
            System.out.println("Error SQL al listar Libro"+s.getMessage());
        }catch(Exception e){
            System.out.println("Error al listar Libro"+e.getMessage());
        }
        return listaLibros;

    }
        
    public static void eliminarLibros(int idEliminar){
        try{
            Connection conexion = Conexion.getConexion();
            String query = "DELETE FROM book WHERE id=?";
            PreparedStatement eliminar = conexion.prepareStatement(query);
            eliminar.setInt(1, idEliminar);
            eliminar.execute();
            eliminar.close();
            conexion.close();
        }catch(SQLException s){
            System.out.println("Error SQL al eliminar Libros"+s.getMessage());
        }catch(Exception e){
            System.out.println("Error al eliminar Libros"+e.getMessage());
        }
    }
    /*Falta: Buscar por libro*/
    public static  Libros buscarPorLibro(int idBuscado){
        ArrayList<Libros> listaLibros = new ArrayList<Libros>();
        Libros libroBuscado = null;
        try{
            Connection conexion = Conexion.getConexion();
            String query = "SELECT id,titulo,autor,año_de_aparicion,numero_paginas,gen_literario FROM book WHERE id=?";
            PreparedStatement buscarPorLibro = conexion.prepareStatement(query);
            buscarPorLibro.setInt(1, idBuscado);
            ResultSet rs = buscarPorLibro.executeQuery();
            while(rs.next()){
                Libros libEncontrado = new Libros();
                libEncontrado.setId(rs.getInt("rut"));
                libEncontrado.setTitulo(rs.getString("nombre"));
                libEncontrado.setAutor(rs.getString("apellido"));
                libEncontrado.setAño_de_publicacion(rs.getInt("Año_de_publicacion"));
                libEncontrado.setNumero_paginas(rs.getInt("numero_paginas"));
                libEncontrado.setGen_literario(rs.getString("gen_literario"));
                libroBuscado = libEncontrado;
                return libroBuscado;
            }
        }catch(SQLException s){
            System.out.println("Error SQL al listar usuario"+s.getMessage());
        }catch(Exception e){
            System.out.println("Error al listar usuario"+e.getMessage());
        }
        return null;
        
    
    }
    
}