--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   09:39:49 05/27/2019
-- Design Name:   
-- Module Name:   C:/Users/lab/Documents/ucisw2-organki/SawToothGenerator_TB.vhd
-- Project Name:  ucisw2-organki
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: SawToothGenerator
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
--
-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY SawToothGenerator_TB IS
END SawToothGenerator_TB;
 
ARCHITECTURE behavior OF SawToothGenerator_TB IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT SawToothGenerator
    PORT(
         Clk : IN  std_logic;
         Clr : IN  std_logic;
         FreqIN : IN  std_logic_vector(3 downto 0);
         StartOUT : OUT  std_logic;
         CmdOUT : OUT  std_logic_vector(3 downto 0);
         AddrOUT : OUT  std_logic_vector(3 downto 0);
         SawtoothOUT : OUT  std_logic_vector(11 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal Clk : std_logic := '0';
   signal Clr : std_logic := '0';
   signal FreqIN : std_logic_vector(3 downto 0) := (others => '0');

 	--Outputs
   signal StartOUT : std_logic;
   signal CmdOUT : std_logic_vector(3 downto 0);
   signal AddrOUT : std_logic_vector(3 downto 0);
   signal SawtoothOUT : std_logic_vector(11 downto 0);

   -- Clock period definitions
   constant Clk_period : time := 20ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: SawToothGenerator PORT MAP (
          Clk => Clk,
          Clr => Clr,
          FreqIN => FreqIN,
          StartOUT => StartOUT,
          CmdOUT => CmdOUT,
          AddrOUT => AddrOUT,
          SawtoothOUT => SawtoothOUT
        );

   -- Clock process definitions
   Clk_process :process
   begin
   
      Clk <= '0';
      wait for Clk_period/2;
      Clk <= '1';
      wait for Clk_period/2;
      
      Clk <= '0';
      wait for Clk_period/2;
      Clk <= '1';
      wait for Clk_period/2;
   end process;
   Clr <='0';

END;
