% -------------------------------------------------------------
%           Podstawy Przetwarzania Sygna³ów (laboratorium) - B.Szlachetko
%  ZADANIE: Funkcja nieliniowa
%  AUTOR:   Pawe³ Szynal    226026
%-------------------------------------------------------------



%-------------------------------------------------------------
%   Prosze napisac skrypt, ktory wyswietla przebieg funkcji nieliniowej:
%       y=2x^3?x^2/2+x?2
%       gdzie x?<?3,3>. 
%   Ilosc punktow wewnatrz tego przedzia³u (czyli alternatywnie precyzja) jest do wyboru 
%       - mo¿e to byæ np. skok 0.01; moze to byæ 1000 punktów.

%PodpowiedŸ:
%   1) do wygenerowania zmiennej x mo¿na u¿yæ generator wektorów (b:step:e) lub linspace()
%   2) nale¿y wyœwietliæ wykres funkcj¹ plot i opisaæ osie Ox i OY


x = -3 : 0.01 : 3;   % x?<?3,3>. precyzja 0.01
y = 2*x.^3 - (x.^2)/2 + x - 2; % funkcjia nieliniowa

figure(1);
plot(x,y);
title('ZADANIE: Funkcja nieliniowa')
xlabel('[x]');
ylabel('[y]');
grid;