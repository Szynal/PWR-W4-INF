//---------------------------------------------------------------------
// Program do konwersji danych TSP z formatu TSPLIB
//
// Plik z danymi wejsciowymi (FILE_IN):
// Dane w formacie TSPLIB z naglowkiem
//
// Plik z danymi przetworzonymi:
// Nazwa:           tsp_n.txt    gdzie n = liczba miast, np. tsp_17.txt  
// Pierwsza linia:  liczba miast
// Kolejne linie:   odleglosci z danego miasta do pozostalych
// Przekatna:       0 (odleglosc z miasta do niego samego)
//---------------------------------------------------------------------

#include <iostream>
#include <iomanip>
#include <fstream>
#include <sstream>
#include <vector>
#include <string>

using namespace std;

#define     FILE_IN         "ftv170.atsp"         // Nazwa pliku z danymi do przetworzenia
#define     FILE_OUT        "tsp_"              // Prefiks nazwy pliku z danymi przetworzonymi
                                                // Do prefiksu dodawana jest liczba miast
#define     HEADER_LINES    7

typedef     vector<int>     c_v_i;              // vector of int
typedef     vector<c_v_i>   c_v_v_i;            // vector of vector of int

//---------------------------------------------------------------------
// Wyjscie w przypadku bledu z komunikatem msg
//---------------------------------------------------------------------
void error_exit(const string &msg)
{
  cout << msg << endl;
  system("PAUSE");
  exit(1);
}

//---------------------------------------------------------------------
// Odczyt liczby miast z naglowka pliku TSPLIB
// Odczytywane sa wszystkie linie naglowka 
//---------------------------------------------------------------------
int header_get_size(ifstream &file)
{
int     header_lines = 0;
int     size = 0;
string  line;

  for(;;)
  {
    getline(file, line);
    if(file.fail() || line.empty())
      error_exit("File READ error - HEADER");

    if(line.find("DIMENSION") != string::npos)
    {
      istringstream in_ss(line);
      string tmp;
      in_ss >> tmp;
      in_ss >> size;
    }
    else
      if(line.find("EDGE_WEIGHT_SECTION") != string::npos)
        if(size <= 0)
          error_exit("File READ error - SIZE");
        else
          return(size);

    if(++header_lines >= 7)
      error_exit("File READ error - HEADER");
  }
}

//---------------------------------------------------------------------
// Okreslenie liczby cyfr liczby nieujemnej
// Dla wartosci 0 jedna cyfra
//---------------------------------------------------------------------
int value_digits(int val)
{
int width = 0;

  if(val == 0)
    return(1);

  while(val)
  {
    val /= 10;
    width++;
  }

  return(width);
}

//---------------------------------------------------------------------
int main()
{
ifstream    file_in;
ofstream    file_out;
int         size;
int         width;
int         diagonal;
int         dist, dist_max;
c_v_v_i     city_distance;

  file_in.open(FILE_IN);
  if(!file_in.is_open())
    error_exit("File OPEN error - FILE_IN");

  size = header_get_size(file_in);

  city_distance.resize(size);
  for(int i = 0; i < size; i++)
    city_distance[i].resize(size);

  dist_max = 0;
  for(int i = 0; i < size; i++)
    for(int j = 0; j < size; j++)
    {
      file_in >> dist;
      if(file_in.fail())
        error_exit("File READ error - DATA");

      if(i == j)
      {
        if(i == 0)
          diagonal = dist;
        else
          if(dist != diagonal)
            cout << "WARNING: Diagonal data inconsistent (" << i << "," << j << ")" << endl << endl;
        dist = 0;
      }

      city_distance[i][j] = dist;

      if(dist > dist_max)
        dist_max = dist;
    }

  file_in.close();

  file_out.open(FILE_OUT + to_string(size) + ".txt");
  if(!file_out.is_open())
    error_exit("File OPEN error - FILE_OUT");

  width = value_digits(dist_max);

  file_out << size << endl;

  for(int i = 0; i < size; i++)
  {
    for(int j = 0; j < size; j++)
      if(j == 0)
        file_out << setw(width) << city_distance[i][j];
      else
        file_out << setw(width + 1) << city_distance[i][j];

    file_out << endl;
  }

  file_out.close();

  cout << "Size = " << size << endl;
  cout << "Conversion OK" << endl << endl;

  system("PAUSE");
  return(0);
}
