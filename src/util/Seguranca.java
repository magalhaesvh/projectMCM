package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Seguranca {

    public static String geraSenhaPadrao(String str1, int str2) {
        try {
            String[] aux = str1.split(" ");
            String senha = aux[0] + String.valueOf(str2);
            return criptografa(senha);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException u){
            return null;            
        }
    }
    
    

    public static String criptografa(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
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
