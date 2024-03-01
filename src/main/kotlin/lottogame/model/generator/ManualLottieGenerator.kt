package lottogame.model.generator

import lottogame.model.Lotto
import lottogame.model.LottoNumber

class DefaultManualLottieGenerator : ManualLottieGenerator {
    override fun generate(lottieNumbers: List<List<LottoNumber>>): List<Lotto> {
        return lottieNumbers.map { Lotto(it) }
    }
}

fun interface ManualLottieGenerator {
    fun generate(lottieNumbers: List<List<LottoNumber>>): List<Lotto>
}
