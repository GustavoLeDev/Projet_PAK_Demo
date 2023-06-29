[![Build Status](<https://travis-ci.org/nbouteme/projet-java.svg?branch=master>)](<https://travis-ci.org/nbouteme/projet-java>)

Projet de Java - Gestion de parking
=================================

Compiler
--------

Pour compiler, vous avez besoin de Maven.

`mvn package`

dans un terminal dans le répertoire source du projet.Puis vous pouvez executer le programme en allant dans le dossier target/classes et taper la commande `java parking.business.App`
Vous pouvez convertir le projet en projet eclipse avec

`mvn eclipse:eclipse`

Importez alors dans eclipse (Projet existant dans l'espace de travail)
Vous avez besoin de paramétrer une variable M2_REPO de build pour les dépendances (JUnit)

Allez dans la barre de menu, Window > Preferences
Dans la barre de coté. Java > Build Path > Classpath Variables.
Faite New.
Creer une variable qui s'appelle M2_REPO.
Donnez lui pour valeur le chemin des repo de maven.
Sous linux, il y a un dossier caché .m2 dans votre home et dedans il y a un dossier repository.
Donc un exemple de valeur correcte serai `/home/[user]/.m2/repository`
Aucune idée de la valeur correcte sous windows, je sais juste que le nom du repertoire est aussi repository.

Enfin, comme dirait Nedjar : `Un développeur n'ayant pas un Unix sur sa machine ne peut pas être raisonnablement considéré comme un vrai développeur.`
Vous pouvez alors ouvrir la classe qui contient le main et lancer le programme.

Contribuer
----------

Lors de vos commits, ne commitez que les fichiers que vous modifiez, les .java, pas de .class ou autre merdes d'eclipse.

Vous n'avez pas les droits d'acces sur ce repot, vous les obtiendrez quand vous vous montrerez a peu pres capable d'utiliser git.

Contentez vous de fork ce repot (le bouton en haut), et de faire un `git clone [url de votre fork]` dans un terminal

Vous aurez alors un dossier projet-java dans le dossier ou vous avez effectuer la commande.
Vous pouvez taper votre code, cool.
Configurez votre git
`git config --global user.name [username de github]`
`git config --global user.email [email de github]`
Quand vous avez fini, vous allez commit votre code.
`git add [fichiers modifiés]`
`git commit -m ["Description des modification apportées"]`
`git push`
Entrez votre mot de passe et votre code est sur votre fork.
Ensuite allez sur github et appuyez sur le bouton vert, pour faire une "pull request" (PR)
Une pull request est une demande a ce que votre code soit incorporé (merge) dans la base existante.

L'avantage, c'est que on peut eviter que du code non-fonctionnel se retrouve dans le repo principal.
Lorsque vous ferez votre pull request, vous verrez des icones qui indique un status, c'est Travis qui fait sont boulot.
Il compile et lance les test unitaire sur le code qui resultera du merge, pour confirmer que le code est fonctionnel.
Vous pouvez voir en haut de ce readme une petite image qui montre l'etat du build.


*Note:* Le java permet les nom de variable et de classe accentué, genre Véhicule.

Résumé
------

Classes:

<table  cellspacing="0" cellpadding="6" >


<colgroup>
<col   />

<col   />

<col   />
</colgroup>
<thead>
<tr>
<th scope="col" >Parking</th>
<th scope="col" >Singleton</th>
<th scope="col" >Description</th>
</tr>
</thead>

<tbody>
<tr>
<td >instance</td>
<td >Parking</td>
<td >&#xa0;</td>
</tr>


<tr>
<td >getInstance</td>
<td >Parking</td>
<td >Renvoie l'instance unique du parking</td>
</tr>


<tr>
<td >places</td>
<td >ArrayList<Place></td>
<td >Contient les places du parking</td>
</tr>


<tr>
<td >vehiculeExiste(Vehicule v)</td>
<td >bool</td>
<td >Renvois true si le vehicule est stationé</td>
</tr>


<tr>
<td >park(Vehicule v)</td>
<td >void, throws</td>
<td >Gare un vehicule</td>
</tr>


<tr>
<td >park(Vehicule v, int place)</td>
<td >void, throws</td>
<td >Pareil mais a une place donnée</td>
</tr>


<tr>
<td >unpark(int place)</td>
<td >Vehicule</td>
<td >Enleve un véhicule</td>
</tr>


<tr>
<td >etatParking</td>
<td >void</td>
<td >Affiche l'état du parking(Places, et infos sur les voiture stationnée)</td>
</tr>


<tr>
<td >bookPlace</td>
<td >Place</td>
<td >Renvois la premiere place libre trouvée</td>
</tr>


<tr>
<td >bookPlace(int)</td>
<td >Place</td>
<td >Renvois une place si celle a l'indice est vide</td>
</tr>


<tr>
<td >freePlace()</td>
<td >void</td>
<td >throw. Libere une place reservee</td>
</tr>


<tr>
<td >getLocation(String immat)</td>
<td >int</td>
<td >Renvoie le num de la place d'une voiture selon son immat</td>
</tr>


<tr>
<td >retirerVehicule(immat)</td>
<td >Voiture</td>
<td >Retire une voiture de sa place et la renvoie, null si non trouvé</td>
</tr>


<tr>
<td >reorganiserPlace</td>
<td >void</td>
<td >Deplace les vehicules sur les place transporteur si une place normal est liberée</td>
</tr>
</tbody>
</table>

<table  cellspacing="0" cellpadding="6" >


<colgroup>
<col   />

<col   />

<col   />
</colgroup>
<thead>
<tr>
<th scope="col" >Abstraite: Place</th>
<th scope="col" >Description</th>
<th scope="col" >&#xa0;</th>
</tr>
</thead>

<tbody>
<tr>
<td >Place</td>
<td >Contructeur privé</td>
<td >&#xa0;</td>
</tr>


<tr>
<td >bool reserve</td>
<td >vrai si la place est reservée</td>
<td >&#xa0;</td>
</tr>


<tr>
<td >reserver(bool)</td>
<td >void</td>
<td >Reserve la place throw</td>
</tr>


<tr>
<td >liberer()</td>
<td >void</td>
<td >libere la place, throw</td>
</tr>


<tr>
<td >Abstraite: park(ITransporteur)</td>
<td >Gare un vehicule transporteur</td>
<td >&#xa0;</td>
</tr>


<tr>
<td >park(IParticulier)</td>
<td >Gare un vehicule particulier</td>
<td >&#xa0;</td>
</tr>
</tbody>
</table>

park(IParticulier) n'a pas besoin d'etre abstraite car on sait que
quoi qu'il arrive, un vehicule particulier peut se garer sur n'importe
quel type de place

<table  cellspacing="0" cellpadding="6" >


<colgroup>
<col   />

<col   />

<col   />
</colgroup>
<thead>
<tr>
<th scope="col" >PlaceTransporteur</th>
<th scope="col" >Place</th>
<th scope="col" >Description</th>
</tr>
</thead>

<tbody>
<tr>
<td >park(ITransporteur)</td>
<td >gare un transporteur</td>
<td >&#xa0;</td>
</tr>
</tbody>
</table>

<table  cellspacing="0" cellpadding="6" >


<colgroup>
<col   />

<col   />

<col   />
</colgroup>
<thead>
<tr>
<th scope="col" >PlaceParticulier</th>
<th scope="col" >Place</th>
<th scope="col" >Description</th>
</tr>
</thead>

<tbody>
<tr>
<td >park(ITransporteur)</td>
<td >Throw, on ne peut pas garer un transporteur ici !</td>
<td >&#xa0;</td>
</tr>
</tbody>
</table>

<table  cellspacing="0" cellpadding="6" >


<colgroup>
<col   />
</colgroup>
<tbody>
<tr>
<td >Abstraite: Vehicule</td>
</tr>


<tr>
<td >String immatricule</td>
</tr>


<tr>
<td >String modele</td>
</tr>


<tr>
<td >String marque</td>
</tr>


<tr>
<td >String Proprietaire</td>
</tr>
</tbody>
</table>

<table  cellspacing="0" cellpadding="6" >


<colgroup>
<col   />

<col   />

<col   />
</colgroup>
<tbody>
<tr>
<td >Voiture</td>
<td >Vehicule</td>
<td >implémente IParticulier</td>
</tr>


<tr>
<td >todo: Trouver des caracteristique de voiture</td>
<td >&#xa0;</td>
</tr>
</tbody>
</table>

<table  cellspacing="0" cellpadding="6" >


<colgroup>
<col   />

<col   />

<col   />
</colgroup>
<tbody>
<tr>
<td >Moto</td>
<td >Vehicule</td>
<td >implémente IParticulier</td>
</tr>


<tr>
<td >todo: Trouver des caracteristique de moto</td>
<td >&#xa0;</td>
<td >&#xa0;</td>
</tr>
</tbody>
</table>

<table  cellspacing="0" cellpadding="6" >


<colgroup>
<col   />

<col   />

<col   />
</colgroup>
<tbody>
<tr>
<td >Camion</td>
<td >Vehicule</td>
<td >implémente ITranporteur</td>
</tr>


<tr>
<td >todo: Trouver des caracteristique de camion</td>
<td >&#xa0;</td>
<td >&#xa0;</td>
</tr>
</tbody>
</table>

Interfaces:

<table  cellspacing="0" cellpadding="6" >


<colgroup>
<col   />

<col   />
</colgroup>
<tbody>
<tr>
<td >ITransporteur</td>
<td >Interface vide qui garanti qu'un vehicule est transporteur</td>
</tr>


<tr>
<td >IParticulier</td>
<td >Interface vide qui garanti qu'un vehicule est Particulier</td>
</tr>
</tbody>
</table>

On verra plus tard pour l'interface graphique(swing/AWT) et les factures(serialisable).

