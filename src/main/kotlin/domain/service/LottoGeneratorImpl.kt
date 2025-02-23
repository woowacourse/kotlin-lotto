package domain.service

import domain.model.Lotto
import domain.model.Lotto.Companion.LOTTO_SIZE
import domain.model.LottoNumber
import domain.model.LottoNumber.Companion.LOTTO_MAX
import domain.model.LottoNumber.Companion.LOTTO_MIN
import domain.model.PurchasePrice
import domain.service.LottoGenerator.Companion.STANDARD_AMOUNT_UNIT
import util.ErrorConstants.ERROR

class LottoGeneratorImpl(
    val standardPrice: Int = STANDARD_AMOUNT_UNIT,
) : LottoGenerator {
    override fun generate(quickPickLottoAmout: Int): List<Lotto> {
        return List<Lotto>(quickPickLottoAmout) { makeOneLotto() }
    }

    override fun validateMoney(price: PurchasePrice) {
        val money = price.value
        require(money > 0 && money >= standardPrice) { "$ERROR ${standardPrice}원 이상 입력해주세요." }
        require(money % standardPrice == 0) { "$ERROR ${standardPrice}원 단위로 입력해주세요." }
    }

    private fun makeOneLotto(): Lotto {
        val newLotto = (LOTTO_MIN..LOTTO_MAX).shuffled().take(LOTTO_SIZE).map { LottoNumber(it) }
        return Lotto(newLotto)
    }
}
