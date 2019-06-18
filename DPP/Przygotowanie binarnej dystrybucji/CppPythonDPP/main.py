from ctypes import *
import re
import sys
import os

if __name__ == '__main__':

    isFrozen = getattr(sys, 'frozen', False)

    # Check if app is frozen and load the proper path
    if isFrozen:
        print("**** App is FROZEN *****")
        frozenTempPath = getattr(sys, '_MEIPASS', '')
        sysPath = frozenTempPath
    else:
        print("App is NOT frozen")
        sysPath = os.path.dirname(os.path.abspath(__file__))

    print("App path: " + sysPath)
    print("Enter word to permutate: ", end="")

    word = input()
    encodedWord = word.encode('utf-8')

    path = sysPath.replace('\\', '/')
    libWrapper = CDLL(path + "/resources/lib.dll")
    libWrapper.getPermutations.argtypes = [c_char_p]
    libWrapper.getPermutations.restype = c_char_p

    result = libWrapper.getPermutations(encodedWord)
    print("Result from lib: ", end="")
    print(result)

    resultValue = c_char_p(result).value
    print("Result Value: ", end="")
    print(resultValue)

    # Decode returned value
    decodedResult = str(result, 'utf-8')
    print("Decoded result: ", end="")
    print(decodedResult)

    # Prepare result as a list
    wordList = re.sub("[^\w]", "\\n", decodedResult).split()
    print("Permutations: ", end="")
    print(wordList)
