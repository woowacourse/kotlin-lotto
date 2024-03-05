package lotto.view

import lotto.model.Lotto

object ManualLottoInputView {
    private const val SPLIT_DELIMITER = ","
    private const val ERROR_PREFIX = "[ERROR] "
    private const val ERROR_EMPTY_INPUT_MESSAGE = "입력값이 없습니다."

    fun readManualLottoNumbers(manualLottoCount: Int): List<Lotto> {
        println("\n수동으로 구매할 번호를 입력해 주세요.")
        val manualLotto = mutableListOf<Lotto>()
        repeat(manualLottoCount) {
            val lottoNumbers = readValidLottoNumbers()
            runCatching {
                Lotto.lottoNumbersOf(lottoNumbers)
            }.onFailure { e ->
                println("${ERROR_PREFIX}${e.message} \n다시 처음부터 입력해주세요")
                return readManualLottoNumbers(manualLottoCount)
            }
            manualLotto.add(Lotto.lottoNumbersOf(lottoNumbers))
        }
        return manualLotto.toList()
    }

    private fun readValidLottoNumbers(): List<String> {
        while (true) {
            val inputResult =
                runCatching {
                    val input = validateNullInput(readlnOrNull())
                    input.replace(" ", "").split(SPLIT_DELIMITER).map(String::trim)
                }
            inputResult.onFailure { e ->
                println("${ERROR_PREFIX}${e.message}")
            }
            inputResult.onSuccess { return it }
        }
    }

    private fun validateNullInput(input: String?): String {
        require(!(input.isNullOrBlank())) { ERROR_EMPTY_INPUT_MESSAGE }
        return input
    }
}
