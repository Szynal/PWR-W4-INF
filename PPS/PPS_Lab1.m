% -------------------------------------------------------------
%           WPROWADZENIE DO PAKIETU MATLAB/OCTAVE
% %  AUTOR:   Pawe³ Szynal    226026
%-------------------------------------------------------------

% -------------------------------------------------------------
% t= 0: 0,01:0,5  Odpowiednie kroki generacji osi czasu
%   Zaczynamy od 0_0,01_0,02______0,5   
%
%  T jest odwrotnoscia okresu .. odlegloscia… pomiedzy probkami 
%-------------------------------------------------------------


 % -------------------------------------------------------------
%   wygenerowaæ sygna³ sinusoidalny o czêst. probkowania fs=1000 Hz
%   i czêst. sygna³u wybranej z przedzia³u f?(10,200)) Hz.
%   Czas trwania sygna³u nale¿y wybraæ z przedzia³u T?(75,225)T?(75,225) ms.
%   narysowaæ wykres sygna³u z prawid³ow¹ osi¹ czasu
%   wykonaæ eksperyment dla ró¿nych czêstotliwoœci sygna³u - jakie zmiany obserwujesz? dlaczego?
%   wypróbowaæ dzia³anie funkcji plot() i stem(); jaka jest ró¿nica miêdzy obiema funkcjami?
%-------------------------------------------------------------

clear all; % Wyczysc wszystkie zmienne

N= 1000; % probki
fp = 1000; % czestotliwosc probkowania ..Ilosc sparawdzania sygnalu
t = 0:1/fp:(N-1)/fp; % generowanie osi czasu / funkcji 

% ----------------------------------------------
% parametr sinusoidalny x=A*sin(2*pi*f*t+fi)
% ----------------------------------------------

A = 3;   % AMPLITUDA 
f0 = 10; %  czestotliwos. sygnalu wybranej z przedzialu f?(10,200)f?(10,200)
fi = 0.2; % faza w radianach

x=A*sin(2*pi*f0*t+fi); 

figure (1);   % WYKRES NR 1
stem(x);
xlabel ('czas');
ylabel ('sygnal');
title ('Wykres Sinusoidalny');

% ----------------------------------------------
% ODPOWIEDZ: funkcjia plot(), stem() i subplot(). ; 
%
%   sublot: uzywamy jesli chcemy wykreslic kilka wykresów w jednym miejscu
%            np. subplot(4,1,4)          
%            4 - ( num_of_rows)
%            1 - ( num_of _columns ) 
%            4 - ktory obraz   
%
%    stem: uzywamy gdy rysujemy dyskretny wykres (kolokwialnie ujmujac .. nie laczymy punktow)
%     plot: w przeciwienstwie do stem laczy punkty, dzieki czemu uzyskujemy bardziej czytelny wykres
% ----------------------------------------------

figure (2);     % WYKRES NR 2
plot (t, x);    

xlabel ('czas [s]');
ylabel ('sygnal');
title ('wykres sinusoidalny');


% -------------------------------------------------------------------------------------------------------
%   ZAD 2 i 3 ODPOWIEDZI PRZY PARAMATRACH
%   Wygenerowac sygnal sinusoidalny o czest. probkowania wybranej z przedzia³u fs?(700,1500)Hz 
%   i czest. sygna³u wybranej z przedzia³u f?(10,200)Hz. 
%   Dlugosc sygnalu (ilosc probek) nalezy wybrac z przedzia³u N?(512,2000)
%    
%   narysowac wykres sygnalu z prawid³owa osi¹ czasu !
%   wykonac eksperyment dla kilku d³ugoœci sygna³u - co siê zmienia na wykresie?
% -------------------------------------------------------------------------------------------------------

fp2 = 1000;
fs2 = 10;
N2 = 512; % Dlugosc sygnalu (ilosc probek)  N?(512,2000) ODP. Wp³ywa na czas trwania sygnalu :) 
t2 = 0:1/fp2:(N2-1)/fp2;  

% PARAMETRY DO TESTOWANIA 
A2 = 3; % AMPLITUDA    ODP. Wpluwa na maksymalne wychylenie z polozenia rownowagi
fi2 = 100000; % FAZA   OPD - przsuniecie sygnalu 

x2 = A2 * sin(2*pi*fs2*t2 + fi2);

figure (3);     % WYKRES NR 3
plot (t2, x2);    
xlabel ('czas [s]');
ylabel ('sygnal');
title ('ZAD 2');



