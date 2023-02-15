package lotto.view

class InputView {
    fun getNumber(): String {
        return runCatching {
            getNotNullInput()
        }.onFailure {
            println(it.message)
        }.getOrNull() ?: getNumber()
    }

    private fun getNotNullInput(): String {
        val input = readLine()?.trim()
        require(!input.isNullOrBlank()) { ERROR_NULL_OR_BLANK }
        return input
    }
}
