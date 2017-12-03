% -------------------------------------------------------------
%       PROGRAMOWANIE W PAKIECIE MATLAB/OCTAVE
%   AUTOR:   Pawe³ Szynal    226026
%-------------------------------------------------------------

%-------------------------------------------------------------
% - wygenerowaæ sygna³ sinusoidalny od d³ugoœci 1s, czêst. 100Hz tak ¿eby by³o 100tyœ punktów
%  (czyli czêœtotliwoœæ próbkowania 100kHz) ten sygna³ bêdzie naœladowa³ sygna³ pseudoci¹g³y
%-------------------------------------------------------------

clear all; % Wyczysc wszystkie zmienne;

fp = 100000; % czestotliwosc probkowania ..Ilosc sparawdzania sygnalu
t = 0:1/fp:1; % generowanie osi czasu / funkcji 

% ----------------------------------------------
% parametr sinusoidalny x=A*sin(2*pi*f*t+fi)
% ----------------------------------------------

A = 1;   % AMPLITUDA 
f0 = 100 ; % [HZ] 
fi = 0 ; % faza w radianach

x=A*sin(2*pi*f0*t+fi); 

figure (1);   % WYKRES NR 1
subplot(3,1,1)
plot(t,x);
xlabel ('czas');
ylabel ('A');
title ('Wykres Sinusoidalny');


%-------------------------------------------------------------
% - stworzyæ nowy sygna³ przez decymacje pierwszego 
% (np. wybieramy co 10 probkê czyli zmniejszamy czêt próbkowania 10 razy)
%-------------------------------------------------------------

r =1000;% stopnien decymacji
DecimationOfx = decimate(x,r);
decimation_t = 0:(1/fp)*r:1; % generowanie osi czasu / funkcji 

subplot(3,1,2)
plot(decimation_t,DecimationOfx);
xlabel ('czas');
ylabel ('A');
title ('Wykres Sinusoidalny - decymacja');


%-------------------------------------------------------------
% - wygenerowaæ sygna³ linii prostej y(n) : (-1,1)
%-------------------------------------------------------------

n = 1;
t_y = 0:0.001:1;
y = n;
subplot(3,1,3)
plot(t_y,y);
xlabel ('czas');
ylabel ('A');
title ('Sygnal lini prostej');





