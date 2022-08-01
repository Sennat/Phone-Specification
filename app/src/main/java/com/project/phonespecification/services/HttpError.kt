package com.project.phonespecification.services

sealed class HttpError(val code: Int) {
    object Unauthorized: HttpError(401)
    object BadRequest: HttpError(400)
    object NotFound: HttpError(404)
    object ServerError: HttpError(500)
}