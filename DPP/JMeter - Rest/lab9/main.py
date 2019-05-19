from Offer import Offer
from User import User
from flask import Flask, request
import pickle

#otwarcie plikow z danymi
file_users = open('users.obj', 'rb')
file_offers = open('offers.obj', 'rb')

#wczytanie danych z plików
users = pickle.load(file_users)
offers = pickle.load(file_offers)

app = Flask(__name__)

def save():
    # otwarcie plikow z danymi
    file_offers = open('offers.obj', 'wb')
    file_users = open('users.obj', 'wb')

    # zapis danych do plików
    pickle.dump(offers, file_offers)
    pickle.dump(users, file_users)

@app.route('/')
def hello_world():
    return 'Hello, World!'

@app.route('/getOffers')
def getOffers():
    out = ''
    for offer in offers:
        out += offer.getName() + ' ';
    return out

@app.route('/addOffer')
def addOffer():
    name = request.args.get("name")
    nick = request.args.get("nick")

    offers.append(Offer(name,nick))

    for user in users:
        if user.getNick() == nick:
            save()
            return 'Dodano oferte'

    newUser = User(nick)
    users.append(newUser)
    save()
    return 'Dodano oferte'

@app.route('/checkBalance')
def checkBalance():
    nick = request.args.get("nick")
    for user in users:
        if user.getNick() == nick:
            return user.getBalance().__str__()

    newUser = User(nick)
    users.append(newUser)
    save()
    return '0'

@app.route('/execute')
def execute():
    nick = request.args.get("nick")
    name = request.args.get("name")
    for offer in offers:
        if offer.getName() == name:
            for user in users:
                if offer.getNick() == user.getNick():
                    user.changeBalance(-1)

            for user in users:
                if user.getNick() == nick:
                    offers.remove(offer)
                    user.changeBalance(1)
                    save()
                    return 'Zrealiowano'

            newUser = User(nick)
            newUser.changeBalance(1)
            users.append(newUser)
            offers.remove(offer)
            save()
            return 'Zrealiowano'
    return 'Nie znaleziono oferty'

if __name__ == '__main__':
    app.run(debug=False)