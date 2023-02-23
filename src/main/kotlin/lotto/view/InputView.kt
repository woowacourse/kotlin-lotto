package lotto.view

object InputView {
    fun getNumber(): Int? {
        val input = readlnOrNull()?.trim()
        return if (input?.isNumber() == true) input.toIntOrNull() else null
    }

    fun getNumberList(): List<Int?>? {
        val input = readlnOrNull()?.trim()?.split(",")
        return if (input?.isNumbers() == true)
            input.map { it.trim().toIntOrNull() } else null
    }

    private fun String.isNumber() = this.chars().allMatch { Character.isDigit(it) }
    private fun List<String>.isNumbers() = !this.any { !it.trim().isNumber() || it.trim().isBlank() }
}
