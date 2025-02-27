package lotto

import lotto.controller.Validator
import lotto.domain.LottoAmount
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ValidatorTest {
    @ParameterizedTest
    @ValueSource(ints = [11, 12, 19])
    fun `수동으로 구매할 로또의 수는 구입할 로또의 수보다 클 수 없습니다`(manualAmount: Int) {
        val amount = 10
        assertThrows<IllegalArgumentException> { Validator.manualAmountValidator(LottoAmount(manualAmount), LottoAmount(amount)) }
    }
}
