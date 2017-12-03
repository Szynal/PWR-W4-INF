% -------------------------------------------------------------
%           Podstawy Przetwarzania Sygna��w (laboratorium) - B.Szlachetko
%  ZADANIE: Sygnal cos i szum
%  AUTOR:   Pawe� Szynal    226026
%-------------------------------------------------------------

% Wygeneruj sygna� testowy zawieraj�cy sum� sygna�u kosinusoidalnego o cz�stotliwo�ci
% ?
% i szumu o rozk�adzie Gaussa. Czas trwania sygna�u wynosi
% ?/2
% Nalezy narysowaC sygnal wynikowy oraz policzyc SNR (ang Signal to Noise Ratio). 
% Podpowied� moc sygna�u i moc szumu mo�na policzy� pod warunkiem, �e oba sygna�u b�d� zapami�tane w innych zmiennych. 
% Wtedy wystarczy policzy� iloczyn skalarny tych zmiennych.

% Tworzenie sygnalu sin

clear all; % Wyczysc wszystkie zmienne


A = 2;
% ------------------------------------------------------------------------------
%   Rozk�ad normalny  (w literaturze francuskiej zwany rozk�adem Laplace'a-Gaussa) 
% ------------------------------------------------------------------------------
x_szum = [-10:.1:10];
norm = normpdf(x_szum,0,1);

subplot(3,1,1);
plot(x_szum,norm);
xlabel('x');
ylabel('y');
title('Rozk�ad normalny ');
  

% ------------------------------------------------------------------------------
%  sygnal kosinusoidalny o cz�stotliwo�ci pi
% ------------------------------------------------------------------------------

A_s = 1;     % NP. '1' MOZNA ZMIENIC 
f_s = pi;    % cz�stotliwo�c ?
T = pi/2;    % [s] czas trwania 
t_s = 0 : .0001: T;    % os czasu

c = A_s * cos(2*pi* f_s * t_s);

subplot(3,1,2);
plot(t_s,c);
xlabel('t[s]');
ylabel('cos ');
title('Sygna� cos');

% ------------------------------------------------------------------------------
%  Suma sygna�u kosinusoidalnego o cz�stotliwo�ci ? i szumu o rozk�adzie Gaussa.
% ------------------------------------------------------------------------------

szum = normpdf(t_s,0,1);
suma = c + szum ; % UWAGA ZMIENIAM NA TAKI SAM CZAS TRWANIA 

subplot(3,1,3);
plot(t_s,suma);
xlabel('t[s]');
ylabel('suma ');
title('Suma sygna�u kosinusoidalnego i szumu o rozk�adzie Gaussa. ');
% ------------------------------------------------------------------------------
%  OBLICZANIE  SNR (ang Signal to Noise Ratio )
% ------------------------------------------------------------------------------
% snr (x, y) zwraca stosunek sygna�u do szumu (SNR) w decybelach sygna�u, 
moc_sygnalu_kosinusoidalnego = snr(c) 
moc_szumu = snr(szum)
stosunek_mocy = snr(c,szum)

% ------------------------------------------------------------------------------


