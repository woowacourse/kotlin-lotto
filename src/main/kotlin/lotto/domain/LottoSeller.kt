package lotto.domain

import lotto.service.AutoLottoGenerator

class LottoSeller {
    fun getLottos(
        price: Int,
        manualLottoAmount: Int,
        manualLottoNumbers: List<List<Int>>,
    ): List<Lotto> {
        val totalAmount = getLottoAmount(price)
        val autoLottoAMount = totalAmount - manualLottoAmount

        return getAutoLottos(autoLottoAMount) + getManualLottos(manualLottoNumbers)
    }

    // 돈을 받아서 수량을 계산하기
    private fun getLottoAmount(price: Int): Int {
        return LottoCalculator(price).calculateAmountOfLottos()
    }

    // 수량에 맞게 로또 발행해주기
    private fun getAutoLottos(amount: Int): List<Lotto> {
        val autoLottoGenerator = AutoLottoGenerator()
        val lottoFactory = LottoFactory()
        return List(amount) { lottoFactory.generateAutoLotto(autoLottoGenerator) }
    }

    private fun getManualLottos(manualLottoNumbers: List<List<Int>>): List<Lotto> {
        val lottoFactory = LottoFactory()
        return manualLottoNumbers.map { numbers -> lottoFactory.generateManualLotto(numbers) }
    }
}
