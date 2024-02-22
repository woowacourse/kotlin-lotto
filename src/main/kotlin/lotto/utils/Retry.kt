package lotto.utils

import lotto.constants.StringConstants

fun <T> retryWhileNoException(action: () -> T): T {
    return try {
        action()
    } catch (e: IllegalArgumentException) {
        println("${StringConstants.ERROR_MESSAGE} ${e.localizedMessage}")
        action()
    }
}
