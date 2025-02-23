package domain.service

import domain.model.Lotto
import domain.model.Lotto.Companion.LOTTO_MAX
import domain.model.Lotto.Companion.LOTTO_MIN
import domain.model.Lotto.Companion.LOTTO_SIZE
import domain.model.LottoNumber
import domain.model.PurchasePrice
import domain.service.LottoGenerator.Companion.STANDARD_AMOUNT_UNIT
import util.ErrorConstants.ERROR

class LottoGeneratorImpl(
    val standardPrice: Int = STANDARD_AMOUNT_UNIT,
) : LottoGenerator {
    override fun generate(money: PurchasePrice): List<Lotto> {
        validateMoney(money.value)
        val purchaseLottoCount = money.value / standardPrice
        return List<Lotto>(purchaseLottoCount) { makeOneLotto() }
    }

    private fun makeOneLotto(): Lotto {
        val newLotto = (LOTTO_MIN..LOTTO_MAX).shuffled().take(LOTTO_SIZE).map { LottoNumber(it) }
        return Lotto(newLotto)
    }

    private fun validateMoney(money: Int) {
        require(money > 0 && money >= standardPrice) { "$ERROR ${standardPrice}원 이상 입력해주세요." }
        require(money % standardPrice == 0) { "$ERROR ${standardPrice}원 단위로 입력해주세요." }
    }
}
