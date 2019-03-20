
- Si vous n'avez ni Java, ni Wamp (ou Lamp) vous devez avoir une connexion internet afin de télécharger les différents installateurs.

- Si ce n'est pas déjà fait téléchargez Java à cette adresse:
https://java.com/fr/

- Installez le.

- Si ce n'est pas déjà fait téléchargez :

Wamp (pour Windows) à cette adresse:
https://sourceforge.net/projects/wampserver/files/

Pour Wamp vous aurez besoin (si non installé) de Redistribuable Visual C++ pour Visual Studio 2015:
https://www.microsoft.com/fr-FR/download/details.aspx?id=48145 

et Redistribuable Visual C++ pour Visual Studio  2013:
https://www.microsoft.com/fr-FR/download/details.aspx?id=40784 

Si vous êtes sur Windows 10 vous devrez désactiver IIS pour executer correctement Wamp:
https://openclassrooms.com/forum/sujet/probleme-wampserver-windows-10#message-89111670)

Lancez l'installateur avec les droits d'administrateurs !

ou Lamp (pour Linux):
https://doc.ubuntu-fr.org/lamp
https://doc.ubuntu-fr.org/phpmyadmin

Un simple serveur SQL peut aussi faire l'affaire. 
Néanmoins, il faudrat vous assurer qu'il fonctionne bel et bien sur le numéro de port standard à MYSQL : 3306. 

Pour Wamp (Windows):

- Copier le fichier "adminer.php" à la racine du dossier de Wamp, par défaut "C:\wamp64".

- Executer Wampserver (wampmanager.exe)

- Si ce n'est pas déjà fait lancez votre navigateur internet puis tapez "localhost" dans la barre de recherche de site.

- Dans la partie "Vos alias" cliquez sur "adminer".

- Mettez "root" dans le champ "Utilisateur" puis laissez les champs "Mot de passe" et “Base de données” vide. Connectez-vous.

- Cliquez sur “Requête SQL” dans les menus à gauche. Copiez le contenu du fichier BDD_Create.sql (ou importez le fichier avec le menu “Importer”). La base de données est créée et est vide.

(vous pouvez aussi créer et modifier les tables à partir de phpmyadmin)


Pour les utilisateurs Linux vous pouvez aussi utiliser adminer:
http://www.ubuntuboss.com/how-to-install-adminer-on-ubuntu/
Sinon phpmyadmin est fourni avec Lamp par défaut. 
Vous pouvez executer le contenu du fichier sql fourni avec le projet : "BDD_Create.sql".
