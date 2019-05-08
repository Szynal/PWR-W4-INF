import os

class Validation(object):
    """description of class"""
    
    def validation_loop(self):

        select_char = "s"
        while select_char != "2":
            select_char = input("Select action:\n"
                                "\t1. Log in\n"
                                "\t2. EXIT\n").upper()
            if select_char not in "12" or len(select_char) != 1:
                print("No such action")
                continue

            if select_char == '1':
                flagName = False
                                                             
            if select_char == '2':
               gui = GUI()
               gui.clear(0)
               return

