


Spécification Wikitty publication module de synchronisation
============================================================
:Authors: Manoël Fortun




Définitions
------------ 

Objets wikitty <-> Fichier 
++++++++++++++++++++++++++++

Un fichier est défini par un nom, une extention, un contenu et un chemin.
dans wikitty publication les fichiers sont convertis en fonction de leurs type
en objet wikitty. les fichiers sources sont convertit en WikittyPubText et les
fichiers binaires(eg image, etc) en WikittyPubData. 

Les deux types d'objet ont les mêmes attribut:

	- Name: correspondant au nom du fichier
	- MimeType: crrespondant au type, qui donnera l'extension
 	- Content: le contenu binaire pour pour les PubData et textuel pour les PubText



Le mimetype donne l'extention par exemple pour un PubData si on a en mimetype "image/png"
 alors l'extension du fichier associé sera ".png". Dans le cas d'un PubText
le mimetype donnera l'extension et le langage de la source par exemple si on a
en mimetype "application/javascript" le langage est javascript et l'extension
sera donc ".js". 

A ces objets wikitty on associe un wikittyLabel, c'est un objet qui peut
contenir un ensemble de label différent, un label par exemple
"src.org.chorem.entities" sert ici pour contenir le chemin menant au fichier
sur le file system. Un wikitty peut avoir un certain nombre de label, pour les
wikittyPub celà indiquera qu'ils appartiennent à plusieurs arborescence. 

Propriétés
+++++++++++

WikittyService cible
**********************

L'adresse du wikitty service auprès duquel notre espace de travail(ensemble des
fichiers provenant d'un wikitty service) est lié sera stocké à la racine du dis
espace de travail(premier dossier), dans un dossier caché ".wp\". Cette
adresse sera dans un fichier de propriété très certainement.

Fichier de propriété:
wikitty.service= http://www.adresse.com:8080


Versions des wikitty et labels
********************************

Tout objet wikitty dispose d'une version qui est modifier par le wikitty service
à chaque modification de l'objet wikitty, les wikittypub donc aussi. Cette
information de la version sera stocké dans un fichier de propriété dans le
dossier caché ".wp/" afin que l'on garde trâce des versions des objets que
l'on aura transformé en fichier, celà pour mieux gérer les commit et update. 
Système de clé/valeur avec en clé le nom du fichier, et en valeur sa version

On conservera trace ausi dans ce même fichier de propriété sous labels direct
du label courant, celà permettra de pouvoir comparer les différences entre 
ce qui existe sur le file system et les objets présents dans le wikitty service, 
et donc de voir les suppressions et créations

Exemple pour un contenu de file system:

 +racine
 |script.js
 |scripttut.js
 |image.png
 |+directory2
 ||script3.js
 ||+directory3
 |||truc.js
 |+directory22
 ||machin.png

Fichier de propriétés correspondant:

script=numéroVersion
scripttut=numéroVersion
image=numéroVersion
label= racine.directory2, racine.directory22




Fonctionnalités
----------------

Import
++++++++

Définitions
************
La fonctionnalité import doit permettre d'importer auprès d'un wikitty service
un dossier, récurssivement ou non. Si on choisi récursivement alors tout les
sous dossier du dossier cible seront traité, sinon seulement les fichiers
contenu dans le dossier cible. Les fichiers sources seront transformé en
wikittyPubText et les binaires en WikittyPubData

Prototype commande et exemples
********************************
 ''wp import  [--recursion (true|false)] [url du WikittyService] [directory]''
 
 Exemple pour le contenu un file system:
 
 +racine
 |script.js
 |scripttut.js
 |image.png
 |+directory2
 ||script3.js
 
Si on lance la commande "import --recursion true http:// racine" alors tout le contenu 
sera envoyé et convertit en wikitty.

Si on lance la commande "import --recursion false http:// racine" alors seulement racine,
script.js, scripttut.js et image.png seront envoyés et convertis en wikitty.
 
 
CheckOut
+++++++++

Définitions
************

La fonctionnalité checkout permet de récupérer l'ensemble des wikittyPub qui possèdent
un label donné, et de l'option de récursion sur le label. La récupération passe par
la conversion de ses objets en fichier sur le disque local à l'endroit indiqué.
L'option de récursion permet de récupérer les sous labels d'un label par exemple le label:
"org.chorem.entities" est un sous label de "org.chorem". Le checkout écrit aussi des fichiers
de propriété caché permettant le bon fonctionnement des autres fonctions.

