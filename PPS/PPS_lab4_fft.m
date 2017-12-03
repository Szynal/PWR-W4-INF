# Laboratorium 4
# Mariusz W³odarczyk 234960

# Zadanie 1
A = 1;
fp = 128;
N = 256;
fs1 = 10;
fs2 = 12;
t = 0: 1/fp :(N-1)/fp;

x1 = A*sin(2*pi*fs1*t);
x2 = A*sin(2*pi*fs2*t);
x = x1+x2;

figure(1);
subplot(2,1,1);
plot(t,x,'b; x=x1+x2;');
xlabel('Czas [s]');
ylabel('Amplituda');
title('Wykresy funkcji okresowych');

# Zadanie 2
SNR = 0.5;
xSNR = awgn(x, SNR);

subplot(2,1,2);
plot(t,xSNR,'r; xSNR = awgn(x,SNR);');
xlabel('Czas [s]');
ylabel('Amplituda');


# Zadanie 3
figure(2);
X = fft(x);
X_SNR = fft(xSNR);
f = 0: fp/N : fp - fp/N;

subplot(311);
plot(t,xSNR);
legend('xSNR(t)');
xlabel('Czas [s]');
ylabel('Amplituda');
title('Sygnal');

subplot(312);
plot(f,20*log10(abs(X_SNR)));
legend('20*log10(abs(X))');
xlabel('Czestotliwosc [Hz]');
ylabel('[dB]');
title('Widmo amplitudowe sygnalu');

# Zadanie 4
phase = unwrap(angle(X_SNR));

subplot(313);
plot(f, phase);
legend('unwrap(angle(X))');
xlabel('Czestotliwosc [Hz]');
ylabel('faza [stopnie]');
title('Widmo fazowe sygnalu');

# Zadanie 5
figure(3);

f1 = -fp/2 : fp/N : fp/2 - fp/N;

subplot(211);
plot(f1,20*log10(abs(X_SNR)));
legend('20*log10(abs(X))');
xlabel('Czestotliwosc [Hz]');
ylabel('[dB]');
title('Widmo amplitudowe sygnalu');

subplot(212);
plot(f1, phase);
legend('unwrap(angle(X))');
xlabel('Czestotliwosc [Hz]');
ylabel('faza [stopnie]');
title('Widmo fazowe sygnalu');

# Zadanie 6
figure(4);

plot(f, abs(X_SNR/N));
legend('X = ftt(x)');
xlabel('Czestotliwosc [Hz]');
title('Widmo amplitudowe sygnalu bez szumu');

# Zadanie 7/8
figure(5);
xSNR_with_HannWin = xSNR.*hanning(length(xSNR))';
X_SNR_with_HannWin = fft(xSNR_with_HannWin);

subplot(211);
plot(f, abs(X_SNR));
xlabel('[Hz];');
title('Widmo amplitudowe sygnalu przed okienkowanie oknem Hanninga');

subplot(212);
plot(f, abs(X_SNR_with_HannWin));
hold on;
plot(f, abs(fft(hanning(length(xSNR))' )));
xlabel('[Hz];');
legend('Zokienkowany sygnal', 'Widmo okna Hanninga');
title('Widmo amplitudowe sygnalu po okienkowaniu oknem Hanninga');
# Zadanie 9
figure(6);
xZeros = [x zeros(1, 10000)];
X_Zeros = fft(xZeros);

subplot(211);
plot(xZeros);
title('Sygnal wypelniony zerami (zero-padding)');

subplot(212);
plot([0:length(xZeros)-1], abs(X_Zeros));
title('Widmo sygnalu wypelnionego zerami');