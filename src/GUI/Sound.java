package GUI;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements AutoCloseable {
		private boolean released = false;
		private AudioInputStream stream = null;
		private Clip clip = null;
		private FloatControl volumeControl = null;
		private boolean playing = false;
		
	public Sound(URL urlSound) {
			try {
	//Отримання AudioInputStream
				stream = AudioSystem.getAudioInputStream(urlSound);
	//Отримання реалізації інтерфейсу Clip
				clip = AudioSystem.getClip();
	//Завантаження звукового потоку до Clip
				clip.open(stream);
	//Ініціюємо слухача подій програвання
				clip.addLineListener(new Listener());
	//Ініціюємо регулятор гучності			
	volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	//Позначаємо, що все завантажилося успішно			
	released = true;
			} catch (IOException | LineUnavailableException | UnsupportedAudioFileException  exc) {
				exc.printStackTrace();
				released = false;
				
				close();
			}
		}
		// Метод перевірки завантаження
	//Повертає true якщо звук завантажився, falseякщо відбулася помилка
		public boolean isReleased() {
			return released;
		}
		
		// перевіряє чи відбувається програвання на даний момент
		public boolean isPlaying() {
			return playing;
		}

		// Метод запуску
		/*
		  breakOld  визначає поведінку якщо звук програється
		  Якщо breakOld==true, то звук будет перевано та запущено знову
		  Інакше нічого не відбудеться
		*/
		public void play(boolean breakOld) {
			if (released) {
				if (breakOld) {
					clip.stop();//Зупинка
					clip.setFramePosition(0);//Показчик  на початок
					clip.start();//Початок
					playing = true; //Відбувається програвання
				} else if (!isPlaying()) {//Якщо програвання не відбувається
					clip.setFramePosition(0);//Показчик  на початок

					clip.start();//початок
					playing = true;}}} //Відбувається програвання
		//Метод для виклику програвання спочатку
		public void play() {
			play(true);
		}
		
		//Зупинка програвання
		public void stop() {
			if (playing) {//Якщо програвання ще відбувається, то зупинити його
				clip.stop();
			}
		}
		
		public void close() {//Закриття потоку
			if (clip != null)
				clip.close();
			
			if (stream != null)
				try {
					stream.close();
				} catch (IOException exc) {
					exc.printStackTrace();
				}
		}

		//Налаштування гучності
		public void setVolume(float x) {

			if (x<0) x = 0;
			if (x>1) x = 1;
			float min = volumeControl.getMinimum();
			float max = volumeControl.getMaximum();

			volumeControl.setValue((max-min)*x+min);
		}
		
		//Поточнагучність
	        public float getVolume() {
			float v = volumeControl.getValue();
			float min = volumeControl.getMinimum();
			float max = volumeControl.getMaximum();
			return (v-min)/(max-min);
		}

		// Метод чекає доки закінчення відбудеться
		public void join() {
			if (!released) return;
	//Блок синхонізації
			synchronized(clip) {
				try {
					while (playing)
						clip.wait();//Зупинка потоку
				} catch (InterruptedException exc) {
					exc.printStackTrace();
				}
			}
		}
		
		// Статичний метод для виклику програвання
		public Sound playSound(URL urlSound) {
	        try{
	        Sound snd = new Sound(urlSound);
	        snd.play();
	        return snd;}catch (Exception e) {
	        e.printStackTrace();
	        }
	        return null;}
		
	//Створений внутрішній клас слухача події зупинки
		private class Listener implements LineListener {
			public void update(LineEvent ev) {
				if (ev.getType() == LineEvent.Type.STOP) {
					playing = false;
					synchronized(clip) {
						clip.notify();}
	}}
	}}
