compile c function to dll on windows:
gcc -c -fPIC lib.c -o lib.o
gcc -shared -o lib.dll lib.o -W


To do that, you must have installed: MinGW with gcc.
Also add this to your environment variables (Path)


if you hava a lot of warnings of missing dlls,
just execute command before pyinstaller:

set PATH=%PATH%;C:\Windows\System32\downlevel;

https://hackernoon.com/the-one-stop-guide-to-easy-cross-platform-python-freezing-part-1-c53e66556a0a?fbclid=IwAR2jj3XsfxeLytvw52avXy1sduD1xXUQ2TsRvKC4JtDRL9uZONk91TemEb8

# generowane za każdym razem na nowo, w celu spakowania na innej maszynie należy to przegenerować:
# pyinstaller main.py permutateApp
# następnie otworzyć ten nowo powstały plik permutateApp.spec i dodać informacje o pobieraniu binarek

# generowanie .spec
pyinstaller main.py -n newApp

#skopiować ode mnie get_bin(), uzupełnić binaries=

pyinstaller newApp.spec