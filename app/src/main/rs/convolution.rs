#pragma version (1)
#pragma rs java_package_name (com.example.image)
#pragma rs_fp_relaxed

int width;
int height;
const uchar4 *pixels;
int sizeConvolution;
int* filtre;

uchar4 __attribute__((kernel)) toConvolution(uchar4 in,uint32_t x,uint32_t y) {
    int a=0;
    int l =(sizeConvolution-1)/2;
    in.r=0;
    in.b=0;
    in.g=0;
    in.a=0;
    float4 color=rsUnpackColor8888(in);
        for (int i=-l;i<=l;i++){
            for(int j=-l;j<=l;j++){
            if(x+((y+j)*width)+i>=0 && x+((y+j)*width)+i < width*height){
                color+=filtre[a]*rsUnpackColor8888(pixels[x+((y+j)*width)+i])/98;
                a++;
            }
        }
    }

         return rsPackColorTo8888(color);
 }





