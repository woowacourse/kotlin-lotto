package lotto.utils

private const val ERROR_MESSAGE = "[ERROR]"

fun <T> retryWhileNoException(action: () -> T) =
    try {
        action()
    } catch (e: IllegalArgumentException) {
        println("$ERROR_MESSAGE ${e.localizedMessage}")
        action()
    }
