package tecno.launcher.managers;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tecno.launcher.frame.ErrorFrame;
import tecno.launcher.main.App;

/**
 * This class manage Authentication from Mojang Auth Servers
 * @author MRtecno98
 * @version 1.0.0, 08/20/1017
 */
@SuppressWarnings("deprecation")
public class AuthManager {
	/** Username field */
	private static String username;
	/** Password field */
	private static String password;
	
	/**
	 * Autenticate user from username and password fields
	 * Requires Internet Connection
	 * @return autenticated True if user is autenticated False if not
	 */
	@SuppressWarnings("unchecked")
	public static boolean autenticate() {
    	JSONObject body = new JSONObject();
    	JSONObject agent = new JSONObject();
    	agent.put("name", "Minecraft");
    	agent.put("version", 1);
    	body.put("agent", agent);
    	body.put("username" , username);
    	body.put("password" , password);
    	
    	String bodyText = body.toJSONString();
    	
    	String jsonResponse = post(App.autentication , bodyText);
    	
    	if(jsonResponse == null) {
    		String[] textError = {"Impossibile effettuare la connessione" , "ai server mojang"};
    		try {
				ErrorFrame err = new ErrorFrame(textError , "Errore di connessione");
				err.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		return false;
    	}
    	
    	JSONParser parser = new JSONParser();
    	JSONObject response = null;
    	try {
			response = (JSONObject) parser.parse(jsonResponse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	if(response == null) {
    		return false;
    	}
    	
    	return response.containsKey("accessToken");
    }
    
	/**
	 * Build and send a HTTP POST request for autenticate at mojang servers
	 * @param completeUrl URL of the HTTP server to send request
	 * @param body JSON body of the POST request
	 * @return response Response of the server
	 */
    public static String post(String completeUrl, String body) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(completeUrl);
        httpPost.setHeader("Content-type", "application/json");
        HttpEntity entity = null;
        try {
            StringEntity stringEntity = new StringEntity(body);
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            entity = response.getEntity();
            String responseString = EntityUtils.toString(entity);
            return responseString;
        } catch (Exception e) {
            return null;
        }finally {
        	try {
        		EntityUtils.consume(entity);
       		} catch (IOException e) {
       			e.printStackTrace();
       		}
        }
    }
    
    /**
     * Get Username
     * @return username The object Username
     */
	public static String getUsername() {
		return username;
	}
	
	/**
	 * Get Passowrd
	 * @return password The object Password
	 */
	public static String getPassword() {
		return password;
	}
	
	/**
	 * Set Username
	 * @param username New Username
	 */
	public static void setUsername(String username) {
		AuthManager.username = username;
	}
	
	/**
	 * Set Password
	 * @param password New Password
	 */
	public static void setPassword(String password) {
		AuthManager.password = password;
	}
	
	/**
	 * Construct the Manager using username and password
	 * @param username Username for auth
	 * @param password Password for auth
	 */
	@SuppressWarnings("static-access")
	public AuthManager(String username , String password) {
		this.username = username;
		this.password = password;
	}
}
