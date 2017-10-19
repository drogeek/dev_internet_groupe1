Pour l'installation de SonarQube (en Local):
- Télécharger sonarqube 6.5
- Extrait l'archive dans /etc/
- Lancer sonar avec la commande suivante:
/etc/sonarqube/bin/[OS]/sonar.sh console
- Se connecter à l'URL http://localhost:9000/users avec les identifiants admin et le mot de passe "admin"
- Créer un token et le copier (on ne peut plus le voir après)

Pour ajouter SonarQube à IntelliJ:
- Aller Settings/ Plug-In
- Ajouter le plugIn SonarLint
- Dans settings/SonarLint General Settings:
  - Choisir SonarQube
  - Ajouter le serveur avec l'URL précédemment utiliser
  - Saisir ensuite le token généré précédément

