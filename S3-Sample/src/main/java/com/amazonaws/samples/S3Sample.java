package com.amazonaws.samples;


import java.io.IOException;
import java.text.ParseException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.util.UUID;
import java.io.Reader;

//import com.amazonaws.AmazonClientException;
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.auth.profile.ProfileCredentialsProvider;
//import com.amazonaws.regions.Region;
//import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
//import com.amazonaws.services.s3.model.ListObjectsRequest;
//import com.amazonaws.services.s3.model.ObjectListing;
//import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
//import com.amazonaws.services.s3.model.S3ObjectInputStream;
//import com.amazonaws.services.s3.model.S3ObjectSummary;

//import java.io.*;
import java.util.Arrays;



public class S3Sample {
	
//    這邊是二氧化碳讀檔變數
//	  private String fileName;
////	  private String writePath;
//	  private double[] r;       //  release date.
//	  private double[] p;       //  processing time
//	  private double[] d;       //  due-date
//	  private double[] d_bar;   //  deadline
//	  private double[] e;       //  revenue
//	  private double[] w;       //  weight
//	  private double[] power;   //  power
//	  private double[][] s;     //  setup times
//	  private int size;         //  instance lengh
	
//    這邊是流水線讀檔變數
	  private String data;
	  private String fileName;
	  private int piTotal;
	  private int machineTotal;
	  private int[] fristProfit;    //  revenue of order
	  private int[] di;        //  due-date
	  private double[] wi;     //  tardiness penalty weight
	  private int[][] processingTime;
	  private int processingTimeStart;
	  public double b;
	  
	  private String[] STXT;    //processingTime
	  private String[] STXT2;   //numberofOrder,numberOfMachines
	  int p = 2;  //0
	  int d = 3;  //1
	  int w = 4;  //2

	public static void main(String[] args) throws IOException {
		



		// 存儲庫登錄與讀檔		
    	String Awskey = "AWS_Key";
    	String AwsSecret = "AWS_ID";
    	BasicAWSCredentials creds = new BasicAWSCredentials(Awskey, AwsSecret);

    	//Amazon S3 Client
        AmazonS3 s3Client = AmazonS3Client.builder()
        	    .withRegion("ap-northeast-1")//Region S3 資料庫儲存位置 亞太地區東京
        	    .withCredentials(new AWSStaticCredentialsProvider(creds))
        	    .build();
        //讀檔
        	String bucketName = "openga-test1"; // S3 資料庫名稱
        	int[] order = new int[]{100};
        	int[] x = new int[]{3};
        	int instanceReplications = 5;  
        	
        	// 流水線讀檔迴圈	
        	for (int i=0;i<order.length;i++) {
        		for (int j=0;j<x.length;j++) {
        			for (int k=0;k<instanceReplications;k++) {        				
    	        	    String fileName = "p"+order[i]+"x"+x[j]+"_"+k+".txt";
    	        	    System.out.println(fileName);
    	        	    S3Object object = s3Client.getObject(new GetObjectRequest(bucketName,fileName));
    	        	    S3Sample OAS = new S3Sample();
    	        	    OAS.setData(fileName);
    	        	    OAS.readfile(object.getObjectContent());    	        	    
        			}
        		}
        	}        
//          S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, "instances/SingleMachineOASWithTOU/10orders/Tao1/R1/Dataslack_10orders_Tao1R1_1.txt"));
        	
        	//二氧化碳讀檔規則
            
//            OAS.setData(10);
//            OAS.getDataFromFile(object.getObjectContent());
            
//            String str = getDataFromFile(object.getObjectContent());            
//            String[] array = str.split(",| |\t");
//            System.out.println(array.length);
//            for(int i = 0 ; i < array.length ; i++) 
//            {
//            	System.out.print(array[i]+",");
//            }              

//            System.out.println("r: " + Arrays.toString(OAS.r));
//            System.out.println("p: " + Arrays.toString(OAS.p));
//            System.out.println("d: " + Arrays.toString(OAS.d));
//            System.out.println("d_bar: " + Arrays.toString(OAS.d_bar));
//            System.out.println("e: " + Arrays.toString(OAS.e));
//            System.out.println("w: " + Arrays.toString(OAS.w));
//            System.out.println("power: " + Arrays.toString(OAS.power));
//            System.out.println("s: ");
//            for(int i=0; i<OAS.s.length; i++){
//              System.out.println(Arrays.toString(OAS.s[i]));
//            } 
            
    }
	
	
//  流水線讀檔規則
	  public void setData(String fileName) {
		    this.fileName = fileName;
	  }
	
