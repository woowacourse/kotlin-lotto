package lotto.utils

fun <T> illegalArgumentExceptionHandler(
    error: Throwable,
    function: () -> T,
): T {
    println(error.message)
    return function()
}

fun <T> inputNullHandler(
    message: String,
    value: T,
): T {
    println(message)
    return value
}
