package model

class DefaultLottoMachine(
    private val lottoPrice: Money,
    private val autoLottieGenerator: AutoLottieGenerator,
    private val manualLottieGenerator: ManualLottieGenerator,
) : LottoMachine {
    override fun generateAutoLottie(cost: Money): List<Lotto> {
        return autoLottieGenerator.generate(cost)
    }

    override fun generateManualLottie(lottieNumbers: List<List<Int>>): List<Lotto> {
        return manualLottieGenerator.generate(lottieNumbers)
    }

    override fun canGenerate(
        count: LottoCount,
        cost: Money,
    ) = (cost >= (lottoPrice * count.amount))
}

interface LottoMachine {
    fun generateAutoLottie(cost: Money): List<Lotto>

    fun generateManualLottie(lottieNumbers: List<List<Int>>): List<Lotto>

    fun canGenerate(
        count: LottoCount,
        cost: Money,
    ): Boolean
}
