%---------------------------------------------------------------------------
% - napisa� w�asn� funkcj� do kwantyzacji z podan� liczba bit�w przetwornika
%---------------------------------------------------------------------------

function [ sygnal ] = Kwantyzacja( sygnal, quantizer,t )
        
    subplot(2,1,1);
     plot(t,sygnal)
         title('Sygnal')
      xlabel('t [s]');
      ylabel('A');
      
      sygnal = quantizer * floor(sygnal/quantizer + 0.5); %
      
      plot(t,sygnal)
      subplot(2,1,2);
      title('Sygnal po kwantyzacji')
      xlabel('t [s]');
      ylabel('A');

end


% cail !!!! DO ZAPAMIETANIA 
