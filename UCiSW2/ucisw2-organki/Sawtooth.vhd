----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    08:36:24 03/18/2019 
-- Design Name: 
-- Module Name:    Sawtooth - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.std_logic_arith.all;
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity SawToothDivider is
    Port ( Clk          : in  STD_LOGIC;
           Clr          : in  STD_LOGIC;
           FreqIN       : in  STD_LOGIC_VECTOR (3 downto 0);
			  
           StartOUT 		: out  STD_LOGIC;
			  CmdOUT 		: out  STD_LOGIC_VECTOR (3 downto 0);
           AddrOUT 		: out  STD_LOGIC_VECTOR (3 downto 0);
			  
			  SawtoothOUT 	: out  STD_LOGIC_VECTOR (11 downto 0));
end SawToothDivider;

architecture Behavioral of SawToothDivider is
	signal iFreqDiv      : integer   := 1;
	signal tmpFreqDiv    : STD_LOGIC := '0';
	
	-- Wersja binarna, w normalu nie potrafi³em tego sobie ³adnie przekazywaæ
   signal Freq          : integer;
	
	-- Zmienne
	signal ClkSawTooth 	: STD_LOGIC;
	signal iSawGen 		: integer := 0;
	signal Cmd 				: integer := 3;
	signal Addr 			: integer := 2;
   signal stCnt         : integer := 0;
begin

SetFreq: process(FreqIN, Freq)
begin
-- Otrzymane obrobione
	if(FreqIN = "0001") then
		Freq <= 92;
	elsif(FreqIN = "0010") then
		Freq <= 97;
   elsif(FreqIN = "0011") then
		Freq <= 103;
   elsif(FreqIN = "0100") then
		Freq <= 109;
   elsif(FreqIN = "0101") then
		Freq <= 116;
   elsif(FreqIN = "0110") then
		Freq <= 123;
   elsif(FreqIN = "0111") then
		Freq <= 130;
   elsif(FreqIN = "1000") then
		Freq <= 137;
   elsif(FreqIN = "1001") then
		Freq <= 146;
   elsif(FreqIN = "1010") then
		Freq <= 154;
   elsif(FreqIN = "1011") then
		Freq <= 164;
   elsif(FreqIN = "1100") then
		Freq <= 173;
	else
		Freq <= 0;
	end if;
end process;


-- Nasze podzielenie 50MHz do 1,5KHz
FreqDiv: process( Clk, Clr, tmpFreqDiv )
begin
	if(Clr = '1' ) or (Freq = 0) then 
		iFreqDiv <= 1;
		tmpFreqDiv <= '0';
      StartOUT <= '0';
	elsif rising_edge(Clk)  then
		if (iFreqDiv = Freq) then
			tmpFreqDiv <= NOT tmpFreqDiv;
			iFreqDiv <= 1;
         if (stCnt mod 2 = 0) then
            StartOUT <= '1';
            stCnt <= stCnt + 1;
         else
            stCnt <= stCnt + 1;
         end if;
      else
         iFreqDiv <= iFreqDiv + 1;
         StartOUT <= '0';
		end if;
	end if;
ClkSawTooth <= tmpFreqDiv;
end process;

-- Pila
SawToothGen: process( ClkSawTooth, Clr )
begin
	if(Clr = '1') then
        iSawGen <= 0;
    elsif(rising_edge(ClkSawTooth)) then
        if(iSawGen = 63) then
            iSawGen <= 0;
        else
            iSawGen <= iSawGen + 1;
        end if;
    end if;
end process;

CmdOUT <= conv_std_logic_vector(Cmd,4);
AddrOUT <= conv_std_logic_vector(Addr,4);
SawtoothOUT <= conv_std_logic_vector(iSawGen,6) & "000000";

end Behavioral;
