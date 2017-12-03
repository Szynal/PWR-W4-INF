% -------------------------------------------------------------
%           WPROWADZENIE DO PAKIETU MATLAB/OCTAVE
%  ZADANIE: Generowanie sygna�u - r�ne
%  AUTOR:   Pawe� Szynal    226026
%-------------------------------------------------------------


% ===== P ======


%---------------------------------------------------------------------------------------------------
%  napisa� skrypt, kt�ry wygeneruje i wyplotuje sygna� testowy o nast�puj�cych parametrach:

% cz�stotliwo�� pr�bkowania 1819 Hz
% czas trwania sygna�u 0.23 s
% sygna� sinusoidalny o cz�stotliwo�ci 11.13 Hz
% amplituda 2
% faza pocz�tkowa 90 stopni
%---------------------------------------------------------------------------------------------------

fs = 1819; % cz�stotliwo�� pr�bkowania 1819 Hz
T = 0.23 ; % czas trwania sygna�u 0.23 s
f = 11.13; %sygna� sinusoidalny o cz�stotliwo�ci 11.13 Hz
A = 2; % amplituda 2
fi = 90; %   faza pocz�tkowa 90 stopni

x = 0 : 1/fs : T;
y = A * sin(2*pi * f * x * fi);  %  x=A*sin(2*pi*f*t+fi)

subplot(2,1,1);
plot(x,y);
xlabel('[s]');
ylabel('sygnal');
title('===== P ======');



% ===== N ======


%---------------------------------------------------------------------------------------------------
%  napisa� skrypt, kt�ry wygeneruje i wyplotuje sygna� testowy o nast�puj�cych parametrach:
%
%  Prosze napisac skrypt, ktory wygeneruje i wyplotuje sygna� testowy - funkcja sinus z dodanym szumem 
% - o nast�puj�cych parametrach:
% czestotliwosc probkowania 717 Hz
% dugosc sygna�u 512 probek
% czestotliwo�ci sinusoidy ???? BRAK 
% amplituda sinusoidy 3
% moc szumu dodanego do sinusoidy wynosi 0.2 (czyli 0.2*szum)
%---------------------------------------------------------------------------------------------------

fs_N = 717; % czestotliwosc probkowania 717 Hz
N = 512; %dlugosc sygnalu 512 probek

f_N = 11.13 + 11.13* rand(); %BRAK INFORMACJI ??? B�AD NA STRONIE 
A_N = 3; % amplituda 3
fi_N = 0.2; %   moc szumu dodanego do sinusoidy wynosi 0.2 (czyli 0.2*szum)

x_N = 0 : 1/fs_N : (N-1)/fs_N;


y_N = A_N * sin(2*pi * f_N * x_N * fi_N);  %  x=A*sin(2*pi*f*t+fi)

subplot(2,1,2);
plot(x_N,y_N);
xlabel('[s]');
ylabel('sygnal');
title('===== N ======');
grid;


