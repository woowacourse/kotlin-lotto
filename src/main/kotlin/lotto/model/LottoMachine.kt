package lotto.model

import lotto.util.NumbersGenerator

object LottoMachine {
    fun issueLotto(
        lottoCount: LottoCount,
        manualNumbers: List<Set<Int>>,
        lottoNumbersGenerator: NumbersGenerator,
    ): List<Lotto> {
        val manualLottoTickets = generateManualLotto(lottoCount.numberOfManualLotto, manualNumbers)
        val autoLotto = generateAutoLotto(lottoCount.getNumberOfAutoTickets(), lottoNumbersGenerator)

        return manualLottoTickets.plus(autoLotto)
    }

    private fun generateLotto(numbers: List<Set<Int>>): List<Lotto> =
        numbers.map { number -> Lotto(number.mapNotNull { LottoNumber.valueOf(it) }.toSet()) }

    private fun generateManualLotto(
        count: Int,
        manualNumbers: List<Set<Int>>,
    ): List<Lotto> = if (count > 0) generateLotto(manualNumbers) else emptyList()

    private fun generateAutoLotto(
        count: Int,
        lottoNumbersGenerator: NumbersGenerator,
    ): List<Lotto> {
        val autoNumbers =
            List(count) {
                lottoNumbersGenerator.generateNumbers().toSet()
            }
        return generateLotto(autoNumbers)
    }
}
