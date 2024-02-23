package lotto.utils

import lotto.constants.StringConstants

fun <T> retryWhileNoException(action: () -> T) =
    try {
        action()
    } catch (e: IllegalArgumentException) {
        println("${StringConstants.ERROR_MESSAGE} ${e.localizedMessage}")
        action()
    }
