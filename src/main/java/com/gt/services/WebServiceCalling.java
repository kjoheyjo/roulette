package com.gt.services;

import java.io.BufferedReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.github.kevinsawicki.http.HttpRequest;

@Service
public class WebServiceCalling {

	public String sendPost(HashMap<String, Object> requestesParameters, String url, String sessionKey,
			boolean isServerKey) throws Exception {

		URL obj = new URL(url);

		// Attempt to use HttpRequest to send post request to parse cloud
		HttpRequest request = HttpRequest.post(obj).contentType("application/json");
		if (isServerKey) {
			request.header("server_key", sessionKey);
		} else {
			request.header("session_key", sessionKey);
		}

		request.acceptJson();
		JSONObject jsonParam = new JSONObject();
		for (Map.Entry<String, Object> entry : requestesParameters.entrySet()) {
			jsonParam.put(entry.getKey(), entry.getValue());
			// log.info(entry.getKey() + " : " + entry.getValue());
		}
		// log.info(jsonParam.toString());
		request.send(jsonParam.toString().getBytes("UTF-8"));
		// log.info("\nSending 'POST' request to URL : " + url);
		// log.info("Response Code : " + request.code());
		// log.debug("\nSending 'POST' request to URL : " + url);
		// log.debug("Response Code : " + request.code());
		if (request.ok()) {
			System.out.println("HttpRequest WORKED");
			String inputLine;
			Map<String, List<String>> map = request.headers();

			StringBuffer response = new StringBuffer();
			BufferedReader in = new BufferedReader(request.bufferedReader());
			while ((inputLine = in.readLine()) != null) {
				response.append("\n");
				response.append(inputLine);
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