	  public void readfile(InputStream input) throws FileNotFoundException, IOException {

		    BufferedReader br = new BufferedReader(new InputStreamReader(input));
		    String message = "", eachLine = "";

		    while ((eachLine = br.readLine()) != null) {
		      message += eachLine + "\n";
		    }
		    STXT = message.split("\t|\n");


		    for (int i = 0; i < STXT.length ; i++) {
		    	System.out.println(STXT[i]); 
		    }
		    
//		    piTotal = Integer.parseInt(STXT[0]);
//		    machineTotal = Integer.parseInt(STXT[1]);
//		    fristProfit = new int[piTotal];
//		    di = new int[piTotal];
//		    wi = new double[piTotal];
//		    processingTime = new int[piTotal][machineTotal];
//
//
//		    processingTimeStart = (w + 1) + 3 * (piTotal - 1);
////		    System.out.println(processingTimeStart);
//
//		    for (int i = 0; i < piTotal; i++) {
//		      fristProfit[i] = Integer.parseInt(STXT[p]);
//		      p += 3;
//		      di[i] = Integer.parseInt(STXT[d]);
//		      d += 3;
//		      wi[i] = Double.parseDouble(STXT[w]);
//		      w += 3;
//		      
//		      for (int j = 0; j < machineTotal; j++) {
//		        processingTime[i][j] = Integer.parseInt(STXT[processingTimeStart]);
//		        processingTimeStart += 1;
////		        System.out.print(processingTime[i][j] + " ");
//		      }
////		      System.out.println();
//		    }
		  }
	  
//    流水線讀檔規則
	  public int getPiTotal() {
		    return this.piTotal;
		  }
	  public int getMachineTotal() {
		    return this.machineTotal;
	  }
	  public int[] getprofit() {
		    return this.fristProfit;
		  }
	  public int[] getdi() {
		    return this.di;
		  }
	  public double[] getwi() {
		    return this.wi;
		  }
	  public int[][] getprocessingTime() {
		    return this.processingTime;
		  }
	  public String getfileName() {
		    return this.fileName;
		  }
	  public double getb(){
		    return this.b;
		  }
	

//    二氧化碳讀檔規則
//    public void getDataFromFile(InputStream input)throws IOException {
//        try {  
//          BufferedReader br = new BufferedReader(new InputStreamReader(input));
//          String[] tmp;
//          r = new double[size];
//          p = new double[size];
//          d = new double[size];
//          d_bar = new double[size];
//          e = new double[size];
//          w = new double[size];
//          power = new double[size];
//          s = new double[size+1][size];
//
//          tmp = br.readLine().split(",");//split 0,10,10,2,4,6,4,5,7,3,5,0
//          for (int i = 0; i < size; i++) {  //i = orders,test 10
//            r[i] = Double.parseDouble(tmp[i + 1]);
//          }
//
//          tmp = br.readLine().split(",");//split 0,10,10,2,4,6,4,5,7,3,5,0
//          for (int i = 0; i < size; i++) {  //i = orders,test 10
//            p[i] = Double.parseDouble(tmp[i + 1]);
//          }
//
//          tmp = br.readLine().split(",");//split 0,10,10,2,4,6,4,5,7,3,5,0
//          for (int i = 0; i < size; i++) {  //i = orders,test 10
//            d[i] = Double.parseDouble(tmp[i + 1]);
//          }
//
//          tmp = br.readLine().split(",");//split 0,10,10,2,4,6,4,5,7,3,5,0
//          for (int i = 0; i < size; i++) {  //i = orders,test 10
//            d_bar[i] = Double.parseDouble(tmp[i + 1]);
//          }
//
//          tmp = br.readLine().split(",");//split 0,10,10,2,4,6,4,5,7,3,5,0
//          for (int i = 0; i < size; i++) {  //i = orders,test 10
//            e[i] = Double.parseDouble(tmp[i + 1]);
//          }
//
//          tmp = br.readLine().split(",");//split 0,10,10,2,4,6,4,5,7,3,5,0
//          for (int i = 0; i < size; i++) {  //i = orders,test 10
//            w[i] = Double.parseDouble(tmp[i + 1]);
//          }
//          
//          tmp = br.readLine().split(",");//split 0,10,10,2,4,6,4,5,7,3,5,0
//          for (int i = 0; i < size; i++) {  //i = orders,test 10
//            power[i] = Double.parseDouble(tmp[i + 1]);
//          }
//
//          for (int i = 0; i < size+1; i++) {
//            tmp = br.readLine().split(",");
//            for (int j = 0; j < size; j++) {
//              s[i][j] = Double.parseDouble(tmp[j+1]);
//            }
//          }
//        } catch (Exception e) {
//          e.printStackTrace();
//          System.out.println(e.toString());
//        }
//      }
//    public void setData(int size) { 
//        this.size = size;
//    }
//    public String getFileName() {
//        return fileName;
//      }
//    public double[] getR() {
//        return r;
//      }
//    public double[] getP() {
//        return p;
//      }
//    public double[] getD() {
//        return d;
//      }
//    public double[] getD_bar() {
//        return d_bar;
//      }
//    public double[] getE() {
//        return e;
//      }
//    public double[] getW() {
//        return w;
//      }
//    public double[] getPower() {
//        return power;
//      }
//    public double[][] getS() {
//        return s;
//      }
//    public int getSize() {
//        return size;
//      }
	
	
	
//  private static String displayTextInputStream(InputStream input) throws IOException {
//  // Read the text input stream one line at a time and display each line.
//  BufferedReader br = new BufferedReader(new InputStreamReader(input));
//  String read = "",buffread="";
//  while ((buffread = br.readLine()) != null) {
//      read += buffread + "\n";
//  }
//	return read;
//}
}
