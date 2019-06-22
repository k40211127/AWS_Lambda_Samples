package com.amazonaws.samples;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import java.nio.ByteBuffer;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class flowShop implements Runnable{

	String fileName = "";
	
	public synchronized static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(330);	//多執行緒		
      	int[] p = new int[]{10};
      	int[] x = new int[]{3,5};
      	int Repeat = 3;

      	// 流水線讀檔迴圈	
      	for (int i=0;i<p.length;i++) {
      		for (int j=0;j<x.length;j++) {      			
      			for (int k=0;k<Repeat;k++) {      
      				if (p[i]==100 && x[j]==10) {      					
      					System.out.println("end");
      					break;
      				}else{
      					flowShop main = new flowShop();	
      	        	    main.setFileName("\"p"+p[i]+"x"+x[j]+"_0"+".txt"+"\""); 
      	        	    executor.execute(main); 
      				}
      			}
      		}
      	}
      	executor.shutdown();      	
	}
	
	@Override
	public void run() {
		invoke();
	}

	public  void invoke() {				
//     System.out.println(Thread.currentThread().getName() + "被調用了        ");
      
	  // (1) Define the AWS Region in which the function is to be invoked
      Regions region = Regions.fromName("us-east-1"); // 美國東部地區
      
      // (2) Modify to leverage credentials
      BasicAWSCredentials credentials = new 
              BasicAWSCredentials("AWS_ID", 
                      "AWS_Key");
      
      // (3) Build the client, which will ultimately invoke the function
      AWSLambdaClientBuilder builder = AWSLambdaClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region);                                     

      // (4) Create an InvokeRequest with required parameters
      InvokeRequest req = new InvokeRequest()
                                 .withFunctionName("MyFunction2018") // 在AWS Lambda上的函數名稱
                                 .withPayload(this.fileName); // optional       // 傳送給函數接收的一個   字串、陣列、JSON格式、數值等
      
      // (5) Invoke the function and capture response
      AWSLambda client = builder.build();
      InvokeResult res = client.invoke(req);	       	        
      
      if (res.getStatusCode() == 200) {	
          ByteBuffer response_payload = res.getPayload();          
          String str = new String(response_payload.array());
          str = new String(str.substring(1, str.length()-1));
          System.out.println(str);                           
//          StringTokenizer st = new StringTokenizer(str, ",");
//          while (st.hasMoreTokens()){
//              System.out.println(st.nextToken());
//          }
      }
      else {
          System.out.format("Received a non-OK response from AWS: %d\n",
                  res.getStatusCode());
      }		
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
