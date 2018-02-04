package com.jlt.wikier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONObject;

public class Wikier {
	
	private static final String baseUrl = "https://en.wikipedia.org/api/rest_v1/page/summary/";
	
	public static void main(String[] args) {
		try {
			int i=40;
			while(i>0) {
				long time = System.currentTimeMillis();
				InputStream is = new URL(baseUrl+"Java").openStream();
				BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
				String fileLine = rd.readLine();
				JSONObject json = new JSONObject(fileLine);
				String extract = json.getString("extract");
				i--;
				long timetaken = System.currentTimeMillis() - time;
				System.out.println("Time Taken "+ (timetaken/1000) + "." + (timetaken%1000) + " seconds");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
