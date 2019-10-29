package com.usman.aws;

import java.util.HashMap;
import java.util.Map;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentity.CognitoIdentityAsyncClient;
import software.amazon.awssdk.services.cognitoidentity.model.GetOpenIdTokenRequest;
import software.amazon.awssdk.services.cognitoidentity.model.GetOpenIdTokenResponse;

//@Service
public class CognitoService {

	String USER_POOL_ID = "ap-northeast-2_a8emYYZzs";
	String CLIENT_ID = "ap-northeast-2:3r8n6e2fsm686umcuhk1oi7jj5";
	Region REGION = Region.AP_NORTHEAST_2;

	public void authenticate(String username, String password) {

		// StaticCredentialsProvider provider =
		// StaticCredentialsProvider.create(AwsBasicCredentials.create("",
		// secretAccessKey))

		Map<String, String> credentials = new HashMap<>();
		credentials.put("USERNAME", username);
		credentials.put("PASSWORD", password);

		CognitoIdentityAsyncClient client = CognitoIdentityAsyncClient.builder().region(REGION).build();

		GetOpenIdTokenRequest request = GetOpenIdTokenRequest.builder().logins(credentials).build();

		GetOpenIdTokenResponse response = client.getOpenIdToken(request).join();

		System.out.println(response);

	}

}
