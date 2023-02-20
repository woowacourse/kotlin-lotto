package view

import domain.LottoNumber

class InputView {
    fun inputMoney(): Int {
        OutputView().outputMoneyMessage()
        return getInputMoney(readlnOrNull())
    }

    private fun getInputMoney(input: String?): Int {
        return runCatching {
            require(!input.isNullOrBlank()) { INPUT_VALUE_ERROR_MESSAGE }
            input!!.toInt()
        }.onFailure { OutputView().outputErrorMessage(it.message!!) }
            .getOrThrow()
    }

    fun inputWinningLotto(): List<LottoNumber> {
        OutputView().outputWinningLottoMessage()
        return getInputWinningLotto(readlnOrNull())
    }

    private fun getInputWinningLotto(input: String?): List<LottoNumber> {
        return runCatching {
            require(!input.isNullOrBlank()) { INPUT_VALUE_ERROR_MESSAGE }
            input!!.split(",").map { LottoNumber.from(it.trim().toInt()) }.sortedBy { it.number }
        }.onFailure { OutputView().outputErrorMessage(it.message!!) }
            .getOrThrow()
    }

    fun inputBonusNumber(): Int {
        OutputView().outputBonusNumberMessage()
        return getInputBonusNumber(readlnOrNull())
    }

    private fun getInputBonusNumber(input: String?): Int {
        return runCatching {
            require(!input.isNullOrBlank()) { INPUT_VALUE_ERROR_MESSAGE }
            input!!.toInt()
        }.onFailure { OutputView().outputErrorMessage(it.message!!) }
            .getOrThrow()
    }

    companion object {
        const val INPUT_VALUE_ERROR_MESSAGE = "값이 입력되지 않았습니다."
    }
}
