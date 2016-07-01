package com.fireshot.contractprinter.print;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.sec.android.ngen.common.lib.ssp.CapabilitiesExceededException;
import com.sec.android.ngen.common.lib.ssp.DeviceNotReadyException;
import com.sec.android.ngen.common.lib.ssp.Result;
import com.sec.android.ngen.common.lib.ssp.Ssp;
import com.sec.android.ngen.common.lib.ssp.printer.PrintAttributes;
import com.sec.android.ngen.common.lib.ssp.printer.PrintAttributesCaps;
import com.sec.android.ngen.common.lib.ssp.printer.PrinterService;
import com.sec.android.ngen.common.lib.ssp.printer.PrintletAttributes;

import java.io.File;

/**
 * Created by Felipe on 01/07/2016.
 */
public class Printer extends AsyncTask<Void, Void, Void>{

    final String TAG = "";
    private Context mContext;
    String filePath;
    int copies;

    public Printer(Context context, int copies, String filePath){
        this.copies = copies;
        this.filePath = filePath;
        mContext = context;
        try{
            Ssp.getInstance().initialize(mContext);
        }catch(final SsdkUnsupportedException e){

        }catch(final SecurityException e){

        }catch(final DeviceNotReadyException e){

        }
    }


    @Override
    protected Void doInBackground(Void... params) {
        try {
            print();
        } catch (CapabilitiesExceededException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void print() throws CapabilitiesExceededException {
        PrintAttributesCaps caps = requestCaps(mContext);

        final PrintAttributes attributes = new PrintAttributes.PrintFromStorageBuilder(Uri.fromFile(new File(filePath)))
                .setColorMode(caps.getColorModeList().get(0))
                .setDuplex(PrintAttributes.Duplex.DEFAULT)
                .setAutoFit(PrintAttributes.AutoFit.DEFAULT)
                .setCopies(copies)
                .build(caps);

        final PrintletAttributes taskAttribs = new PrintletAttributes.Builder()
                .setShowSettingsUi(false)
                .build();

        final String rid = PrinterService.submit(mContext, attributes, taskAttribs);
    }

    /**
     * Get printer capacity
     * @param context
     * @return
     */
    private static PrintAttributesCaps requestCaps(final Context context){
        final Result result = new Result();
        final PrintAttributesCaps caps = PrinterService.getCapabilities(context, result);

        if(caps != null){
//            Log.d(TAG, "Received Caps as:"+
//                    ",ColorMode: " + caps.getColorModeList()+
//                    ",Max Copies: " + caps.getMaxCopies()+
//                    ",Duplex: " + caps.getDuplexList());
        }
        return caps;
    }

}
