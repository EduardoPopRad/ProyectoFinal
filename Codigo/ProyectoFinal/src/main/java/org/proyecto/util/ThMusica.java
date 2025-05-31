package org.proyecto.util;

import java.net.URL;

import org.proyecto.controler.MenuController;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ThMusica implements Runnable {
	
	private MenuController controller;
	private MediaPlayer mediaPlayer;
	private boolean reproducir;
	private double volumen;

	public ThMusica(MenuController c) {
		this.controller=c;
		this.volumen = leerVolumen();
		this.reproducir= false; //Esta en falso para que inicie la reproduccion de la musica en el while
	}

	@Override
	public void run() {
		while (!controller.getCerrar()) {
			try {
				volumen = leerVolumen();

				if (volumen > 0) {
					if (!reproducir) {
						iniciarReproduccion(volumen);
					} else {
						mediaPlayer.setVolume(volumen);
					}
				} else {
					detenerReproduccion();
				}
				Thread.sleep(1000); // verificar volumen cada segundo
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private double leerVolumen() {
		try {
			String volumen = GestorFicheroConfiguracion.obtenerRuta("volumen");
			return Double.parseDouble(volumen);
		} catch (Exception e) {
			return 0.0;
		}
	}

	private void iniciarReproduccion(double volumen) {
		try {
			URL archivo = getClass().getResource("/recursos/audio/ambiental.mp3");
			if (archivo == null) {
				System.out.println("No encuentra el archivo y hace return");
				return;
			}

			Media media = new Media(archivo.toExternalForm());
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // repetir indefinidamente
			mediaPlayer.setVolume(volumen);
			mediaPlayer.play();
			reproducir = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void detenerReproduccion() {
        if (mediaPlayer != null && reproducir) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null;
            reproducir = false;
        }
    }

}
