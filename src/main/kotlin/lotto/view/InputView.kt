package lotto.view

class InputView {
    fun getSingleNumber(): Int = getInput { it.toInt() }

    fun getMultipleNumber(): List<Int> =
        getInput {
            it.split(",").map { num -> num.trim().toInt() }
        }

    private fun <T> getInput(transform: (String) -> T): T {
        val input = readln().also { validateInput(it) }
        return transform(input)
    }

    private fun validateInput(input: String) {
        require(input.trim().isNotEmpty()) { "[ERROR] 입력이 존재하지 않습니다." }
        input.split(",").forEach { require(it.trim().toIntOrNull() != null) { "[ERROR] 입력이 정수가 아닙니다: $it" } }
    }
}
