package lotto.domain

import lotto.service.AutoLottoGenerator

class LottoSeller(
    val price: Int,
    val manualLottoAmount: Int,
    val manualLottos: List<Lotto>,
) {
    fun getLottos(): List<Lotto> {
        val totalAmount = getLottoAmount(price)
        val autoLottoAMount = totalAmount - manualLottoAmount

        return getAutoLottos(autoLottoAMount) + manualLottos
    }

    fun getAutoLottoAmount(): Int {
        return LottoCalculator(price).calculateAutoLottos(manualLottoAmount)
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

    //    fun getManualLottos(amount: Int): List<Lotto> {
//        val manualLottos = mutableListOf<Lotto>()
//
//        repeat(amount) {
//            val numbers = inputView.inputManualLottoNumber()
//            val lotto = Lotto(numbers.map { number -> LottoNumber(number) })
//            manualLottos.add(lotto)
//        }
//        return manualLottos
//    }
    private fun getManualLottos(manualLottoNumbers: List<List<Int>>): List<Lotto> {
        val lottoFactory = LottoFactory()
        return manualLottoNumbers.map { numbers -> lottoFactory.generateManualLotto(numbers) }
    }
}