Prototype commande et exemples
********************************

 ''wp checkout [--recursion (true|false)] [url du WikittyService] [Label à extraire] [directory local d'accueil]''

Si on lance la commande "checkout --recursion true http:// org racine" on récpérera tout les éléments dont un des labels
commence par org et on les placera dans le dossier racine, et sous le dossier "org" on trouvera caché le fichier de 
propriété donnant l'adresse du wikitty service.

Si on lance la commande "checkout --recursion false http:// org racine" on récpérera tout les éléments dont un des labels
est org et on les placera dans le dossier racine, et sous le dossier "org" on trouvera caché le fichier de 
propriété donnant l'adresse du wikitty service.



Relocate
+++++++++

Définitions
************

La fonctionnalités relocate permet de changer le wikitty service cible d'un espace de travail
ce qui signifie que l'on va modifier le fichier de propriété caché à la racine de notre espace de 
travail qui contient l'adresse du wikitty avec lequel on travail, l'adresse par défaut des commandes
update, delete et commit. La devra s'effectuer dans le dossier père de l'espace de travail.

Prototype commande 
*******************

 ''wp relocate [nouvelle url du WikittyService par defaut] [directory a relocaliser]''
 


Commit
+++++++

Définitions
************

La fonctionnalité commit permet d'envoyer l'espace de travail ou une partie de cet espace,
que l'on a précédement checkout depuis un wikitty service, au wikitty service enregistré
dans le fichier de propriété, ou bien de l'envoyer à un wikitty service dont on a explicitement 
donné l'adresse en ligne de commande. Un commit peut avoir lieu que si la version local des fichiers
est supérieurs ou égale à celle enregistré auprès du wikitty service. 
On envois tout ce qui est contenu dans l'espace de travail, avec les fichiers de propriété
caché on peut obtenir la liste des nouveaux éléments, pour les éléments manquant ils ne sont
pas pris en compte, la fonction delete est prévu à cet effet. 

Le commit peut être récurssif, le fonctionnent de l'option récursif est le même que pour les
fonctionnalités précédente.

Si on commit depuis un sous dossier de l'espace de travail, l'adresse sera automatiquement retrouvé
en fouillant les fichiers de propriétés caché dans les dossiers parents.



Prototype commande et exemples
*******************************

 ''wp commit [--recursion (true|false)] [--ws (url du WikittyService)] [répertoire à pousser]''

L'option "ws" permet de donner explicitement un wikitty service pour le commit, par
défaut on cherchera le fichier de propriété contenant l'adresse du wikitty service.



Delete
++++++++

Définitions
************

La fonctionnalité delete permet de supprimer un fichier ou dossier de l'espace de travail
et de le supprimer dans le wikitty service associé, ou le wikitty service explicitement donné.

Dans le cas d'une suppression de dossier, celà supprime le label, donc enlève le label lié au 
wikittyPub, si ces wikittyPub se retrouve sans label on peut les supprimer du service.

Dans le cas d'un fichier on supprime le label du wikittyPub, si celui ci se retrouve sans label
on peut le supprimer du wkittyservice.

Dans les deux cas on supprime localement fichier ou dossier de la cible de la commande, dans les 
fichiers de propriétés aussi. 

Prototype commande 
*******************

 ''wp delete [--ws (url du WikittyService)] [répertoire ou fichier à supprimer]''

L'option "ws" permet de donner explicitement un wikitty service pour le delete, par
défaut on cherchera le fichier de propriété contenant l'adresse du wikitty service.



Update
++++++++

Définitions
************

La fonctionnalité update permet de mettre à jour l'espace de travail ou une partie de cet espace,
que l'on a précédement checkout depuis un wikitty service, au wikitty service enregistré
dans le fichier de propriété, ou bien de l'envoyer à un wikitty service dont on a explicitement 
donné l'adresse en ligne de commande. Un update permet de mettre à jour ses fichiers locaux
pour faire cette opération on va comparer les versions locales et les versions du serveur.

L'update permet de supprimer les fichiers qui ont été supprimé sur le wikitty service, 
on remplace les fichiers qui n'existent plus localement mais qui existe toujours sur le serveur.

Si il y a une différence de version entre les fichiers locaux et distant, pour les fichier binaire
on écrase la version locale par la version distante, pour les sources on écrit dans le fichier les différences
entre local et distant à l'utilisateur de faire le merge par la suite.


L'update peut être récurssif, le fonctionnent de l'option récursif est le même que pour les
fonctionnalités précédente.

Si on update depuis un sous dossier de l'espace de travail, l'adresse sera automatiquement retrouvé
en fouillant les fichiers de propriétés caché dans les dossiers parents.


Prototype commande
*******************

 ''wp update [--recursion (true|false)] [--ws (url du WikittyService)] [répertoire à mettre à jour]''

L'option "ws" permet de donner explicitement un wikitty service pour le delete, par
défaut on cherchera le fichier de propriété contenant l'adresse du wikitty service.




