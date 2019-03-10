Zadanie: 
Napisz program umożliwiający potokowe przetwarzanie tekstów. Każdy etap potokowego przetwarzania powinien być realizowany przez którąś z klas ładowanych dynamicznie. Każda dynamicznie ładowana klasa powinna posiadać metody:

void setInput(int port) - do określania portu gniazda serwerowego (na tym porcie przyjmować będzie nadchodzące ciągi znaków);
void setOutput(String host, int port) - do określania hosta i portu gniazda klienckiego (na ten host i port wysyłane będą przetworzone ciągi znaków).
void  start() - do uruchomienia przetwarzania
void stop() - do zatrzymania przetwarzania

Program powinien umożliwiać zarządzanie ładowaniem klas, tj. za jego pomocą powinno dać się wskazać klasy do załadowania, jak również klasy do wyładowania.
Ładowanie danej klasy powinno odbywać się własnym ładowaczem klas w osobnym wątku. Ładowane klasy powinny być umieszczane w podfolderach wskazanego folderu rozszerzeń. Nazwy podfolderów powinny być interpretowane jako nazwy rozszerzeń.

Sposób potokowego przetwarzania powinien być konfigurowalny. W najprostszej postaci może przyjąc postać listy z możliwością zmiany kolejności występujących na niej elementów.

Przetwarzanie tekstu może polegać na zamianie wielkości liter, wstawianiu znaków interpunkcyjnych itp. Każda z klas przetwarza dane przychodzące na wskazany port wejściowy oraz odsyła wynik przetwarzania do wskazanego hosta na wskazany port wyjściowy robiąc to w wątku.