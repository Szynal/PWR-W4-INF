function [ resendRequest ] = kanal( pakiet, packetSize )
%Kana³ - wprowadzenie zak³óceñ do transmisji
%
global totalInformationBitsSent;
global protectionLength;
global bitsCounter;
global errorType;
global transferCapacity;
global nextError;
global errorTime;


for i = 1:(packetSize+protectionLength)
    if(errorType == 1)
        if(rand <= 0.1)
            if(pakiet(i) == 1)
                pakiet(i) = 0;
            else
                pakiet(i) = 1;
            end
        end
    end
    if(errorType == 2)
        currentTime = bitsCounter/transferCapacity;
        if(currentTime >= nextError)
            errorTime = (normrnd(0.5,0.1));
            nextError = (currentTime + abs(normrnd(3,2)) + errorTime);
            errorTime = errorTime + currentTime;
        end
        if(currentTime < errorTime)
             pakiet(i) = 0;
        end
    end
    bitsCounter = bitsCounter + 1;
end
totalInformationBitsSent = totalInformationBitsSent + packetSize + protectionLength;
resendRequest = odbiornik(pakiet, packetSize);
end

