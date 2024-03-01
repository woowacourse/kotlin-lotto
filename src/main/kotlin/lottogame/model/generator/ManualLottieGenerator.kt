package lottogame.model.generator

import lottogame.model.Lotto
import lottogame.model.LottoNumber
import lottogame.model.LottoResult

class DefaultManualLottieGenerator : ManualLottieGenerator {
    override fun generate(lottieNumbers: List<List<LottoNumber>>): List<LottoResult> {
        return lottieNumbers.map { Lotto.createLottoResult(it) }
    }
}

fun interface ManualLottieGenerator {
    fun generate(lottieNumbers: List<List<LottoNumber>>): List<LottoResult>
}
