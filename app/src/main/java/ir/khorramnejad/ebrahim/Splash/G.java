package ir.khorramnejad.ebrahim.Splash;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;


public class G extends Application {


    public static Context context;
    public static LayoutInflater inflater;
    public static final String
                                            DIR_SDCARD 				    = Environment.getExternalStorageDirectory()
                                                                        .getAbsolutePath();
    public static int                       gyear,
                                            gmounth,
                                            gday,
                                            jyear,
                                            jmounth,
                                            jday,
                                            hTime,
                                            mTime,
                                            sTime;

//    public static final String APP_SD_DIR                  = DIR_SDCARD    + "/ArTest";
//    public static String FILE_NAME                   = "Audio";
//    public static String dirName                     ="SoundRecorderPro",
//                                            REC_DIR                     = DIR_SDCARD +"/" +dirName,
//                                            filePath                    = REC_DIR + "/" + FILE_NAME +".mp3" ;
//    public static String inComment                   ="",
//                                            inAlbum                     ="",
//                                            inTitle                     ="Recorded by Sound Recorder Pro",
//                                            inArtist                    ="";
//    public static int                       minBuffer,
//                                            inSamplerate                = 44100,
//                                            inChanels                   =1,
//                                            inMp3Bitrate                =128,
//                                            inQuality                   =5;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

}
