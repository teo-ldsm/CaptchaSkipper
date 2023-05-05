# Captcha Skipper

Captcha Skipper est une application Android qui permet de d'aficher une page contenant un captcha sur un telephone Android.

L'application va chercher la lien de la page sur un serveur local sur lequel est hebergé une API.
Une fois que l'utilisateur a résolu la capotcha, elle renvoie le lien a l'API

L'appli vous demande de rentrer l'IP et le port de l'API. Elle peut être implémentée avec la library Python Flask.
**Les chemins d'accès http de l'API doivent impérativement être '/get_url' et '/upload_url'**

https://github.com/teo-ldsm/CaptchaSkipper/url_transmitter_api.py est un exemple fonctionnel de l'API pour vos projets.
