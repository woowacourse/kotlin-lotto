package lotto.view

class InputView {
    fun getNumber(): String {
        return runCatching {
            getNotNullInput()
        }.onFailure {
            println(it.message)
        }.getOrNull() ?: getNumber()
    }

    fun getNumberList(): List<String> {
        return runCatching {
            getNotNullInput().split(",").map { it.trim() }
        }.onFailure {
            println(it.message)
        }.getOrNull() ?: getNumberList()
    }

    private fun getNotNullInput(): String {
        val input = readLine()?.trim()
        require(!input.isNullOrBlank()) { ERROR_NULL_OR_BLANK }
        return input
    }
}
