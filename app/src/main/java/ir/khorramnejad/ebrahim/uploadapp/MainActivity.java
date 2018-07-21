package ir.khorramnejad.ebrahim.uploadapp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ir.khorramnejad.ebrahim.PermissenRequest.RequestHelper;
import ir.khorramnejad.ebrahim.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String selectedFilePath;
    private String SERVER_URL = "http://khorramnejad.ir/uploads/upload.php";
    ImageView ivAttachment;
    Button bUpload;
    TextView tvFileName;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        requestForReadSdPermission();
        requestForNetworkPermission();
        requestForInternetPermission();



        ivAttachment = (ImageView) findViewById(R.id.ivAttachment);
        bUpload = (Button) findViewById(R.id.b_upload);
        tvFileName = (TextView) findViewById(R.id.tv_file_name);
        ivAttachment.setOnClickListener(this);
        bUpload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v== ivAttachment){

            showFileChooser();
        }
        if(v== bUpload){

            //on upload button Click
            if(selectedFilePath != null){
                dialog = ProgressDialog.show(MainActivity.this,"",
                        "Uploading File...",true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        uploadFile(selectedFilePath);
                    }
                }).start();
            }else{
                Toast.makeText(MainActivity.this,"Please choose a File First",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == PICK_FILE_REQUEST){
                if(data == null){
                    return;
                }


                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(this,selectedFileUri);
                Log.i(TAG,"Selected File Path:" + selectedFilePath);

                if(selectedFilePath != null && !selectedFilePath.equals("")){
                    tvFileName.setText(selectedFilePath);
                }else{
                    Toast.makeText(this,"Cannot upload file to server",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public int uploadFile(final String selectedFilePath){

        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead,bytesAvailable,bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length-1];

        if (!selectedFile.isFile()){
            dialog.dismiss();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvFileName.setText("Source File Doesn't Exist: " + selectedFilePath);
                }
            });
            return 0;
        }else{
            try{
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL(SERVER_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file",selectedFilePath);

                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);

                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable,maxBufferSize);
                buffer = new byte[bufferSize];

                bytesRead = fileInputStream.read(buffer,0,bufferSize);

                while (bytesRead > 0){
                    dataOutputStream.write(buffer,0,bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable,maxBufferSize);
                    bytesRead = fileInputStream.read(buffer,0,bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();

                Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvFileName.setText("File Upload completed.\n\n You can see the uploaded file here: \n\n" +
                                    "http://khorramnejad.ir/uploads/"+ fileName);
                        }
                    });
                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();



            } catch (FileNotFoundException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"File Not Found",Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "URL error!", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            return serverResponseCode;
        }

    }



    private void requestForReadSdPermission() {
        RequestHelper request = new RequestHelper(this);
        request.request(Manifest.permission.READ_EXTERNAL_STORAGE, new RequestHelper.OnGrantedListener() {
            @Override
            public void onGranted() {
                Toast.makeText(MainActivity.this, "Granted For RECEIVE SMS", Toast.LENGTH_SHORT).show();
            }
        }, new RequestHelper.OnDeniedListener() {
            @Override
            public void onDenied() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Permission Required")
                        .setMessage("Read SD card required for app")
                        .setPositiveButton("Ask me again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestForReadSdPermission();
                            }
                        })
                        .create()
                        .show();
            }
        });
    }

    private void requestForNetworkPermission() {
        RequestHelper request = new RequestHelper(this);
        request.request(Manifest.permission.ACCESS_NETWORK_STATE, new RequestHelper.OnGrantedListener() {
            @Override
            public void onGranted() {
                Toast.makeText(MainActivity.this, "Granted For RECEIVE SMS", Toast.LENGTH_SHORT).show();
            }
        }, new RequestHelper.OnDeniedListener() {
            @Override
            public void onDenied() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Permission Required")
                        .setMessage("network required for app")
                        .setPositiveButton("Ask me again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestForNetworkPermission();
                            }
                        })
                        .create()
                        .show();
            }
        });
    }


    private void requestForInternetPermission() {
        RequestHelper request = new RequestHelper(this);
        request.request(Manifest.permission.ACCESS_NETWORK_STATE, new RequestHelper.OnGrantedListener() {
            @Override
            public void onGranted() {
                Toast.makeText(MainActivity.this, "Granted For RECEIVE SMS", Toast.LENGTH_SHORT).show();
            }
        }, new RequestHelper.OnDeniedListener() {
            @Override
            public void onDenied() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Permission Required")
                        .setMessage("Internet required for app")
                        .setPositiveButton("Ask me again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestForInternetPermission();
                            }
                        })
                        .create()
                        .show();
            }
        });
    }

}