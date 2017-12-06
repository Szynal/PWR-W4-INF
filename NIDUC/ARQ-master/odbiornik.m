function [ resendRequest ] = odbiornik( pakiet, packetSize )
%jeœli pakiet dobry to wywaliæ 1 i do pliku/macierzy globalnej
%jeœli z³y to return 1;
global output;
global protectionType;
global protectionLength;

if(protectionType == 1)
    controlBit = 0;
    for i = (1:packetSize+protectionLength)
        if pakiet(i) == 1
            controlBit = controlBit + 1;
        end
    end
    if(mod(controlBit, 2) ~= 0)
            resendRequest = 1;
    else
            fid = fopen(output, 'at');
            fprintf(fid,'%d', pakiet(1:packetSize));
            fclose(fid);
            resendRequest = 0;
    end
else
    checkSum = 0;
    for i = (1:packetSize)
        if pakiet(i) == 1
            checkSum = checkSum + 1;
        end
    end
    controlBits = mod(checkSum, 2^protectionLength);
    if(~(isequal(de2bi(controlBits, protectionLength), pakiet(packetSize+1:packetSize+protectionLength))))
        resendRequest = 1;
    else
        fid = fopen(output, 'at');
        fprintf(fid,'%d', pakiet(1:packetSize));
        fclose(fid);
        resendRequest = 0;
    end
end

end

