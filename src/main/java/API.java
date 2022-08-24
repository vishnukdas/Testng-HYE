import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class API {

    public void aws () throws IOException {
        String bucket = "stage-magicleap-screenshots-v1";
        String key = "005FM-CKVXE-LK6GX-JLPL8/misc/command-log";

        S3Client client = S3Client.builder().build();

        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        ResponseInputStream<GetObjectResponse> response = client.getObject(request);

        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(key));

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = response.read(buffer)) !=  -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        response.close();
        outputStream.close();
    }
}