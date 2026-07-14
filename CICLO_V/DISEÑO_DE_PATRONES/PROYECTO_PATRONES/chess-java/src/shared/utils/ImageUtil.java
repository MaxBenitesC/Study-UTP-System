package shared.utils;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageUtil {
    public static void escalarImg(JLabel label, String rutaImagen) {
        try {
            ImageIcon imagenOriginal = cargarImagen(rutaImagen);
            if (imagenOriginal == null) {
                System.err.println("Imagen no encontrada: " + rutaImagen);
                return;
            }

            Image img = imagenOriginal.getImage();
            int ancholabel = label.getWidth();
            int altolabel = label.getHeight();

            if (ancholabel == 0 || altolabel == 0) {
                ancholabel = label.getPreferredSize().width;
                altolabel = label.getPreferredSize().height;
            }

            if (ancholabel > 0 && altolabel > 0) {
                Image imagenEscalada = img.getScaledInstance(ancholabel, altolabel, Image.SCALE_SMOOTH);
                ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
                label.setIcon(iconoEscalado);
            } else {
                label.setIcon(imagenOriginal);
            }
        } catch (Exception e) {
            System.err.println("Error al escalar la imagen: " + e.getMessage());
            label.setText("Error Imagen");
        }
    }

    private static ImageIcon cargarImagen(String rutaImagen) {
        java.net.URL imageUrl = ImageUtil.class.getResource(rutaImagen);
        if (imageUrl != null) {
            return new ImageIcon(imageUrl);
        }
        if (!rutaImagen.startsWith("/")) {
            imageUrl = ImageUtil.class.getResource("/" + rutaImagen);
            if (imageUrl != null) {
                return new ImageIcon(imageUrl);
            }
        }
        String cleanPath = rutaImagen.startsWith("/") ? rutaImagen.substring(1) : rutaImagen;
        File f = new File("resources/" + cleanPath);
        if (f.exists()) {
            return new ImageIcon(f.getAbsolutePath());
        }
        f = new File(cleanPath);
        if (f.exists()) {
            return new ImageIcon(f.getAbsolutePath());
        }
        return null;
    }

    public static void escalarImgPorAltoYCentrar(JLabel label, String rutaImagen) {
        try {
            ImageIcon imagenOriginal = cargarImagen(rutaImagen);
            Image img = imagenOriginal.getImage();

            int altoLabel = label.getHeight();

            int anchoOriginal = img.getWidth(label);
            int altoOriginal = img.getHeight(label);

            double factorEscala = (double) altoLabel / altoOriginal;
            int nuevoAncho = (int) (anchoOriginal * factorEscala);

            Image imagenEscalada = img.getScaledInstance(nuevoAncho, altoLabel, Image.SCALE_SMOOTH);
            ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

            label.setHorizontalAlignment(JLabel.CENTER);
            label.setIcon(iconoEscalado);
        } catch (Exception e) {
            System.err.println("Error al escalar la imagen: " + e.getMessage());
            label.setText("Error Imagen");
        }
    }

    public static void escalarImgMantenerProporcion(JLabel label, String rutaImagen) {
        try {
            ImageIcon imagenOriginal = cargarImagen(rutaImagen);
            Image img = imagenOriginal.getImage();

            int anchoLabel = label.getWidth();
            int altoLabel = label.getHeight();

            int anchoOriginal = img.getWidth(label);
            int altoOriginal = img.getHeight(label);

            int nuevoAncho;
            int nuevoAlto;

            double factorEscalaAncho = (double) anchoLabel / anchoOriginal;
            double factorEscalaAlto = (double) altoLabel / altoOriginal;

            double factorFinal = Math.min(factorEscalaAncho, factorEscalaAlto);

            nuevoAncho = (int) (anchoOriginal * factorFinal);
            nuevoAlto = (int) (altoOriginal * factorFinal);

            Image imagenEscalada = img.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
            ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setIcon(iconoEscalado);

        } catch (Exception e) {
            System.err.println("Error al escalar la imagen: " + e.getMessage());
            label.setText("Error Imagen");
        }
    }
}
