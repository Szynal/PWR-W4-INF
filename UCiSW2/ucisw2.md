#UCiSW 2


Założeniem projektu było wykonanie prostych organów z wykorzystaniem układu Spartan3E w celach dydaktycznych.  Jako grupa zdecydowaliśmy się na próbę implementacji jedno-gamowego piania obsługiwanego przez klawiaturę podłączaną do układu, z możliwością zapisywania i odtwarzania danego fragmentu na pamięci RAM urządzenia. Do odtwarzania naszej muzyki wykorzystaliśmy przetwornik DAC i podłączony do niego głośnik.\\\\Prace nad projektem podzieliliśmy na pomniejsze etapy, tj.:


* Generacja sygnału 1,5 kHz,
* Wyprowadzenie sygnału z modułu Podzielnika Częstotliwości,
* Obsłużenie przetwornika DAC,
* Obsługa klawiatury jako wejścia,
* Implementacja pamięci RAM,
* Odtwarzanie z pamięci,
* Nagrywanie, odgrywanie.


###Sygnał piłokształtny

Wykorzystałem: [wiki](https://en.wikipedia.org/wiki/sawtooth_wave)


```vhd
entity Pila is
port (Clk : in std_logic; 
    Sygnal_wyjsciowy : out std_logic_vector(11 downto 0);
    Clr :in std_logic);
end Pila;

architecture Behavioral of Pila is
	signal iterator : integer := 0;

begin

process(Clk,Clr)
begin
	if(Clr = '1') then
	    iterator <= 0;
	elsif(rising_edge(Clk)) then
	    if(iterator = 4095) then
	        iterator <= 0;
	    else
	        iterator <= iterator + 1;
	    end if;
	end if;
end process;

Sygnal_wyjsciowy <= conv_std_logic_vector(iterator,12);

end Behavioral;
```
