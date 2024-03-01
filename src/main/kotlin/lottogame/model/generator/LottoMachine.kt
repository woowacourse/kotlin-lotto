package lottogame.model.generator

import lottogame.model.Lotto
import lottogame.model.LottoNumber
import lottogame.model.Money

class DefaultLottoMachine(
    private val autoLottieGenerator: AutoLottieGenerator,
    private val manualLottieGenerator: ManualLottieGenerator,
) : LottoMachine {
    override fun generateAutoLottie(cost: Money): List<Lotto> {
        return autoLottieGenerator.generate(cost)
    }

    override fun generateManualLottie(lottieNumbers: List<List<LottoNumber>>): List<Lotto> {
        return manualLottieGenerator.generate(lottieNumbers)
    }
}

interface LottoMachine {
    fun generateAutoLottie(cost: Money): List<Lotto>

    fun generateManualLottie(lottieNumbers: List<List<LottoNumber>>): List<Lotto>
}
