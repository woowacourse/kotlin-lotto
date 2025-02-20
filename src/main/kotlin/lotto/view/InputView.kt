package lotto.view

class InputView {
    fun getSingleNumber(): Int {
        val input = readln()
        validateSingleInput(input)
        return input.toInt()
    }

    fun getMultipleNumber(): List<Int> {
        val input = readln()
        validateMultipleInput(input)
        return input.split(",").map { it.trim().toInt() }
    }

    private fun validateSingleInput(input: String) {
        validateEmptyInput(input)
        validateNumber(input)
    }

    private fun validateMultipleInput(input: String) {
        validateEmptyInput(input)
        input.split(",").forEach { validateNumber(it.trim()) }
    }

    private fun validateEmptyInput(input: String) {
        require(input.trim().isNotEmpty()) {
            "[ERROR] 입력이 존재하지 않습니다."
        }
    }

    private fun validateNumber(input: String) {
        require(input.toIntOrNull() != null) {
            "[ERROR] 입력이 정수가 아닙니다: $input"
        }
    }
}
