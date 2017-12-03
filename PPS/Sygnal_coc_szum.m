% -------------------------------------------------------------
%           Podstawy Przetwarzania Sygna³ów (laboratorium) - B.Szlachetko
%  ZADANIE: Sygnal cos i szum
%  AUTOR:   Pawe³ Szynal    226026
%-------------------------------------------------------------

% Wygeneruj sygna³ testowy zawieraj¹cy sumê sygna³u kosinusoidalnego o czêstotliwoœci
% ?
% i szumu o rozk³adzie Gaussa. Czas trwania sygna³u wynosi
% ?/2
% Nalezy narysowaC sygnal wynikowy oraz policzyc SNR (ang Signal to Noise Ratio). 
% PodpowiedŸ moc sygna³u i moc szumu mo¿na policzyæ pod warunkiem, ¿e oba sygna³u bêd¹ zapamiêtane w innych zmiennych. 
% Wtedy wystarczy policzyæ iloczyn skalarny tych zmiennych.

% Tworzenie sygnalu sin

clear all; % Wyczysc wszystkie zmienne


A = 2;
% ------------------------------------------------------------------------------
%   Rozk³ad normalny  (w literaturze francuskiej zwany rozk³adem Laplace'a-Gaussa) 
% ------------------------------------------------------------------------------
x_szum = [-10:.1:10];
norm = normpdf(x_szum,0,1);

subplot(3,1,1);
plot(x_szum,norm);
xlabel('x');
ylabel('y');
title('Rozk³ad normalny ');
  

% ------------------------------------------------------------------------------
%  sygnal kosinusoidalny o czêstotliwoœci pi
% ------------------------------------------------------------------------------

A_s = 1;     % NP. '1' MOZNA ZMIENIC 
f_s = pi;    % czêstotliwoœc ?
T = pi/2;    % [s] czas trwania 
t_s = 0 : .0001: T;    % os czasu

c = A_s * cos(2*pi* f_s * t_s);

subplot(3,1,2);
plot(t_s,c);
xlabel('t[s]');
ylabel('cos ');
title('Sygna³ cos');

% ------------------------------------------------------------------------------
%  Suma sygna³u kosinusoidalnego o czêstotliwoœci ? i szumu o rozk³adzie Gaussa.
% ------------------------------------------------------------------------------

szum = normpdf(t_s,0,1);
suma = c + szum ; % UWAGA ZMIENIAM NA TAKI SAM CZAS TRWANIA 

subplot(3,1,3);
plot(t_s,suma);
xlabel('t[s]');
ylabel('suma ');
title('Suma sygna³u kosinusoidalnego i szumu o rozk³adzie Gaussa. ');
% ------------------------------------------------------------------------------
%  OBLICZANIE  SNR (ang Signal to Noise Ratio )
% ------------------------------------------------------------------------------
% snr (x, y) zwraca stosunek sygna³u do szumu (SNR) w decybelach sygna³u, 
moc_sygnalu_kosinusoidalnego = snr(c) 
moc_szumu = snr(szum)
stosunek_mocy = snr(c,szum)

% ------------------------------------------------------------------------------


