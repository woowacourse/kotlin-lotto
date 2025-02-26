package lotto

import lotto.controller.Validator
import lotto.domain.LottoAmount
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ValidatorTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `수동 로또의 개수는 0개 이상이어야 한다`(manualAmount: Int) {
        assertDoesNotThrow { LottoAmount(manualAmount) }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -9])
    fun `수동으로 구매할 로또의 수는 정수로 입력받아야 한다`(manualAmount: Int) {
        assertThrows<IllegalArgumentException> { LottoAmount(manualAmount) }
    }

    @ParameterizedTest
    @ValueSource(ints = [11, 12, 19])
    fun `수동으로 구매할 로또의 수는 구입할 로또의 수보다 클 수 없습니다`(manualAmount: Int) {
        val amount = 10
        assertThrows<IllegalArgumentException> { Validator.manualAmountValidator(LottoAmount(manualAmount), LottoAmount(amount)) }
    }
}
