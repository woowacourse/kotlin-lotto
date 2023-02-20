package lotto.view

import lotto.model.LottoNumber

class InputView {
    fun getNumber(): Int {
        val input = readlnOrNull()?.trim()
        val result = getInput(input!!.isNumber(), input)

        return if (result) input.toInt() else getNumber()
    }

    fun getNumberList(): List<LottoNumber> {
        val input = readlnOrNull()?.trim()
        val result = getInput(input!!.split(",").isNumbers(), input)

        return if (result) input.split(",").map { LottoNumber(it.trim().toInt()) } else getNumberList()
    }

    private fun getInput(checkIsNumber: Boolean, input: String): Boolean {
        val result = validateInput {
            require(input.isNotBlank())
            require(checkIsNumber) { println(ERROR_NOT_POSITIVE_NUMBER) }
        }
        return result
    }

    private fun validateInput(validate: () -> Unit): Boolean {
        return runCatching {
            validate()
        }.isSuccess
    }

    private fun String.isNumber() = this.chars().allMatch { Character.isDigit(it) }
    private fun List<String>.isNumbers(): Boolean {
        this.forEach {
            if (!it.trim().isNumber() || it.trim().isBlank()) return false
        }
        return true
    }
}
