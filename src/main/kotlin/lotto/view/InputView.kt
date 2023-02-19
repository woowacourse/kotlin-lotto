package lotto.view

class InputView {
    fun getNumber(printGuideMessage: () -> Unit): Int {
        printGuideMessage()
        val input = getNotNullValue()
        return if (input.isNumber()) input.toInt() else getNumber { printGuideMessage() }
    }

    fun getNumberList(printGuideMessage: () -> Unit): List<Int> {
        printGuideMessage()
        val input = getNotNullValue()
        val values = input.split(",")
        return if (values.isNumbers()) return values.map { it.trim().toInt() } else getNumberList { printGuideMessage() }
    }

    private fun getNotNullValue(): String {
        val input = readlnOrNull()?.trim()
        return if (!input.isNullOrBlank()) input else getNotNullValue()
    }

    private fun String.isNumber() = this.chars().allMatch { Character.isDigit(it) }
    private fun List<String>.isNumbers(): Boolean {
        this.forEach {
            if (!it.trim().isNumber() || it.trim().isBlank()) return false
        }
        return true
    }
}
