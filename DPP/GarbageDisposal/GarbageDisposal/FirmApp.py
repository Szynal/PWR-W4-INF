from Container import Container
from Order import Order
from GUI import GUI 

class Firm(object):
    """description of class"""

    def __init__(self):
        self.container_list = [Container() for i in range(5)]
        self.order = []
        self.set_container_values()

    def set_container_values(self):
        price = 50
        size = 100

        for x in range(5):
            self.container_list[x].size = size
            self.container_list[x].price = price
            price *= 2
            size += 100

        self.order.append(Order())
        self.order[0].set_order(0, self.container_list[1], False)

    def firm_loop(self):
        gui = GUI()
        gui.print_firm()

        select_char = "s"
        while select_char != "q":
            select_char = input("\nSelect action:\n"
                                "\t1. Display of containers\n"
                                "\t2. Edit containers\n"
                                "\t3. Add a new container\n"
                                "\t4. Display orders\n"
                                "\t5. Exit\n").upper()
            if select_char not in "12345" or len(select_char) != 1:
                print("No such action")
                continue

            if select_char == '1':
                for i in range(len(self.container_list)):
                    self.container_list[i].print_info(i)

            elif select_char == '2':
                for i in range(len(self.container_list)):
                    self.container_list[i].print_info(i)
                container_id = int(input("Enter the container id: "))
                self.container_list[container_id].edit_container()

            elif select_char == '3':
                container = Container()
                container.price = int(input("Enter price of the container: "))
                container.size = int(input("Enter container size: "))
                self.container_list.append(container)

            if select_char == '4':
                for i in range(len(self.order)):
                    self.order[i].print_order(i)

                order_input = int(input("Action:\n"
                                  "1. Accept order\n"
                                  "2. Delete order\n"
                                  "3. Return\n"))

                if order_input == 1:
                    index = int(input("Enter ID orders to accept"))
                    if 0 <= index <= len(self.order):
                        self.order[index].paid = True

                if order_input == 2:
                    index = int(input("Enter ID orders to deleted"))
                    if 0 <= index <= len(self.order):
                        self.order.remove(self.order[index])

            if select_char == '5':
               gui = GUI()
               gui.clear(0)
               return

