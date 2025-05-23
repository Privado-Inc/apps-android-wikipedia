package org.wikipedia.login

import org.wikipedia.dataclient.WikiSite

internal class LoginEmailAuthResult(site: WikiSite, status: String, userName: String?, password: String?,
    message: String?) : LoginResult(site, status, userName, password, message)
