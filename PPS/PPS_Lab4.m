% -------------------------------------------------------------
%       4. TRANSFORMATA FOURIERA I WIDMO SYGNA£U
%   AUTOR:   Pawe³ Szynal    226026
%-------------------------------------------------------------

%----------------------------------------------------------------------------
% - wygenerowaæ sygna³ z³o¿ony z dwóch lub wiêcej przebiegów okresowych
%----------------------------------------------------------------------------


% x=A*sin(2*pi*f*t+fi)

fp = 1000;  %czestotliwosc probkowania
fs1 = 10;  %czestotliwosc sygnalu #1
fs2 = 50;  %czestotliwosc sygnalu #2
A = 1;      %amplituda sygnalu
N = 500;   %liczba probek
phi = pi/3; %przesuniecie fazowe
t = 0:1/fp:(N-1)/fp; %wektor czasu
% otrzymujemy wektory _x_ i _x1_
x1 = A*sin(2*pi*fs1*t + phi);% sygnal 1 
x2= A*sin(2*pi*fs2*t);%nasz sygnal
% bêd¹ce odwzorowaniem sygna³u widzianego po procesie próbkowania.
s = x1+ x2;
figure (1)
plot(t,s);
%hold on;% Zatrzymanie wykresu na figurze
legend('x1 + x2')
%hold off; % uwolnienie wykresu
xlabel('[s]');
ylabel('[A]');
title('suma 2 sygna³ów sinusoidalnych')


%----------------------------------------------------------------------------
% - dodaæ szum bia³y w okreœlonej proporcji SNR (u¿ywaj¹c np. randn() lub awgn())
%----------------------------------------------------------------------------

SNR = 10; %% -3082 krytycvzne 
xNoise = awgn(s,SNR);

figure  (2)
plot(t,xNoise,'r');
%hold on;% Zatrzymanie wykresu na figurze
legend('awgn(s,SNR)')
xlabel('[s]');
ylabel('[A]');
title('suma sygnalów + szum bia³y');
%hold off; % uwolnienie wykresu

%----------------------------------------------------------------------------
% narysowaæ wykres widma amplitudowego (koniecznie w [dB] 20*log10(abs(X)))
%----------------------------------------------------------------------------

X = fft(xNoise); % szybka transformata fouriera
f = 0:fp/N:fp - fp/N; % generowanie osi czêstotliwosci

figure (3)
subplot(2,1,1)
plot(t,xNoise)
xlabel 'czas [s]'
ylabel '[A]'
legend('awgn(s,10)')
title 'sygnal'
subplot(2,1,2)
plot(f,20*log10(abs(xNoise)),'r') % abs  wylicza wartoœæ bezwzglêdna 
xlabel 'czestotliwosc [Hz]'
ylabel '[dB]'
legend('20*log10(abs(xNoise)')
title 'widmo amplitudowe sygnalu'

%------------------------------------------------------------------------------------------------------------------------------------
% - narysowaæ wykres widma fazowego angle(); widmo fazowe jest okresowe w okresie 2?2? a zatem mo¿na je rozwin¹æ unwrap()
%------------------------------------------------------------------------------------------------------------------------------------

%   angle - Phase angle
%
%    This MATLAB function returns the phase angles, in radians, for each element of
%    complex array Z.
%
%   unwrap - Correct phase angles to produce smoother phase plots
%
%    This MATLAB function corrects the radian phase angles in a vector P by adding
%    multiples of ±2? when absolute jumps between consecutive elements of P are
%    greater than or equal to the default jump tolerance of ? radians.
%------------------------------------------------------------------------------------------------------------------------------------

figure (4)
subplot(2,1,1)
plot(t,xNoise)
xlabel 'czestotliwosc [Hz]'
ylabel '[A]'
legend('awgn(s,10)')
title 'sygnal'
subplot(2,1,2)
plot(f,unwrap(angle(X)),'g') % abs  wylicza wartoœæ bezwzglêdna 
xlabel 'czestotliwosc [Hz]'
ylabel '[faza (stopnie)]'
legend('unwrap(angle(X))')
title 'widmo fazowe sygnalu'

%------------------------------------------------------------------------------------------------------------------------------------
% narysowaæ widmo amplitudowe i fazowe w zakresie (?fs/2:fs/2)
%------------------------------------------------------------------------------------------------------------------------------------


 f2 = -fp/2 : fp/N : fp/2 - fp/N;
 

figure (5)
subplot(2,1,1)
plot(f2,20*log10(abs(xNoise)),'r')
xlabel 'czestotliwosc [Hz]'
ylabel '[dB]'
legend('20*log10(abs(xNoise)')
title 'widmo amplitudowe sygnalu'
subplot(2,1,2)
plot(f2,unwrap(angle(X)),'b') % abs  wylicza wartoœæ bezwzglêdna 
xlabel 'czestotliwosc [Hz]'
ylabel '[faza (stopnie)]'
legend('unwrap(angle(X))')
title 'widmo fazowe sygnalu'

%------------------------------------------------------------------------------------------------------------------------------------
% sprawdziæ kiedy zachodzi przeciek widma i jakie warunki musi spe³niaæ sygna³
% , ¿eby przecieku nie by³o w ogóle (trzeba to sprawdzaæ przy zerowym szumie czyli SNR=??
%------------------------------------------------------------------------------------------------------------------------------------

%ODP:  DLA SNR >= - 3083
figure(7);
w = hann(length(x));
plot(ftt(abs(w)));


