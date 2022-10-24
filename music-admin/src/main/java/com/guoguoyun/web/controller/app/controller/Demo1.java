package com.guoguoyun.web.controller.app.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


import sun.audio.*;

public class Demo1 {
	// 定义两个存放分钟和秒的数组
	private static double[] d1 = new double[55];
	private static double[] d2 = new double[55];

	// JDK自带音乐播放无需导包
	public static void MusicPlayer() {
		try {
			AudioPlayer.player.start(new FileInputStream(new File("./music/邓紫棋 - Paomo.wav")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		// 歌曲播放
		MusicPlayer();

		// 读取lrc文件并转编码到 data4中
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("./data/画-邓紫棋.lrc"), "GBK"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./data/data4.txt"), "UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String i = reader.readLine(); i != null; i = reader.readLine()) {

			writer.write(i);
			writer.newLine();
		}
		reader.close();
		writer.close();
		
		
		// 获取秒数组 d1[]
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/data4.txt")));
		int countLine = 0;
		while ((reader2.readLine()) != null) {
			countLine++;

			if (countLine <= 4)
				continue;
			for (int i = 0; i < d1.length; i++) {
				d1[i] = Double.parseDouble(reader2.readLine().substring(4, 9));
//            	System.out.println(d1[i]);
			}

		}
		reader2.close();

		// 获取分数组d2[]
		BufferedReader reader3 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/data4.txt")));
		int countLine2 = 0;
		while ((reader3.readLine()) != null) {
			countLine2++;

			if (countLine2 <= 4)
				continue;

			for (int i = 0; i < d2.length; i++) {
				d2[i] = Double.parseDouble(reader3.readLine().substring(2, 3));
//            	System.out.println(d2[i]);
			}
		}
		reader3.close();

		// 歌词根据时间差输出到控制面板
		BufferedReader reader4 = new BufferedReader(new InputStreamReader(new FileInputStream("./data/data4.txt")));
		int countLine3 = 0;
		while ((reader4.readLine()) != null) {
			countLine3++;

			if (countLine3 <= 4)
				continue;

			// 延迟判断
			for (int i = 0; i < 55; i++) {
				// 歌词
				System.out.println(reader4.readLine().substring(10));
				
				//时间差
				double num1 = d1[i] * 1000 + d2[i] * 60 * 1000;
				double num2 = d1[i + 1] * 1000 + d2[i + 1] * 60 * 1000;
				long num3 = Math.round(num2 - num1);
				try {
					Thread.sleep(num3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
		reader4.close();

	}
}