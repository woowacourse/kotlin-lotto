package domain.service

import domain.model.Lotto
import domain.model.Lotto.Companion.LOTTO_SIZE
import domain.model.LottoNumber
import domain.model.PurchasePrice
import domain.service.LottoGenerator.Companion.LOTTO_MAX
import domain.service.LottoGenerator.Companion.LOTTO_MIN
import domain.service.LottoGenerator.Companion.STANDARD_AMOUNT_UNIT
import domain.service.LottoGenerator.Companion.STANDARD_AMOUNT_UNIT_KOREAN
import util.ErrorConstants.ERROR

class LottoGeneratorImpl(
    val lottoPrice: Int = STANDARD_AMOUNT_UNIT,
    val lottoPriceKorean: String = STANDARD_AMOUNT_UNIT_KOREAN,
) : LottoGenerator {
    override fun generate(money: PurchasePrice): List<Lotto> {
        validateMoney(money.value)
        val purchaseLottoCount = money.value / lottoPrice
        return List<Lotto>(purchaseLottoCount) { makeOneLotto() }
    }

    private fun makeOneLotto(): Lotto {
        val newLotto = (LOTTO_MIN..LOTTO_MAX).shuffled().take(LOTTO_SIZE).map { LottoNumber(it) }
        return Lotto(newLotto)
    }

    private fun validateMoney(money: Int) {
        require(money > 0) { "$ERROR $lottoPriceKorean 이상 입력해주세요." }
        require(money % lottoPrice == 0) { "$ERROR $lottoPriceKorean 단위로 입력해주세요." }
    }
}
