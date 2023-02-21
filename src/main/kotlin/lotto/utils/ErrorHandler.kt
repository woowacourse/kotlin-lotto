package lotto.utils

fun <T> illegalArgumentExceptionHandler(
    error: Throwable,
    repeatFunction: () -> T,
): T {
    println(error.message)
    return repeatFunction()
}

fun <T> inputNullHandler(
    message: String,
    repeatFunction: T,
): T {
    println(message)
    return repeatFunction
}
