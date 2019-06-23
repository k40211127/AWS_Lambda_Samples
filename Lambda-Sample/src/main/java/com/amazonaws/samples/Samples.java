package com.amazonaws.samples;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.nio.ByteBuffer;


public class Samples implements Runnable{
	
    String ThreadCount;
	long start,end,start2=0,end2=0;

    
	public synchronized static void main(String[] args) {
//		 int cores = Runtime.getRuntime().availableProcessors();//Math.max(1, Runtime.getRuntime().availableProcessors() - 2)
		 ExecutorService executor = Executors.newFixedThreadPool(20);
		 for (int i=0;i<40;i++) {				 
			 Samples main = new Samples();	
			 main.setThreadCount(String.valueOf(i));			 
			 executor.execute(main);						 
//			 try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		 }		 
		 executor.shutdown();	
//		 System.out.println(colock.getExecutionTime()/1000);
	}
	
	@Override
	public void run() {
		start = 0;
		end = 0;
		setStart();
		invoke();
		
	} 
	
	public  void invoke() {		
			
//        System.out.println(Thread.currentThread().getName() + "³Q½Õ¥Î¤F        ");
        
	    // (1) Define the AWS Region in which the function is to be invoked
        Regions region = Regions.fromName("us-east-1");
        BasicAWSCredentials credentials = new 
                BasicAWSCredentials("AWS ID", 
                        "AWS KEY");
        
        // (2) Modify to leverage credentials
        AWSLambdaClientBuilder builder = AWSLambdaClientBuilder.standard()
          .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region);                                     

        // (3) Build the client, which will ultimately invoke the function
        
        AWSLambda client = builder.build();  
        // (4) Create an InvokeRequest with required parameters
        // String function_input = "Rui-Wen";
        InvokeRequest req = new InvokeRequest()
                                   .withFunctionName("MyFunction2018") // your functions name
                                   .withPayload(this.ThreadCount); // optional // pay any meassage       
        
        // (5) Invoke the function and capture response
        InvokeResult res = client.invoke(req);	       	        
        
        if (res.getStatusCode() == 200) {
        	setEnd();	
            ByteBuffer response_payload = res.getPayload();
            System.out.println(new String(response_payload.array())+"\t"+getExecutionTime()/1000.0);
             
        }
        else {
            System.out.format("Received a non-OK response from AWS: %d\n",
                    res.getStatusCode());
        }		
	}
	
    
    public long getStart() {
		return start;
	}

	public void setStart() {
		this.start = System.currentTimeMillis();
	}

	public long getEnd() {
		return end;
	}

	public void setEnd() {
		this.end = System.currentTimeMillis();
	}

	public long getExecutionTime(){
	    return (end - start);
	}
	  
	public String getThreadCount() {
		return this.ThreadCount;
	}

	public void setThreadCount(String str) {
		this.ThreadCount = str;
	}

}
