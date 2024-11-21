/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.SQLException;
import misau.model.MissauAdmin;

/**
 *
 * @author Pedro Manjate
 */
public class teste {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        MissauAdmin nn = new MissauAdmin();
//        nn.setPassWord("1234");
//        nn.setUserName("Dio");
//        
        MissauAdminDAO dao = new MissauAdminDAO();
       
        System.out.println( dao.readAll().get(0).getUserName());
    }
}
