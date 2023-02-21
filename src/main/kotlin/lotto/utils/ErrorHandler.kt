package lotto.utils

fun <T> inputErrorHandler(
    error: Throwable,
    repeatFunction: () -> T,
): T {
    return when (error) {
        is IllegalArgumentException -> inputIllegalArgumentExceptionHandler(error, repeatFunction)
        else -> throw IllegalStateException(ERROR_INPUT_HANDLER)
    }
}

private fun <T> inputIllegalArgumentExceptionHandler(
    error: Throwable,
    repeatFunction: () -> T,
): T {
    println(error.message)
    return repeatFunction()
}

const val ERROR_INPUT_HANDLER = "값 전달 과정에 상태 오류가 발생했습니다."