% ----------------------------------------------------------------------------------------------------------------
%                       ZAD 4
% Wygenerowac i narysowac sygnal bedacy suma trzech sinusow o roznych czestotliwosciach 
% i amplitudach (mozna wygenerowaæ trzy rozne sygnaly o zmiennych np. x1, x2, x3 i potem dodaæ te wektory
% -----------------------------------------------------------------------------------------------------------------

fp1_zad4 = 700;
fp2_zad4 = 200;
fp3_zad4 = 500;

N1_zad4 = 512;
N2_zad4 = 800;
N3_zad4 = 900;

t_zad4 = 0:1/fp1_zad4:(N1_zad4 -1)/fp1_zad4;

x1_zad4= A*sin(2*pi*5* t_zad4 +0.1);
x2_zad4= A*sin(2*pi*2* t_zad4 +0.1);
x3_zad4= A*sin(2*pi*10* t_zad4 +  0.1);

suma = x1_zad4 +x2_zad4 + x3_zad4; % ABY dodac synaly musza miec zgodna faze

figure(4);
plot (t_zad4,suma);
xlabel ('czas [s]');
ylabel ('sygnal');
title ('ZAD 4');

% -----------------------------------------------------------------------------------------------------------------
%                     ZAD5
%   Wygenerowac sygnal prostokatny o parametrach 
%   j.w. - przeprowadzic eksperymenty dla roznego stopnia wypelnienia (ang. duty)
% -----------------------------------------------------------------------------------------------------------------



t_prost= 0:0.01:1;

y_prost = sin(2 *(5*pi)/6 * t_prost);

for k=1:length(y_prost),
    if (y_prost(k) >=0) y_prost(k)=1; 
    else y_prost(k)=-1;
    end
end

figure (5);
plot(t_prost,y_prost);
xlabel ('czas [s]');
ylabel ('sygnal');
title ('ZAD5');
hold on 

% -----------------------------------------------------------------------------------------------------------------
%                     ZAD6
%   Wygenerowac sygna³ losowy (Gausowski) - funkcja randn() i drugi sygnal losowy funkcja rand().
%   jaka jest sk³adnia funkcji rand() - "help rand" lub "doc rand"
%   narysowac plot obu sygna³ow na jednym wykresie - jaka jest roznica miedzy obiema funkcjami?
% -----------------------------------------------------------------------------------------------------------------

t6 = 0:200;
smax =  1.5;
smin = -0.8;
s6_1 = smin + (smax-smin)*rand(1,length(t6));
s6_2 = smin + (smax-smin)*rand(1,length(t6));

figure (6);
subplot(2,1,1);
plot(t6, s6_1)
grid
xlabel ('czas [s]');
ylabel ('sygnal');
title ('ZAD6');

subplot(2,1,2);
plot(t6, s6_2)
grid
xlabel ('czas [s]');
ylabel ('sygnal');


% -----------------------------------------------------------------------------------------------
%                     ZAD7
%   wygenerowac sygnal losowy o wartoœciach ?(?0.25,0.5) - funkcja rand().
%   Tzn. ¿adna wygenerowana wartoœæ nie mo¿e byæ mniejsza ni¿ -0.25 ani wiêksza ni¿ 0.5.
% -----------------------------------------------------------------------------------------------

t7 = 0:0.1:10;
s7max =  0.5;
s7min = -0.25;
s7 = s7min + (s7max-s7min)*rand(1,length(t7));
figure(7);
plot(t7,s7);
title('ZAD7');
grid


% -----------------------------------------------------------
%                     ZAD8
%   wygenerowac impuls jednostkowy o d³ugoœci 200 probek; 
%   narysowac wykres sygna³u zaznaczaj¹c probki kropkami
% -----------------------------------------------------------

x8 = -100 : 1 : 100;
y8 = double(x8 == 0);
figure(8);
stem(x8, y8);
title('ZAD8');
grid       
       
       
       
       
% ----------------------------------------------------------------------
%                     ZAD9
%   wygenerowaæ impuls jednostkowy o czasie trwania(tj. d³ugoœci) 0.137 s
%   przy czêstotliwoœci próbkowania fs=1750
% ----------------------------------------------------------------------


t9 = 0 : 1/1750 : 0.137;
y9 = sin(2*pi*10*t9);

for k=1:length(y9),
    if( k == length(y9)/2) y9(k)=1;
    else y9(k)=0;
    end
end 

figure(9);
stem(t9, y9);
title('ZAD9');
grid       


       
% ------------------------------------------------------------------------------------------------
%                     ZAD10
%   wygenerowaæ sygna³ linowego narostu wg wzoru y=2x?3 w zakresie x?(?3:5) narysowaæ wykres
% ------------------------------------------------------------------------------------------------

x10 = -3:5;
y10 = 2 * x10 -3 ;

figure(10);
plot(x10,y10);
title('ZAD10');


% ------------------------------------------------------------------------------------------------------------
%                     ZAD11
%   wygenerowaæ sygna³ nieliniowy wg wzoru y=x^2+2x?1 w zakresie zmiennoœci x?(?4:2)
% ------------------------------------------------------------------------------------------------------------

x11 = -4:0.1:2;
y11 = x11.^2 + 2 * x11 - 1;

figure(11);
plot(x11,y11);
title('ZAD11');






