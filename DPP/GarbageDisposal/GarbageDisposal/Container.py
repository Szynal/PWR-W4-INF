class Container(object):
    """description of class"""

    def __init__(self, price=0, size=0):
        self.price = price
        self.size = size

    def print_info(self, id):
        print("ID: {} Kontener o wielkości {} litrów kosztuje {}zł".format(id, self.size, self.price))

    def edit_container(self):
        self.price = int(input("Podaj cenę kontenera: "))
        self.size = int(input("Podaj rozmiar kontenera: "))
