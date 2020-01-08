package com.example.image;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.renderscript.*;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


import static android.graphics.Color.HSVToColor;
import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;
import static android.graphics.Color.rgb;

public class MainActivity extends AppCompatActivity {
    Bitmap b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inMutable = true;
        TextView size = findViewById(R.id.size);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.mari, o);
        int width = b.getWidth();
        int height = b.getHeight();
        size.setText(width + "x" + height);
        ImageView original = findViewById(R.id.original1);
        original.setImageResource(R.drawable.mari);
        final Button button1 = findViewById(R.id.grey);
        final Button button2 = findViewById(R.id.filtre);
        final Button button3 = findViewById(R.id.saturation);
        final Button button4 = findViewById(R.id.contraste);
        final Button button5 = findViewById(R.id.flou);
        final Button button6 = findViewById(R.id.reset);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toGray(b);
                final ImageView Filter = findViewById(R.id.modified1);
                Filter.setImageBitmap(b);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toEgalHisto(b);
                final ImageView Filter = findViewById(R.id.modified1);
                Filter.setImageBitmap(b);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final SeekBar bar = findViewById(R.id.seekBar);
                bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {
                        BitmapFactory.Options o = new BitmapFactory.Options();
                        o.inMutable = true;
                        b = BitmapFactory.decodeResource(getResources(), R.drawable.mari, o);
                        toFilter(b, progress);
                        final ImageView Filter = findViewById(R.id.modified1);
                        Filter.setImageBitmap(b);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final SeekBar bar = findViewById(R.id.seekBar);
                bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {
                        BitmapFactory.Options o = new BitmapFactory.Options();
                        o.inMutable = true;
                        b = BitmapFactory.decodeResource(getResources(), R.drawable.mari, o);
                        toSature(b, progress);
                        final ImageView Filter = findViewById(R.id.modified1);
                        Filter.setImageBitmap(b);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                convolution(b,5);
                final ImageView Filter = findViewById(R.id.modified1);
                Filter.setImageBitmap(b);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BitmapFactory.Options o = new BitmapFactory.Options();
                o.inMutable = true;
                b = BitmapFactory.decodeResource(getResources(), R.drawable.mari, o);
                final ImageView original = findViewById(R.id.modified1);
                original.setImageResource(R.drawable.mari);
                original.setImageBitmap(b);
                TextView size = findViewById(R.id.size);
                int width = b.getWidth();
                int height = b.getHeight();
                size.setText(width + "x" + height);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.grey:
                BitmapFactory.Options o = new BitmapFactory.Options();
                o.inMutable = true;
                o = new BitmapFactory.Options();
                o.inMutable = true;
                b = BitmapFactory.decodeResource(getResources(), R.drawable.mari, o);
                ImageView original = findViewById(R.id.original1);
                original.setImageResource(R.drawable.mari);
                Button reset = findViewById(R.id.reset);
                reset.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        BitmapFactory.Options o = new BitmapFactory.Options();
                        o.inMutable = true;
                        b = BitmapFactory.decodeResource(getResources(), R.drawable.mari, o);
                        final ImageView original = findViewById(R.id.modified1);
                        original.setImageResource(R.drawable.mari);
                        original.setImageBitmap(b);
                        TextView size = findViewById(R.id.size);
                        int width = b.getWidth();
                        int height = b.getHeight();
                        size.setText(width + "x" + height);
                    }
                });
                Button button2 = findViewById(R.id.filtre);
                button2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        final SeekBar bar = findViewById(R.id.seekBar);
                        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress,
                                                          boolean fromUser) {
                                BitmapFactory.Options o = new BitmapFactory.Options();
                                o.inMutable = true;
                                b = BitmapFactory.decodeResource(getResources(), R.drawable.mari, o);
                                toFilter(b, progress);
                                final ImageView Filter = findViewById(R.id.modified1);
                                Filter.setImageBitmap(b);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });

                    }
                });
                Button button3 = findViewById(R.id.saturation);
                button3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        final SeekBar bar = findViewById(R.id.seekBar);
                        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress,
                                                          boolean fromUser) {
                                BitmapFactory.Options o = new BitmapFactory.Options();
                                o.inMutable = true;
                                b = BitmapFactory.decodeResource(getResources(), R.drawable.mari, o);
                                toSature(b, progress);
                                final ImageView Filter = findViewById(R.id.modified1);
                                Filter.setImageBitmap(b);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });

                    }
                });
                return true;

            case R.id.colored1:
                 o = new BitmapFactory.Options();
                o.inMutable = true;
                o = new BitmapFactory.Options();
                o.inMutable = true;
                b = BitmapFactory.decodeResource(getResources(), R.drawable.flowers, o);
                original = findViewById(R.id.original1);
                original.setImageResource(R.drawable.flowers);
                 reset = findViewById(R.id.reset);
                reset.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        BitmapFactory.Options o = new BitmapFactory.Options();
                        o.inMutable = true;
                        b = BitmapFactory.decodeResource(getResources(), R.drawable.flowers, o);
                        final ImageView original = findViewById(R.id.modified1);
                        original.setImageResource(R.drawable.flowers);
                        original.setImageBitmap(b);
                        TextView size = findViewById(R.id.size);
                        int width = b.getWidth();
                        int height = b.getHeight();
                        size.setText(width + "x" + height);
                    }
                });
                button2 = findViewById(R.id.filtre);
                button2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        final SeekBar bar = findViewById(R.id.seekBar);
                        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress,
                                                          boolean fromUser) {
                                BitmapFactory.Options o = new BitmapFactory.Options();
                                o.inMutable = true;
                                b = BitmapFactory.decodeResource(getResources(), R.drawable.flowers, o);
                                toFilter(b, progress);
                                final ImageView Filter = findViewById(R.id.modified1);
                                Filter.setImageBitmap(b);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });

                    }
                });
                button3 = findViewById(R.id.saturation);
                button3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        final SeekBar bar = findViewById(R.id.seekBar);
                        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress,
                                                          boolean fromUser) {
                                BitmapFactory.Options o = new BitmapFactory.Options();
                                o.inMutable = true;
                                b = BitmapFactory.decodeResource(getResources(), R.drawable.flowers, o);
                                toSature(b, progress);
                                final ImageView Filter = findViewById(R.id.modified1);
                                Filter.setImageBitmap(b);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });

                    }
                });
                return true;

            case R.id.colored2:
                o = new BitmapFactory.Options();
                o.inMutable = true;
                b = BitmapFactory.decodeResource(getResources(), R.drawable.landscape, o);
                original = findViewById(R.id.original1);
                original.setImageResource(R.drawable.landscape);
                reset = findViewById(R.id.reset);
                reset.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        BitmapFactory.Options o = new BitmapFactory.Options();
                        o.inMutable = true;
                        b = BitmapFactory.decodeResource(getResources(), R.drawable.landscape, o);
                        final ImageView original = findViewById(R.id.modified1);
                        original.setImageResource(R.drawable.landscape);
                        original.setImageBitmap(b);
                        TextView size = findViewById(R.id.size);
                        int width = b.getWidth();
                        int height = b.getHeight();
                        size.setText(width + "x" + height);
                    }
                });
                button2=findViewById(R.id.filtre);
                button2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        final SeekBar bar = findViewById(R.id.seekBar);
                        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress,
                                                          boolean fromUser) {
                                BitmapFactory.Options o = new BitmapFactory.Options();
                                o.inMutable = true;
                                b = BitmapFactory.decodeResource(getResources(), R.drawable.landscape, o);
                                toFilter(b, progress);
                                final ImageView Filter = findViewById(R.id.modified1);
                                Filter.setImageBitmap(b);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });

                    }
                });
                button3 = findViewById(R.id.saturation);
                button3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        final SeekBar bar = findViewById(R.id.seekBar);
                        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress,
                                                          boolean fromUser) {
                                BitmapFactory.Options o = new BitmapFactory.Options();
                                o.inMutable = true;
                                b = BitmapFactory.decodeResource(getResources(), R.drawable.landscape, o);
                                toSature(b, progress);
                                final ImageView Filter = findViewById(R.id.modified1);
                                Filter.setImageBitmap(b);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });

                    }
                });
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
 // version RenderScript : griser une image
    private void toGray(Bitmap bmp) {
        RenderScript rs = RenderScript.create(this);
        Allocation input = Allocation.createFromBitmap(rs, bmp);
        Allocation output = Allocation.createTyped(rs, input.getType());
        ScriptC_gray grayScript = new ScriptC_gray(rs);
        grayScript.forEach_toGray(input, output);
        output.copyTo(bmp);
        input.destroy();
        output.destroy();
        grayScript.destroy();
        rs.destroy();
    }

    // version RenderScript : saturer une image d'une couleur souhaitée
    private void toSature(Bitmap bmp,int color) {

        RenderScript rs = RenderScript.create(this);
        Allocation input = Allocation.createFromBitmap(rs, bmp);
        Allocation output = Allocation.createTyped(rs, input.getType());
        ScriptC_saturation saturationScript = new ScriptC_saturation(rs);
        saturationScript.set_choosedColor((float) color);
        saturationScript.forEach_toSaturate(input, output);
        output.copyTo(bmp);
        input.destroy();
        output.destroy();
        saturationScript.destroy();
        rs.destroy();
    }

    // version RenderScript : appliquer un filtre de la couleur souhaitée
    private void toFilter(Bitmap bmp,int newHuer) {
        RenderScript rs = RenderScript.create(this);
        Allocation input = Allocation.createFromBitmap(rs, bmp);
        Allocation output = Allocation.createTyped(rs, input.getType());
        ScriptC_randomHue randomHueScript = new ScriptC_randomHue(rs);
        randomHueScript.set_newHue((double)newHuer);
        randomHueScript.forEach_toNewHue(input, output);
        output.copyTo(bmp);
        input.destroy();
        output.destroy();
        randomHueScript.destroy();
        rs.destroy();
    }

    //version RenderScript : augmentation de contraste par extension dynamique
    public void toExtDyn(Bitmap bmp){
        Allocation mInAllocation;
        Allocation mOutAllocation;
        Allocation histoR;
        Allocation histoG;
        Allocation histoB;
        Allocation LUTr;
        Allocation LUTg;
        Allocation LUTb;
        RenderScript mRS = RenderScript.create(this);
        mInAllocation = Allocation.createFromBitmap(mRS, bmp);
        mOutAllocation = Allocation.createFromBitmap(mRS, bmp);
        histoR = Allocation.createSized(mRS, Element.U32(mRS), 256);
        histoG = Allocation.createSized(mRS, Element.U32(mRS), 256);
        histoB = Allocation.createSized(mRS, Element.U32(mRS), 256);
        LUTr = Allocation.createSized(mRS, Element.U32(mRS), 256);
        LUTg = Allocation.createSized(mRS, Element.U32(mRS), 256);
        LUTb = Allocation.createSized(mRS, Element.U32(mRS), 256);
        ScriptC_histo mScript = new ScriptC_histo(mRS);
        mScript.bind_histogramR(histoR);
        mScript.bind_histogramG(histoG);
        mScript.bind_histogramB(histoB);
        mScript.forEach_compute_histogram(mInAllocation);
        int []histogramR = new int[256];
        int []histogramG = new int[256];
        int []histogramB = new int[256];
        histoR.copyTo(histogramR);
        histoB.copyTo(histogramG);
        histoG.copyTo(histogramB);
        int mymaxr = maxArray(histogramR);
        int myminr = minArray(histogramR);
        int mymaxg = maxArray(histogramG);
        int myming = minArray(histogramG);
        int mymaxb = maxArray(histogramB);
        int myminb = minArray(histogramB);
        int[] lutr = new int[256];
        int[] lutg = new int[256];
        int[] lutb = new int[256];
        for ( int i=0; i < 256; i++ ){
            lutr[i]=(255*(i-myminr))/(mymaxr-myminr);
            lutg[i]=(255*(i-myming))/(mymaxg-myming);
            lutb[i]=(255*(i-myminb))/(mymaxb-myminb);
        }
        LUTr.copyFrom(lutr);
        LUTg.copyFrom(lutg);
        LUTb.copyFrom(lutb);
        mScript.bind_LUTr(LUTr);
        mScript.bind_LUTg(LUTg);
        mScript.bind_LUTb(LUTb);
        mScript.forEach_apply_histogram(mInAllocation, mOutAllocation);
        histoR.destroy();
        histoB.destroy();
        histoG.destroy();
        LUTb.destroy();
        LUTg.destroy();
        LUTr.destroy();
        mOutAllocation.copyTo(bmp);
        mInAllocation.destroy();
        mOutAllocation.destroy();
        mScript.destroy();
        mRS.destroy();
    }

    //version RenderScript : convolution filtre Gauss -> ne fonctionne pas
    private void toConvolution(Bitmap bmp,int size) {
        RenderScript rs = RenderScript.create(this);
        Allocation input = Allocation.createFromBitmap(rs, bmp);
        Allocation output =Allocation.createTyped(rs,input.getType());
        ScriptC_convolution convolutionScript = new ScriptC_convolution(rs);
        convolutionScript.bind_pixels(input);
        int[] gauss = new int[]{1,2,3,2,1,2,6,8,6,2,3,8,10,8,3,2,6,8,6,2,1,2,3,2,1};
        Allocation fGauss = Allocation.createSized(rs, Element.U32(rs), 25);
        convolutionScript.bind_filtre(fGauss);
        fGauss.copyFrom(gauss);
        convolutionScript.set_sizeConvolution(size);
        convolutionScript.set_width(bmp.getWidth());
        convolutionScript.set_height(bmp.getHeight());
        convolutionScript.forEach_toConvolution(input,output);
        output.copyTo(bmp);
        input.destroy();
        output.destroy();
        convolutionScript.destroy();
        rs.destroy();
    }

    //version RenderScript : augmentation de contrast par égalisation d'histogramme
    public void toEgalHisto(Bitmap bmp){
        RenderScript mRS = RenderScript.create(this);
        Allocation mInAllocation = Allocation.createFromBitmap(mRS, bmp);
        Allocation mOutAllocation = Allocation.createFromBitmap(mRS, bmp);
        Allocation histoR = Allocation.createSized(mRS, Element.U32(mRS), 256);
        Allocation histoG = Allocation.createSized(mRS, Element.U32(mRS), 256);
        Allocation histoB = Allocation.createSized(mRS, Element.U32(mRS), 256);
        Allocation histoR2 = Allocation.createSized(mRS, Element.U32(mRS), 256);
        Allocation histoG2 = Allocation.createSized(mRS, Element.U32(mRS), 256);
        Allocation histoB2 = Allocation.createSized(mRS, Element.U32(mRS), 256);
        ScriptC_histo mScript = new ScriptC_histo(mRS);
        mScript.bind_histogramR(histoR);
        mScript.bind_histogramG(histoG);
        mScript.bind_histogramB(histoB);
        mScript.forEach_compute_histogram(mInAllocation);

        int []histoRj = new int[256];
        int []histoGj = new int[256];
        int []histoBj = new int[256];

        histoR.copyTo(histoRj);
        histoB.copyTo(histoGj);
        histoG.copyTo(histoBj);

        for (int i=1;i<256;i++){
            histoRj[i]+=histoRj[i-1];
            histoGj[i]+=histoGj[i-1];
            histoBj[i]+=histoBj[i-1];
        }

        histoR2.copyFrom(histoRj);
        histoG2.copyFrom(histoGj);
        histoB2.copyFrom(histoBj);

        mScript.bind_histogramR2(histoR2);
        mScript.bind_histogramG2(histoG2);
        mScript.bind_histogramB2(histoB2);
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        mScript.forEach_apply_egal(mInAllocation, mOutAllocation);
        histoR.destroy();
        histoB.destroy();
        histoG.destroy();
        histoR2.destroy();
        histoB2.destroy();
        histoG2.destroy();
        mOutAllocation.copyTo(bmp);
        mInAllocation.destroy();
        mOutAllocation.destroy();
        mScript.destroy();
        mRS.destroy();
    }
    public int minArray(int[] array) {
        int i = 0;
        while (array[i] == 0) {
            i++;
        }
        return i;
    }

    public int maxArray(int[] array) {
        int i = (array.length) - 1;
        while (array[i] == 0) {
            i--;
        }
        return i;
    }

    //version RenderScript : diminution de contraste -> ne fonctionne pas
    public void toDimContrast(Bitmap bmp){
        Allocation mInAllocation;
        Allocation mOutAllocation;
        Allocation histoR;
        Allocation histoG;
        Allocation histoB;
        Allocation LUTr;
        Allocation LUTg;
        Allocation LUTb;
        RenderScript mRS = RenderScript.create(this);
        mInAllocation = Allocation.createFromBitmap(mRS, bmp);
        mOutAllocation = Allocation.createFromBitmap(mRS, bmp);
        histoR = Allocation.createSized(mRS, Element.U32(mRS), 256);
        histoG = Allocation.createSized(mRS, Element.U32(mRS), 256);
        histoB = Allocation.createSized(mRS, Element.U32(mRS), 256);
        LUTr = Allocation.createSized(mRS, Element.U32(mRS), 256);
        LUTg = Allocation.createSized(mRS, Element.U32(mRS), 256);
        LUTb = Allocation.createSized(mRS, Element.U32(mRS), 256);
        Allocation LUTr2 = Allocation.createSized(mRS, Element.U32(mRS), 256);
        Allocation LUTg2 = Allocation.createSized(mRS, Element.U32(mRS), 256);
        Allocation LUTb2 = Allocation.createSized(mRS, Element.U32(mRS), 256);
        ScriptC_histo mScript = new ScriptC_histo(mRS);
        mScript.bind_histogramR(histoR);
        mScript.bind_histogramG(histoG);
        mScript.bind_histogramB(histoB);
        mScript.forEach_compute_histogram(mInAllocation);
        int []histogramR = new int[256];
        int []histogramG = new int[256];
        int []histogramB = new int[256];
        histoR.copyTo(histogramR);
        histoB.copyTo(histogramG);
        histoG.copyTo(histogramB);
        int[] lutr = newLut(histogramR);
        int[] lutg = newLut(histogramG);
        int[] lutb = newLut(histogramB);
        LUTr.copyFrom(lutr);
        LUTg.copyFrom(lutg);
        LUTb.copyFrom(lutb);
        mScript.bind_LUTr(LUTr);
        mScript.bind_LUTg(LUTg);
        mScript.bind_LUTb(LUTb);
        mScript.forEach_apply_histogram(mInAllocation, mOutAllocation);
        histoR.destroy();
        histoB.destroy();
        histoG.destroy();
        LUTb.destroy();
        LUTg.destroy();
        LUTr.destroy();
        LUTb2.destroy();
        LUTg2.destroy();
        LUTr2.destroy();
        mOutAllocation.copyTo(bmp);
        mInAllocation.destroy();
        mOutAllocation.destroy();
        mScript.destroy();
        mRS.destroy();
    }

    int[] newLut(int[] lut){
        int cmpt=0;
        int[] newLut=new int [256];
        for ( int i=0; i <256; i++ ){
            if(lut[i]==0){
                cmpt++;
            }
        }
        int start=0;
        for(int i=0;i<256;i++){
            newLut[i]=0;
        }
        for (int i=0;i<256;i++){
            if(lut[i]!=0){
                newLut[start]=lut[i];
                start++;
            }
        }
        return newLut;
    }

    // version JAVA : convolution filtre Gauss
    private void convolution(Bitmap bmp, int size) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] out = new int[width * height];
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        int[] gauss = new int[]{1,2,3,2,1,2,6,8,6,2,3,8,10,8,3,2,6,8,6,2,1,2,3,2,1};
        int[] laPlace = new int[]{1,1,1,1,-8,1,1,1,1};
        for (int i = 0; i < pixels.length; i++) {
            int newRed = 0;
            int newGreen = 0;
            int newBlue = 0;
            int a=0;
            for (int c = -(size - 1) / 2; c <= (size - 1) / 2; c++) {
                for (int d = -(size - 1) / 2; d <= (size - 1) / 2; d++) {
                    if(i + c * width + d>=0 && i + c* width + d <pixels.length ) {
                        newRed += gauss[a]*red(pixels[i + c * width + d])/98;
                        newGreen += gauss[a]*green(pixels[i + c * width + d])/98;
                        newBlue +=gauss[a]*blue(pixels[i + c * width + d])/98;
                        a++;
                    }
                    else{
                        newRed+=255/98;
                        newBlue+=255/98;
                        newGreen+=255/98;

                    }
                }
            }

            out[i] = rgb(newRed , newGreen , newBlue);
        }
        bmp.setPixels(out, 0, width, 0, 0, width, height);
    }

   /* UNUSED CODE (versions java)
     public int newHSVToColor(float[] hue) {

        int ti = (int) (Math.floor(hue[0]) / 60) % 6;
        float f = (hue[0] / 60) - ti;
        float l = hue[2] * (1 - hue[1]);
        float m = hue[2] * (1 - (f * hue[1]));
        float n = hue[2] * (1 - ((1 - f) * hue[1]));
        if (ti == 0) {
            return Color.rgb((int) hue[2], (int) n, (int) l);
        }
        if (ti == 1) {
            return Color.rgb((int) m, (int) hue[2], (int) l);
        }
        if (ti == 2) {
            return Color.rgb((int) l, (int) hue[2], (int) n);
        }
        if (ti == 3) {
            return Color.rgb((int) l, (int) m, (int) hue[2]);
        }
        if (ti == 4) {
            return Color.rgb((int) n, (int) l, (int) hue[2]);
        } else {
            return Color.rgb((int) hue[2], (int) l, (int) m);
        }
    }
    public void toSature(Bitmap im) {
        int width = im.getWidth();
        int height = im.getHeight();
        int[] pixels = new int[width * height];
        im.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < pixels.length; i++) {
            float[] hsv = new float[3];
            newRGBToHSV(red(pixels[i]), green(pixels[i]), blue(pixels[i]), hsv);
            if ((hsv[0] <20) || (hsv[0] >340)) {
                pixels[i] = newHSVToColor(hsv);
            } else {
                double newColor = (0.3 * red(pixels[i])) + (0.59 * green(pixels[i])) + (0.11 * blue(pixels[i]));
                int nc = (int) newColor;
                pixels[i] = rgb(nc, nc, nc);
            }
        }
        im.setPixels(pixels, 0, width, 0, 0, width, height);
    }
    public void toFilter(Bitmap im,int randomFilter) {
        int width = im.getWidth();
        int height = im.getHeight();
        int[] pixels = new int[width * height];
        //double randomFilter = Math.random() * 360;
        im.getPixels(pixels, 0, width, 0, 0, width, height);
        int i;
        for (i = 0; i < pixels.length; i++) {
            float[] hsv = new float[3];
            newRGBToHSV(red(pixels[i]), green(pixels[i]), blue(pixels[i]), hsv);
            hsv[0] = (float) randomFilter;
            pixels[i] = newHSVToColor(hsv);
        }
        im.setPixels(pixels, 0, width, 0, 0, width, height);
    }


    //grise une image couleur en utilisant getPixels et setPixels
    public void toGray(Bitmap im) {
        int width = im.getWidth();
        int height = im.getHeight();
        int[] pixels = new int[width * height];
        im.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < pixels.length; i++) {
            double newColor = (0.3 * red(pixels[i])) + (0.59 * green(pixels[i])) + (0.11 * blue(pixels[i]));
            int nc = (int) newColor;
            pixels[i] = rgb(nc, nc, nc);
        }
        im.setPixels(pixels, 0, width, 0, 0, width, height);
    }




    //nouvelle version de RGBToHSV
    public void newRGBToHSV(int red, int green, int blue, float[] hue) {
        int min = Math.min(Math.min(red, blue), green);
        int max = Math.max(Math.max(red, blue), green);
        int m = max - min;
        if (max == min) {
            hue[0] = 0;
        } else {
            if (max == red) {
                hue[0] = ((60 * (green - blue) / (m)) + 360) % 360;
            }
            if (max == green) {
                hue[0] = (60 * ((blue - red) / m)) + 120;
            } else {
                hue[0] = (60 * ((red - green) / m)) + 240;
            }
        }
        if (max == 0) {
            hue[1] = 0;
        } else {
            hue[1] = 1 - (min / max);
        }
        hue[2] = (float) max;
    }


    //calcule l'histogramme du niveau de gris d'une image
    public int[] greyScale(Bitmap im) {
        int[] hist = new int[256];
        for (int i = 0; i < im.getWidth() - 1; i++) {
            for (int j = 0; j < im.getHeight() - 1; j++) {
                int greyLevel = red(im.getPixel(i, j));
                hist[greyLevel]++;
            }
        }
        return hist;
    }

    //calcule l'histogramme du niveau de rouge d'une image
    public int[] redScale(Bitmap im) {
        int[] hist = new int[256];
        for (int i = 0; i < im.getWidth() ; i++) {
            for (int j = 0; j < im.getHeight(); j++) {
                int redLevel = red(im.getPixel(i, j));
                hist[redLevel]++;
            }
        }
        return hist;
    }

    //calcule l'histoggrame du niveau de bleu d'une image
    public int[] blueScale(Bitmap im) {
        int[] hist = new int[256];
        for (int i = 0; i < im.getWidth() ; i++) {
            for (int j = 0; j < im.getHeight() ; j++) {
                int blueLevel = blue(im.getPixel(i, j));
                hist[blueLevel]++;
            }
        }
        return hist;
    }

    //calcule l'histogramme du niveau de vert d'une image
    public int[] greenScale(Bitmap im) {
        int[] hist = new int[256];
        for (int i = 0; i < im.getWidth() ; i++) {
            for (int j = 0; j < im.getHeight(); j++) {
                int greenLevel = green(im.getPixel(i, j));
                hist[greenLevel]++;
            }
        }
        return hist;
    }


    public int minArray(int[] array) {
        int i = 0;
        while (array[i] == 0) {
            i++;
        }
        return i;
    }

    public int maxArray(int[] array) {
        int i = (array.length) - 1;
        while (array[i] == 0) {
            i--;
        }
        return i;
    }

    //augmentation du contraste d'une image couleur par extension dynamique
    public void toExtDyn(Bitmap im) {
        int[] hist1 = redScale(im);
        int[] hist2 = blueScale(im);
        int[] hist3 = greenScale(im);
        int width = im.getWidth();
        int height = im.getHeight();
        int maxr = maxArray(hist1);
        int minr = minArray(hist1);
        int maxg = maxArray(hist2);
        int ming = minArray(hist2);
        int maxb = maxArray(hist3);
        int minb = minArray(hist3);
        int[] LUTr = new int[256];
        int[] LUTg = new int[256];
        int[] LUTb = new int[256];
        for (int n = 0; n < 256; n++) {
            LUTr[n] = (255 * (n - minr)) / (maxr - minr);
            LUTg[n] = (255 * (n - ming)) / (maxg - ming);
            LUTb[n] = (255 * (n - minb)) / (maxb - minb);
        }
        int[] pixels = new int[width * height];
        im.getPixels(pixels, 0, width, 0, 0, width, height);
        int i;
        for (i = 0; i < pixels.length; i++) {
            pixels[i] = rgb(LUTr[red(pixels[i])], LUTg[green(pixels[i])], LUTb[blue(pixels[i])]);
        }
        im.setPixels(pixels, 0, width, 0, 0, width, height);
    }

    //diminution du contraste d'une image grise
    public void diminutionContrasteGris(Bitmap im) {
        int[] hist = greyScale(im);
        int width = im.getWidth();
        int height = im.getHeight();
        int[] LUT = new int[256];
        int[] newLUT = new int[256];
        int size=0;
        for(int j=0;j<256;j++){
            if(hist[j]>50){
                LUT[size]=hist[j];
                size++;
            }
        }
        for(int z=0;z<size;z++){
            newLUT[z]=LUT[z];
        }
        int[] pixels = new int[width * height];
        im.getPixels(pixels, 0, width, 0, 0, width, height);
        int i;
        for (i = 0; i < pixels.length; i++) {
            int grey=red(pixels[i]);
            int newGrey=newLUT[grey];
            pixels[i]=rgb(newGrey,newGrey,newGrey);

        }
        im.setPixels(pixels, 0, width, 0, 0, width, height);
    }



    //augmentation du contraste d'une image couleur par égalisation d'histogramme
    public void toEgalHisto(Bitmap im) {
        int[] histR = redScale(im);
        int[] histG = greenScale(im);
        int[] histB = blueScale(im);
        int width = im.getWidth();
        int height = im.getHeight();
        int i;
        int[] histCR = new int[256];
        int[] histCG = new int[256];
        int[] histCB = new int[256];
        int cmptR = 0;
        int cmptG = 0;
        int cmptB = 0;
        for (i = 0; i < 256; i++) {
            cmptR = cmptR + histR[i];
            histCR[i] = cmptR;
            cmptG = cmptG + histG[i];
            histCG[i] = cmptG;
            cmptB = cmptB + histB[i];
            histCB[i] = cmptB;
        }
        int j;
        for (j = 0; j <width; j++) {
            for(int x=0;x<height;x++) {
                double newRed = ((histCR[red(im.getPixel(j,x))]));
                double newGreen = ((histCG[green(im.getPixel(j,x))]));
                double newBlue = ((histCB[blue(im.getPixel(j,x))])) ;
                im.setPixel(j,x,rgb((int)((newRed*255) /cmptR),(int)((newGreen*255) / cmptG),(int)((newBlue*255))/ cmptB));
            }
        }
    }
    private void convolution(Bitmap bmp, int size) {
        int width = bmp.getWidth()-1;
        int height = bmp.getHeight();
        int[] out = new int[width * height];
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        int[] gauss = new int[]{1,2,3,2,1,2,6,8,6,2,3,8,10,8,3,2,6,8,6,2,1,2,3,2,1};
        int[] laPlace = new int[]{1,1,1,1,-8,1,1,1,1};
        for (int i = 0; i < pixels.length; i++) {
            int newRed = 0;
            int newGreen = 0;
            int newBlue = 0;
            int a=0;
            for (int c = -(size - 1) / 2; c <= (size - 1) / 2; c++) {
                for (int d = -(size - 1) / 2; d <= (size - 1) / 2; d++) {
                    if(i + c * width + d>=0 && i + c* width + d <pixels.length ) {
                        newRed += red(pixels[i + c * width + d])/9;
                        newGreen += green(pixels[i + c * width + d])/9;
                        newBlue +=blue(pixels[i + c * width + d])/9;
                        a++;
                    }
                    else{
                        newRed+=255/(size*size);
                    }
                }
            }

            out[i] = rgb(newRed , newGreen , newBlue);
        }
        bmp.setPixels(out, 0, width, 0, 0, width, height);
    }
*/
}
