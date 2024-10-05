# Capteur d'Humidité et Température Connecté

## Description
Le **Capteur d'Humidité et Température Connecté** est un dispositif innovant conçu pour mesurer et transmettre en temps réel la température et l'humidité de l'environnement dans lequel il est installé. Ce système allie performance et économie, offrant une solution efficace pour surveiller les conditions ambiantes.

## Fonctionnalités
- **Mesure Précise** : Utilisation de capteurs de qualité tels que LM35, DHT11 et TCN75A pour assurer des mesures fiables.
- **Connectivité Cloud** : Intégration avec Google Firestore pour un stockage et une accessibilité faciles des données à distance.
- **Alimentation Flexible** : Fonctionne avec une alimentation de 5V ou 3.3V, adapté à diverses applications.
- **Interface Utilisateur** : Envoi des données directement à l'utilisateur pour une surveillance en temps réel.


https://github.com/user-attachments/assets/062273b5-16ea-417a-9a7e-316a08d5dbec


## Matériel Utilisé
- **Carte de Contrôle** : Arduino UNO, choisie pour sa disponibilité et sa compatibilité.
- **Plateforme de Traitement** : Raspberry Pi 3B+, permettant l'utilisation de l'OS Raspbian pour la gestion des données.
- **Capteurs** : 
  - **LM35** : Pour des mesures de température.
  - **DHT11** : Pour la mesure de l'humidité.
  - **TCN75A** : Pour des mesures de température additionnelles.

  
## Architecture du Système
Le système fonctionne comme suit :
- Les capteurs mesurent la température et l'humidité et transmettent les données à la Raspberry Pi.
- La Raspberry Pi, en tant que nœud principal, traite les données et les envoie à la base de données Google Firestore.
- L'utilisateur peut accéder aux données en temps réel via une interface conviviale.

## Avantages
- **Coût Réduit** : La solution est économique grâce à l'utilisation de composants accessibles.
- **Simplicité d'Intégration** : Facile à mettre en place et à configurer grâce à l'utilisation de Raspbian et Google Firestore.
- **Polyvalence** : Peut être utilisé dans divers environnements, tels que les maisons, les serres, ou les bureaux.

