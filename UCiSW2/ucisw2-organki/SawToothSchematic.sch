<?xml version="1.0" encoding="UTF-8"?>
<drawing version="7">
    <attr value="spartan3e" name="DeviceFamilyName">
        <trait delete="all:0" />
        <trait editname="all:0" />
        <trait edittrait="all:0" />
    </attr>
    <netlist>
        <signal name="XLXN_14(3:0)" />
        <signal name="XLXN_15(3:0)" />
        <signal name="XLXN_16(11:0)" />
        <signal name="XLXN_13" />
        <signal name="PS2_Clk" />
        <signal name="SPI_MOSI" />
        <signal name="SPI_SCK" />
        <signal name="SPI_MISO" />
        <signal name="DAC_CS" />
        <signal name="DAC_CLR" />
        <signal name="SPI_SS_B" />
        <signal name="AMP_CS" />
        <signal name="AD_CONV" />
        <signal name="SF_CE0" />
        <signal name="FPGA_INIT_B" />
        <signal name="XLXN_17(3:0)" />
        <signal name="XLXN_44(7:0)" />
        <signal name="XLXN_45" />
        <signal name="XLXN_46" />
        <signal name="PS2_Data" />
        <signal name="CLK_IN" />
        <signal name="CLR_IN" />
        <port polarity="Input" name="PS2_Clk" />
        <port polarity="Output" name="SPI_MOSI" />
        <port polarity="Output" name="SPI_SCK" />
        <port polarity="Output" name="SPI_MISO" />
        <port polarity="Output" name="DAC_CS" />
        <port polarity="Output" name="DAC_CLR" />
        <port polarity="Output" name="SPI_SS_B" />
        <port polarity="Output" name="AMP_CS" />
        <port polarity="Output" name="AD_CONV" />
        <port polarity="Output" name="SF_CE0" />
        <port polarity="Output" name="FPGA_INIT_B" />
        <port polarity="Input" name="PS2_Data" />
        <port polarity="Input" name="CLK_IN" />
        <port polarity="Input" name="CLR_IN" />
        <blockdef name="DACWrite">
            <timestamp>2009-10-4T15:3:32</timestamp>
            <line x2="0" y1="-544" y2="-544" x1="64" />
            <rect width="64" x="0" y="-492" height="24" />
            <line x2="0" y1="-480" y2="-480" x1="64" />
            <rect width="64" x="0" y="-428" height="24" />
            <line x2="0" y1="-416" y2="-416" x1="64" />
            <rect width="64" x="0" y="-364" height="24" />
            <line x2="0" y1="-352" y2="-352" x1="64" />
            <line x2="448" y1="-224" y2="-224" x1="384" />
            <line x2="448" y1="-160" y2="-160" x1="384" />
            <line x2="448" y1="-96" y2="-96" x1="384" />
            <line x2="448" y1="-32" y2="-32" x1="384" />
            <line x2="448" y1="32" y2="32" x1="384" />
            <line x2="448" y1="96" y2="96" x1="384" />
            <line x2="448" y1="-544" y2="-544" x1="384" />
            <line x2="448" y1="-416" y2="-416" x1="384" />
            <line x2="384" y1="-480" y2="-480" x1="448" />
            <line x2="448" y1="-352" y2="-352" x1="384" />
            <line x2="448" y1="-288" y2="-288" x1="384" />
            <line x2="0" y1="32" y2="32" x1="64" />
            <rect width="320" x="64" y="-576" height="760" />
            <line x2="0" y1="96" y2="96" x1="64" />
            <line x2="0" y1="160" y2="160" x1="64" />
        </blockdef>
        <blockdef name="PS2_Kbd">
            <timestamp>2008-9-19T9:9:36</timestamp>
            <line x2="384" y1="-160" y2="-160" x1="320" />
            <line x2="384" y1="-96" y2="-96" x1="320" />
            <line x2="0" y1="-224" y2="-224" x1="64" />
            <line x2="0" y1="-160" y2="-160" x1="64" />
            <line x2="0" y1="-96" y2="-96" x1="64" />
            <line x2="384" y1="-32" y2="-32" x1="320" />
            <rect width="64" x="320" y="-236" height="24" />
            <line x2="384" y1="-224" y2="-224" x1="320" />
            <rect width="256" x="64" y="-256" height="256" />
            <line x2="0" y1="-32" y2="-32" x1="64" />
        </blockdef>
        <blockdef name="SawToothGenerator">
            <timestamp>2019-5-27T7:19:56</timestamp>
            <rect width="336" x="64" y="-256" height="256" />
            <line x2="0" y1="-224" y2="-224" x1="64" />
            <line x2="0" y1="-128" y2="-128" x1="64" />
            <rect width="64" x="0" y="-44" height="24" />
            <line x2="0" y1="-32" y2="-32" x1="64" />
            <line x2="464" y1="-224" y2="-224" x1="400" />
            <rect width="64" x="400" y="-172" height="24" />
            <line x2="464" y1="-160" y2="-160" x1="400" />
            <rect width="64" x="400" y="-108" height="24" />
            <line x2="464" y1="-96" y2="-96" x1="400" />
            <rect width="64" x="400" y="-44" height="24" />
            <line x2="464" y1="-32" y2="-32" x1="400" />
        </blockdef>
        <blockdef name="PianoKey">
            <timestamp>2019-5-27T7:31:51</timestamp>
            <rect width="256" x="64" y="-320" height="320" />
            <line x2="0" y1="-288" y2="-288" x1="64" />
            <line x2="0" y1="-224" y2="-224" x1="64" />
            <line x2="0" y1="-160" y2="-160" x1="64" />
            <line x2="0" y1="-96" y2="-96" x1="64" />
            <rect width="64" x="0" y="-44" height="24" />
            <line x2="0" y1="-32" y2="-32" x1="64" />
            <rect width="64" x="320" y="-300" height="24" />
            <line x2="384" y1="-288" y2="-288" x1="320" />
        </blockdef>
        <block symbolname="DACWrite" name="XLXI_2">
            <blockpin signalname="CLR_IN" name="Reset" />
            <blockpin signalname="XLXN_13" name="Start" />
            <blockpin signalname="SPI_MISO" name="SPI_MISO" />
            <blockpin signalname="XLXN_14(3:0)" name="Cmd(3:0)" />
            <blockpin signalname="XLXN_15(3:0)" name="Addr(3:0)" />
            <blockpin signalname="XLXN_16(11:0)" name="DATA(11:0)" />
            <blockpin signalname="DAC_CLR" name="DAC_CLR" />
            <blockpin signalname="DAC_CS" name="DAC_CS" />
            <blockpin signalname="SPI_MOSI" name="SPI_MOSI" />
            <blockpin signalname="SPI_SCK" name="SPI_SCK" />
            <blockpin signalname="SPI_SS_B" name="SPI_SS_B" />
            <blockpin signalname="AMP_CS" name="AMP_CS" />
            <blockpin signalname="AD_CONV" name="AD_CONV" />
            <blockpin signalname="SF_CE0" name="SF_CE0" />
            <blockpin signalname="FPGA_INIT_B" name="FPGA_INIT_B" />
            <blockpin name="Busy" />
            <blockpin signalname="CLK_IN" name="Clk_50MHz" />
            <blockpin signalname="CLK_IN" name="Clk_Sys" />
        </block>
        <block symbolname="PS2_Kbd" name="XLXI_7">
            <blockpin signalname="PS2_Clk" name="PS2_Clk" />
            <blockpin signalname="PS2_Data" name="PS2_Data" />
            <blockpin signalname="CLK_IN" name="Clk_50MHz" />
            <blockpin name="E0" />
            <blockpin signalname="XLXN_45" name="F0" />
            <blockpin signalname="XLXN_46" name="DO_Rdy" />
            <blockpin signalname="XLXN_44(7:0)" name="DO(7:0)" />
            <blockpin signalname="CLK_IN" name="Clk_Sys" />
        </block>
        <block symbolname="SawToothGenerator" name="XLXI_8">
            <blockpin signalname="CLK_IN" name="Clk" />
            <blockpin signalname="CLR_IN" name="Clr" />
            <blockpin signalname="XLXN_17(3:0)" name="FreqIN(3:0)" />
            <blockpin signalname="XLXN_13" name="StartOUT" />
            <blockpin signalname="XLXN_14(3:0)" name="CmdOUT(3:0)" />
            <blockpin signalname="XLXN_15(3:0)" name="AddrOUT(3:0)" />
            <blockpin signalname="XLXN_16(11:0)" name="SawtoothOUT(11:0)" />
        </block>
        <block symbolname="PianoKey" name="XLXI_9">
            <blockpin signalname="CLR_IN" name="Clr" />
            <blockpin signalname="CLK_IN" name="Clk" />
            <blockpin signalname="XLXN_45" name="F0" />
            <blockpin signalname="XLXN_46" name="K_rdy" />
            <blockpin signalname="XLXN_44(7:0)" name="In_key(7:0)" />
            <blockpin signalname="XLXN_17(3:0)" name="FreqOUT(3:0)" />
        </block>
    </netlist>
    <sheet sheetnum="1" width="3520" height="2720">
        <branch name="XLXN_14(3:0)">
            <wire x2="2400" y1="1120" y2="1120" x1="2208" />
        </branch>
        <branch name="XLXN_15(3:0)">
            <wire x2="2400" y1="1184" y2="1184" x1="2208" />
        </branch>
        <branch name="XLXN_16(11:0)">
            <wire x2="2400" y1="1248" y2="1248" x1="2208" />
        </branch>
        <branch name="XLXN_13">
            <wire x2="2400" y1="1056" y2="1056" x1="2208" />
        </branch>
        <instance x="2400" y="1600" name="XLXI_2" orien="R0">
        </instance>
        <branch name="SPI_MOSI">
            <wire x2="2880" y1="1056" y2="1056" x1="2848" />
        </branch>
        <branch name="SPI_SCK">
            <wire x2="2880" y1="1184" y2="1184" x1="2848" />
        </branch>
        <branch name="SPI_MISO">
            <wire x2="2880" y1="1120" y2="1120" x1="2848" />
        </branch>
        <branch name="DAC_CS">
            <wire x2="2880" y1="1248" y2="1248" x1="2848" />
        </branch>
        <branch name="DAC_CLR">
            <wire x2="2880" y1="1312" y2="1312" x1="2848" />
        </branch>
        <branch name="SPI_SS_B">
            <wire x2="2880" y1="1376" y2="1376" x1="2848" />
        </branch>
        <branch name="AMP_CS">
            <wire x2="2880" y1="1440" y2="1440" x1="2848" />
        </branch>
        <branch name="AD_CONV">
            <wire x2="2880" y1="1504" y2="1504" x1="2848" />
        </branch>
        <branch name="SF_CE0">
            <wire x2="2880" y1="1568" y2="1568" x1="2848" />
        </branch>
        <branch name="FPGA_INIT_B">
            <wire x2="2880" y1="1632" y2="1632" x1="2848" />
        </branch>
        <branch name="XLXN_17(3:0)">
            <wire x2="1728" y1="1520" y2="1520" x1="1488" />
            <wire x2="1744" y1="1248" y2="1248" x1="1728" />
            <wire x2="1728" y1="1248" y2="1520" x1="1728" />
        </branch>
        <iomarker fontsize="28" x="2880" y="1056" name="SPI_MOSI" orien="R0" />
        <iomarker fontsize="28" x="2880" y="1184" name="SPI_SCK" orien="R0" />
        <iomarker fontsize="28" x="2880" y="1120" name="SPI_MISO" orien="R0" />
        <iomarker fontsize="28" x="2880" y="1248" name="DAC_CS" orien="R0" />
        <iomarker fontsize="28" x="2880" y="1312" name="DAC_CLR" orien="R0" />
        <iomarker fontsize="28" x="2880" y="1376" name="SPI_SS_B" orien="R0" />
        <iomarker fontsize="28" x="2880" y="1440" name="AMP_CS" orien="R0" />
        <iomarker fontsize="28" x="2880" y="1504" name="AD_CONV" orien="R0" />
        <iomarker fontsize="28" x="2880" y="1568" name="SF_CE0" orien="R0" />
        <iomarker fontsize="28" x="2880" y="1632" name="FPGA_INIT_B" orien="R0" />
        <branch name="XLXN_44(7:0)">
            <wire x2="960" y1="1520" y2="1520" x1="800" />
            <wire x2="960" y1="1520" y2="1776" x1="960" />
            <wire x2="1104" y1="1776" y2="1776" x1="960" />
        </branch>
        <branch name="PS2_Clk">
            <wire x2="416" y1="1520" y2="1520" x1="368" />
        </branch>
        <branch name="PS2_Data">
            <wire x2="416" y1="1584" y2="1584" x1="368" />
        </branch>
        <instance x="416" y="1744" name="XLXI_7" orien="R0">
        </instance>
        <branch name="XLXN_45">
            <wire x2="1104" y1="1648" y2="1648" x1="800" />
        </branch>
        <branch name="XLXN_46">
            <wire x2="1104" y1="1712" y2="1712" x1="800" />
        </branch>
        <iomarker fontsize="28" x="368" y="1520" name="PS2_Clk" orien="R180" />
        <iomarker fontsize="28" x="368" y="1584" name="PS2_Data" orien="R180" />
        <branch name="CLK_IN">
            <wire x2="192" y1="1056" y2="1056" x1="160" />
            <wire x2="192" y1="1056" y2="1648" x1="192" />
            <wire x2="192" y1="1648" y2="1712" x1="192" />
            <wire x2="416" y1="1712" y2="1712" x1="192" />
            <wire x2="416" y1="1648" y2="1648" x1="192" />
            <wire x2="1008" y1="1056" y2="1056" x1="192" />
            <wire x2="1744" y1="1056" y2="1056" x1="1008" />
            <wire x2="1008" y1="1056" y2="1584" x1="1008" />
            <wire x2="1104" y1="1584" y2="1584" x1="1008" />
            <wire x2="1008" y1="896" y2="1056" x1="1008" />
            <wire x2="1552" y1="896" y2="896" x1="1008" />
            <wire x2="1552" y1="896" y2="1696" x1="1552" />
            <wire x2="1552" y1="1696" y2="1760" x1="1552" />
            <wire x2="2400" y1="1760" y2="1760" x1="1552" />
            <wire x2="2400" y1="1696" y2="1696" x1="1552" />
        </branch>
        <branch name="CLR_IN">
            <wire x2="1072" y1="1248" y2="1248" x1="160" />
            <wire x2="1072" y1="1248" y2="1328" x1="1072" />
            <wire x2="1584" y1="1328" y2="1328" x1="1072" />
            <wire x2="1584" y1="1328" y2="1632" x1="1584" />
            <wire x2="2400" y1="1632" y2="1632" x1="1584" />
            <wire x2="1072" y1="1328" y2="1520" x1="1072" />
            <wire x2="1104" y1="1520" y2="1520" x1="1072" />
            <wire x2="1744" y1="1152" y2="1152" x1="1072" />
            <wire x2="1072" y1="1152" y2="1248" x1="1072" />
        </branch>
        <iomarker fontsize="28" x="160" y="1056" name="CLK_IN" orien="R180" />
        <iomarker fontsize="28" x="160" y="1248" name="CLR_IN" orien="R180" />
        <instance x="1744" y="1280" name="XLXI_8" orien="R0">
        </instance>
        <instance x="1104" y="1808" name="XLXI_9" orien="R0">
        </instance>
    </sheet>
</drawing>