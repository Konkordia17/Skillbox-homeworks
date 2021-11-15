package com.skillbox.github.data

import net.openid.appauth.ResponseTypeValues

object AuthConfig {

    const val AUTH_URI = "https://github.com/login/oauth/authorize"
    const val TOKEN_URI = "https://github.com/login/oauth/access_token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "user,repo"

    const val CLIENT_ID = "48bdd903c5a244cf3257"
    const val CLIENT_SECRET = "de4a52a21a97d61d87919e5961b5e0261386856d"
    const val CALLBACK_URL = "skillbox://go.skillbox.ru//callback"
}