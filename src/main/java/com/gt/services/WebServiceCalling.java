package com.gt.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.github.kevinsawicki.http.HttpRequest;
import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;

@Service
public class WebServiceCalling {

	public String sendPost(HashMap<String, Object> requestesParameters, String url, String sessionKey,
			boolean isServerKey) throws Exception {

		URL obj;
		try {
			obj = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw e;
		}

		// Attempt to use HttpRequest to send post request to parse cloud
		HttpRequest request = HttpRequest.post(obj).contentType("application/json");
		if (isServerKey) {
			request.header("server_key", sessionKey);
		}

		request.acceptJson();
		JSONObject jsonParam = new JSONObject();
		for (Map.Entry<String, Object> entry : requestesParameters.entrySet()) {
			try {
				jsonParam.put(entry.getKey(), entry.getValue());
				System.out.println(entry.getKey() + " " + entry.getValue());
			} catch (JSONException e) {
				e.printStackTrace();
				throw e;
			}
			// log.info(entry.getKey() + " : " + entry.getValue());
		}
		// log.info(jsonParam.toString());
		try {
			request.send(jsonParam.toString().getBytes("UTF-8"));
		} catch (HttpRequestException | UnsupportedEncodingException e) {
			throw e;
		}
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " + request.code());
		// log.debug("\nSending 'POST' request to URL : " + url);
		// log.debug("Response Code : " + request.code());
		if (request.ok()) {
			System.out.println("HttpRequest WORKED");
			String inputLine;
			Map<String, List<String>> map = request.headers();

			StringBuffer response = new StringBuffer();
			BufferedReader in = new BufferedReader(request.bufferedReader());
			try {
				while ((inputLine = in.readLine()) != null) {
					response.append("\n");
					response.append(inputLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
			System.out.println(response.toString());
			// log.debug(response.toString());
			return response.toString();
		} else {
			// log.debug("HttpRequest FAILED " + request.code() + request.body());
			return null;
		}

	}

}
