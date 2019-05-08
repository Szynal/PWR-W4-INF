from FirmApp import Firm
from CustomerApp import Customer
from GUI import GUI
import os

class App(object):
    """Main App"""

    if __name__ == '__main__':

        firm = Firm()
        customer = Customer()
        gui = GUI()
        select_char = "s"

        while select_char != "q":

            gui.print_title()
            select_char = input("Select view:\n"
                               "\t1. Firm Panel\n"
                               "\t2. Customer Panel\n"
                               "\t3. Exit\n").upper()

            if select_char not in "123" or len(select_char) != 1:
                print("No such action")
                gui.clear(1);
                continue

            if select_char == '1':
                firm.firm_loop()

            elif select_char == '2':
                id = int(input("Enter customer id: "))
                customer.customer_loop(id, firm.container_list, firm.order)

            elif select_char == '3':
                os._exit(1)