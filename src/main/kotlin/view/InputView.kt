package view

class InputView {
    fun inputMoney(): Int? {
        OutputView().outputMoneyMessage()
        return getInputMoney(readlnOrNull())
    }

    private fun getInputMoney(input: String?): Int? {
        return runCatching {
            require(!input.isNullOrBlank()) { INPUT_VALUE_ERROR_MESSAGE }
            input!!.toIntOrNull() ?: throw IllegalArgumentException(INPUT_VALUE_NOT_INT_ERROR_MESSAGE)
        }.onFailure { OutputView().outputErrorMessage(it.message!!) }
            .getOrNull() ?: inputMoney()
    }

    fun inputWinningLotto(): IntArray {
        OutputView().outputWinningLottoMessage()
        return getInputWinningLotto(readlnOrNull())
    }

    private fun getInputWinningLotto(input: String?): IntArray {
        return runCatching {
            require(!input.isNullOrBlank()) { INPUT_VALUE_ERROR_MESSAGE }
            input.split(",").map { it.trim().toInt() }.toIntArray()
        }.onFailure { OutputView().outputErrorMessage(it.message!!) }
            .getOrNull() ?: inputWinningLotto()
    }

    fun inputBonusNumber(): Int {
        OutputView().outputBonusNumberMessage()
        return getInputBonusNumber(readlnOrNull())
    }

    private fun getInputBonusNumber(input: String?): Int {
        return runCatching {
            require(!input.isNullOrBlank()) { INPUT_VALUE_ERROR_MESSAGE }
            input.toInt()
        }.onFailure { OutputView().outputErrorMessage(it.message!!) }
            .getOrNull() ?: inputBonusNumber()
    }

    fun inputManualLottoCount(): Int? {
        OutputView().outputManualLottoCountMessage()
        return getInputManualLottoCount(readlnOrNull())
    }

    private fun getInputManualLottoCount(input: String?): Int? {
        return runCatching {
            require(!input.isNullOrBlank()) { INPUT_VALUE_ERROR_MESSAGE }
            input.toInt()
        }.onFailure { OutputView().outputErrorMessage(it.message!!) }
            .getOrNull() ?: inputManualLottoCount()
    }
    companion object {
        const val INPUT_RIGHT_VALUE_ERROR_MESSAGE = "올바른 값이 입력되지 않았습니다."
        const val INPUT_VALUE_ERROR_MESSAGE = "값이 입력되지 않았습니다."
        const val INPUT_VALUE_NOT_INT_ERROR_MESSAGE = "숫자를 입력하세요."
    }
}
