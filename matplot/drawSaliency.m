%t = 0:900; A = 1000; a = 0.005; b = 0.005;
%z1 = A*exp(-a*t);
%z2 = sin(b*t);
%[haxes,hline1,hline2] = plotyy(t,z1,t,z2,'semilogy','plot');
k_num = [4,5,6,7,8,9,10,11,12,13];
s_time = [0.51,0.29,0.41,0.41,0.41,0.99,0.41,0.40,0.38,0.41];
r_time = [0.51,0.29,0.41,0.41,0.41,0.99,0.41,0.40,0.38,0.41];
lc_num = [6.23,8.34,9.23,10.21,9.16,10.43,14.23,13.65,12.37,14.43];
sa_num = [8.33,7.44,6.23,3.28,10.16,10.43,3.26,15.65,11.27,14.48];
random_num = [3.12,6.34,4.21,2.45,3.43,8.22,3.21,4.23,1.11,3.33];
plot(k_num,operate_num);
hold on 
plot(k_num,random_num);
hold on 
plot(k_num,sa_num);