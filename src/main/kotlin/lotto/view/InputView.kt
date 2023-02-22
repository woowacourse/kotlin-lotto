package lotto.view

object InputView {
    fun getNumber(): Int? {
        val input = readlnOrNull()?.trim()
        return if (input?.isNumber() == true) input.toIntOrNull() else null
    }

    fun getNumberList(): List<Int>? {
        val input = readlnOrNull()?.trim()?.split(",")
        val result = input?.map { it.trim().toIntOrNull() }
        return if (input?.isNumbers() == true && result?.contains(null) == false)
            input.map { it.trim().toInt() } else null
    }

    private fun String.isNumber() = this.chars().allMatch { Character.isDigit(it) }
    private fun List<String>.isNumbers() = !this.any { !it.trim().isNumber() || it.trim().isBlank() }
}
