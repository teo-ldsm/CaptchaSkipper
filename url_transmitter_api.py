from flask import Flask, request


app = Flask(__name__)

url = input("Quel est le lien que vous souhaitez envoyer à l'appli Android")


@app.route('/get_url')
def get_url():
    return url


@app.route('/upload_url', methods=['POST'])
def upload_url():
    new_url = request.form.get('url')

    print(new_url)
    #Vous pouvez traiter le nouvel url ici

    return 'OK'


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
