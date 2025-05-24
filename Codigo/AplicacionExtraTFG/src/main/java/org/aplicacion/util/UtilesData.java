package org.aplicacion.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import javafx.animation.FadeTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class UtilesData {

	//////////////////////////////////////
	///////////////////////////// Imagenes
	public static byte[] pasarImagenAByte(Image imagen) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try {
			// Convierte la imagen a un formato de bytes (por ejemplo, PNG)
			ImageIO.write(SwingFXUtils.fromFXImage(imagen, null), "png", byteArrayOutputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteArrayOutputStream.toByteArray(); // Devuelve el array de bytes
	}

	public static Image pasarByteAImagen(byte[] bytes) {
		try {
			return SwingFXUtils.toFXImage(ImageIO.read(new ByteArrayInputStream(bytes)), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Las imagenes de los usuarios siempre son de 50px asique es mejor redimensionarlas
	public static byte[] redimensionarImagenAByte(Image imagenOriginal) {
		// Redimensionamos la imagen
		BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imagenOriginal, null);
		BufferedImage imagenRedimensionada = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = imagenRedimensionada.createGraphics();
		g2d.drawImage(bufferedImage, 0, 0, 50, 50, null);
		g2d.dispose();

		// Convertimos la imagen redimensionada a byte[]
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			ImageIO.write(imagenRedimensionada, "png", byteArrayOutputStream); // Puedes elegir el formato (PNG, JPEG,
																				// etc.)
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteArrayOutputStream.toByteArray(); // Devuelve el array de bytes
	}

	/////////////////////////////////////
	////////////////////////////// Fechas
	public static Date convertirDate(String fechaStr) {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate = formato.parse(fechaStr);

			return new Date(utilDate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//////////////////////////////////////
	//////////////////// Animacion Botones
	public static void animacionFade(Button button) {
		FadeTransition ft = new FadeTransition(Duration.millis(300), button);
		ft.setFromValue(1.0);
		ft.setToValue(0.5);
		ft.setAutoReverse(true);
		ft.setCycleCount(2);
		ft.play();
	}

}
