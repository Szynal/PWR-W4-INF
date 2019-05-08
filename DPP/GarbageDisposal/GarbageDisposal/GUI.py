import os
import time

class GUI(object):
    """description of class"""

    def print_title(self):
        print(" ____________________________________________________")
        print("|                                                    |")
        print("|                   Garbage Disposal                 |")
        print("|____________________________________________________|\n")
       

    def print_firm(self):
        cls  = lambda: os.system('cls')
        cls()
        print("\nHello in Firm App\n")

    def clear(self, number_of_seconds):
        time.sleep(number_of_seconds)
        cls  = lambda: os.system('cls')
        cls()
           