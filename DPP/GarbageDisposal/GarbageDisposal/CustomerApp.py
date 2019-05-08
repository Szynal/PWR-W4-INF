from Order import Order 

class Customer(object):
    """description of class"""

    def customer_loop(self, id, containers_list, orders):
        print("Hello in Klient App")

        select_char = "s"
        while select_char != "q":
            select_char = input("Select action:\n"
                                "\t1. Show available offer\n"
                                "\t2. Submit your order\n"
                                "\t3. My orders\n"
                                "\t4. Exit\n").upper()

            if select_char not in "1234" or len(select_char) != 1:
                print("No such action")
                continue

            if select_char == '1':
                for i in range(len(containers_list)):
                    containers_list[i].print_info(i)

            elif select_char == '2':
                order = Order()
                for i in range(len(containers_list)):
                    containers_list[i].print_info(i)

                container_id = int(input("Enter the container id: "))
                order.set_order(id, containers_list[container_id], False)
                orders.append(order)

            elif select_char == '3':
                for i in range(len(orders)):
                    if orders[i].id == id:
                        orders[i].print_order(i)

            elif select_char == "4":
                return

