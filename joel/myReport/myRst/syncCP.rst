


Spécification Wikitty publication module de synchronisation
===========================================================
:Authors: Manoël Fortun


Nouvelle approche sur le modèle Rsync
-------------------------------------

Cette nouvelle approche du module wikitty publication, basé sur Rsync, et plus
un "simple" système de commit/update/import/delete/relocate comme svn, est motivé
par l'aspect plus généraliste que celà apporte.

Cette approche permet de syncrhoniser le contenu de n'importe quel wikitty service 
avec un autre, et donc de redéployer simplement tout les wikittys que l'on souhaite
d'un wikitty service à un autre. 

La nature du wikitty service cible importe pas, on peut synchroniser un wikitty service
sur un file system avec un wikitty service sur un serveur cajo, ou deux wikitty service
sur des serveurs cajo, cette approche se veut plus universelle. Et pourra être testé justement
entre deux wikitty service sur serveur.


Définitions
-----------

Fichier -> Objets wikitty
+++++++++++++++++++++++++

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
wikittyPub celà indiquera qu'ils appartiennent à plusieurs arborescences. 


Objets wikitty -> Fichier
+++++++++++++++++++++++++

Lorsque l'on va enregistrer des wikitties en fichier en utilisant un 
wikitty service file system, on devra restaurer les fichiers en se servant
des labels pour localiser sa place dans l'arborescence, du mimetype pour
les extensions, etc.

Dans ce cas le repo local des wikitty devra contenir toutes les informations 
du wikitty si l'on veux le renvoyer vers un autre wikitty service en se servant 
de l'application wikitty publication sync.

Propriétés pour l'inversabilité fichier/wikitty
+++++++++++++++++++++++++++++++++++++++++++++++

Tout objet wikitty dispose d'une version qui est modifiée par le wikitty service
à chaque modification de l'objet wikitty, les wikittypub donc aussi. Cette
information de la version sera stockée dans un fichier de propriété dans un
dossier caché ".wp/" afin que l'on garde trâce des versions des objets que
l'on aura transformé en fichier, celà pour une synchronisation ultérieure avec un
autre wikitty service.

On conservera trace ausi dans ce même fichier de propriété du label courant, permettant
de ne pas faire d'opération "complexes" et pénible sur les noms de fichier afin de retrouver
le label de travail. Conserver trace du label actuel à l'avantage de n'avoir pas
besoin de rechercher dans l'arborescence la première occurence du fichier de propriété
pour pouvoir reconstituer le label complet. 

On distinguera deux fichiers de propriétés pour les informations un qui conservera 
les id des wikitty lié à leur nom de fichier. Et un autre fichiers de propriété 
qui conservera un checksum, la version et les id aussi.

On conserve les id dans un premier fichier puisque celà permet simplement de récupérer
l'ensemble des id et leurs noms de fichier lié sans avoir besoin de faire le tri
parmis toutes les propriétés enregistrées. On converse l'id aussi dans un autre fichier de
propriété, à défaut d'avoir un system de type bidimap pour les proriétés, celà permet 
de récupérer l'id d'un wikitty à partir de son nom de fichier, et inversement du nom de fichier
à l'id. 

La propriété checksum sera utilisée pour enregistrer la somme de controle de 
l'objet lors de son enregistrement, pour plus tard, savoir si celui ci à été
modifié depuis lors.

Exemple pour un contenu de file system:

 +racine
 |script.js
 |scripttut.js
 |image.png
 |+.wp
 ||id.properties 
 ||meta.properties 
 |+directory2
 ||script3.js
 ||+.wp
 |||meta.properties
 |||id.properties 
 ||+directory3
 |||truc.js
 |||+.wp
 ||||meta.properties
 ||||id.properties 
 |+directory22
 ||machin.png
 ||+.wp
 |||versions.properties
 |||id.properties 

Exemples de fichiers de propriétés:

meta.properties:

script.js.version=numéroVersion7
scripttut.js.version=numéroVersion
image.png.version=numéroVersion
current.label=racine
script.js.checksum= checksum
scripttut.js.checksum= checksum
image.png.checksum= checksum
id.image.png=uubdazudba
id.scripttut.js=11daz5facz
id.script.js=jbdub1dza8

id.properties:

uubdazudba=image.png
11daz5facz=scripttut.js
jbdub1dza8=script.js

Fonctionnalité
--------------

Sync
++++

Définitions
***********
La fonctionnalité CP permet de transférer l'ensemble des wikittys ciblés par l'uri,
d'un service wikitty à un autre. Son fonctionnement doit être similaire à la commande
linux "rsync". 

On reprendra donc quelques options comme:

   - Recursion pour savoir si l'on s'occupe des sous labels du label ciblé.
   - Update, qui permettra de mettre à jour ce qui est présent et antérieur 
     sur la cible et d'y envoyer les nouveaux wikitty. Par défaut cette option
     sera active, et sera desactivée lorsque les autres option (delete ou existing)
     seront choisis.
   - Existing qui est un update mais sans l'envois des nouveaux fichiers, on 
     envois juste ce qui à été mis à jour et qui existe sur le wikitty service cible.
   - Delete pour supprimer dans le wikitty cible, ce qui n'existe plus dans le 
     wikitty origine.

La suppression n'est pas une vraie suppression elle se contente de supprimer le label
ciblé du wikitty. 

En fonction des uris des wikitty services ciblé par la fonction, une implémentation
différente de service wikitty sera instancié, en fonction des protocoles (file, hessian
ou cajo).


Prototype commande
******************


''wp sync [--norecursion] [--delete|--existing] [URI orgirine] [URI cible]''

Avec URI sous forme: 
   - file:///truc/machin/#label
   - hessian://www.adresse.com:8827/etc/etc#label
   - cajo://www.adresse.com:8827/etc/etc#label

Evidement le path pour le protocole File indiquant le répertoire où aller
chercher/mettre les wikitties.

Les labels de l'uri cible et local peuvent être différent, et pendant ils seront
conservé, c'est à dire que des wikitties de la cible si ils ont besoin de se
mettre à jour, leurs labels seront conservés.
Dans le cas de wikitty qui n'existe pas dans la cible, on remplacera le label
origine qui à permis de trouver ces wikitties et le remplacer par le label cible,
les autres labels du wikitty seront transmit.
 

Wikitty Service File System
---------------------------

Un tel service devra fournir les méthodes suivantes les méthodes de sauvegarde
des wikitty, de restauration, ainsi qu'un certain nombre de fonctionnalités concernant
les recherches de wikitty.

Le wikitty service sur file system prendra en charge les recherches sur critéria
de façon compléte. A chaque recherche sur le wikitty service file system, il faudra
indexer les nouveaux wikitty, enlever les property des fichiers/wikitty supprimé,
incrémenter la version mineur si il y a eut des modifications depuis la dernière 
indexation.



