import kotlin.IllegalArgumentException

object ExceptionHandler {
    private const val ERROR_INVALID_INPUT = "입력이 잘못되었습니다, 다시 입력해주세요."

    fun <T> handleInputValue(action: () -> T): T =
        runCatching(action).onFailure {
            if (it.message.isNullOrEmpty()) {
                println(it.message)
            } else {
                println(ERROR_INVALID_INPUT)
            }
            return handleInputException(action, it) ?: return@onFailure
        }.getOrThrow()

    private fun <T, E> handleInputException(
        action: () -> T,
        exception: E,
    ): T? {
        if (exception is IllegalArgumentException) return handleInputValue(action)
        return null
    }
}
