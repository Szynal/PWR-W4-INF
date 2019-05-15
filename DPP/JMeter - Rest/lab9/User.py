class User:

    def __init__(self, nick):
        self.nick = nick
        self.balance = 0

    def getNick(self):
        return self.nick

    def getBalance(self):
        return self.balance

    def changeBalance(self, delta):
        self.balance += delta
        return self.balance