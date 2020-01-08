#pragma version (1)
#pragma rs java_package_name (com.example.image)
#pragma rs_fp_relaxed
float choosedColor;
uchar4 RS_KERNEL toSaturate(uchar4 in) {
uchar mymax = max(in.r,max(in.g,in.b));
uchar mymin = min(in.r,min(in.g,in.b));
float t=0;
float s=0;
if (mymax==mymin){
t=0;
}
if (mymax==in.r){
t=(60*(in.g-in.b)/(mymax-mymin));
}
if(mymax==in.g){
t=(60*(in.b-in.r)/(mymax-mymin))+120;
}
if(mymax==in.b){
t=(60*(in.r-in.g)/(mymax-mymin))+240;
}
if(mymax==0){
s=0;
}
else{
s=1-(mymax-mymin);
}

if(t-choosedColor>=-25 && t-choosedColor<=25)
{return in;}
else{
const uchar gray = (30 * in.r+ 59 * in.g + 11 * in.b) / 100;
return (uchar4) {gray, gray, gray,in.a };
}
/*
uchar ti = (uchar) ((newin.r)/60)%6;
uchar f = (newin.r/60)-ti;
uchar l = (newin.b)*(1-newin.g);
uchar m = (newin.b)*(1-(f*newin.g));
uchar n = (newin.b)*(1-((1-f)*newin.g));
if (ti==0)
{return (uchar4){newin.b,n,l,newin.a};}
if (ti==1)
{return (uchar4){m,newin.b,l,newin.a};}
if (ti==2)
{return (uchar4){l,newin.b,n,newin.a};}
if (ti==3)
{return (uchar4){l,m,newin.b,newin.a};}
if (ti==4)
{return (uchar4){n,l,newin.b,newin.a};}
else
{return (uchar4){newin.b,l,m,newin.a};}
*/
}
