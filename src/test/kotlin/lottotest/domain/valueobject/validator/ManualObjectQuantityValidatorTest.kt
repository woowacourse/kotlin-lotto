package lottotest.domain.valueobject.validator

import lotto.domain.valueobject.LottoPaymentMoney
import lotto.domain.valueobject.LottoQuantity
import lotto.domain.valueobject.validator.ManualLottoQuantityValidator
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ManualObjectQuantityValidatorTest {
    @ParameterizedTest
    @CsvSource(
        "0, 0",
        "1000, 0",
        "1000, 1",
        "123_000, 0",
        "123_000, 123",
    )
    fun `수동 구매수량은 로또 구입 금액으로 구매가 가능한 한도 이내이다`(
        money: Int,
        manualLottoQuantityValue: Int,
    ) {
        // given
        val lottoPaymentMoney = LottoPaymentMoney(money)
        val manualLottoQuantityValidator = ManualLottoQuantityValidator()
        val manualLottoQuantity = LottoQuantity(manualLottoQuantityValue)

        // when than
        assertDoesNotThrow {
            manualLottoQuantityValidator.validate(lottoPaymentMoney, manualLottoQuantity)
        }
    }

    @ParameterizedTest
    @CsvSource(
        "0, 1",
        "1000, 2",
        "123_000, 124",
    )
    fun `수동 구매수량은 로또 구입 금액으로 구매가 가능한 한도를 넘어설 수 없다`(
        money: Int,
        manualLottoQuantityValue: Int,
    ) {
        // given
        val lottoPaymentMoney = LottoPaymentMoney(money)
        val manualLottoQuantityValidator = ManualLottoQuantityValidator()
        val manualLottoQuantity = LottoQuantity(manualLottoQuantityValue)

        // when than
        assertThrows<IllegalArgumentException> {
            manualLottoQuantityValidator.validate(lottoPaymentMoney, manualLottoQuantity)
        }
    }
}
