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
	//��������� AudioInputStream
				stream = AudioSystem.getAudioInputStream(urlSound);
	//��������� ��������� ���������� Clip
				clip = AudioSystem.getClip();
	//������������ ��������� ������ �� Clip
				clip.open(stream);
	//�������� ������� ���� �����������
				clip.addLineListener(new Listener());
	//�������� ��������� �������			
	volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	//���������, �� ��� ������������� ������			
	released = true;
			} catch (IOException | LineUnavailableException | UnsupportedAudioFileException  exc) {
				exc.printStackTrace();
				released = false;
				
				close();
			}
		}
		// ����� �������� ������������
	//������� true ���� ���� ������������, false���� �������� �������
		public boolean isReleased() {
			return released;
		}
		
		// �������� �� ���������� ����������� �� ����� ������
		public boolean isPlaying() {
			return playing;
		}

		// ����� �������
		/*
		  breakOld  ������� �������� ���� ���� ����������
		  ���� breakOld==true, �� ���� ����� �������� �� �������� �����
		  ������ ����� �� ����������
		*/
		public void play(boolean breakOld) {
			if (released) {
				if (breakOld) {
					clip.stop();//�������
					clip.setFramePosition(0);//��������  �� �������
					clip.start();//�������
					playing = true; //³��������� �����������
				} else if (!isPlaying()) {//���� ����������� �� ����������
					clip.setFramePosition(0);//��������  �� �������

					clip.start();//�������
					playing = true;}}} //³��������� �����������
		//����� ��� ������� ����������� ��������
		public void play() {
			play(true);
		}
		
		//������� �����������
		public void stop() {
			if (playing) {//���� ����������� �� ����������, �� �������� ����
				clip.stop();
			}
		}
		
		public void close() {//�������� ������
			if (clip != null)
				clip.close();
			
			if (stream != null)
				try {
					stream.close();
				} catch (IOException exc) {
					exc.printStackTrace();
				}
		}

		//������������ �������
		public void setVolume(float x) {

			if (x<0) x = 0;
			if (x>1) x = 1;
			float min = volumeControl.getMinimum();
			float max = volumeControl.getMaximum();

			volumeControl.setValue((max-min)*x+min);
		}
		
		//��������������
	        public float getVolume() {
			float v = volumeControl.getValue();
			float min = volumeControl.getMinimum();
			float max = volumeControl.getMaximum();
			return (v-min)/(max-min);
		}

		// ����� ���� ���� ��������� ����������
		public void join() {
			if (!released) return;
	//���� �����������
			synchronized(clip) {
				try {
					while (playing)
						clip.wait();//������� ������
				} catch (InterruptedException exc) {
					exc.printStackTrace();
				}
			}
		}
		
		// ��������� ����� ��� ������� �����������
		public Sound playSound(URL urlSound) {
	        try{
	        Sound snd = new Sound(urlSound);
	        snd.play();
	        return snd;}catch (Exception e) {
	        e.printStackTrace();
	        }
	        return null;}
		
	//��������� �������� ���� ������� ��䳿 �������
		private class Listener implements LineListener {
			public void update(LineEvent ev) {
				if (ev.getType() == LineEvent.Type.STOP) {
					playing = false;
					synchronized(clip) {
						clip.notify();}
	}}
	}}
