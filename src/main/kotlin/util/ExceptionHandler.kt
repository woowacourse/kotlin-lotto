package util

interface ExceptionHandler {
    fun <T> handleInputValue(action: () -> T): T

    fun handleOutputValue(action: () -> Unit)
}
