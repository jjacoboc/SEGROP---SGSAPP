/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;


/**
 *
 * @author Jonatan Jacobo
 */
public final class SHA1BASE64 {

    /**
     * Encripta un texto plano en base de 64 bits.
     * @param textoplano Texto a encriptar
     * @return hash Texto encriptado
     * @throws IllegalStateException
     */
    public static String encriptar(String textoplano) throws IllegalStateException {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA"); // Instancia de generador SHA-1
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage());
        }

        try {
            md.update(textoplano.getBytes("ISO-8859-1")); // Generación de resumen de mensaje
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage());
        }

        byte raw[] = md.digest(); // Obtención del resumen de mensaje
        String hash = (new BASE64Encoder()).encode(raw); // Traducción a BASE64
        return hash;
    }
}