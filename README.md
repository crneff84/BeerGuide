# BeerGuide

This program allows users to enter a location and find breweries near that location. It also allows users to search for beers and breweries. The searched items will eventually be clickable and return relevant information on that brewery/beer via an API request from BreweryDB.

## Specifications

##### App collects user's location

* Example Input: Location
* Example Output: Location

##### App adds user input to favorites list

* Example Input: Beer/brewery
* Example Output: Beer/brewery

## Known Bugs

The app is currently not integrated with a database. The "locate breweries" button currently only places a marker on the map based on the user's input. Eventually, the program will populate the map with nearby breweries using an API call.

The Beer list also only has placeholder tags for attributes other than "name". This will be resolved once I figure out a JSON parsing issue.

## Prerequisites

This app requires Android 4.0.3 or higher.
