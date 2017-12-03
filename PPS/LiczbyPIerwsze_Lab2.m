% -------------------------------------------------------------
%       PROGRAMOWANIE W PAKIECIE MATLAB/OCTAVE
%   AUTOR:   Pawe³ Szynal    226026
%-------------------------------------------------------------

%---------------------------------------------------------------------------------------------------
% tworzenie w³asnych funkcji, (np. napisaæ funkcjê, która zwróci liczby pierwsze z podanego zakresu)
%---------------------------------------------------------------------------------------------------



function [] = LiczbyPIerwsze_Lab2(min,max)
x= [];
    for i = min : max
        if i >1

            cauter =0;
        
                for k = 2: i
                    if(mod(i,k)==0) 
                        cauter= cauter +1;                    
                    end
                end
            

            if(cauter <=1)
                x = cat(1,x,i);         
            end
        end
      
    end
     disp(x)
end



%---------------------------------------------------------------------------------------------------
% Napisz funkcjê, która wyliczy maksymaln¹ i minimaln¹ wartoœæ sygna³u,
% gdzie sygna³em jest suma trzech sygna³ów sinusoidalnych o ró¿nych amplitudach,
% czêstotliwoœciach i fazach - czyli lista parametrów wejœciowych jest nastêpuj¹ca (A, f, ph, T, Fs),
% przy czym czas trwania T i czêstotliwoœæ próbkowania Fs s¹ wartoœciami domyœlnymi 
% np. T=1 s, Fs=1000 Hz, a parametry A, f, ph s¹ wektorami.
% (nale¿y wykorzystaæ funkcjê max() i min())
%---------------------------------------------------------------------------------------------------


 