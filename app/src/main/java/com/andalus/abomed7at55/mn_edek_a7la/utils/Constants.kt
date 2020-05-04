package com.andalus.abomed7at55.mn_edek_a7la.utils

object Constants {

    //DB
    const val DATABASE_NAME = "Food.db"
    const val VERSION_KEY = "version_key"

    //RecipeTable
    const val COLUMN_ID = "id"
    const val COLUMN_TITLE = "title"
    const val COLUMN_INGREDIENTS = "ingredients"
    const val COLUMN_STEPS = "steps"
    const val COLUMN_CATEGORY = "category"
    const val COLUMN_PHOTO_LINK = "photo_link"
    const val COLUMN_VIDEO_LINK = "video_link"

    //Categories
    const val CATEGORY_KEY = "category"
    const val RECENT = "recent"
    const val MEAT = "meat"
    const val FISH = "fish"
    const val SWEET = "sweet"
    const val DRINK = "drink"
    const val STARCHES = "starches"
    const val APPETIZER = "appetizer"
    const val IDEA = "idea"
    const val DIET = "diet"
    const val BAKERY = "bakery"
    const val MILK = "milk"

    //prefs
    const val METADATA_FILE_NAME = "meta-data"
    const val FAVORITE_PREFS_FILE_NAME = "favorite-recipes"
    const val LATER_PREFS_FILE_NAME = "later-recipes"
    const val DUMB_PREFS_FILE = "dumb"

    //services
    const val FILE_SERVICE = "file-service"

    //keys
    const val RECIPE_ID_KEY = "recipe-id"
    const val SOURCE_KEY = "source-key"
    const val SOURCE_FAVORITE = "source-favorite"
    const val SEARCH_KEYWORD = "search-keyword"

    //others
    const val STRING_RESOURCE = "string"

    //recipesAdapter
    const val SIZE_SMALL = "small"
    const val SIZE_LARGE = "large"

    //IntentActions
    const val ACTION_DATABASE_SERVICE_COMPLETE = "com.andalus.abomed7at55.mn_edek_a7la-database-copied"

    //Networking
    const val BASE_URL = "https://men-edek-a7la.herokuapp.com/recipes/"

    //links
    const val YOUTUBE_SUBSCRIPTION_LINK = "https://www.youtube.com/channel/UCck1oeRJ5a--yWmwFB2aoFQ?sub_confirmation=1"
    const val FACEBOOK_PAGE_LINK = "https://www.facebook.com/MenEdikAhla/"
    const val FACEBOOK_GROUP_LINK = "https://www.facebook.com/groups/MenEdikAhla/"
    const val REVIEW_LINK = "market://details?id=com.andalus.abomed7at55.mn_edek_a7la"
    const val APP_LINK = "https://play.google.com/store/apps/details?id=com.andalus.abomed7at55.mn_edek_a7la"
}