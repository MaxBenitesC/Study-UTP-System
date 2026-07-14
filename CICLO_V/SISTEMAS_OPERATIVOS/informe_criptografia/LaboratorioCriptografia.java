import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.MGF1ParameterSpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

public class LaboratorioCriptografia {
    private static final String AES_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String RSA_TRANSFORMATION =
            "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String cifrarAES(String texto, SecretKey clave) throws Exception {
        byte[] iv = new byte[16];
        RANDOM.nextBytes(iv);

        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, clave, new IvParameterSpec(iv));
        byte[] cifrado = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));

        ByteBuffer resultado = ByteBuffer.allocate(iv.length + cifrado.length);
        resultado.put(iv);
        resultado.put(cifrado);
        return Base64.getEncoder().encodeToString(resultado.array());
    }

    public static String descifrarAES(String datosBase64, SecretKey clave)
            throws Exception {
        byte[] datos = Base64.getDecoder().decode(datosBase64);
        ByteBuffer buffer = ByteBuffer.wrap(datos);
        byte[] iv = new byte[16];
        buffer.get(iv);
        byte[] cifrado = new byte[buffer.remaining()];
        buffer.get(cifrado);

        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, clave, new IvParameterSpec(iv));
        byte[] textoPlano = cipher.doFinal(cifrado);
        return new String(textoPlano, StandardCharsets.UTF_8);
    }

    private static OAEPParameterSpec parametrosOAEP() {
        return new OAEPParameterSpec(
                "SHA-256",
                "MGF1",
                MGF1ParameterSpec.SHA256,
                PSource.PSpecified.DEFAULT);
    }

    public static String cifrarRSA(String texto, PublicKey clavePublica)
            throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, clavePublica, parametrosOAEP());
        byte[] cifrado = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(cifrado);
    }

    public static String descifrarRSA(String datosBase64, PrivateKey clavePrivada)
            throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, clavePrivada, parametrosOAEP());
        byte[] cifrado = Base64.getDecoder().decode(datosBase64);
        byte[] textoPlano = cipher.doFinal(cifrado);
        return new String(textoPlano, StandardCharsets.UTF_8);
    }

    public static String calcularSHA256(String texto) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(texto.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexadecimal = new StringBuilder(hash.length * 2);
        for (byte valor : hash) {
            hexadecimal.append(String.format("%02x", valor & 0xff));
        }
        return hexadecimal.toString();
    }

    public static void main(String[] args) throws Exception {
        String texto = "Sistemas Operativos UTP - 2026";

        KeyGenerator generadorAES = KeyGenerator.getInstance("AES");
        generadorAES.init(128);
        SecretKey claveAES = generadorAES.generateKey();
        String cifradoAES = cifrarAES(texto, claveAES);
        String descifradoAES = descifrarAES(cifradoAES, claveAES);

        KeyPairGenerator generadorRSA = KeyPairGenerator.getInstance("RSA");
        generadorRSA.initialize(2048);
        KeyPair parRSA = generadorRSA.generateKeyPair();
        String cifradoRSA = cifrarRSA(texto, parRSA.getPublic());
        String descifradoRSA = descifrarRSA(cifradoRSA, parRSA.getPrivate());

        String hashSHA256 = calcularSHA256(texto);

        System.out.println("=== LABORATORIO DE CRIPTOGRAFIA ===");
        System.out.println("Texto original : " + texto);
        System.out.println("\n[AES]");
        System.out.println("Cifrado Base64 : " + cifradoAES);
        System.out.println("Descifrado     : " + descifradoAES);
        System.out.println("\n[RSA]");
        System.out.println("Cifrado Base64 : " + cifradoRSA);
        System.out.println("Descifrado     : " + descifradoRSA);
        System.out.println("\n[SHA-256]");
        System.out.println("Hash hexadecimal: " + hashSHA256);
    }
}
