class Container(object):
    """Class of the container intended for garbage disposal"""

    def __init__(self, price=0, size=0):
        self.price = price
        self.size = size

    def print_info(self, id):
        print("ID: {} A container of {} liters costs {} PLN".format(id, self.size, self.price))

    def edit_container(self):
        self.price = int(input("Enter price of the container: "))
        self.size = int(input("Enter container size:"))
