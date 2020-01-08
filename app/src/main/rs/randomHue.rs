#pragma version (1)
#pragma rs java_package_name (com.example.image)
#pragma rs_fp_relaxed

double newHue;
uchar4 RS_KERNEL toNewHue (uchar4 in) {
int mymax = (int)max(in.r,max(in.g,in.b));
int mymin =(int) min(in.r,min(in.g,in.b));
float s=0;
if(mymax==0){
s=0;
}
else{
s=1-(mymin/mymax);
}

float tig = (newHue/60);
int ti = fmod(tig,6);
uchar f = (newHue/60)-ti;
uchar l = (mymax)*(1-s);
uchar m = (mymax)*(1-(f*s));
uchar n = (mymax)*(1-((1-f)*s));
if (ti==0)
{return (uchar4){mymax,n,l,in.a};}
if (ti==1)
{return (uchar4){m,mymax,l,in.a};}
if (ti==2)
{return (uchar4){l,mymax,n,in.a};}
if (ti==3)
{return (uchar4){l,m,mymax,in.a};}
if (ti==4)
{return (uchar4){n,l,mymax,in.a};}
else
{return (uchar4){mymax,l,m,in.a};}
}