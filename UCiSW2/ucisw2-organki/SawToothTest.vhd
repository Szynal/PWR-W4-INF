--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   09:26:21 03/18/2019
-- Design Name:   
-- Module Name:   C:/Users/lab/Desktop/ucisw2-organki/ucisw2-organki/SawtoothTest.vhd
-- Project Name:  ucisw2-organki
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: SawToothDivider
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
 
ENTITY SawtoothTest IS
END SawtoothTest;
 
ARCHITECTURE behavior OF SawtoothTest IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT SawToothDivider
    PORT(
         Clk : IN  std_logic;
         Clr : IN  std_logic;
         StartOUT : OUT  std_logic;
         CmdOUT : OUT  std_logic_vector(3 downto 0);
         AddrOUT : OUT  std_logic_vector(3 downto 0);
         SawtoothOUT : OUT  std_logic_vector(11 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal Clk : std_logic := '0';
   signal Clr : std_logic := '0';

 	--Outputs
   signal StartOUT : std_logic;
   signal CmdOUT : std_logic_vector(3 downto 0);
   signal AddrOUT : std_logic_vector(3 downto 0);
   signal SawtoothOUT : std_logic_vector(11 downto 0);

   --Okres 50MHz
	constant Clk_period : time := 20ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: SawToothDivider PORT MAP (
          Clk => Clk,
          Clr => Clr,
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
   end process;
   Clr <= '0';
 

--   -- Stimulus process
--   stim_proc: process
--   begin		
--      -- hold reset state for 100 ns.
--      wait for 100 ns;	
--
--      wait for Clk_period*10;
--
--      -- insert stimulus here 
--
--      wait;
--   end process;

END;
