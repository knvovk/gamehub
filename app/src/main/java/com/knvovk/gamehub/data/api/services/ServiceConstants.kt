package com.knvovk.gamehub.data.api.services

// Retrofit configuration
const val BASE_URL = "https://api.rawg.io/api/"
const val USER_AGENT = "GameHub Mobile App"

// Paths
const val GAMES = "games"

// Queries
const val DATES = "dates"
const val ORDERING = "ordering"
const val PAGE = "page"

//page
//integer
//A page number within the paginated result set.
//
//page_size
//integer
//Number of results to return per page.
//
//search
//string
//Search query.
//
//parent_platforms
//string
//Filter by parent platforms, for example: 1,2,3.
//
//platforms
//string
//Filter by platforms, for example: 4,5.
//
//stores
//string
//Filter by stores, for example: 5,6.
//
//developers
//string
//Filter by developers, for example: 1612,18893 or valve-software,feral-interactive.
//
//publishers
//string
//Filter by publishers, for example: 354,20987 or electronic-arts,microsoft-studios.
//
//genres
//string
//Filter by genres, for example: 4,51 or action,indie.
//
//tags
//string
//Filter by tags, for example: 31,7 or singleplayer,multiplayer.
//
//creators
//string
//Filter by creators, for example: 78,28 or cris-velasco,mike-morasky.
//
//dates
//string
//Filter by a release date, for example: 2010-01-01,2018-12-31.1960-01-01,1969-12-31.
//
//platforms_count
//integer
//Filter by platforms count, for example: 1.
//
//exclude_collection
//integer
//Exclude games from a particular collection, for example: 123.
//
//exclude_additions
//boolean
//Exclude additions.
//
//exclude_parents
//boolean
//Exclude games which have additions.
//
//exclude_game_series
//boolean
//Exclude games which included in a game series.
//
//ordering
//string
//Available fields: name, released, added, created, rating. You can reverse the sort order adding a hyphen, for example: -released.

// Fields
const val ADDED_ASC = "added"
const val ADDED_DESC = "-added"
const val CREATED_ASC = "created"
const val CREATED_DESC = "-created"
const val NAME_ASC = "name"
const val NAME_DESC = "-name"
const val RATING_ASC = "rating"
const val RATING_DESC = "-rating"
const val RELEASED_ASC = "released"
const val RELEASED_DESC = "-released"