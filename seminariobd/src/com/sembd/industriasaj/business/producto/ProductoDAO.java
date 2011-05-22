package com.sembd.industriasaj.business.producto;

import java.util.List;

import com.sembd.industriasaj.business.tipo.TipoDTO;
import com.sembd.industriasaj.services.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductoDAO {
	
	private static ProductoDAO dao;

    private ProductoDAO(){}

    public static ProductoDAO getProductoDAO(){
        if(dao == null){
            dao = new ProductoDAO();
        }
        return dao;
    }

    public ProductoDTO getProducto(ProductoDTO pd){
        ProductoDTO result = null;
        Connection con = null;
        try{
            con = DBConnection.getConnection();
            PreparedStatement p = con.prepareStatement(ProductoDAOHelper.getProducto());

            p.setString(1, pd.getReferencia());
            ResultSet rs = p.executeQuery();
            while(rs.next()){
                ProductoDTO pro = new ProductoDTO();
                pro.setReferencia(rs.getString(1));
                pro.setIdentificador(rs.getString(2));
                TipoDTO tipo = new TipoDTO();
                tipo.setCodigo(rs.getString(3));
                tipo.setMultiplo(rs.getInt(4));
                pro.setTbTipo(tipo);
                pro.setDescripcion(rs.getString(5));
                pro.setCantStock(rs.getInt(6));
                pro.setPrecioUnitario(rs.getInt(7));
                pro.setValorMin(rs.getInt(8));
                pro.setValorOptimo(rs.getInt(9));
                result = pro;
            }
        }
        catch(Exception ex){
                ex.printStackTrace();
        }
        finally{
            try{
            	DBConnection.returnConnection(con);
            }
            catch(Exception clo){}
        }
        return result;
    }

    public List<ProductoDTO> getProductos(){
            List<ProductoDTO> result = null;
            Connection con = null;
            try{
                con = DBConnection.getConnection();
                PreparedStatement p = con.prepareStatement(ProductoDAOHelper.getProductos());
                ResultSet rs = p.executeQuery();
                result = new ArrayList<ProductoDTO>();
                while(rs.next()){
                	ProductoDTO pro = new ProductoDTO();
                    pro.setReferencia(rs.getString(1));
                    pro.setIdentificador(rs.getString(2));
                    TipoDTO tipo = new TipoDTO();
                    tipo.setCodigo(rs.getString(3));
                    tipo.setMultiplo(rs.getInt(4));
                    pro.setTbTipo(tipo);
                    pro.setDescripcion(rs.getString(5));
                    pro.setCantStock(rs.getInt(6));
                    pro.setPrecioUnitario(rs.getInt(7));
                    pro.setValorMin(rs.getInt(8));
                    pro.setValorOptimo(rs.getInt(9));
                    result.add(pro);
                }
            }
            catch(Exception ex){
                    ex.printStackTrace();
            }
            finally{
                try{
                	DBConnection.returnConnection(con);
                }
                catch(Exception clo){}
            }
            return result;
	}

    public boolean existProducto(ProductoDTO pd){
        boolean result=false;
        Connection con=null;
        try{
            con = DBConnection.getConnection();
            PreparedStatement p = con.prepareStatement(ProductoDAOHelper.getProducto());
            p.setString(1, pd.getReferencia());
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                result=true;
            }
        }
        catch(Exception ex){
                ex.printStackTrace();
        }
        finally{
            try{
            	DBConnection.returnConnection(con);
            }
            catch(Exception clo){}
        }
        return result;
    }

}
