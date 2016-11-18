package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Seguranca {

    public static String geraSenhaPadrao(String str1, int str2) {

            String[] aux = str1.split(" ");
            String senha = aux[0] + String.valueOf(str2);
            return criptografa(senha);
        
    }
    
    

    public static String criptografa(String senha) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Seguranca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Seguranca.class.getName()).log(Level.SEVERE, null, ex);
        }
        return senha;
    }
    
    
    public static boolean validarCpf(String cpf){
        return true;
    } 
    
    
    public static boolean validarEmail(String email){
        return true;
    } 
    
    public static boolean validarRg(String email){
        return true;
    }
    
    public static boolean validarCnpj(String email){
        return true;
    }
}
