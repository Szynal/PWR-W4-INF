% -------------------------------------------------------------
%       PROGRAMOWANIE W PAKIECIE MATLAB/OCTAVE
%   AUTOR:   Pawe� Szynal    226026
%-------------------------------------------------------------

%---------------------------------------------------------------------------------------------------
% tworzenie w�asnych funkcji, (np. napisa� funkcj�, kt�ra zwr�ci liczby pierwsze z podanego zakresu)
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
% Napisz funkcj�, kt�ra wyliczy maksymaln� i minimaln� warto�� sygna�u,
% gdzie sygna�em jest suma trzech sygna��w sinusoidalnych o r�nych amplitudach,
% cz�stotliwo�ciach i fazach - czyli lista parametr�w wej�ciowych jest nast�puj�ca (A, f, ph, T, Fs),
% przy czym czas trwania T i cz�stotliwo�� pr�bkowania Fs s� warto�ciami domy�lnymi 
% np. T=1 s, Fs=1000 Hz, a parametry A, f, ph s� wektorami.
% (nale�y wykorzysta� funkcj� max() i min())
%---------------------------------------------------------------------------------------------------


 