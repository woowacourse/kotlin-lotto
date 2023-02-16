package lotto.view

import lotto.model.LottoNumber

class InputView {
    fun getNumber(): Int {
        val input = readlnOrNull()?.trim()
        val result = validateInput {
            require(!input.isNullOrBlank()) { ERROR_NULL_OR_BLANK }
            require(input.isNumber()) { ERROR_NOT_POSITIVE_NUMBER }
        }
        return if (result) input!!.toInt() else getNumber()
    }

    fun getNumberList(): List<LottoNumber> {
        val input = readlnOrNull()?.trim()
        val result = validateInput {
            require(!input.isNullOrBlank()) { ERROR_NULL_OR_BLANK }
            require(input.split(",").isNumbers()) { ERROR_NOT_POSITIVE_NUMBER }
        }
        return if (result) input!!.split(",").map { LottoNumber(it.trim().toInt()) } else getNumberList()
    }

    private fun validateInput(validate: () -> Unit): Boolean {
        return runCatching {
            validate()
        }.onFailure {
            println(it.message)
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
