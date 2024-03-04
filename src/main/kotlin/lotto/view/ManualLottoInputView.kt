package lotto.view

import lotto.model.Lotto

object ManualLottoInputView {
    private const val SPLIT_DELIMITER = ","
    private const val ERROR_PREFIX = "[ERROR] "
    private const val ERROR_EMPTY_INPUT_MESSAGE = "${ERROR_PREFIX}입력값이 없습니다."

    fun readManualLottoNumbers(manualLottoCount: Int): List<Lotto> {
        println("\n수동으로 구매할 번호를 입력해 주세요.")
        val manualLotto = mutableListOf<Lotto>()
        repeat(manualLottoCount) {
            val lottoNumbers =
                validateNullInput(readlnOrNull()).replace(" ", "").split(SPLIT_DELIMITER).map(String::trim)
            manualLotto.add(Lotto.lottoNumbersOf(lottoNumbers))
        }
        return manualLotto.toList()
    }

    private fun validateNullInput(input: String?): String {
        return input ?: throw IllegalArgumentException(ERROR_EMPTY_INPUT_MESSAGE)
    }
}
