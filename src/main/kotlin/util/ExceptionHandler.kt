package util

class ExceptionHandler {
    fun <T> handleInputValue(action: () -> T): T =
        runCatching(action).onFailure {
            println(it.message)
            return handleInputException(action, it) ?: return@onFailure
        }.getOrThrow()

    fun handleOutputValue(action: () -> Unit) {
        runCatching(action).onFailure { handleOutputException(action, it) }
    }

    private fun <T, E> handleInputException(
        action: () -> T,
        exception: E,
    ): T? {
        if (exception is IllegalArgumentException) return handleInputValue(action)
        return null
    }

    private fun <E> handleOutputException(
        action: () -> Unit,
        exception: E,
    ) {
        if (exception is IllegalArgumentException) return handleInputValue(action)
    }
}
