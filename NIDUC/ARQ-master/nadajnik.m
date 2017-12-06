clear;
%Parametry programu
%Program dzia³a dla ka¿dego rozmiaru pakietów
%plik dataFile zawiera jednak dope³niaj¹ce -1 do rozmiaru pakietu

global output;
global transferCapacity;
global time;
global totalInformationBitsSent;
global bitsCounter;
global protectionLength;
global errorType;
global protectionType;
global nextError;
global errorTime;

%pokrêt³a
packetSize = 200;
errorType = 2;  %1-single bits change, 2-block change
protectionType = 2; %1-parity bit, 2- check sum
transferCapacity = 100; % bit per second tego chyba nie trzeba zmieniaæ
%koniec pokrête³

time = 0;               % total time it took to transmit data
bitsCounter = 0;
nextError = abs(normrnd(3,2));
errorTime = 0;
totalInformationBitsSent = 0;
changedBits = 0;
changedPackets = 0;
output = 'output.txt';
input = 'daneTestowe.txt';
dataFile = 'dataFile.txt';
%koniec parametrów
%inicjalizacja
if(protectionType == 1)
    protectionLength = 1;
else
    protectionLength = 8;
end

fid=fopen(output,'wt');
fclose(fid);
fid=fopen(dataFile,'wt');
fclose(fid);
%koniec inicjalizacji

p = fopen(input);
inputVector = fscanf(p,'%s')-'0';
PACKETS = zeros((ceil(length(inputVector)/packetSize)),packetSize);
for m = 1:(ceil(length(inputVector)/packetSize))
    for n = 1:(packetSize) 
        if ((m-1)*packetSize+n) <= length(inputVector)
            PACKETS(m, n) = inputVector((m-1)*packetSize+n); 
        else
            PACKETS(m, n) = -1;
        end
    end
end
fclose(p);
clear m;
clear n;
clear p;
clear ans;

%Wyliczenie zabezpieczenia i dodanie go na koniec pakietu
if(protectionType == 1)
    %bit parzystoœci
    for i = 1:size(PACKETS, 1)
        packet=PACKETS(i,:);
        controlBit = 0;
        for j = 1:packetSize
            if packet(j) == 1
                controlBit = controlBit + 1;
            end
        end
        controlBit = mod(controlBit, 2);
        PACKETS(i, packetSize+1) = controlBit;
    end
else
    %suma kontrolna
    for i = 1:size(PACKETS, 1)
        packet=PACKETS(i,:);
        checkSum = 0;
        for j = 1:packetSize
            if packet(j) == 1
                checkSum = checkSum + 1;
            end
        end
        controlBits = mod(checkSum, 2^protectionLength);
        PACKETS(i, packetSize+1:packetSize+protectionLength) = de2bi(controlBits, protectionLength) ;
    end
end

%za³adowanie i wys³anie pakietów bez ostatniego (mo¿e nie mieæ takiego
%samego rozmiaru)
for i = 1:(size(PACKETS, 1)-1)
    repeat = 1;
    while repeat ~= 0
        repeat = kanal(PACKETS(i,:), packetSize);
    end
end
%za³adowanie i wys³anie ostatniego pakietu
packet = PACKETS(size(PACKETS, 1),:);
for i = 1:size(PACKETS, 2) 
    if(packet(packetSize) ~= -1)
        repeat = 1;
        while repeat ~= 0
            repeat = kanal([PACKETS(size(PACKETS, 1),1:packetSize),PACKETS(size(PACKETS, 1),packetSize+1:packetSize+protectionLength)], packetSize);
        end
        break;
    end
    if(packet(i) == -1)
        repeat = 1;
        while repeat ~= 0
            repeat = kanal([PACKETS(size(PACKETS, 1),1:i-1),PACKETS(size(PACKETS, 1),packetSize+1:packetSize+protectionLength)], i-1);
        end
        break;
    end
end

%sprawdzenie poprawnoœci wykonania transmisji
p = fopen(output);
outputVector = fscanf(p,'%s')-'0';
fclose(p);
RECEIVED_PACKETS = zeros((ceil(length(outputVector)/packetSize)),packetSize);
PACKETS = PACKETS(:,1:packetSize);

for m = 1:(ceil(length(outputVector)/packetSize))
    for n = 1:(packetSize) 
        if ((m-1)*packetSize+n) <= length(outputVector)
            RECEIVED_PACKETS(m, n) = outputVector((m-1)*packetSize+n); 
        else
            RECEIVED_PACKETS(m, n) = -1;
        end
    end
end

fid = fopen(dataFile, 'at');
for i = 1:size(PACKETS, 1)
    fprintf(fid,'%s', '# ');
    fprintf(fid,'%7d', i);
    fprintf(fid,'%s', ' #');
end
fprintf(fid,'%s\n', '');
for i = 1:size(PACKETS, 1)
    fprintf(fid,'%s', '# ');
    fprintf(fid,'%d', PACKETS(i,:));
    fprintf(fid,'%s', ' #');
end
fprintf(fid,'%s\n', '');
for i = 1:size(PACKETS, 1)
    fprintf(fid,'%s', '# ');
    fprintf(fid,'%d', RECEIVED_PACKETS(i,:));
    fprintf(fid,'%s', ' #');
end
fprintf(fid,'%s\n', '');
for i = 1:size(PACKETS, 1)
    fprintf(fid,'%s', '# ');
    if ( isequal(PACKETS(i,:), RECEIVED_PACKETS(i,:)))
        fprintf(fid,'%s', '  OK   ');
    else
        fprintf(fid,'%s', ' b³¹d  ');
        changedPackets = changedPackets + 1;
    end
    fprintf(fid,'%s', ' #');
end
for i = 1:size(inputVector, 2)
    if( inputVector(1,i) ~= outputVector(1,i))
        changedBits = changedBits + 1;
    end
end
% time and error rate
fprintf(fid, '\n');
fprintf(fid, '\nError type: %d', errorType);
fprintf(fid, '\nPackets size: %d', packetSize);
fprintf(fid, '\nTransfer capacity bit/s: %d', transferCapacity);
fprintf(fid, '\nTotal time: %0.3fs', totalInformationBitsSent / transferCapacity);
fprintf(fid, '\nNumber of all bits transmitted: %d', totalInformationBitsSent);
fprintf(fid, '\nNumber of message bits: %d', size(inputVector, 2));
fprintf(fid, '\nNumber of changed bits: %d', changedBits);
fprintf(fid, '\nBits error rate: %0.3f%%', changedBits/size(inputVector, 2)*100);
fprintf(fid, '\n');
fprintf(fid, '\nNumber of packets: %d', size(PACKETS, 1));
fprintf(fid, '\nNumber of changed packets: %d', changedPackets);
fprintf(fid, '\npackets error rate: %0.3f%%', changedPackets/size(PACKETS, 1)*100);

fclose(fid);
