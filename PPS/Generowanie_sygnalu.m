% -------------------------------------------------------------
%           WPROWADZENIE DO PAKIETU MATLAB/OCTAVE
%  ZADANIE: Generowanie sygna³u - ró¿ne
%  AUTOR:   Pawe³ Szynal    226026
%-------------------------------------------------------------


% ===== P ======


%---------------------------------------------------------------------------------------------------
%  napisaæ skrypt, który wygeneruje i wyplotuje sygna³ testowy o nastêpuj¹cych parametrach:

% czêstotliwoœæ próbkowania 1819 Hz
% czas trwania sygna³u 0.23 s
% sygna³ sinusoidalny o czêstotliwoœci 11.13 Hz
% amplituda 2
% faza pocz¹tkowa 90 stopni
%---------------------------------------------------------------------------------------------------

fs = 1819; % czêstotliwoœæ próbkowania 1819 Hz
T = 0.23 ; % czas trwania sygna³u 0.23 s
f = 11.13; %sygna³ sinusoidalny o czêstotliwoœci 11.13 Hz
A = 2; % amplituda 2
fi = 90; %   faza pocz¹tkowa 90 stopni

x = 0 : 1/fs : T;
y = A * sin(2*pi * f * x * fi);  %  x=A*sin(2*pi*f*t+fi)

subplot(2,1,1);
plot(x,y);
xlabel('[s]');
ylabel('sygnal');
title('===== P ======');



% ===== N ======


%---------------------------------------------------------------------------------------------------
%  napisaæ skrypt, który wygeneruje i wyplotuje sygna³ testowy o nastêpuj¹cych parametrach:
%
%  Prosze napisac skrypt, ktory wygeneruje i wyplotuje sygna³ testowy - funkcja sinus z dodanym szumem 
% - o nastêpuj¹cych parametrach:
% czestotliwosc probkowania 717 Hz
% dugosc sygna³u 512 probek
% czestotliwoœci sinusoidy ???? BRAK 
% amplituda sinusoidy 3
% moc szumu dodanego do sinusoidy wynosi 0.2 (czyli 0.2*szum)
%---------------------------------------------------------------------------------------------------

fs_N = 717; % czestotliwosc probkowania 717 Hz
N = 512; %dlugosc sygnalu 512 probek

f_N = 11.13 + 11.13* rand(); %BRAK INFORMACJI ??? B£AD NA STRONIE 
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


