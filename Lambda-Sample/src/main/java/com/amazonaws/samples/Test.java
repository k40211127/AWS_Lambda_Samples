package com.amazonaws.samples;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaAsync;
import com.amazonaws.services.lambda.AWSLambdaAsyncClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;


public class Test {

	public static void main(String[] args) {
//        String function_name = "Myfunction2018";
		String params =  "{\"Pc:0.5\", \"Pm\":\"0.5\"}";
		
        String function_input = "\"hi\"";
      
        Regions region = Regions.fromName("us-east-1");
        AWSLambdaAsync lambda = AWSLambdaAsyncClientBuilder.standard().withRegion(region).build();
       
//        System.out.println(ByteBuffer.wrap(function_input.getBytes()));// "ByteBuffer.wrap(function_input.getBytes())"
        
        InvokeRequest req = new InvokeRequest().withFunctionName("MyFunction2018").withPayload(ByteBuffer.wrap(function_input.getBytes()));       
            
        Future<InvokeResult> future_res = lambda.invokeAsync(req, (new AsyncLambdaHandler()));
       
    }
	
}
