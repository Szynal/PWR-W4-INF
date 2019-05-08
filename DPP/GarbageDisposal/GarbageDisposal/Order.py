class Order(object):
    """Class responsible for the order at the garbage disposal company."""

    def __init__(self):
        self.id = -1
        self.container = None
        self.paid = False

    def set_order(self, id, kontener, paid):
        self.id = id
        self.container = kontener
        self.paid = paid

    def print_order(self, id):
        print("ID: {} \n"
              "Paid? {}\n"
              "A container with a size of {} liters costs PLN {0}\n\n".format(id, self.paid, self.container.size,
                                                                       self.container.price))
