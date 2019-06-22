package com.amazonaws.samples;

import java.nio.ByteBuffer;

import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

public class AsyncLambdaHandler implements AsyncHandler<InvokeRequest, InvokeResult>
{

    public void onSuccess(InvokeRequest req, InvokeResult res) {
        System.out.println("\nLambda function returned:");
        ByteBuffer response_payload = res.getPayload();
        System.out.println(new String(response_payload.array()));
        System.exit(0);
    }

    public void onError(Exception e) {
        System.out.println(e.getMessage());
        System.exit(1);
    }


}
